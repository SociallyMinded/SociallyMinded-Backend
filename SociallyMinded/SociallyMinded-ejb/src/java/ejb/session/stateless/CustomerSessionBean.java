/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
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
        return customer.getId();
    }
    
    @Override
    public List<Customer> retrieveAllCustomers() {
        Query query = em.createQuery("SELECT c FROM Customer c");
        return query.getResultList();
    }
    
}
