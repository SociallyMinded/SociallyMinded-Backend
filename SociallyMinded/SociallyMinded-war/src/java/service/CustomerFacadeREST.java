/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import ejb.session.stateless.CustomerSessionBeanLocal;
import entity.Customer;
import entity.SocialEnterprise;
import exception.CustomerNotFoundException;
import exception.InputDataValidationException;
import java.util.List;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.ProductRequestTemplate;
import model.ErrorResponseTemplate;

/**
 *
 * @author ongyongen
 */
@Stateless
@Path("entity.customer")
public class CustomerFacadeREST extends AbstractFacade<Customer> {

    @EJB
    private CustomerSessionBeanLocal customerSessionBeanLocal;
    
    @PersistenceContext(unitName = "SociallyMinded-warPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public CustomerFacadeREST() {
        super(Customer.class);
    }

 
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAllCustomers() {
        try {
            List<Customer> customers = customerSessionBeanLocal.retrieveAllCustomers();
            return Response
                    .status(Response.Status.OK)
                    .entity(customers)
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
    public Response findCustomerById(@PathParam("id") Long id) {
        try {
            Customer customer = customerSessionBeanLocal.retrieveCustomerById(id);
            return Response
                    .status(Response.Status.OK)
                    .entity(customer)
                    .build();
        } catch (CustomerNotFoundException ex) {
            ErrorResponseTemplate errorRsp = new ErrorResponseTemplate(ex.toString());
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(errorRsp)
                    .build();
        }
          
    }
    
    @GET
    @Path("findCustomerByUsername/{username}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findCustomerByUsername(@PathParam("username") String username) {
        try {
            Customer customer = customerSessionBeanLocal.retrieveCustomerByUsername(username);
            return Response
                    .status(Response.Status.OK)
                    .entity(customer)
                    .build();
        } catch (CustomerNotFoundException ex) {
            ErrorResponseTemplate errorRsp = new ErrorResponseTemplate(ex.toString());
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(errorRsp)
                    .build();
        }
    }
    
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Customer> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @POST
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Asynchronous
    public void create(@Suspended final AsyncResponse asyncResponse, final Customer entity) {
        asyncResponse.resume(doCreate(entity));
    }

    private Response doCreate(Customer entity) {
        try {
            customerSessionBeanLocal.createNewCustomer(entity);
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
    public Response edit(@PathParam("id") Long id, Customer entity) {
        try {
            customerSessionBeanLocal.updateCustomerProfile(entity);
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
    
//    @DELETE
//    @Path("{id}")
//    public void remove(@PathParam("id") Long id) {
//        super.remove(super.find(id));
//    }
    
}

 