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
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Blob;
import java.util.Base64;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;


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
    
    private javax.ejb.SessionContext ctx;
    
  
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
                
                //image for product a
               
                int countA = 0;
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                URL imageUrla = classLoader.getResource("ejb/session/images/image1.jpg");
                countA++;
                URL imageUrla1 = classLoader.getResource("ejb/session/images/image2.jpg");
                countA++;
                URL imageUrla2 = classLoader.getResource("ejb/session/images/imageA3.jpg");
                countA++;
                 String imageForProductA[] = new String[countA];
                if (imageUrla == null) {
                    // Resource not found, handle error appropriately
                    throw new FileNotFoundException("Image resource not found");
                }
//                String imagePath = servletContext.getRealPath("/images/myImage.jpg");
                String imagePatha = imageUrla.getPath();
                String imagePatha1 = imageUrla1.getPath();
                 String imagePatha2 = imageUrla2.getPath();
                String[] imagePathsA = {imagePatha, imagePatha1,imagePatha2};
//                if(imageUrl != null) {
            for (int i = 0; i < imagePathsA.length; i++) {
            // Read image file
            File imageFileA = new File(imagePathsA[i]);
            byte[] imageBytesA = Files.readAllBytes(imageFileA.toPath()); 
            // Convert image to base64
            imageForProductA[i] = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytesA);}
            //imageForProductA[i] = Base64.getEncoder().encodeToString(imageBytesA);}
//            }

//image for product b
                int countB = 0;
  
                URL imageUrlB1 = classLoader.getResource("ejb/session/images/imageB1.jpg");
                countB++;
                URL imageUrlB2 = classLoader.getResource("ejb/session/images/imageB2.jpg");
                countB++;
                String imageForProductB[] = new String[countB];
                String imagePathB1 = imageUrlB1.getPath();
                String imagePathB2 = imageUrlB2.getPath();
                String[] imagePathsB = {imagePathB1, imagePathB2};
            for (int i = 0; i < imagePathsB.length; i++) {
            File imageFileB = new File(imagePathsB[i]);
            byte[] imageBytesB = Files.readAllBytes(imageFileB.toPath()); 
             imageForProductB[i] = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytesB);}

            //image for product c
                int countC = 0;
                URL imageUrlC1 = classLoader.getResource("ejb/session/images/imageC1.jpg");
                countC++;
//                URL imageUrlC2 = classLoader.getResource("ejb/session/images/imageC2.jpg");

                String imagePathC1 = imageUrlC1.getPath();
