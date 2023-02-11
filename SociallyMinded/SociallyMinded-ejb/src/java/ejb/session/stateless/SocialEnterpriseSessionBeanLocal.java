/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.SocialEnterprise;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ongyongen
 */
@Local
public interface SocialEnterpriseSessionBeanLocal {

    public Long createNewSocialEnterprise(SocialEnterprise enterprise);

    public List<SocialEnterprise> retrieveAllSocialEnterprises();

    public SocialEnterprise retrieveSocialEnterpriseById(Long enterpriseId);

    public List<SocialEnterprise> retrieveSocialEnterpriseByName(String enterpriseName);
    
}
