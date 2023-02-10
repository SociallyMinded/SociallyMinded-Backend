/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import entity.Product;
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
public class ProductSessionBean implements ProductSessionBeanRemote, ProductSessionBeanLocal {

    @PersistenceContext(unitName = "SociallyMinded-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewProduct(Product product) {
        em.persist(product);
        em.flush();
        return product.getProductId();
    }
    
    @Override
    public List<Product> retrieveAllProducts() {
        Query query = em.createQuery("SELECT p FROM Product p");
        return query.getResultList();
    }
    
    public Product retrieveProductById(Long productId) {
        Product product = em.find(Product.class, productId);
        return product;
    }
    
    public List<Product> retrieveProductByName(String productName) {
        Query query = em.createQuery("SELECT p FROM Product p"
                + "WHERE p.name = :productName"
        );
        query.setParameter("productName", productName);
        return query.getResultList();
    }
}
