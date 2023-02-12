/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import exception.CustomerNotFoundException;
import exception.InputDataValidationException;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author ongyongen
 */
@Remote
public interface CustomerSessionBeanRemote {
    
    public List<Customer> retrieveAllCustomers();

    public Long createNewCustomer(Customer customer) throws InputDataValidationException;
    
    public Customer retrieveCustomerById(Long customerId) throws CustomerNotFoundException;
}
