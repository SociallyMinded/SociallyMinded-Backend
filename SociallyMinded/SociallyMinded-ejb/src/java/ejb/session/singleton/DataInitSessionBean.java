/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package ejb.session.singleton;

import ejb.session.stateless.CustomerSessionBeanLocal;
import ejb.session.stateless.ProductSessionBeanLocal;
import ejb.session.stateless.SocialEnterpriseSessionBeanLocal;
import entity.Customer;
import entity.Product;
import entity.SocialEnterprise;
import exception.InputDataValidationException;
import java.math.BigDecimal;
import java.sql.Blob;
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
    private SocialEnterpriseSessionBeanLocal socialEnterpriseSessionBean;

    @PersistenceContext(unitName = "SociallyMinded-ejbPU")
    private EntityManager em;

    @EJB
    private CustomerSessionBeanLocal customerSessionBeanLocal;
    
    @EJB
    private ProductSessionBeanLocal productSessionBeanLocal;
    
  
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
                
                SocialEnterprise sA = new SocialEnterprise("A");
                
                socialEnterpriseSessionBean.createNewSocialEnterprise(sA);
                
                
                String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
                     
         
                        
                String imagelink = "e";
                
                Product pA = new Product("Crafts A", new BigDecimal(10), description, imagelink, "CRAFTS");
                Product pB = new Product("Crafts B", new BigDecimal(10), description, imagelink, "CRAFTS");
                Product pC = new Product("Clothing C", new BigDecimal(10), description, imagelink, "CLOTHING");
                Product pD = new Product("Clothing D", new BigDecimal(10), description, imagelink, "CLOTHING");
                Product pE = new Product("Clothing E", new BigDecimal(10), description, imagelink, "CLOTHING");
                Product pF = new Product("Clothing F", new BigDecimal(10), description, imagelink, "CLOTHING");
                Product pG = new Product("Clothing G", new BigDecimal(10), description, imagelink, "CLOTHING");
                Product pH = new Product("Clothing H", new BigDecimal(10), description, imagelink, "CLOTHING");
                Product pI = new Product("Crafts I", new BigDecimal(10), description, imagelink, "CRAFTS");
                Product pJ = new Product("Clothing J", new BigDecimal(10), description, imagelink, "CLOTHING");
                Product pK = new Product("Food K", new BigDecimal(10), description, imagelink, "FOOD");
                Product pL = new Product("Crafts L", new BigDecimal(10), description, imagelink, "CRAFTS");
                Product pM = new Product("Food M", new BigDecimal(10), description, imagelink, "FOOD");
                Product pN = new Product("Food N", new BigDecimal(10), description, imagelink, "FOOD");
                Product pO = new Product("Food O", new BigDecimal(10), description, imagelink, "FOOD");
                Product pP = new Product("Food P", new BigDecimal(10), description, imagelink, "FOOD");
                Product pQ = new Product("Others Q", new BigDecimal(10), description, imagelink, "OTHERS");
                Product pR = new Product("Crafts R", new BigDecimal(10), description, imagelink, "CRAFTS");
                Product pS = new Product("Others S", new BigDecimal(10), description, imagelink, "OTHERS");
                Product pT = new Product("Crafts T", new BigDecimal(10), description, imagelink, "CRAFTS");

                productSessionBeanLocal.createNewProduct(pA, 1l);
                productSessionBeanLocal.createNewProduct(pB, 1l);
                productSessionBeanLocal.createNewProduct(pC, 1l);
                productSessionBeanLocal.createNewProduct(pD, 1l);
                productSessionBeanLocal.createNewProduct(pE, 1l);
                productSessionBeanLocal.createNewProduct(pF, 1l);
                productSessionBeanLocal.createNewProduct(pG, 1l);
                productSessionBeanLocal.createNewProduct(pH, 1l);
                productSessionBeanLocal.createNewProduct(pI, 1l);
                productSessionBeanLocal.createNewProduct(pJ, 1l);
                productSessionBeanLocal.createNewProduct(pK, 1l);
                productSessionBeanLocal.createNewProduct(pL, 1l);
                productSessionBeanLocal.createNewProduct(pM, 1l);
                productSessionBeanLocal.createNewProduct(pN, 1l);
                productSessionBeanLocal.createNewProduct(pO, 1l);
                productSessionBeanLocal.createNewProduct(pP, 1l);
                productSessionBeanLocal.createNewProduct(pQ, 1l);
                productSessionBeanLocal.createNewProduct(pR, 1l);
                productSessionBeanLocal.createNewProduct(pS, 1l);
                productSessionBeanLocal.createNewProduct(pT, 1l);

            } catch (Exception ex) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    

    
}
