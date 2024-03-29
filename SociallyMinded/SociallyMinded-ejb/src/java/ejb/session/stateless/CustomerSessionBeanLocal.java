/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import enumeration.AccountStatus;
import exception.CustomerNotFoundException;
import exception.InputDataValidationException;
import java.text.ParseException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ongyongen
 */
@Local
public interface CustomerSessionBeanLocal {

    public List<Customer> retrieveAllCustomers();

    public Long createNewCustomer(Customer customer) throws InputDataValidationException, ParseException;

    public Customer retrieveCustomerById(Long customerId) throws CustomerNotFoundException;

    public void deactivateCustomerAccount(Long customerId) throws CustomerNotFoundException;

    public Customer retrieveCustomerByUsername(String username) throws CustomerNotFoundException;

    public void updateCustomerProfile(Customer newCustomer) throws InputDataValidationException;

    public Customer retrieveCustomerByFirebaseUid(String firebaseUid) throws CustomerNotFoundException;

    public void logInViaGmailAccount(Customer newCustomer) throws InputDataValidationException, CustomerNotFoundException;
}
