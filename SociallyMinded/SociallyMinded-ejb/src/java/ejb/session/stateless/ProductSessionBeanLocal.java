/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.Product;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ongyongen
 */
@Local
public interface ProductSessionBeanLocal {

    public List<Product> retrieveAllProducts();

    public Long createNewProduct(Product product, Long enterpriseId);

    public List<Product> retrieveAllProductsByEnterpriseId(Long enterpriseId);

    public List<Product> retrieveAllProductsByEnterpriseName(String enterpriseName);

    public Product retrieveProductById(Long productId);

    public List<Product> retrieveProductByName(String productName);
    
}