//                String imagePathC2 = imageUrlC2.getPath();
                String[] imagePathsC = {imagePathC1};
                String imageForProductC[] = new String[countC];
            for (int i = 0; i < imagePathsC.length; i++) {
            File imageFileC = new File(imagePathsC[i]);
            byte[] imageBytesC = Files.readAllBytes(imageFileC.toPath()); 
            imageForProductC[i] = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytesC);}
                
            //image for product d
                int countD = 0;
                URL imageUrlD1 = classLoader.getResource("ejb/session/images/imageD1.jpg");
                countD++;
                URL imageUrlD2 = classLoader.getResource("ejb/session/images/imageD2.jpg");
                countD++;
                URL imageUrlD3 = classLoader.getResource("ejb/session/images/imageD3.jpg");
                countD++;
                String imagePathD1 = imageUrlD1.getPath();
                String imagePathD2 = imageUrlD2.getPath();
                String imagePathD3 = imageUrlD3.getPath();
                
                String imageForProductD[] = new String[countD];
                String[] imagePathsD = {imagePathD1, imagePathD2, imagePathD3};
            for (int i = 0; i < imagePathsD.length; i++) {
            File imageFileD = new File(imagePathsD[i]);
            byte[] imageBytesD = Files.readAllBytes(imageFileD.toPath()); 
            imageForProductD[i] = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytesD);}
            
              //image for product E
              int countE = 0;
                URL imageUrlE1 = classLoader.getResource("ejb/session/images/imageE1.jpg");
                countE++;
                URL imageUrlE2 = classLoader.getResource("ejb/session/images/imageE2.jpg");
                countE++;
                URL imageUrlE3 = classLoader.getResource("ejb/session/images/imageE3.jpg");
                countE++;
                String imagePathE1 = imageUrlE1.getPath();
                String imagePathE2 = imageUrlE2.getPath();
                String imagePathE3 = imageUrlE3.getPath();
                
                String imageForProductE[] = new String[countE];
                String[] imagePathsE = {imagePathE1, imagePathE2, imagePathE3};
            for (int i = 0; i < imagePathsE.length; i++) {
            File imageFileE = new File(imagePathsE[i]);
            byte[] imageBytesE = Files.readAllBytes(imageFileE.toPath()); 
            imageForProductE[i] = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytesE);}
            
              //image for product F
            int countF = 0;
                URL imageUrlF1 = classLoader.getResource("ejb/session/images/imageF1.jpg");
                countF++;
                URL imageUrlF2 = classLoader.getResource("ejb/session/images/imageF2.jpg");
                countF++;
                URL imageUrlF3 = classLoader.getResource("ejb/session/images/imageF3.jpg");
                countF++;
                String imagePathF1 = imageUrlF1.getPath();
                String imagePathF2 = imageUrlF2.getPath();
                String imagePathF3 = imageUrlF3.getPath();
                String imageForProductF[] = new String[countF];
                String[] imagePathsF = {imagePathF1, imagePathF2, imagePathF3};
            for (int i = 0; i < imagePathsF.length; i++) {
            File imageFileF = new File(imagePathsF[i]);
            byte[] imageBytesF = Files.readAllBytes(imageFileF.toPath()); 
            imageForProductF[i] = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytesF);}
            
            //image for product G
             int countG = 0;
                URL imageUrlG1 = classLoader.getResource("ejb/session/images/imageG1.jpg");
                countG++;
                URL imageUrlG2 = classLoader.getResource("ejb/session/images/imageG2.jpg");
                countG++;
                URL imageUrlG3 = classLoader.getResource("ejb/session/images/imageG3.jpg");
                countG++;
                String imagePathG1 = imageUrlG1.getPath();
                String imagePathG2 = imageUrlG2.getPath();
                String imagePathG3 = imageUrlG3.getPath();
                String imageForProductG[] = new String[countG];
                String[] imagePathsG = {imagePathG1, imagePathG2, imagePathG3};
            for (int i = 0; i < imagePathsG.length; i++) {
            File imageFileG = new File(imagePathsG[i]);
            byte[] imageBytesG = Files.readAllBytes(imageFileG.toPath()); 
            imageForProductG[i] = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytesG);}
            
            //image for product H
             int countH = 0;
                URL imageUrlH1 = classLoader.getResource("ejb/session/images/imageH1.jpg");
                countH++;
                String imagePathH1 = imageUrlH1.getPath();
                String imageForProductH[] = new String[countH];
                String[] imagePathsH = {imagePathH1};
            for (int i = 0; i < imagePathsH.length; i++) {
            File imageFileH = new File(imagePathsH[i]);
            byte[] imageBytesH = Files.readAllBytes(imageFileH.toPath()); 
            imageForProductH[i] = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytesH);}
            
             //image for product I
             int countI = 0;
                URL imageUrlI1 = classLoader.getResource("ejb/session/images/imageI1.jpg");
                countI++;
                URL imageUrlI2 = classLoader.getResource("ejb/session/images/imageI2.jpg");
                countI++;
                String imagePathI1 = imageUrlI1.getPath();
                String imagePathI2 = imageUrlI2.getPath();
                String imageForProductI[] = new String[countI];
                String[] imagePathsI = {imagePathI1, imagePathI2};
            for (int i = 0; i < imagePathsI.length; i++) {
            File imageFileI = new File(imagePathsI[i]);
            byte[] imageBytesI = Files.readAllBytes(imageFileI.toPath()); 
            imageForProductI[i] = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytesI);}
            
              
             //image for product J
            int countJ = 0;
                URL imageUrlJ1 = classLoader.getResource("ejb/session/images/imageJ1.jpg");
                countJ++;
                URL imageUrlJ2 = classLoader.getResource("ejb/session/images/imageJ2.jpg");
                countJ++;
                String imagePathJ1 = imageUrlJ1.getPath();
                String imagePathJ2 = imageUrlJ2.getPath();
                String imageForProductJ[] = new String[countJ];
                String[] imagePathsJ = {imagePathJ1, imagePathJ2};
            for (int i = 0; i < imagePathsJ.length; i++) {
            File imageFileJ = new File(imagePathsJ[i]);
            byte[] imageBytesJ = Files.readAllBytes(imageFileJ.toPath()); 
            imageForProductJ[i] = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytesJ);}
            
              //image for product K
            int countK = 0;
                URL imageUrlK1 = classLoader.getResource("ejb/session/images/imageK1.jpg");
                countK++;
                URL imageUrlK2 = classLoader.getResource("ejb/session/images/imageK2.jpg");
                countK++;
                URL imageUrlK3 = classLoader.getResource("ejb/session/images/imageK3.jpg");
                countK++;
                String imagePathK1 = imageUrlK1.getPath();
                String imagePathK2 = imageUrlK2.getPath();
                String imagePathK3 = imageUrlK3.getPath();
                String imageForProductK[] = new String[countK];
                String[] imagePathsK = {imagePathK1, imagePathK2, imagePathK3};
            for (int i = 0; i < imagePathsK.length; i++) {
            File imageFileK = new File(imagePathsK[i]);
            byte[] imageBytesK = Files.readAllBytes(imageFileK.toPath()); 
            imageForProductK[i] = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytesK);}
            
             //image for product L
             int countL = 0;
                URL imageUrlL1 = classLoader.getResource("ejb/session/images/imageL1.jpg");
                countL++;
                String imagePathL1 = imageUrlL1.getPath();
                String imageForProductL[] = new String[countL];
                String[] imagePathsL = {imagePathL1};
            for (int i = 0; i < imagePathsL.length; i++) {
            File imageFileL = new File(imagePathsL[i]);
            byte[] imageBytesL = Files.readAllBytes(imageFileL.toPath()); 
            imageForProductL[i] = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytesL);}
            
              //image for product M
             int countM = 0;
                URL imageUrlM1 = classLoader.getResource("ejb/session/images/imageM1.jpg");
                countM++;
                String imagePathM1 = imageUrlM1.getPath();
                String imageForProductM[] = new String[countM];
                String[] imagePathsM = {imagePathM1};
            for (int i = 0; i < imagePathsM.length; i++) {
            File imageFileM = new File(imagePathsM[i]);
            byte[] imageBytesM = Files.readAllBytes(imageFileM.toPath()); 
            imageForProductM[i] = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytesM);}
            
              //image for product N
             int countN = 0;
                URL imageUrlN1 = classLoader.getResource("ejb/session/images/imageN1.jpg");
                countN++;
                String imagePathN1 = imageUrlN1.getPath();
                String imageForProductN[] = new String[countN];
                String[] imagePathsN = {imagePathN1};
            for (int i = 0; i < imagePathsN.length; i++) {
            File imageFileN = new File(imagePathsN[i]);
            byte[] imageBytesN = Files.readAllBytes(imageFileN.toPath()); 
            imageForProductN[i] = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytesN);}
            
              //image for product O
             int countO = 0;
                URL imageUrlO1 = classLoader.getResource("ejb/session/images/imageO1.jpg");
                countO++;
                String imagePathO1 = imageUrlO1.getPath();
                String imageForProductO[] = new String[countO];
                String[] imagePathsO = {imagePathO1};
            for (int i = 0; i < imagePathsO.length; i++) {
            File imageFileO = new File(imagePathsO[i]);
            byte[] imageBytesO = Files.readAllBytes(imageFileO.toPath()); 
            imageForProductO[i] = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytesO);}
            
              //image for product P
             int countP = 0;
                URL imageUrlP1 = classLoader.getResource("ejb/session/images/imageP1.jpg");
                countP++;
                String imagePathP1 = imageUrlP1.getPath();
                String imageForProductP[] = new String[countP];
                String[] imagePathsP = {imagePathP1};
            for (int i = 0; i < imagePathsP.length; i++) {
            File imageFileP = new File(imagePathsP[i]);
            byte[] imageBytesP = Files.readAllBytes(imageFileP.toPath()); 
            imageForProductP[i] = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytesP);}
            
              //image for product Q
            int countQ = 0;
                URL imageUrlQ1 = classLoader.getResource("ejb/session/images/imageQ1.jpg");
                countQ++;
                URL imageUrlQ2 = classLoader.getResource("ejb/session/images/imageQ2.jpg");
                countQ++;
                String imagePathQ1 = imageUrlQ1.getPath();
                String imagePathQ2 = imageUrlQ2.getPath();
                String imageForProductQ[] = new String[countQ];
                String[] imagePathsQ = {imagePathQ1, imagePathQ2};
            for (int i = 0; i < imagePathsQ.length; i++) {
            File imageFileQ = new File(imagePathsQ[i]);
            byte[] imageBytesQ = Files.readAllBytes(imageFileQ.toPath()); 
            imageForProductQ[i] = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytesQ);}
            
              //image for product R
            int countR = 0;
                URL imageUrlR1 = classLoader.getResource("ejb/session/images/imageR1.jpg");
                countR++;
                URL imageUrlR2 = classLoader.getResource("ejb/session/images/imageR2.jpg");
                countR++;
                String imagePathR1 = imageUrlR1.getPath();
                String imagePathR2 = imageUrlR2.getPath();
                String imageForProductR[] = new String[countR];
                String[] imagePathsR = {imagePathR1, imagePathR2};
            for (int i = 0; i < imagePathsR.length; i++) {
            File imageFileR = new File(imagePathsR[i]);
            byte[] imageBytesR = Files.readAllBytes(imageFileR.toPath()); 
            imageForProductR[i] = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytesR);}
            
              //image for product S
            int countS = 0;
                URL imageUrlS1 = classLoader.getResource("ejb/session/images/imageS1.jpg");
                countS++;
                URL imageUrlS2 = classLoader.getResource("ejb/session/images/imageS2.jpg");
                countS++;
                String imagePathS1 = imageUrlS1.getPath();
                String imagePathS2 = imageUrlS2.getPath();
                String imageForProductS[] = new String[countS];
                String[] imagePathsS = {imagePathS1, imagePathS2};
            for (int i = 0; i < imagePathsS.length; i++) {
            File imageFileS = new File(imagePathsS[i]);
            byte[] imageBytesS = Files.readAllBytes(imageFileS.toPath()); 
            imageForProductS[i] = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytesS);}
            
              //image for product T
            int countT = 0;
                URL imageUrlT1 = classLoader.getResource("ejb/session/images/imageT1.jpg");
                countT++;
                String imagePathT1 = imageUrlT1.getPath();
                String imageForProductT[] = new String[countT];
                String[] imagePathsT = {imagePathT1};
            for (int i = 0; i < imagePathsT.length; i++) {
            File imageFileT = new File(imagePathsT[i]);
            byte[] imageBytesT = Files.readAllBytes(imageFileT.toPath()); 
            imageForProductT[i] = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytesT);}
            
                Product pA = new Product("Amber Jersey Pleated Dress", new BigDecimal(169), "Light TENCEL™ fabric : skin-friendly, soft texture, high breathability, made from sustainable wood sources.", imageForProductA, "CLOTHING", new BigDecimal(18),new BigDecimal(4));
                Product pB = new Product("Raffles Asymmetrical Wide Leg Pants", new BigDecimal(159), "This mid waist and wide legs pants was designed to suit any occasion." , imageForProductB, "CLOTHING",new BigDecimal(20),new BigDecimal(4));
                Product pC = new Product("Bugis Stitch Maxi Dress", new BigDecimal(189), "This dress was designed to suit any occasion.", imageForProductC, "CLOTHING",new BigDecimal(17),new BigDecimal(4));
                Product pD = new Product("Raffles X-line Midi Dress", new BigDecimal(199), "Soft woven fabric made of TENCEL™ lyocell with decorative seams", imageForProductD, "CLOTHING",new BigDecimal(16),new BigDecimal(4));
                Product pE = new Product("Raffles Blend Dress", new BigDecimal(179), "Jersey made of TENCEL™ modal and and skirt belnd with Woven TENCEL, designed with a V-neckline" , imageForProductE, "CLOTHING",new BigDecimal(17),new BigDecimal(4));
                Product pF = new Product("U-Shaped Back Draped Dress", new BigDecimal(159), "Made from Tencel - known as environment-friendly fabrics. Designed with a bowtie and a side open slit.", imageForProductF, "CLOTHING",new BigDecimal(16),new BigDecimal(4));
                Product pG = new Product("Amber Belted Maxi Dress", new BigDecimal(199), "A timeless piece, this dress will last a lifetime.  Featuring front panel details and a removable waist tie.", imageForProductG, "CLOTHING",new BigDecimal(20),new BigDecimal(4));
                Product pH = new Product("Handmade Natural Soap (Tamarind/ Rice)", new BigDecimal(10), "Our NO. 1 BESTSELLER hand-crafted soap is made using high-quality tamarind and rice.", imageForProductH, "CRAFTS",new BigDecimal(20),new BigDecimal(4)); //Olive Tree
                Product pI = new Product("Assorted Crocheted Angels", new BigDecimal(25), "Bring home these large decorative assorted crocheted angels crafted by our volunteer Aunty Nancy!", imageForProductI, "CRAFTS",new BigDecimal(20),new BigDecimal(4)); //Olive Tree
                Product pJ = new Product("Elastic Collar Shirt", new BigDecimal(139), "TENCEL is a natural fibre which is degradable.  Special properties include: enhanced breathability &  gentle on the skin.", imageForProductJ, "CLOTHING",new BigDecimal(20),new BigDecimal(4));
                Product pK = new Product("Sunshine Chips", new BigDecimal(5), "We took the spectrum of sunlight, separated it and vacuum fried them into chips. No artificial colourings or preservatives!", imageForProductK, "FOOD",new BigDecimal(20),new BigDecimal(4)); //boxgreen
                Product pL = new Product("Grounded Sea Salt Peanut Butter Jar", new BigDecimal(10), "Roasted peanuts grounded twice for extra smoothness with heart-healthy coconut oil and a dash of salt to bring the flavours home. ", imageForProductL, "CRAFTS",new BigDecimal(16),new BigDecimal(4)); //boxgreen
                Product pM = new Product("Thai Mountain Espresso", new BigDecimal(75), "A world class single origin coffee, backed by multiple awards, our Organic Espresso is the perfect espresso shot you’ve been looking for.", imageForProductM, "FOOD",new BigDecimal(20),new BigDecimal(4)); //noharmdone
                Product pN = new Product("Thai Iced Tea", new BigDecimal(10), "Earthy black Ceylon tea leaves brewed to give you an authentic orange coloured tea that'll take your senses to the bustling streets of Bangkok.", imageForProductN, "FOOD",new BigDecimal(20),new BigDecimal(4));//noharmdone
                Product pO = new Product("Choco Kopi", new BigDecimal(8), "For Mondays, hangovers, make ups, breakups, late nights, early starts, rainy days… A hug in a mug. Our toasty Kopi-o with a malty chocolate infusion. ", imageForProductO, "FOOD",new BigDecimal(20),new BigDecimal(4)); //noharmdone
                Product pP = new Product("Gula Melaka Kopi", new BigDecimal(8), "We've blended our signature Kopi-o with caramelised coconut sugar, giving your favourite brew a decadent twist." , imageForProductP, "FOOD",new BigDecimal(20),new BigDecimal(4));
                Product pQ = new Product("Personalised Photo Dome", new BigDecimal(170), "Our Signature Preserved Flower Dome is meticulously handcrafted using long-lasting premium flowers. Make a lasting impression with this thoughtful gift, personalized with a memorable photo of your loved one.", imageForProductQ, "OTHERS",new BigDecimal(20),new BigDecimal(4)); //bloomback
                Product pR = new Product("Handmade Natural Soap (Lemon Grass)", new BigDecimal(10), "Our NO. 1 BESTSELLER hand-crafted soap is made using high-quality lemongrass.", imageForProductR, "CRAFTS",new BigDecimal(20),new BigDecimal(4)); //olive tree
                Product pS = new Product("Personalised Birthstone Bag Charm", new BigDecimal(75), "This bag charm is crafted using long-lasting premium flowers and completed with a meaningful birthstone.", imageForProductS, "OTHERS",new BigDecimal(20),new BigDecimal(4));//bloomback
                Product pT = new Product("Handmade Natural Soap (Roselle/ Rice Milk)", new BigDecimal(10), "Our NO. 1 BESTSELLER hand-crafted soap is made using high-quality roselle and rice milk.", imageForProductT, "CRAFTS",new BigDecimal(20),new BigDecimal(4)); //olive tree

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

                //image for review A
                int countReviewA = 0;
  
