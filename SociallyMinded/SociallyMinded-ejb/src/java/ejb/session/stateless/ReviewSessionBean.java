/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Customer;
import entity.Product;
import entity.Review;
import entity.SocialEnterprise;
import exception.CustomerNotFoundException;
import exception.InputDataValidationException;
import exception.ProductNotFoundException;
import exception.ReviewNotFoundException;
import exception.SocialEnterpriseNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

/**
 *
 * @author ongyongen
 */
@Stateless
public class ReviewSessionBean implements ReviewSessionBeanRemote, ReviewSessionBeanLocal {

    @EJB
    private ProductSessionBeanLocal productSessionBeanLocal;

    @EJB
    private CustomerSessionBeanLocal customerSessionBeanLocal;
    
    
    @PersistenceContext(unitName = "SociallyMinded-ejbPU")
    private EntityManager em;
    
    private final ValidatorFactory validatorFactory;
    private final javax.validation.Validator validator;
    
    public ReviewSessionBean() {
        this.validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();
    }
    
    private String prepareInputDataValidationErrorMsg(Set<ConstraintViolation<Review>> violations) {
        String msg = "";

        for (ConstraintViolation violation : violations) {
            msg += violation.getPropertyPath() + " - " + violation.getMessage() + ";";
        }

        return msg;
    }
    
    
    @Override
    public Long createNewReview(Review review, Long productId, Long customerId) 
            throws ProductNotFoundException, CustomerNotFoundException, InputDataValidationException {
        if (productSessionBeanLocal.retrieveProductById(productId) == null) {
            throw new ProductNotFoundException();
        } else if (customerSessionBeanLocal.retrieveCustomerById(customerId) == null) {
            throw new CustomerNotFoundException();
        } else {
            Set<ConstraintViolation<Review>> constraintViolations = validator.validate(review);
            if (constraintViolations.isEmpty()) {
                Product product = productSessionBeanLocal.retrieveProductById(productId);
                Customer customer = customerSessionBeanLocal.retrieveCustomerById(customerId);
                product.getReviews().add(review);
                customer.getReviews().add(review);
                review.setProduct(product);
                review.setCustomer(customer);
                em.persist(review);
                em.flush();
                return review.getReviewId();
            } else {
                throw new InputDataValidationException();
            }
        }
    }
    
    @Override
    public Review retrieveReviewById(Long reviewId) throws ReviewNotFoundException {
        if (em.find(Review.class, reviewId) == null) {
            throw new ReviewNotFoundException();
        } else {
            Review review = em.find(Review.class, reviewId);
            return review;
        }
    }
    
    @Override
    public List<Review> retrieveAllReviews() {
        Query query = em.createQuery("SELECT r FROM Review r");
        return query.getResultList();
    }
    
    @Override
    public List<Review> retrieveReviewsByCustomerId(Long customerId) {
        Query query = em.createQuery("SELECT r FROM Review r "
                + "WHERE r.customer.customerId = :customerId");
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }
    
    @Override
    public List<Review> retrieveReviewsByProductId(Long productId) {
        Query query = em.createQuery("SELECT r FROM Review r "
                + "WHERE r.product.productId = :productId");
        query.setParameter("productId", productId);
        return query.getResultList();
    }
    
    public void updateReviewDetails(Review newReview, Long productId, Long customerId) throws InputDataValidationException, ProductNotFoundException, CustomerNotFoundException {
        if (productSessionBeanLocal.retrieveProductById(productId) == null) {
            throw new ProductNotFoundException();
        } else if (customerSessionBeanLocal.retrieveCustomerById(customerId) == null) {
            throw new CustomerNotFoundException();
        } else {
            Set<ConstraintViolation<Review>> constraintViolations = validator.validate(newReview);
            if (constraintViolations.isEmpty()) {
                Product product = productSessionBeanLocal.retrieveProductById(productId);
                Customer customer = customerSessionBeanLocal.retrieveCustomerById(customerId);
                newReview.setProduct(product);
                newReview.setCustomer(customer);
                em.merge(newReview);
            } else {
                throw new InputDataValidationException(prepareInputDataValidationErrorMsg(constraintViolations));
            }
        }
    }
    
    public void deleteReview(Long reviewId) throws ReviewNotFoundException {
        if (this.retrieveReviewById(reviewId) == null) {
            throw new ReviewNotFoundException();
        } else {
            Review review = this.retrieveReviewById(reviewId);
            review.getProduct().getReviews().remove(review);
            review.getCustomer().getReviews().remove(review);
            review.setProduct(null);
            review.setCustomer(null);
            em.remove(review);
        }
    }
}
