/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import entity.OrderRecord;
import entity.Product;
import entity.Review;
import entity.SocialEnterprise;
import enumeration.OrderStatus;
import exception.InputDataValidationException;
import exception.ProductNotFoundException;
import exception.SocialEnterpriseNotFoundException;
import exception.UncompletedOrdersException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.Order;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

/**
 *
 * @author ongyongen
 */
@Stateless
public class ProductSessionBean implements ProductSessionBeanRemote, ProductSessionBeanLocal {

    @EJB
    private SocialEnterpriseSessionBeanLocal socialEnterpriseSessionBeanLocal;
    

    @PersistenceContext(unitName = "SociallyMinded-ejbPU")
    private EntityManager em;
    
    private final ValidatorFactory validatorFactory;
    private final javax.validation.Validator validator;
    
    public ProductSessionBean() {
        this.validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();
    }
    
    private String prepareInputDataValidationErrorMsg(Set<ConstraintViolation<Product>> violations) {
        String msg = "";

        for (ConstraintViolation violation : violations) {
            msg += violation.getPropertyPath() + " - " + violation.getMessage() + ";";
        }

        return msg;
    }
    
    @Override
    public Long createNewProduct(Product product, Long enterpriseId) throws SocialEnterpriseNotFoundException, InputDataValidationException {
        if (socialEnterpriseSessionBeanLocal.retrieveSocialEnterpriseById(enterpriseId) == null) {
            throw new SocialEnterpriseNotFoundException();
        } else {
            Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);
            if (constraintViolations.isEmpty()) {
                SocialEnterprise enterprise = socialEnterpriseSessionBeanLocal.retrieveSocialEnterpriseById(enterpriseId);
                enterprise.getProducts().add(product);
                product.setSocialenterprise(enterprise);
                em.persist(product);
                em.flush();
                return product.getProductId();
            } else {
                throw new InputDataValidationException(prepareInputDataValidationErrorMsg(constraintViolations));
            }
        }
    }
    
    @Override
    public List<Product> retrieveAllProducts() {
        Query query = em.createQuery("SELECT p FROM Product p");
        return query.getResultList();
    }
    
    @Override
    public List<Product> retrieveAllProductsByEnterpriseId(Long socialEnterpriseId) {
        Query query = em.createQuery("SELECT p FROM Product p "
                + "WHERE p.socialenterprise.socialEnterpriseId = :socialEnterpriseId");
        query.setParameter("socialEnterpriseId", socialEnterpriseId);
        return query.getResultList();       
    }
    
    @Override
    public List<Product> retrieveAllProductsByEnterpriseName(String enterpriseName) {
        Query query = em.createQuery("SELECT p FROM Product p "
                + "WHERE p.socialenterprise.enterpriseName = :enterpriseName");
        query.setParameter("enterpriseName", enterpriseName);
        return query.getResultList();       
    }
    
    @Override
    public Product retrieveProductById(Long productId) throws ProductNotFoundException {
        if (em.find(Product.class, productId) == null) {
            throw new ProductNotFoundException();
        } else {
            Product product = em.find(Product.class, productId);
            return product;
        }
    }
    
    @Override
    public Product retrieveProductByName(String productName) throws ProductNotFoundException {
        Query query = em.createQuery("SELECT p FROM Product p "
                + "WHERE p.name = :productName"
        );
        query.setParameter("productName", productName);
        
        if (query.getResultList().isEmpty()) {
            throw new ProductNotFoundException();
        } else {
            return (Product) query.getSingleResult();
        }
    }

    
    @Override
    public void updateProductDetails(Product newProduct, Long enterpriseId) throws InputDataValidationException, SocialEnterpriseNotFoundException {
        if (socialEnterpriseSessionBeanLocal.retrieveSocialEnterpriseById(enterpriseId) == null) {
            throw new SocialEnterpriseNotFoundException();
        } else {
            Set<ConstraintViolation<Product>> constraintViolations = validator.validate(newProduct);
            if (constraintViolations.isEmpty()) {
                SocialEnterprise enterprise = socialEnterpriseSessionBeanLocal.retrieveSocialEnterpriseById(enterpriseId);
                newProduct.setSocialenterprise(enterprise);
                em.merge(newProduct);
            } else {
                throw new InputDataValidationException(prepareInputDataValidationErrorMsg(constraintViolations));
            }
        }
    }
    
    @Override
    public void deleteProduct(Long oldProductId) throws ProductNotFoundException, UncompletedOrdersException {
        if (this.retrieveProductById(oldProductId) == null) {
            throw new ProductNotFoundException();
        } else {
            Product product = this.retrieveProductById(oldProductId);
            
            boolean noOrdersLeft = true;
            for (OrderRecord order : product.getOrders()) {
                if (order.getOrderStatus() != OrderStatus.COMPLETED) {
                    noOrdersLeft = false;
                }
            }
            
            if (noOrdersLeft) {
                product.getSocialenterprise().getProducts().remove(product);
                for (Review review : product.getReviews()) {
                    em.remove(review);
                }
                for (OrderRecord order : product.getOrders()) {
                    em.remove(order);
                }

                product.setSocialenterprise(null);
                product.setReviews(null);
                product.setOrders(null);
                em.remove(product);
                
            } else {
                throw new UncompletedOrdersException();
            }   
        }    
    }
}
    
    
   
