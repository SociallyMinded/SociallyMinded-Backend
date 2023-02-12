/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.Product;
import entity.SocialEnterprise;
import exception.InputDataValidationException;
import exception.SocialEnterpriseNotFoundException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ongyongen
 */
@Local
public interface SocialEnterpriseSessionBeanLocal {

    public Long createNewSocialEnterprise(SocialEnterprise enterprise) throws InputDataValidationException;

    public List<SocialEnterprise> retrieveAllSocialEnterprises();

    public SocialEnterprise retrieveSocialEnterpriseById(Long enterpriseId) throws SocialEnterpriseNotFoundException;

    public SocialEnterprise retrieveSocialEnterpriseByName(String enterpriseName) throws SocialEnterpriseNotFoundException;

    public void updateSocialEnterpriseDetails(SocialEnterprise newSocialEnterprise) throws InputDataValidationException;
    
}
