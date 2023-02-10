/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Product;
import entity.SocialEnterprise;
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
public class SocialEnterpriseSessionBean implements SocialEnterpriseSessionBeanRemote, SocialEnterpriseSessionBeanLocal {

    @PersistenceContext(unitName = "SociallyMinded-ejbPU")
    private EntityManager em;

    public Long createNewSocialEnterprise(SocialEnterprise enterprise) {
        em.persist(enterprise);
        em.flush();
        return enterprise.getSocialEnterpriseId();
    }
    
    public List<SocialEnterprise> retrieveAllSocialEnterprises() {
        Query query = em.createQuery("SELECT e FROM SocialEnterprise e");
        return query.getResultList();
    }
    
    public SocialEnterprise retrieveSocialEnterpriseById(Long enterpriseId) {
        SocialEnterprise enterprise = em.find(SocialEnterprise.class, enterpriseId);
        return enterprise;
    }
    
    public List<SocialEnterprise> retrieveSocialEnterpriseByName(String enterpriseName) {
        Query query = em.createQuery("SELECT e FROM SocialEnterprise e"
                + "WHERE e.enterpriseName = :enterpriseName");
        return query.getResultList();
        
    }
    
}
