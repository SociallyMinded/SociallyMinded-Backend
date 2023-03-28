/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.session.stateless.ReviewSessionBeanLocal;
import entity.Review;
import exception.ReviewNotFoundException;
import java.util.Date;
import java.util.List;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.CreateOrUpdateReviewTemplate;
import model.ErrorResponseTemplate;

/**
 *
 * @author admin
 */
@Stateless
@Path("entity.review")
public class ReviewFacadeREST extends AbstractFacade<Review> {

    @EJB
    private ReviewSessionBeanLocal reviewSessionBeanLocal;
    

    @PersistenceContext(unitName = "SociallyMinded-warPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReviewFacadeREST() {
        super(Review.class);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAllReviews() {
        try {
            List<Review> reviews = reviewSessionBeanLocal.retrieveAllReviews();
            return Response
                    .status(Response.Status.OK)
                    .entity(reviews)
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
    public Response findReviewById(@PathParam("id") Long id) {
        try {
            Review review = reviewSessionBeanLocal.retrieveReviewById(id);
            return Response
                    .status(Response.Status.OK)
                    .entity(review)
                    .build();
        } catch (ReviewNotFoundException ex) {
            ErrorResponseTemplate errorRsp = new ErrorResponseTemplate(ex.toString());
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(errorRsp)
                    .build();
        }
    }
    
    @GET
    @Path("findReviewsByProductId/{productId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findReviewsByProductId(@PathParam("productId") Long productId) {
        try {
            List<Review> reviews = reviewSessionBeanLocal.retrieveReviewsByProductId(productId);
            return Response
                    .status(Response.Status.OK)
                    .entity(reviews)
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
    @Path("findReviewsByCustomerId/{customerId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findReviewsByCustomerId(@PathParam("customerId") Long customerId) {
        try {
            List<Review> reviews = reviewSessionBeanLocal.retrieveReviewsByCustomerId(customerId);
            return Response
                    .status(Response.Status.OK)
                    .entity(reviews)
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
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(CreateOrUpdateReviewTemplate reviewReq) {
        try {
            Review r = reviewReq.getReview();
            r.setDateOfReview(new Date());
            Long reviewId = reviewSessionBeanLocal.createNewReview(reviewReq.getReview(), reviewReq.getProductId(), reviewReq.getCustFirebaseUid());
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
    public Response edit(@PathParam("id") Long id, CreateOrUpdateReviewTemplate reviewReq) {
        try {
            reviewSessionBeanLocal.updateReviewDetails(reviewReq.getReview(), reviewReq.getProductId(), reviewReq.getCustFirebaseUid());            
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
            reviewSessionBeanLocal.deleteReview(id);
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
    