//                URL imageUrlReviewA1 = classLoader.getResource("ejb/session/images/imageB1.jpg");
                countReviewA++;
//                URL imageUrlReviewA2 = classLoader.getResource("ejb/session/images/imageB2.jpg");
                countReviewA++;
                countReviewA++;
                String imageForProductReviewA[] = new String[countReviewA];
//                String imagePathB1 = imageUrlReviewA1.getPath();
//                String imagePathB2 = imageUrlReviewA2.getPath();
               // String[] imagePathsReviewA = {imagePathA, imagePathA2};
                 String[] imagePathsReviewA = {imagePatha1,imagePatha,imagePatha2};
            for (int i = 0; i < imagePathsReviewA.length; i++) {
            File imageFileReviewA = new File(imagePathsReviewA[i]);
            byte[] imageBytesReviewA = Files.readAllBytes(imageFileReviewA.toPath()); 
             imageForProductReviewA[i] = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytesReviewA);}

                
                
                Review rA1 = new Review("Very pretty dress, I wore it to a friend's birthday party and received compliments from all of my friends.", 5, false, new Date());
                Review rA2 = new Review("The material of the dress is very good.  This dress is on the pricier side but this is truly worth every dollar spent!", 5, false, imageForProductReviewA,new Date());

                Review rA3 = new Review("The sizing is not accurate as this dress runs small.  It is recommended that you upsize if you are in between sizes.", 3, false, new Date());
                Review rA4 = new Review("The colour of the dress is very unique and it has become my favourite piece in my closet!",5, false, new Date());
                
                Review rB1 = new Review("Very nice!",5, false, new Date());
                Review rB2 = new Review("These pants are so flare that it looks like a skirt from far.  A very versatile piece to style :)",5, false, new Date());
                Review rB3 = new Review("I like these pants so much that I bought 2 of it in both colours, black and blue.",5, false, new Date());
                Review rB4 = new Review("This is my favourite pants at the moment :)",5, false, new Date());
                
                Review rC1 = new Review("Looks very nice",5, false, new Date());

                Review rC2 = new Review("The turquoise colour of the dress is very unique. It is hard to find dresses in this colour so I really like this piece",5, false, new Date());
                Review rC3 = new Review("The material of the dress is very good.  This dress is on the pricier side but this is truly worth every dollar spent!",5, false, new Date());
                Review rC4 = new Review("The sizing of this dress runs big. It is recommended that you size down if you are in between sizes",2, false, new Date());
                   
                Review rD1 = new Review("Buy this for my friend, she like it a lot", 5, false, new Date());
                Review rD2 = new Review("This is my first time purchasing clothing of this material and it has become my favourite kind due to its high quality!", 5, false, new Date());
                Review rD3 = new Review("Very pretty dress, I wore it to a friend's birthday party and received compliments from all of my friends.", 5, false, new Date());
                Review rD4 = new Review("This dress is very long and not suitable for girls who are petite.  I am 154cm and this dress is way too long for me :(", 1, false, new Date());
                
                Review rE1 = new Review("Beautiful!", 5, false, new Date());
                Review rE2 = new Review("The sizing is not accurate as this dress runs small.  It is recommended that you upsize if you are in between sizes.", 2, false, new Date());
                Review rE3 = new Review("I love the flowy cut of the dress, it is very easy to move in and is definitely very comfortable to wear!", 5, false, new Date());
                Review rE4 = new Review("Love the material of the dress!  It is resistant to high temperatures especially when ironing.", 5, false, new Date()); 

                Review rF1 = new Review("WORTH IT", 5, false, new Date());
                Review rF2 = new Review("Long the high neckline for the dress.  Modest yet stylish!", 5, false, new Date());
                Review rF3 = new Review("Dress is made of premium quality, purchase is very worth it!", 5, false, new Date());
                Review rF4 = new Review("Dress is too expensive.  Bought the dress but found a cheaper version elsewhere.  The price difference was a $100.", 1, false, new Date()); 

                Review rG1 = new Review("Very WORTH IT", 5, false, new Date());
                Review rG2 = new Review("This dress is very long and not suitable for girls who are petite.  I am 154cm and this dress is way too long for me :(", 5, false, new Date());
                Review rG3 = new Review("I like this dress so much that I bought 2 of it in both colours, red and blue.", 5, false, new Date());
                Review rG4 = new Review("Dress is made of premium quality, purchase is very worth it!", 5, false, new Date()); 
                
                Review rH1 = new Review("The scent is very nice. I love it", 5, false, new Date());
                Review rH2 = new Review("The tamarined and rice really complement eachother to produce a very fragrant scent", 5, false,new Date());
                Review rH3 = new Review("I prefer this to store-bought soap products as there are no artificial ingredeints in this one!", 5, false, new Date());
                Review rH4 = new Review("The quality of this soap is very good and it smells good", 5, false, new Date()); 

                
                Review rI1 = new Review("I love the design. Thank you :)", 5, false, new Date());
                Review rI2 = new Review("The workmanship of this crotcheted prodcut is top-tier.  There are no loose threads at all, I am impressed!", 5, false, new Date());
                Review rI3 = new Review("Very cute product, would definitely buy again as I am a huge fan of crotchet handicrafts!", 5, false, new Date());
                Review rI4 = new Review("I bought this for my granddaughter and it is her favourite toy now!", 5, false, new Date()); 

                Review rJ1 = new Review("Beautiful", 5, false, new Date());
                Review rJ2 = new Review("Very pretty blouse, I wore it to a friend's birthday party and received compliments from all of my friends.\"", 5, false, new Date());
                Review rJ3 = new Review("The sizing is not accurate as this blouse runs small.  It is recommended that you upsize if you are in between sizes.", 5, false, new Date());
                Review rJ4 = new Review("Love the material of the dress!  It is resistant to high temperatures especially when ironing.", 5, false, new Date()); 
                
                Review rK1 = new Review("I give it to my friend as a birthday gift and she love it!", 5, false, new Date());
                Review rK2 = new Review("Very tasty even with no artificial flavouring! A very healthy and delicious snack :)", 5, false, new Date());
                Review rK3 = new Review("I stunbled upon this website and decided to try some of their food products and I must say that I am not disappointed at all!", 5, false, new Date());
                Review rK4 = new Review("This snack was a little too salty for my liking, would prefer if it was more bland.  Nonetheless, it still makes a very good snack!", 5, false, new Date()); 
                
                Review rL1 = new Review("Delicious!!", 5, false, new Date());
                Review rL2 = new Review("This peanut butter beats those that are commonly found in the supermarkets!  It is very healthy to with no artificial ingredients and flavouring.", 5, false, new Date());
                Review rL3 = new Review("This peanut butter is much more expensive than other brands like Skippy and I feel that it does not even taste as good:(", 1, false, new Date());
                Review rL4 = new Review("I stunbled upon this website and decided to try some of their food products and I must say that I am very impressed with this peanut butter!", 5, false, new Date()); 
                
                Review rM1 = new Review("Smell nice yet to taste it.", 5, false , new Date());
                Review rM2 = new Review("The coffee beans were very fragrant.  These are of very good quality and is worth every dollar spent.", 5, false, new Date());
                Review rM3 = new Review("These are my favourite coffee beans, I have tried many brands but nothing can compare to these ones", 5, false, new Date());
                Review rM4 = new Review("This is my first time buying coffee beans in these flavour and I wil definitely be buying this again very soon :)", 5, false, new Date()); 
                
                Review rN1 = new Review("My family love this drink a lot", 5, false, new Date());
                Review rN2 = new Review("The tea leaves were very fragrant.  These are of very good quality and is worth every dollar spent.", 5, false, new Date());
                Review rN3 = new Review("The portion of leaves in each packet is very little, this product is slightly expenisve compared to other brands, will not be buying again.", 5, false, new Date());
                Review rN4 = new Review("This is my favourite product for tea, I have tried many brands but nothing can compare to these ones", 5, false, new Date()); 
                
                Review rO1 = new Review("Will buy again. :D", 5, false, new Date());
                Review rO2 = new Review("The coffee beans were very fragrant.  These are of very good quality and is worth every dollar spent.", 5, false, new Date());
                Review rO3 = new Review("Chocolate and coffee taste surprisngly good.  It is one of my favourite combinations for coffee and I will definitely be buying this very often! ", 5, false, new Date());
                Review rO4 = new Review("The chocolate taste overpowered the coffee.  This drink is still decent but I would prefer if the drink had equal portions of coffee and chocolate tastel", 5, false, new Date()); 
                
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
