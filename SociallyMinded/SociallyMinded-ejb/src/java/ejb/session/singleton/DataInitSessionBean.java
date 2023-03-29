/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package ejb.session.singleton;

import ejb.session.stateless.CustomerSessionBeanLocal;
import entity.Customer;
import exception.InputDataValidationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ongyongen
 */
@Singleton
@LocalBean
@Startup
public class DataInitSessionBean {

    @EJB
    private CustomerSessionBeanLocal customerSessionBeanLocal;

    @PersistenceContext(unitName = "SociallyMinded-ejbPU")
    private EntityManager em;
    
    @PostConstruct
    public void postConstruct() {
        if (em.find(Customer.class, 1l) == null) {
            try {
                Customer A = new Customer("A");
                Customer B = new Customer("B");
                Customer C = new Customer("C");
                Customer D = new Customer("D");
                customerSessionBeanLocal.createNewCustomer(A);
                customerSessionBeanLocal.createNewCustomer(B);
                customerSessionBeanLocal.createNewCustomer(C);
                customerSessionBeanLocal.createNewCustomer(D);
            } catch (Exception ex) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    

    
}
