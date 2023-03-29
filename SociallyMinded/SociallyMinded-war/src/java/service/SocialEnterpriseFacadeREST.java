/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.session.stateless.SocialEnterpriseSessionBeanLocal;
import entity.SocialEnterprise;
import exception.SocialEnterpriseNotFoundException;
import java.util.List;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
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
import model.ErrorResponseTemplate;

/**
 *
 * @author ongyongen
 */
@Stateless
@Path("entity.socialenterprise")
public class SocialEnterpriseFacadeREST extends AbstractFacade<SocialEnterprise> {

    @EJB
    private SocialEnterpriseSessionBeanLocal socialEnterpriseSessionBeanLocal;

    @PersistenceContext(unitName = "SociallyMinded-warPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public SocialEnterpriseFacadeREST() {
        super(SocialEnterprise.class);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAllSocialEnterprises() {
        try {
            List<SocialEnterprise> enterprises = socialEnterpriseSessionBeanLocal.retrieveAllSocialEnterprises();
            return Response
                    .status(Response.Status.OK)
                    .entity(enterprises)
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
    public Response findSocialEnterpriseById(@PathParam("id") Long id) {
        try {
            SocialEnterprise enterprise = socialEnterpriseSessionBeanLocal.retrieveSocialEnterpriseById(id);
            return Response
                    .status(Response.Status.OK)
                    .entity(enterprise)
                    .build();
        } catch (SocialEnterpriseNotFoundException ex) {
            ErrorResponseTemplate errorRsp = new ErrorResponseTemplate(ex.toString());
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(errorRsp)
                    .build();
        }
          
    }
    
    @GET
    @Path("findSocialEnterpriseByName/{enterprisename}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findSocialEnterpriseByName(@PathParam("enterprisename") String enterprisename) {
        try {
            SocialEnterprise enterprise = socialEnterpriseSessionBeanLocal.retrieveSocialEnterpriseByName(enterprisename);
            return Response
                    .status(Response.Status.OK)
                    .entity(enterprise)
                    .build();
        } catch (SocialEnterpriseNotFoundException ex) {
            ErrorResponseTemplate errorRsp = new ErrorResponseTemplate(ex.toString());
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(errorRsp)
                    .build();
        }
    }
    
    @GET
    @Path("findSocialEnterpriseByFirebaseUid/{firebaseUid}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findSocialEnterpriseByFirebaseUid(@PathParam("firebaseUid") String firebaseUid) {
        try {
            SocialEnterprise enterprise = socialEnterpriseSessionBeanLocal.retrieveSocialEnterpriseByFirebaseUid(firebaseUid);
            return Response
                    .status(Response.Status.OK)
                    .entity(enterprise)
                    .build();
        } catch (SocialEnterpriseNotFoundException ex) {
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
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Asynchronous
    public void create(@Suspended final AsyncResponse asyncResponse, final SocialEnterprise entity) {
        asyncResponse.resume(doCreate(entity));
    }

    private Response doCreate(SocialEnterprise entity) {
        try {
            socialEnterpriseSessionBeanLocal.createNewSocialEnterprise(entity);
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
    public Response edit(@PathParam("id") Long id, SocialEnterprise entity) {
        try {
            socialEnterpriseSessionBeanLocal.updateSocialEnterpriseDetails(entity);
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
