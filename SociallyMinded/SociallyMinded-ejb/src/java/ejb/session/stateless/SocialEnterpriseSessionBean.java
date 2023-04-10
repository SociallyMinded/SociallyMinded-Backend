/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.SocialEnterprise;
import exception.InputDataValidationException;
import exception.SocialEnterpriseNotFoundException;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

/**
 *
 * @author ongyongen
 */
@Stateless
public class SocialEnterpriseSessionBean implements SocialEnterpriseSessionBeanRemote, SocialEnterpriseSessionBeanLocal {

    @PersistenceContext(unitName = "SociallyMinded-ejbPU")
    private EntityManager em;
    
    private final ValidatorFactory validatorFactory;
    private final javax.validation.Validator validator;
    
    public SocialEnterpriseSessionBean() {
        this.validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();
    }
    
    private String prepareInputDataValidationErrorMsg(Set<ConstraintViolation<SocialEnterprise>> violations) {
        String msg = "";

        for (ConstraintViolation violation : violations) {
            msg += violation.getPropertyPath() + " - " + violation.getMessage() + ";";
        }

        return msg;
    }

    @Override
    public Long createNewSocialEnterprise(SocialEnterprise enterprise) throws InputDataValidationException {
        Set<ConstraintViolation<SocialEnterprise>> constraintViolations = validator.validate(enterprise);
        if (constraintViolations.isEmpty()) {
            em.persist(enterprise);
            em.flush();
            return enterprise.getSocialEnterpriseId();
        } else {
            throw new InputDataValidationException(prepareInputDataValidationErrorMsg(constraintViolations));
        }  
    }
    
    @Override
    public List<SocialEnterprise> retrieveAllSocialEnterprises() {
        Query query = em.createQuery("SELECT e FROM SocialEnterprise e");
        return query.getResultList();
    }
    
    @Override
    public SocialEnterprise retrieveSocialEnterpriseById(Long enterpriseId) throws SocialEnterpriseNotFoundException {
        if (em.find(SocialEnterprise.class, enterpriseId) == null) {
            throw new SocialEnterpriseNotFoundException();
        } else {
            SocialEnterprise enterprise = em.find(SocialEnterprise.class, enterpriseId);
            return enterprise;
        }
    }
    
    @Override
    public SocialEnterprise retrieveSocialEnterpriseByEmail(String email) {
        Query q = em.createQuery("SELECT se FROM SocialEnterprise se WHERE se.email = :email");
        q.setParameter("email", email);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return (SocialEnterprise) q.getSingleResult();
        }
    }
    
    @Override
    public SocialEnterprise retrieveSocialEnterpriseByName(String enterpriseName) throws SocialEnterpriseNotFoundException {
        Query query = em.createQuery("SELECT e FROM SocialEnterprise e "
                + "WHERE e.enterpriseName = :enterpriseName");
        query.setParameter("enterpriseName", enterpriseName);
        
        if (query.getResultList().isEmpty()) {
            throw new SocialEnterpriseNotFoundException();
        } else {
            return (SocialEnterprise) query.getSingleResult();
        }        
    }
    
    @Override
    public SocialEnterprise retrieveSocialEnterpriseByFirebaseUid(String firebaseUid) throws SocialEnterpriseNotFoundException {
        Query query = em.createQuery("SELECT se FROM SocialEnterprise se "
                + "WHERE se.firebaseUid = :firebaseUid"
        );
        query.setParameter("firebaseUid", firebaseUid);
        if (query.getResultList().isEmpty()) {
            throw new SocialEnterpriseNotFoundException();
        } else {
            return (SocialEnterprise) query.getResultList().get(0);           
        }
    }
    
    @Override
    public void updateSocialEnterpriseDetails(SocialEnterprise newSocialEnterprise) throws InputDataValidationException {
        Set<ConstraintViolation<SocialEnterprise>> constraintViolations = validator.validate(newSocialEnterprise);
        if (constraintViolations.isEmpty()) {
            em.merge(newSocialEnterprise);
        } else {
            throw new InputDataValidationException(prepareInputDataValidationErrorMsg(constraintViolations));
        }
    } 
    
    @Override
    public void logInViaGmailAccount(SocialEnterprise newSocialEnterprise) throws InputDataValidationException {
        Set<ConstraintViolation<SocialEnterprise>> constraintViolations = validator.validate(newSocialEnterprise);
        if (constraintViolations.isEmpty()) {     
            if (this.retrieveSocialEnterpriseByEmail(newSocialEnterprise.getEmail()) == null) {
                em.persist(newSocialEnterprise);
                //createNewSocialEnterprise(newSocialEnterprise);
            }
        } else {
            throw new InputDataValidationException(prepareInputDataValidationErrorMsg(constraintViolations));
        }
    }
}
