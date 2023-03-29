/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.Product;
import exception.InputDataValidationException;
import exception.ProductNotFoundException;
import exception.SocialEnterpriseNotFoundException;
import exception.UncompletedOrdersException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ongyongen
 */
@Local
public interface ProductSessionBeanLocal {

    public List<Product> retrieveAllProducts();

    public List<Product> retrieveAllProductsByEnterpriseId(Long enterpriseId);

    public List<Product> retrieveAllProductsByEnterpriseName(String enterpriseName);

    public Product retrieveProductById(Long productId) throws ProductNotFoundException;

    public Product retrieveProductByName(String productName) throws ProductNotFoundException;    

    public void deleteProduct(Long oldProductId) throws ProductNotFoundException, UncompletedOrdersException;

    public Long createNewProduct(Product product, Long enterpriseId) throws SocialEnterpriseNotFoundException, InputDataValidationException;

    public void updateProductDetails(Product newProduct, Long enterpriseId) throws InputDataValidationException, SocialEnterpriseNotFoundException;
}
