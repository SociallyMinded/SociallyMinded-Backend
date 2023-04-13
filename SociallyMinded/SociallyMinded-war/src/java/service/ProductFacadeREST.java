/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import ejb.session.stateless.ProductSessionBeanLocal;
import ejb.session.stateless.SocialEnterpriseSessionBeanLocal;
import entity.Product;
import exception.InputDataValidationException;
import exception.ProductNotFoundException;
import exception.SocialEnterpriseNotFoundException;
import java.util.List;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.CreateOrUpdateProductTemplate;
import model.ErrorResponseTemplate;

/**
 *
 * @author ongyongen
 */
@Stateless
@Path("entity.product")
public class ProductFacadeREST extends AbstractFacade<Product> {

    @EJB
    private SocialEnterpriseSessionBeanLocal socialEnterpriseSessionBeanLocal;

    @EJB
    private ProductSessionBeanLocal productSessionBeanLocal;
    

    @PersistenceContext(unitName = "SociallyMinded-warPU")
    private EntityManager em;
        
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacadeREST() {
        super(Product.class);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAllProducts() {
        try {
            List<Product> products = productSessionBeanLocal.retrieveAllProducts();
            return Response
                    .status(Response.Status.OK)
                    .entity(products)
                    .build();
        } catch (Exception ex) {
            ErrorResponseTemplate errorRsp = new ErrorResponseTemplate(ex.toString());
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(errorRsp)
                    .build();
        }
          
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findProductById(@PathParam("id") Long id) {
        try {
            Product product = productSessionBeanLocal.retrieveProductById(id);
            return Response
                    .status(Response.Status.OK)
                    .entity(product)
                    .build();
        } catch (ProductNotFoundException ex) {
            ErrorResponseTemplate errorRsp = new ErrorResponseTemplate(ex.toString());
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(errorRsp)
                    .build();
        }
    }
    
    @GET
    @Path("findProductByName/{productname}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findProductByName(@PathParam("productname") String productname) {
        try {
            Product product = productSessionBeanLocal.retrieveProductByName(productname);
            return Response
                    .status(Response.Status.OK)
                    .entity(product)
                    .build();
        } catch (Exception ex) {
            ErrorResponseTemplate errorRsp = new ErrorResponseTemplate(ex.toString());
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(errorRsp)
                    .build();
        }
    }
    
    @GET
    @Path("findProductsByEnterpriseId/{enterpriseId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findProductsByEnterpriseId(@PathParam("enterpriseId") Long enterpriseId) {
        try {
            List<Product> products = productSessionBeanLocal.retrieveAllProductsByEnterpriseId(enterpriseId);
            return Response
                    .status(Response.Status.OK)
                    .entity(products)
                    .build();
        } catch (Exception ex) {
            ErrorResponseTemplate errorRsp = new ErrorResponseTemplate(ex.toString());
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(errorRsp)
                    .build();
        }
    }
    
    @GET
    @Path("findProductByEnterpriseName/{enterprisename}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findProductsByEnterpriseName(@PathParam("enterprisename") String enterprisename) {
        try {
            List<Product> products = productSessionBeanLocal.retrieveAllProductsByEnterpriseName(enterprisename);
            return Response
                    .status(Response.Status.OK)
                    .entity(products)
                    .build();
        } catch (Exception ex) {
            ErrorResponseTemplate errorRsp = new ErrorResponseTemplate(ex.toString());
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(errorRsp)
                    .build();
        }
    }


    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(CreateOrUpdateProductTemplate productReq) {
        try {
            Long productId = productSessionBeanLocal.createNewProduct(productReq.getProduct(), productReq.getSocialEnterpriseId());
            return Response
                    .status(Response.Status.OK)
                    .build();
        } catch (Exception ex) {
            ErrorResponseTemplate errorRsp = new ErrorResponseTemplate(ex.toString());
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(errorRsp)
                    .build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Long id, CreateOrUpdateProductTemplate productReq) {
        try {
            productSessionBeanLocal.updateProductDetails(productReq.getProduct(), productReq.getSocialEnterpriseId());
            
            return Response
                    .status(Response.Status.OK)
                    .build();
        } catch (Exception ex) {
            ErrorResponseTemplate errorRsp = new ErrorResponseTemplate(ex.toString());
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(errorRsp)
                    .build();
        }
    }
    
    @PUT
    @Path("deactivateProduct/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response deactiveProduct(@PathParam("id") Long id, CreateOrUpdateProductTemplate productReq) {
        try {
            productSessionBeanLocal.setProductToInactive(id, productReq.getSocialEnterpriseId());
            return Response
                    .status(Response.Status.OK)
                    .build();
        } catch (Exception ex) {
            ErrorResponseTemplate errorRsp = new ErrorResponseTemplate(ex.toString());
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(errorRsp)
                    .build();
        }
    }
      
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
         try {
            productSessionBeanLocal.deleteProduct(id);
            return Response
                    .status(Response.Status.OK)
                    .build();
        } catch (Exception ex) {
            ErrorResponseTemplate errorRsp = new ErrorResponseTemplate(ex.toString());
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(errorRsp)
                    .build();
        }
         
    }

}