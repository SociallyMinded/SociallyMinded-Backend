/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import ejb.session.stateless.CustomerSessionBeanLocal;
import ejb.session.stateless.OrderRecordSessionBeanLocal;
import entity.Customer;
import entity.OrderRecord;
import entity.SocialEnterprise;
import exception.CustomerNotFoundException;
import exception.InputDataValidationException;
import java.util.List;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Order;
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
@Path("entity.orderrecord")
public class OrderRecordFacadeREST extends AbstractFacade<Order> {

    @EJB
    private OrderRecordSessionBeanLocal orderRecordSessionBeanLocal;


    @PersistenceContext(unitName = "SociallyMinded-warPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public OrderRecordFacadeREST() {
        super(Order.class);
    }

 
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAllOrders() {
        try {
            List<OrderRecord> orders = orderRecordSessionBeanLocal.retrieveAllOrderRecords();
            return Response
                    .status(Response.Status.OK)
                    .entity(orders)
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

 