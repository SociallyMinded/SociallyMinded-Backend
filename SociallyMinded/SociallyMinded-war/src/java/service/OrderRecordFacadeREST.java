/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import ejb.session.stateless.OrderRecordSessionBeanLocal;
import entity.OrderRecord;
import enumeration.OrderStatus;
import exception.OrderRecordNotFoundException;
import java.util.Date;
import java.util.List;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.CreateOrUpdateOrderRecordTemplate;
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
    public Response findAllOrderRecords() {
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
       
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findOrderRecordById(@PathParam("id") Long id) {
        try {
            OrderRecord record = orderRecordSessionBeanLocal.retrieveOrderRecordById(id);
            return Response
                    .status(Response.Status.OK)
                    .entity(record)
                    .build();
        } catch (OrderRecordNotFoundException ex) {
            ErrorResponseTemplate errorRsp = new ErrorResponseTemplate(ex.toString());
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(errorRsp)
                    .build();
        }
    }
    
    
    @GET
    @Path("findOrderRecordsByCustomerId/{customerId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findOrderRecordsByCustomerId(@PathParam("customerId") String customerFirebaseUid) {
        try {
            List<OrderRecord> records = orderRecordSessionBeanLocal.retrieveOrderRecordsByCustomerFirebaseUid(customerFirebaseUid);
            return Response
                    .status(Response.Status.OK)
                    .entity(records)
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
    @Path("findOrderRecordsByProductId/{productId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findOrderRecordsByProductId(@PathParam("customerId") Long productId) {
        try {
            List<OrderRecord> records = orderRecordSessionBeanLocal.retrieveOrderRecordsByProductId(productId);
            return Response
                    .status(Response.Status.OK)
                    .entity(records)
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
    public Response create(CreateOrUpdateOrderRecordTemplate orderReq) {
        try {
            OrderRecord r = orderReq.getRecord();
            r.setDateOfOrder(new Date());
            r.setOrderStatus("Pending Approval");
            Long recordId = orderRecordSessionBeanLocal.createNewOrderRecord(orderReq.getRecord(), orderReq.getProductId(), orderReq.getCustFirebaseUid());
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
    public Response edit(@PathParam("id") Long id, CreateOrUpdateOrderRecordTemplate orderReq) {
        try {
            orderRecordSessionBeanLocal.updateOrderRecordDetails(orderReq.getRecord(), orderReq.getProductId(), orderReq.getCustFirebaseUid());
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
            orderRecordSessionBeanLocal.deleteOrderRecord(id);
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

 
