/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import entity.Product;
import entity.Review;
import entity.SocialEnterprise;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ongyongen
 */
@Stateless
public class ProductSessionBean implements ProductSessionBeanRemote, ProductSessionBeanLocal {

    @EJB
    private SocialEnterpriseSessionBeanLocal socialEnterpriseSessionBeanLocal;
    

    @PersistenceContext(unitName = "SociallyMinded-ejbPU")
    private EntityManager em;
    
    

    @Override
    public Long createNewProduct(Product product, Long enterpriseId) {
        SocialEnterprise enterprise = socialEnterpriseSessionBeanLocal.retrieveSocialEnterpriseById(enterpriseId);
        enterprise.getProducts().add(product);
        product.setSocialenterprise(enterprise);
        em.persist(product);
        em.flush();
        return product.getProductId();
    }
    
    @Override
    public List<Product> retrieveAllProducts() {
        Query query = em.createQuery("SELECT p FROM Product p");
        return query.getResultList();
    }
    
    @Override
    public List<Product> retrieveAllProductsByEnterpriseId(Long enterpriseId) {
        Query query = em.createQuery("SELECT p FROM Product p "
                + "WHERE p.enterprise.socialEnterpriseId = :enterpriseId");
        query.setParameter("enterpriseId", enterpriseId);
        return query.getResultList();       
    }
    
    @Override
    public List<Product> retrieveAllProductsByEnterpriseName(String enterpriseName) {
        Query query = em.createQuery("SELECT p FROM Product p "
                + "WHERE p.enterprise.enterpriseName = :enterpriseName");
        query.setParameter("enterpriseName", enterpriseName);
        return query.getResultList();       
    }
    
    @Override
    public Product retrieveProductById(Long productId) {
        Product product = em.find(Product.class, productId);
        return product;
    }
    
    @Override
    public List<Product> retrieveProductByName(String productName) {
        Query query = em.createQuery("SELECT p FROM Product p "
                + "WHERE p.name = :productName"
        );
        query.setParameter("productName", productName);
        return query.getResultList();
    }
    
    public void updateProductDetails(Long oldProductId, String name, BigDecimal price, String description, 
            String imageLink, BigDecimal ratingScore, BigDecimal numRatings) {
        Product product = this.retrieveProductById(oldProductId);
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageLink(imageLink);
        product.setRatingScore(ratingScore);
        product.setNumRatings(numRatings);
       
    }
    
    public void deleteProduct(Long oldProductId) {
        Product product = this.retrieveProductById(oldProductId);
        product.getSocialenterprise().getProducts().remove(product);
        for (Review review : product.getReviews()) {
            em.remove(review);
        }
        // TODO : Check that you cannot delete product if you still have unfulfilled orders
        product.setSocialenterprise(null);
        product.setReviews(null);
        product.setOrders(null);
        em.remove(product);
    
    }
}
    
    
   
