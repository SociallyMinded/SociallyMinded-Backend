/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package ejb.session.singleton;

import ejb.session.stateless.CustomerSessionBeanLocal;
import ejb.session.stateless.ProductSessionBeanLocal;
import ejb.session.stateless.ReviewSessionBeanLocal;
import ejb.session.stateless.SocialEnterpriseSessionBeanLocal;
import entity.Customer;
import entity.Product;
import entity.Review;
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
    private ReviewSessionBeanLocal reviewSessionBeanLocal;

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
                
                String template1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit"
                        + " Maecenas vitae risus rhoncus, fringilla mi sed, fringilla mi. "
                        + "Sed in blandit metus, congue mattis velit. Aenean at lacus vitae "
                        + "nisi condimentum bibendum. Morbi pulvinar nunc nec interdum rhoncus. "
                        + "Curabitur consectetur sagittis laoreet. Maecenas luctus nec mi in mattis. "
                        + "Phasellus velit tortor, dapibus eu lacus id, ullamcorper placerat lorem. "
                        + "Vestibulum gravida ipsum sed urna vulputate volutpat. Suspendisse quis dictum ante. "
                        + "Suspendisse convallis, risus eu semper imperdiet, massa arcu pretium dui, ac pretium "
                        + "lacus justo sit amet mauris. ";
                Review rA1 = new Review("Test A");
                Review rA2 = new Review(template1);
                Review rA3 = new Review(template1);
                Review rA4 = new Review(template1);
                
                Review rB1 = new Review("Test B");
                Review rB2 = new Review(template1);
                Review rB3 = new Review(template1);
                Review rB4 = new Review(template1);
                
                Review rC1 = new Review("Test C");
                Review rC2 = new Review(template1);
                Review rC3 = new Review(template1);
                Review rC4 = new Review(template1);
                   
                Review rD1 = new Review("Test D");
                Review rD2 = new Review(template1);
                Review rD3 = new Review(template1);
                Review rD4 = new Review(template1);
                
                Review rE1 = new Review("Test E");
                Review rE2 = new Review(template1);
                Review rE3 = new Review(template1);
                Review rE4 = new Review(template1); 

                Review rF1 = new Review("Test F");
                Review rF2 = new Review(template1);
                Review rF3 = new Review(template1);
                Review rF4 = new Review(template1); 

                Review rG1 = new Review("Test G");
                Review rG2 = new Review(template1);
                Review rG3 = new Review(template1);
                Review rG4 = new Review(template1); 
                
                Review rH1 = new Review("Test H");
                Review rH2 = new Review(template1);
                Review rH3 = new Review(template1);
                Review rH4 = new Review(template1); 

                
                Review rI1 = new Review("Test I");
                Review rI2 = new Review(template1);
                Review rI3 = new Review(template1);
                Review rI4 = new Review(template1); 

                Review rJ1 = new Review("Test J");
                Review rJ2 = new Review(template1);
                Review rJ3 = new Review(template1);
                Review rJ4 = new Review(template1); 
                
                Review rK1 = new Review("Test K");
                Review rK2 = new Review(template1);
                Review rK3 = new Review(template1);
                Review rK4 = new Review(template1); 
                
                Review rL1 = new Review("Test L");
                Review rL2 = new Review(template1);
                Review rL3 = new Review(template1);
                Review rL4 = new Review(template1); 
                
                Review rM1 = new Review("Test M");
                Review rM2 = new Review(template1);
                Review rM3 = new Review(template1);
                Review rM4 = new Review(template1); 
                
                Review rN1 = new Review("Test N");
                Review rN2 = new Review(template1);
                Review rN3 = new Review(template1);
                Review rN4 = new Review(template1); 
                
                Review rO1 = new Review("Test O");
                Review rO2 = new Review(template1);
                Review rO3 = new Review(template1);
                Review rO4 = new Review(template1); 
                
                reviewSessionBeanLocal.createNewReview(rA1, 1l, 1l);
                reviewSessionBeanLocal.createNewReview(rA2, 1l, 1l);
                reviewSessionBeanLocal.createNewReview(rA3, 1l, 1l);
                reviewSessionBeanLocal.createNewReview(rA4, 1l, 1l);
                
                reviewSessionBeanLocal.createNewReview(rB1, 2l, 1l);
                reviewSessionBeanLocal.createNewReview(rB2, 2l, 1l);
                reviewSessionBeanLocal.createNewReview(rB3, 2l, 1l);
                reviewSessionBeanLocal.createNewReview(rB4, 2l, 1l);
                
                reviewSessionBeanLocal.createNewReview(rC1, 3l, 1l);
                reviewSessionBeanLocal.createNewReview(rC2, 3l, 1l);
                reviewSessionBeanLocal.createNewReview(rC3, 3l, 1l);
                reviewSessionBeanLocal.createNewReview(rC4, 3l, 1l);
                
                reviewSessionBeanLocal.createNewReview(rD1, 4l, 1l);
                reviewSessionBeanLocal.createNewReview(rD2, 4l, 1l);
                reviewSessionBeanLocal.createNewReview(rD3, 4l, 1l);
                reviewSessionBeanLocal.createNewReview(rD4, 4l, 1l);
                
                reviewSessionBeanLocal.createNewReview(rE1, 5l, 1l);
                reviewSessionBeanLocal.createNewReview(rE2, 5l, 1l);
                reviewSessionBeanLocal.createNewReview(rE3, 5l, 1l);
                reviewSessionBeanLocal.createNewReview(rE4, 5l, 1l);
     
                reviewSessionBeanLocal.createNewReview(rF1, 6l, 1l);
                reviewSessionBeanLocal.createNewReview(rF2, 6l, 1l);
                reviewSessionBeanLocal.createNewReview(rF3, 6l, 1l);
                reviewSessionBeanLocal.createNewReview(rF4, 6l, 1l);
                
                     
                reviewSessionBeanLocal.createNewReview(rG1, 7l, 1l);
                reviewSessionBeanLocal.createNewReview(rG2, 7l, 1l);
                reviewSessionBeanLocal.createNewReview(rG3, 7l, 1l);
                reviewSessionBeanLocal.createNewReview(rG4, 7l, 1l);
                
                reviewSessionBeanLocal.createNewReview(rH1, 8l, 1l);
                reviewSessionBeanLocal.createNewReview(rH2, 8l, 1l);
                reviewSessionBeanLocal.createNewReview(rH3, 8l, 1l);
                reviewSessionBeanLocal.createNewReview(rH4, 8l, 1l);
                
                reviewSessionBeanLocal.createNewReview(rI1, 9l, 1l);
                reviewSessionBeanLocal.createNewReview(rI2, 9l, 1l);
                reviewSessionBeanLocal.createNewReview(rI3, 9l, 1l);
                reviewSessionBeanLocal.createNewReview(rI4, 9l, 1l);
                
                reviewSessionBeanLocal.createNewReview(rJ1, 10l, 1l);
                reviewSessionBeanLocal.createNewReview(rJ2, 10l, 1l);
                reviewSessionBeanLocal.createNewReview(rJ3, 10l, 1l);
                reviewSessionBeanLocal.createNewReview(rJ4, 10l, 1l);
                
                reviewSessionBeanLocal.createNewReview(rK1, 11l, 1l);
                reviewSessionBeanLocal.createNewReview(rK2, 11l, 1l);
                reviewSessionBeanLocal.createNewReview(rK3, 11l, 1l);
                reviewSessionBeanLocal.createNewReview(rK4, 11l, 1l);
              
                reviewSessionBeanLocal.createNewReview(rL1, 12l, 1l);
                reviewSessionBeanLocal.createNewReview(rL2, 12l, 1l);
                reviewSessionBeanLocal.createNewReview(rL3, 12l, 1l);
                reviewSessionBeanLocal.createNewReview(rL4, 12l, 1l);
                
                reviewSessionBeanLocal.createNewReview(rM1, 13l, 1l);
                reviewSessionBeanLocal.createNewReview(rM2, 13l, 1l);
                reviewSessionBeanLocal.createNewReview(rM3, 13l, 1l);
                reviewSessionBeanLocal.createNewReview(rM4, 13l, 1l);
                
                reviewSessionBeanLocal.createNewReview(rN1, 14l, 1l);
                reviewSessionBeanLocal.createNewReview(rN2, 14l, 1l);
                reviewSessionBeanLocal.createNewReview(rN3, 14l, 1l);
                reviewSessionBeanLocal.createNewReview(rN4, 14l, 1l);
                
                reviewSessionBeanLocal.createNewReview(rO1, 15l, 1l);
                reviewSessionBeanLocal.createNewReview(rO2, 15l, 1l);
                reviewSessionBeanLocal.createNewReview(rO3, 15l, 1l);
                reviewSessionBeanLocal.createNewReview(rO4, 15l, 1l);

            } catch (Exception ex) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    

    
}
