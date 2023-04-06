/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.SocialEnterprise;
import exception.InputDataValidationException;
import exception.SocialEnterpriseNotFoundException;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author ongyongen
 */
@Remote
public interface SocialEnterpriseSessionBeanRemote {
    public Long createNewSocialEnterprise(SocialEnterprise enterprise) throws InputDataValidationException;

    public List<SocialEnterprise> retrieveAllSocialEnterprises();

    public SocialEnterprise retrieveSocialEnterpriseById(Long enterpriseId) throws SocialEnterpriseNotFoundException;

    public SocialEnterprise retrieveSocialEnterpriseByName(String enterpriseName) throws SocialEnterpriseNotFoundException;

    public void updateSocialEnterpriseDetails(SocialEnterprise newSocialEnterprise) throws InputDataValidationException;

    public SocialEnterprise retrieveSocialEnterpriseByFirebaseUid(String firebaseUid) throws SocialEnterpriseNotFoundException;

    public void logInViaGmailAccount(SocialEnterprise newSocialEnterprise) throws InputDataValidationException;
}
