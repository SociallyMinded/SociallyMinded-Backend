/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import enumeration.AccountStatus;
import exception.CustomerNotFoundException;
import exception.InputDataValidationException;
import java.util.List;
import java.util.Set;
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
public class CustomerSessionBean implements CustomerSessionBeanRemote, CustomerSessionBeanLocal {

    @PersistenceContext(unitName = "SociallyMinded-ejbPU")
    private EntityManager em;
    
    private final ValidatorFactory validatorFactory;
    private final javax.validation.Validator validator;
    
    public CustomerSessionBean() {
        this.validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();
    }
    
    private String prepareInputDataValidationErrorMsg(Set<ConstraintViolation<Customer>> violations) {
        String msg = "";

        for (ConstraintViolation violation : violations) {
            msg += violation.getPropertyPath() + " - " + violation.getMessage() + ";";
        }

        return msg;
    }

    
    @Override
    public Long createNewCustomer(Customer customer) throws InputDataValidationException {
        Set<ConstraintViolation<Customer>> constraintViolations = validator.validate(customer);
        if (constraintViolations.isEmpty()) {
            em.persist(customer);
            em.flush();
            return customer.getCustomerId();
        } else {
            throw new InputDataValidationException(prepareInputDataValidationErrorMsg(constraintViolations));
        }
    }
    
    @Override
    public List<Customer> retrieveAllCustomers() {
        Query query = em.createQuery("SELECT c FROM Customer c");
        return query.getResultList();
    }
    
    @Override
    public Customer retrieveCustomerById(Long customerId) throws CustomerNotFoundException {
        if (em.find(Customer.class, customerId) == null) {
            throw new CustomerNotFoundException();
        } else {
            Customer customer = em.find(Customer.class, customerId);
            return customer;
        }
    }
    
    @Override
    public Customer retrieveCustomerByUsername(String username) throws CustomerNotFoundException {
        Query q = em.createQuery("SELECT c FROM Customer c WHERE c.username = :username");
        q.setParameter("username", username);
        if (q.getResultList().isEmpty()) {
            throw new CustomerNotFoundException();
        } else {
            return (Customer) q.getSingleResult();
        }
    }
    
    @Override
    public Customer retrieveCustomerByUsernameAndPassword(String username, String password) throws CustomerNotFoundException {
        Query query = em.createQuery("SELECT c FROM Customer c "
                + "WHERE c.username = :username AND "
                + "c.password = :password"
        );
        query.setParameter("username", username);
        query.setParameter("password", password);
        if (query.getResultList().isEmpty()) {
            throw new CustomerNotFoundException();
        } else {
            return (Customer) query.getSingleResult();           
        }
    }
      
    public void updateCustomerProfile(Customer newCustomer) throws InputDataValidationException {
        Set<ConstraintViolation<Customer>> constraintViolations = validator.validate(newCustomer);
        if (constraintViolations.isEmpty()) {
            em.merge(newCustomer);
        } else {
            throw new InputDataValidationException(prepareInputDataValidationErrorMsg(constraintViolations));
        }
    }

    // TODO : prevent deactivation if there's pending orders ; decide whether to 
    //        delete this entirely or deactivate?
    @Override
    public void deactivateCustomerAccount(Long customerId) throws CustomerNotFoundException {
        if (this.retrieveCustomerById(customerId) == null) {
            throw new CustomerNotFoundException();
        } else {
            Customer customerToDeactivate = this.retrieveCustomerById(customerId);
            customerToDeactivate.setAccountStatus(AccountStatus.INACTIVE);      
        }
    }
    
}
