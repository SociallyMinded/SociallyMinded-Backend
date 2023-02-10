/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ongyongen
 */
@Local
public interface CustomerSessionBeanLocal {

    public List<Customer> retrieveAllCustomers();

    public Long createNewCustomer(Customer customer);

    public Customer retrieveCustomerById(Long customerId);

    public Customer retrieveCustomerByUsernameAndPassword(String username, String password);    

    public void updateCustomerProfile(Customer newCustomer);

    public void updateCustomerPassword(Long customerId, String password);

    public void deactivateCustomerAccount(Long customerId);
}
