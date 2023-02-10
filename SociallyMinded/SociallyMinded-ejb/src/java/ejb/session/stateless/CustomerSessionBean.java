/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import enumeration.AccountStatus;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ongyongen
 */
@Stateless
public class CustomerSessionBean implements CustomerSessionBeanRemote, CustomerSessionBeanLocal {

    @PersistenceContext(unitName = "SociallyMinded-ejbPU")
    private EntityManager em;

    
    
    @Override
    public Long createNewCustomer(Customer customer) {
        em.persist(customer);
        em.flush();
        return customer.getCustomerId();
    }
    
    @Override
    public List<Customer> retrieveAllCustomers() {
        Query query = em.createQuery("SELECT c FROM Customer c");
        return query.getResultList();
    }
    
    @Override
    public Customer retrieveCustomerById(Long customerId) {
        Customer customer = em.find(Customer.class, customerId);
        return customer;
    }
    
    @Override
    public Customer retrieveCustomerByUsernameAndPassword(String username, String password) {
        Query query = em.createQuery("SELECT c FROM Customer c "
                + "WHERE c.username = :username AND"
                + "c.password = :password"
        );
        query.setParameter("username", username);
        query.setParameter("password", password);
        return (Customer) query.getSingleResult();
    }
    
    
    public void updateCustomerProfile(Customer newCustomer) {
        Customer customerToUpdate = this.retrieveCustomerById(newCustomer.getCustomerId());
        customerToUpdate.setUsername(newCustomer.getUsername());
        customerToUpdate.setPassword(newCustomer.getPassword());
        customerToUpdate.setAddress(newCustomer.getAddress());
        customerToUpdate.setEmail(newCustomer.getEmail());
        customerToUpdate.setFirstName(newCustomer.getFirstName());
        customerToUpdate.setLastName(newCustomer.getLastName());
        customerToUpdate.setDateJoined(newCustomer.getDateJoined());
        customerToUpdate.setCreditCardNos(newCustomer.getCreditCardNos());
        customerToUpdate.setCreditCardCVV(newCustomer.getCreditCardCVV());
        customerToUpdate.setCreditCardExpiryDate(newCustomer.getCreditCardExpiryDate());
    }
    
    public void updateCustomerPassword(Long customerId, String password) {
        Customer customerToUpdate = this.retrieveCustomerById(customerId);
        customerToUpdate.setPassword(password);
    }
    
    public void deactivateCustomerAccount(Long customerId) {
        Customer customerToDeactivate = this.retrieveCustomerById(customerId);
        customerToDeactivate.setAccountStatus(AccountStatus.INACTIVE);
    }
}
