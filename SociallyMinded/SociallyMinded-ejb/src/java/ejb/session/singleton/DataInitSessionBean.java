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
                Customer A = new Customer("Abby", "abc1");
                Customer B = new Customer("Ben", "abc2");
                Customer C = new Customer("Casey", "abc3");
                Customer D = new Customer("Dylan", "abc4");
                customerSessionBeanLocal.createNewCustomer(A);
                customerSessionBeanLocal.createNewCustomer(B);
                customerSessionBeanLocal.createNewCustomer(C);
                customerSessionBeanLocal.createNewCustomer(D);
                
                SocialEnterprise sA = new SocialEnterprise("LittleMatchGirl");
                
                socialEnterpriseSessionBean.createNewSocialEnterprise(sA);
                
                
                //String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
                     
         
                        
                String imagelink = "e";
                
                Product pA = new Product("Amber Jersey Pleated Dress", new BigDecimal(169), "Light TENCEL™ fabric : skin-friendly, soft texture, high breathability, made from sustainable wood sources.", imagelink, "CLOTHING");
                Product pB = new Product("Raffles Asymmetrical Wide Leg Pants", new BigDecimal(159), "This mid waist and wide legs pants was designed to suit any occasion." , imagelink, "CLOTHING");
                Product pC = new Product("Bugis Stitch Maxi Dress", new BigDecimal(189), "This dress that comes in 2 colours was designed to suit any occasion.", imagelink, "CLOTHING");
                Product pD = new Product("Raffles X-line Midi Dress", new BigDecimal(199), "Soft woven fabric made of TENCEL™ lyocell with decorative seams", imagelink, "CLOTHING");
                Product pE = new Product("Raffles Blend Dress", new BigDecimal(179), "Jersey made of TENCEL™ modal and and skirt belnd with Woven TENCEL, designed with a V-neckline" , imagelink, "CLOTHING");
                Product pF = new Product("U-Shaped Back Draped Dress", new BigDecimal(159), "Made from Tencel - known as environment-friendly fabrics. Designed with a bowtie and a side open slit.", imagelink, "CLOTHING");
                Product pG = new Product("Amber Belted Maxi Dress", new BigDecimal(199), "A timeless piece, this dress will last a lifetime.  Featuring front panel details and a removable waist tie.", imagelink, "CLOTHING");
                Product pH = new Product("Handmade Natural Soap (Tamarind/ Rice)", new BigDecimal(10), "Our NO. 1 BESTSELLER hand-crafted soap is made using high-quality tamarind and rice.", imagelink, "CRAFTS"); //Olive Tree
                Product pI = new Product("Assorted Crocheted Angels", new BigDecimal(25), "Bring home these large decorative assorted crocheted angels crafted by our volunteer Aunty Nancy!", imagelink, "CRAFTS"); //Olive Tree
                Product pJ = new Product("Elastic Collar Shirt", new BigDecimal(139), "TENCEL is a natural fibre which is degradable.  Special properties include: enhanced breathability &  gentle on the skin.", imagelink, "CLOTHING");
                Product pK = new Product("Sunshine Chips", new BigDecimal(5), "We took the spectrum of sunlight, separated it and vacuum fried them into chips. No artificial colourings or preservatives!", imagelink, "FOOD"); //boxgreen
                Product pL = new Product("Grounded Sea Salt Peanut Butter Jar", new BigDecimal(10), "Roasted peanuts grounded twice for extra smoothness with heart-healthy coconut oil and a dash of salt to bring the flavours home. ", imagelink, "CRAFTS"); //boxgreen
                Product pM = new Product("Thai Mountain Espresso", new BigDecimal(75), "A world class single origin coffee, backed by multiple awards, our Organic Espresso is the perfect espresso shot you’ve been looking for.", imagelink, "FOOD"); //noharmdone
                Product pN = new Product("Thai Iced Tea", new BigDecimal(10), "Earthy black Ceylon tea leaves brewed to give you an authentic orange coloured tea that'll take your senses to the bustling streets of Bangkok.", imagelink, "FOOD");//noharmdone
                Product pO = new Product("Choco Kopi", new BigDecimal(8), "For Mondays, hangovers, make ups, breakups, late nights, early starts, rainy days… A hug in a mug. Our toasty Kopi-o with a malty chocolate infusion. ", imagelink, "FOOD"); //noharmdone
                Product pP = new Product("Gula Melaka Kopi", new BigDecimal(8), "We've blended our signature Kopi-o with caramelised coconut sugar, giving your favourite brew a decadent twist." , imagelink, "FOOD");
                Product pQ = new Product("Personalised Photo Dome", new BigDecimal(170), "Our Signature Preserved Flower Dome is meticulously handcrafted using long-lasting premium flowers. Make a lasting impression with this thoughtful gift, personalized with a memorable photo of your loved one.", imagelink, "OTHERS"); //bloomback
                Product pR = new Product("Handmade Natural Soap (Lemon Grass)", new BigDecimal(10), "Our NO. 1 BESTSELLER hand-crafted soap is made using high-quality lemongrass.", imagelink, "CRAFTS"); //olive tree
                Product pS = new Product("Personalised Birthstone Bag Charm", new BigDecimal(75), "This bag charm is crafted using long-lasting premium flowers and completed with a meaningful birthstone.", imagelink, "OTHERS");//bloomback
                Product pT = new Product("Handmade Natural Soap (Roselle/ Rice Milk)", new BigDecimal(10), "Our NO. 1 BESTSELLER hand-crafted soap is made using high-quality roselle and rice milk.", imagelink, "CRAFTS"); //olive tree

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
//                
//                String template1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit"
//                        + " Maecenas vitae risus rhoncus, fringilla mi sed, fringilla mi. "
//                        + "Sed in blandit metus, congue mattis velit. Aenean at lacus vitae "
//                        + "nisi condimentum bibendum. Morbi pulvinar nunc nec interdum rhoncus. "
//                        + "Curabitur consectetur sagittis laoreet. Maecenas luctus nec mi in mattis. "
//                        + "Phasellus velit tortor, dapibus eu lacus id, ullamcorper placerat lorem. "
//                        + "Vestibulum gravida ipsum sed urna vulputate volutpat. Suspendisse quis dictum ante. "
//                        + "Suspendisse convallis, risus eu semper imperdiet, massa arcu pretium dui, ac pretium "
//                        + "lacus justo sit amet mauris. ";
//                Review rA1 = new Review("Test A");
//                Review rA2 = new Review(template1);
//                Review rA3 = new Review(template1);
//                Review rA4 = new Review(template1);
//                
//                Review rB1 = new Review("Test B");
//                Review rB2 = new Review(template1);
//                Review rB3 = new Review(template1);
//                Review rB4 = new Review(template1);
//                
//                Review rC1 = new Review("Test C");
//                Review rC2 = new Review(template1);
//                Review rC3 = new Review(template1);
//                Review rC4 = new Review(template1);
//                   
//                Review rD1 = new Review("Test D");
//                Review rD2 = new Review(template1);
//                Review rD3 = new Review(template1);
//                Review rD4 = new Review(template1);
//                
//                Review rE1 = new Review("Test E");
//                Review rE2 = new Review(template1);
//                Review rE3 = new Review(template1);
//                Review rE4 = new Review(template1); 
//
//                Review rF1 = new Review("Test F");
//                Review rF2 = new Review(template1);
//                Review rF3 = new Review(template1);
//                Review rF4 = new Review(template1); 
//
//                Review rG1 = new Review("Test G");
//                Review rG2 = new Review(template1);
//                Review rG3 = new Review(template1);
//                Review rG4 = new Review(template1); 
//                
//                Review rH1 = new Review("Test H");
//                Review rH2 = new Review(template1);
//                Review rH3 = new Review(template1);
//                Review rH4 = new Review(template1); 
//
//                
//                Review rI1 = new Review("Test I");
//                Review rI2 = new Review(template1);
//                Review rI3 = new Review(template1);
//                Review rI4 = new Review(template1); 
//
//                Review rJ1 = new Review("Test J");
//                Review rJ2 = new Review(template1);
//                Review rJ3 = new Review(template1);
//                Review rJ4 = new Review(template1); 
//                
//                Review rK1 = new Review("Test K");
//                Review rK2 = new Review(template1);
//                Review rK3 = new Review(template1);
//                Review rK4 = new Review(template1); 
//                
//                Review rL1 = new Review("Test L");
//                Review rL2 = new Review(template1);
//                Review rL3 = new Review(template1);
//                Review rL4 = new Review(template1); 
//                
//                Review rM1 = new Review("Test M");
//                Review rM2 = new Review(template1);
//                Review rM3 = new Review(template1);
//                Review rM4 = new Review(template1); 
//                
//                Review rN1 = new Review("Test N");
//                Review rN2 = new Review(template1);
//                Review rN3 = new Review(template1);
//                Review rN4 = new Review(template1); 
//                
//                Review rO1 = new Review("Test O");
//                Review rO2 = new Review(template1);
//                Review rO3 = new Review(template1);
//                Review rO4 = new Review(template1); 
                

                /* String template1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit"
                        + " Maecenas vitae risus rhoncus, fringilla mi sed, fringilla mi. "
                        + "Sed in blandit metus, congue mattis velit. Aenean at lacus vitae "
                        + "nisi condimentum bibendum. Morbi pulvinar nunc nec interdum rhoncus. "
                        + "Curabitur consectetur sagittis laoreet. Maecenas luctus nec mi in mattis. "
                        + "Phasellus velit tortor, dapibus eu lacus id, ullamcorper placerat lorem. "
                        + "Vestibulum gravida ipsum sed urna vulputate volutpat. Suspendisse quis dictum ante. "
                        + "Suspendisse convallis, risus eu semper imperdiet, massa arcu pretium dui, ac pretium "
                        + "lacus justo sit amet mauris. "; */
                
                Review rA1 = new Review("Very pretty dress, I wore it to a friend's birthday party and received compliments from all of my friends.", 5, false);
                Review rA2 = new Review("The material of the dress is very good.  This dress is on the pricier side but this is truly worth every dollar spent!", 5, false);
                Review rA3 = new Review("The sizing is not accurate as this dress runs small.  It is recommended that you upsize if you are in between sizes.", 3, false);
                Review rA4 = new Review("The colour of the dress is very unique and it has become my favourite piece in my closet!",4, false);
                
                Review rB1 = new Review("Test B",5, false);
                Review rB2 = new Review("These pants are so flare that it looks like a skirt from far.  A very versatile piece to style :)",5, false);
                Review rB3 = new Review("I like these pants so much that I bought 2 of it in both colours, black and blue.",4, false);
                Review rB4 = new Review("This is my favourite pants at the moment :)",4, false);
                
                Review rC1 = new Review("Test C",4, false);
                Review rC2 = new Review("The turquoise colour of the dress is very unique. It is hard to find dresses in this colour so I really like this piece",5, false);
                Review rC3 = new Review("The material of the dress is very good.  This dress is on the pricier side but this is truly worth every dollar spent!",5, false);
                Review rC4 = new Review("The sizing of this dress runs big. It is recommended that you size down if you are in between sizes",5, false);
                   
                Review rD1 = new Review("Test D", 5, false);
                Review rD2 = new Review("This is my first time purchasing clothing of this material and it has become my favourite kind due to its high quality!", 5, false);
                Review rD3 = new Review("Very pretty dress, I wore it to a friend's birthday party and received compliments from all of my friends.", 5, false);
                Review rD4 = new Review("This dress is very long and not suitable for girls who are petite.  I am 154cm and this dress is way too long for me :(", 5, false);
                
                Review rE1 = new Review("Test E", 5, false);
                Review rE2 = new Review("The sizing is not accurate as this dress runs small.  It is recommended that you upsize if you are in between sizes.", 5, false);
                Review rE3 = new Review("I love the flowy cut of the dress, it is very easy to move in and is definitely very comfortable to wear!", 5, false);
                Review rE4 = new Review("Love the material of the dress!  It is resistant to high temperatures especially when ironing.", 5, false); 

                Review rF1 = new Review("Test F", 5, false);
                Review rF2 = new Review("Long the high neckline for the dress.  Modest yet stylish!", 5, false);
                Review rF3 = new Review("Dress is made of premium quality, purchase is very worth it!", 5, false);
                Review rF4 = new Review("Dress is too expensive.  Bought the dress but found a cheaper version elsewhere.  The price difference was a $100.", 5, false); 

                Review rG1 = new Review("Test G", 5, false);
                Review rG2 = new Review("This dress is very long and not suitable for girls who are petite.  I am 154cm and this dress is way too long for me :(", 5, false);
                Review rG3 = new Review("I like this dress so much that I bought 2 of it in both colours, red and blue.", 5, false);
                Review rG4 = new Review("Dress is made of premium quality, purchase is very worth it!", 5, false); 
                
                Review rH1 = new Review("Test H", 5, false);
                Review rH2 = new Review("The tamarined and rice really complement eachother to produce a very fragrant scent", 5, false);
                Review rH3 = new Review("I prefer this to store-bought soap products as there are no artificial ingredeints in this one!", 5, false);
                Review rH4 = new Review("The quality of this soap is very good and it smells good", 5, false); 

                
                Review rI1 = new Review("Test I", 5, false);
                Review rI2 = new Review("The workmanship of this crotcheted prodcut is top-tier.  There are no loose threads at all, I am impressed!", 5, false);
                Review rI3 = new Review("Very cute product, would definitely buy again as I am a huge fan of crotchet handicrafts!", 5, false);
                Review rI4 = new Review("I bought this for my granddaughter and it is her favourite toy now!", 5, false); 

                Review rJ1 = new Review("Test J", 5, false);
                Review rJ2 = new Review("Very pretty blouse, I wore it to a friend's birthday party and received compliments from all of my friends.\"", 5, false);
                Review rJ3 = new Review("The sizing is not accurate as this blouse runs small.  It is recommended that you upsize if you are in between sizes.", 5, false);
                Review rJ4 = new Review("Love the material of the dress!  It is resistant to high temperatures especially when ironing.", 5, false); 
                
                Review rK1 = new Review("Test K", 5, false);
                Review rK2 = new Review("Very tasty even with no artificial flavouring! A very healthy and delicious snack :)", 5, false);
                Review rK3 = new Review("I stunbled upon this website and decided to try some of their food products and I must say that I am not disappointed at all!", 5, false);
                Review rK4 = new Review("This snack was a little too salty for my liking, would prefer if it was more bland.  Nonetheless, it still makes a very good snack!", 5, false); 
                
                Review rL1 = new Review("Test L", 5, false);
                Review rL2 = new Review("This peanut butter beats those that are commonly found in the supermarkets!  It is very healthy to with no artificial ingredients and flavouring.", 5, false);
                Review rL3 = new Review("This peanut butter is much more expensive than other brands like Skippy and I feel that it does not even taste as good:(", 5, false);
                Review rL4 = new Review("I stunbled upon this website and decided to try some of their food products and I must say that I am very impressed with this peanut butter!", 5, false); 
                
                Review rM1 = new Review("Test M", 5, false);
                Review rM2 = new Review("The coffee beans were very fragrant.  These are of very good quality and is worth every dollar spent.", 5, false);
                Review rM3 = new Review("These are my favourite coffee beans, I have tried many brands but nothing can compare to these ones", 5, false);
                Review rM4 = new Review("This is my first time buying coffee beans in these flavour and I wil definitely be buying this again very soon :)", 5, false); 
                
                Review rN1 = new Review("Test N", 5, false);
                Review rN2 = new Review("The tea leaves were very fragrant.  These are of very good quality and is worth every dollar spent.", 5, false);
                Review rN3 = new Review("The portion of leaves in each packet is very little, this product is slightly expenisve compared to other brands, will not be buying again.", 5, false);
                Review rN4 = new Review("This is my favourite product for tea, I have tried many brands but nothing can compare to these ones", 5, false); 
                
                Review rO1 = new Review("Test O", 5, false);
                Review rO2 = new Review("The coffee beans were very fragrant.  These are of very good quality and is worth every dollar spent.", 5, false);
                Review rO3 = new Review("Chocolate and coffee taste surprisngly good.  It is one of my favourite combinations for coffee and I will definitely be buying this very often! ", 5, false);
                Review rO4 = new Review("The chocolate taste overpowered the coffee.  This drink is still decent but I would prefer if the drink had equal portions of coffee and chocolate tastel", 5, false); 
                
                reviewSessionBeanLocal.createNewReview(rA1, 1l, "abc1");
                reviewSessionBeanLocal.createNewReview(rA2, 1l, "abc2");
                reviewSessionBeanLocal.createNewReview(rA3, 1l, "abc3");
                reviewSessionBeanLocal.createNewReview(rA4, 1l, "abc4");
                
                reviewSessionBeanLocal.createNewReview(rB1, 2l, "abc1");
                reviewSessionBeanLocal.createNewReview(rB2, 2l, "abc2");
                reviewSessionBeanLocal.createNewReview(rB3, 2l,"abc3");
                reviewSessionBeanLocal.createNewReview(rB4, 2l, "abc4");
                
                reviewSessionBeanLocal.createNewReview(rC1, 3l, "abc1");
                reviewSessionBeanLocal.createNewReview(rC2, 3l, "abc2");
                reviewSessionBeanLocal.createNewReview(rC3, 3l, "abc3");
                reviewSessionBeanLocal.createNewReview(rC4, 3l, "abc4");
                
                reviewSessionBeanLocal.createNewReview(rD1, 4l, "abc1");
                reviewSessionBeanLocal.createNewReview(rD2, 4l, "abc2");
                reviewSessionBeanLocal.createNewReview(rD3, 4l, "abc3");
                reviewSessionBeanLocal.createNewReview(rD4, 4l, "abc4");
                
                reviewSessionBeanLocal.createNewReview(rE1, 5l, "abc1");
                reviewSessionBeanLocal.createNewReview(rE2, 5l, "abc2");
                reviewSessionBeanLocal.createNewReview(rE3, 5l, "abc3");
                reviewSessionBeanLocal.createNewReview(rE4, 5l, "abc4");
     
                reviewSessionBeanLocal.createNewReview(rF1, 6l, "abc1");
                reviewSessionBeanLocal.createNewReview(rF2, 6l, "abc2");
                reviewSessionBeanLocal.createNewReview(rF3, 6l, "abc3");
                reviewSessionBeanLocal.createNewReview(rF4, 6l, "abc4");
                
                     
                reviewSessionBeanLocal.createNewReview(rG1, 7l, "abc1");
                reviewSessionBeanLocal.createNewReview(rG2, 7l, "abc2");
                reviewSessionBeanLocal.createNewReview(rG3, 7l, "abc3");
                reviewSessionBeanLocal.createNewReview(rG4, 7l, "abc4");
                
                reviewSessionBeanLocal.createNewReview(rH1, 8l, "abc1");
                reviewSessionBeanLocal.createNewReview(rH2, 8l, "abc2");
                reviewSessionBeanLocal.createNewReview(rH3, 8l, "abc3");
                reviewSessionBeanLocal.createNewReview(rH4, 8l, "abc4");
                
                reviewSessionBeanLocal.createNewReview(rI1, 9l, "abc1");
                reviewSessionBeanLocal.createNewReview(rI2, 9l, "abc2");
                reviewSessionBeanLocal.createNewReview(rI3, 9l, "abc3");
                reviewSessionBeanLocal.createNewReview(rI4, 9l, "abc4");
                
                reviewSessionBeanLocal.createNewReview(rJ1, 10l, "abc1");
                reviewSessionBeanLocal.createNewReview(rJ2, 10l, "abc2");
                reviewSessionBeanLocal.createNewReview(rJ3, 10l, "abc3");
                reviewSessionBeanLocal.createNewReview(rJ4, 10l, "abc4");
                
                reviewSessionBeanLocal.createNewReview(rK1, 11l, "abc1");
                reviewSessionBeanLocal.createNewReview(rK2, 11l, "abc2");
                reviewSessionBeanLocal.createNewReview(rK3, 11l, "abc3");
                reviewSessionBeanLocal.createNewReview(rK4, 11l, "abc4");
              
                reviewSessionBeanLocal.createNewReview(rL1, 12l, "abc1");
                reviewSessionBeanLocal.createNewReview(rL2, 12l, "abc2");
                reviewSessionBeanLocal.createNewReview(rL3, 12l, "abc3");
                reviewSessionBeanLocal.createNewReview(rL4, 12l, "abc4");
                
                reviewSessionBeanLocal.createNewReview(rM1, 13l, "abc1");
                reviewSessionBeanLocal.createNewReview(rM2, 13l, "abc2");
                reviewSessionBeanLocal.createNewReview(rM3, 13l, "abc3");
                reviewSessionBeanLocal.createNewReview(rM4, 13l, "abc4");
                
                reviewSessionBeanLocal.createNewReview(rN1, 14l, "abc1");
                reviewSessionBeanLocal.createNewReview(rN2, 14l, "abc2");
                reviewSessionBeanLocal.createNewReview(rN3, 14l, "abc3");
                reviewSessionBeanLocal.createNewReview(rN4, 14l, "abc4");
                
                reviewSessionBeanLocal.createNewReview(rO1, 15l, "abc1");
                reviewSessionBeanLocal.createNewReview(rO2, 15l, "abc2");
                reviewSessionBeanLocal.createNewReview(rO3, 15l, "abc3");
                reviewSessionBeanLocal.createNewReview(rO4, 15l, "abc4");


            } catch (Exception ex) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    

    
}
