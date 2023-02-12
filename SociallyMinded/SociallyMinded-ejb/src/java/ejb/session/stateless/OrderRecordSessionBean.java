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
import exception.OrderRecordNotFoundException;
import exception.ProductNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    
    @Override
    public Long createNewOrderRecord(OrderRecord order, Long productId, Long customerId) throws ProductNotFoundException, CustomerNotFoundException {
        if (productSessionBeanLocal.retrieveProductById(productId) == null) {
            throw new ProductNotFoundException();
        } else if (customerSessionBeanLocal.retrieveCustomerById(customerId) == null) {
            throw new CustomerNotFoundException();
        } else {
            Product product = productSessionBeanLocal.retrieveProductById(productId);
            Customer customer = customerSessionBeanLocal.retrieveCustomerById(customerId);
            product.getOrders().add(order);
            customer.getOrders().add(order);
            order.setProduct(product);
            order.setCustomer(customer);
            em.persist(order);
            em.flush();
            return order.getOrderRecordId();
        } 
    }

    @Override
    public List<OrderRecord> retrieveAllOrderRecords() {
        Query query = em.createQuery("SELECT ord FROM Order ord");
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
        Query query = em.createQuery("SELECT ord FROM Order ord "
                + "WHERE ord.product.productId = :productId");
        query.setParameter("productId", productId);
        return query.getResultList();
    }
    
    @Override
    public List<OrderRecord> retrieveOrderRecordsByCustomerId(Long customerId) {
        Query query = em.createQuery("SELECT ord FROM Order ord "
                + "WHERE ord.customer.customerId = :customerId");
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }
    
    //TODO : check if need to merge back to customer / product List
    @Override
    public void updateOrderRecord(Long oldOrderRecordId, BigDecimal quantity, 
            BigDecimal totalPrice, OrderStatus orderstatus) throws OrderRecordNotFoundException {
        if (this.retrieveOrderRecordById(oldOrderRecordId) == null) {
            throw new OrderRecordNotFoundException();
        } else {
            OrderRecord order = this.retrieveOrderRecordById(oldOrderRecordId);
            order.setQuantity(quantity);
            order.setTotalPrice(totalPrice);
            order.setOrderStatus(orderstatus);
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
