/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ongyongen
 */
@Entity
@XmlRootElement
public class SocialEnterprise implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long socialEnterpriseId;
    private String enterpriseName; 
    private String password;
    private String address;
    private String email;    
    private Date dateJoined;
    private String phoneNos;

    public SocialEnterprise() {
    }

    public SocialEnterprise(String companyName, String password, String address, String email, Date dateJoined, String phoneNos) {
        this.enterpriseName = companyName;
        this.password = password;
        this.address = address;
        this.email = email;
        this.dateJoined = dateJoined;
        this.phoneNos = phoneNos;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getPhoneNos() {
        return phoneNos;
    }

    public void setPhoneNos(String phoneNos) {
        this.phoneNos = phoneNos;
    }
    

    public Long getSocialEnterpriseId() {
        return socialEnterpriseId;
    }

    public void setSocialEnterpriseId(Long socialEnterpriseId) {
        this.socialEnterpriseId = socialEnterpriseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (socialEnterpriseId != null ? socialEnterpriseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the socialEnterpriseId fields are not set
        if (!(object instanceof SocialEnterprise)) {
            return false;
        }
        SocialEnterprise other = (SocialEnterprise) object;
        if ((this.socialEnterpriseId == null && other.socialEnterpriseId != null) || (this.socialEnterpriseId != null && !this.socialEnterpriseId.equals(other.socialEnterpriseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SocialEnterprise[ id=" + socialEnterpriseId + " ]";
    }
    
}
