/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Customer;
import entity.OrderRecord;
import entity.Product;
import entity.SocialEnterprise;
import enumeration.OrderStatus;
import exception.CustomerNotFoundException;
import exception.InputDataValidationException;
import exception.OrderRecordNotFoundException;
import exception.ProductNotFoundException;
import java.math.BigDecimal;
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
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author admin
 */
@Stateless
public class OrderRecordSessionBean implements OrderRecordSessionBeanRemote, OrderRecordSessionBeanLocal {

    @EJB
    private ProductSessionBeanLocal productSessionBeanLocal;

    @EJB
    private CustomerSessionBeanLocal customerSessionBeanLocal;
    

    @PersistenceContext(unitName = "SociallyMinded-ejbPU")
    private EntityManager em;
    
    private final ValidatorFactory validatorFactory;
    private final javax.validation.Validator validator;
    
    public OrderRecordSessionBean() {
        this.validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();
    }
    
    private String prepareInputDataValidationErrorMsg(Set<ConstraintViolation<OrderRecord>> violations) {
        String msg = "";

        for (ConstraintViolation violation : violations) {
            msg += violation.getPropertyPath() + " - " + violation.getMessage() + ";";
        }

        return msg;
    }

    
    @Override
    public Long createNewOrderRecord(OrderRecord order, Long productId, String customerFirebaseUid) throws ProductNotFoundException, CustomerNotFoundException, InputDataValidationException {
        System.out.println(customerFirebaseUid);
        if (productSessionBeanLocal.retrieveProductById(productId) == null) {
            throw new ProductNotFoundException();
        } else if (customerSessionBeanLocal.retrieveCustomerByFirebaseUid(customerFirebaseUid) == null) {
            throw new CustomerNotFoundException();
        } else {
            Set<ConstraintViolation<OrderRecord>> constraintViolations = validator.validate(order);
            if (constraintViolations.isEmpty()) {
                Product product = productSessionBeanLocal.retrieveProductById(productId);
                Customer customer = customerSessionBeanLocal.retrieveCustomerByFirebaseUid(customerFirebaseUid);
                product.getOrders().add(order);
                customer.getOrders().add(order);
                order.setProduct(product);
                order.setCustomer(customer);
                em.persist(order);
                em.flush();
                return order.getOrderRecordId();
            } else {
                throw new InputDataValidationException(prepareInputDataValidationErrorMsg(constraintViolations));
            }
        } 
    }

    @Override
    public List<OrderRecord> retrieveAllOrderRecords() {
        Query query = em.createQuery("SELECT ord FROM OrderRecord ord");
        return query.getResultList();
    }
    
    
    @Override
    public List<OrderRecord> retrieveAllOrdersByEnterpriseId(Long socialEnterpriseId) {
        Query query = em.createQuery("SELECT o FROM OrderRecord o "
                + "WHERE o.product.socialenterprise.socialEnterpriseId = :socialEnterpriseId");
        query.setParameter("socialEnterpriseId", socialEnterpriseId);
        return query.getResultList();       
    }
    
    @Override
    public OrderRecord retrieveOrderRecordById(Long orderRecordId) throws OrderRecordNotFoundException {
        if (em.find(OrderRecord.class, orderRecordId) == null) {
            throw new OrderRecordNotFoundException();
        } else {
            OrderRecord order = em.find(OrderRecord.class, orderRecordId);
            return order;
        }
    }
    
    @Override
    public List<OrderRecord> retrieveOrderRecordsByProductId(Long productId) {
        Query query = em.createQuery("SELECT ord FROM OrderRecord ord "
                + "WHERE ord.product.productId = :productId");
        query.setParameter("productId", productId);
        return query.getResultList();
    }
    
    @Override
    public List<OrderRecord> retrieveOrderRecordsByCustomerId(Long customerId) {
        Query query = em.createQuery("SELECT ord FROM OrderRecord ord "
                + "WHERE ord.customer.customerId = :customerId");
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }
    
    @Override
    public List<OrderRecord> retrieveOrderRecordsByCustomerFirebaseUid(String firebaseUid) {
        Query query = em.createQuery("SELECT ord FROM OrderRecord ord "
                + "WHERE ord.customer.firebaseUid = :firebaseUid");
        query.setParameter("firebaseUid", firebaseUid);
        return query.getResultList();
    }
    
    //TODO : check if need to merge back to customer / product List
    @Override
    public void updateOrderRecordDetails(OrderRecord newOrderRecord, Long productId, String customerFirebaseUid) throws ProductNotFoundException, CustomerNotFoundException, InputDataValidationException {
        if (productSessionBeanLocal.retrieveProductById(productId) == null) {
            throw new ProductNotFoundException();
        } else if (customerSessionBeanLocal.retrieveCustomerByFirebaseUid(customerFirebaseUid) == null) {
            throw new CustomerNotFoundException();
        } else {
            Set<ConstraintViolation<OrderRecord>> constraintViolations = validator.validate(newOrderRecord);
            if (constraintViolations.isEmpty()) {
                Product product = productSessionBeanLocal.retrieveProductById(productId);
                Customer customer = customerSessionBeanLocal.retrieveCustomerByFirebaseUid(customerFirebaseUid);
                newOrderRecord.setProduct(product);
                newOrderRecord.setCustomer(customer);
                em.merge(newOrderRecord);
            } else {
                throw new InputDataValidationException(prepareInputDataValidationErrorMsg(constraintViolations));
            }
        }
    }
    
    // TODO : check if logic is correct
    @Override
    public void deleteOrderRecord(Long oldOrderRecordId) throws OrderRecordNotFoundException {
        if (this.retrieveOrderRecordById(oldOrderRecordId) == null) {
            throw new OrderRecordNotFoundException();
        } else {
            OrderRecord order = this.retrieveOrderRecordById(oldOrderRecordId);
            order.getProduct().getOrders().remove(order);
            order.getCustomer().getOrders().remove(order);
            order.setProduct(null);
            order.setCustomer(null);
            em.remove(order); 
        }
    }
        
}
