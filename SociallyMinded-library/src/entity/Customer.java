/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import enumeration.AccountStatus;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ongyongen
 */
@Entity
@XmlRootElement
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    
    @NotNull
    private String username;
    
    private String password;
    private String address;
    private String email;
    private String firstName;
    private String lastName;
    private String creditCardNos;
    private String creditCardCVV;
    private AccountStatus accountStatus;
    
    @Temporal(DATE)
    private Date dateJoined;
    
    @Temporal(DATE)
    private Date creditCardExpiryDate; 
    
    @JsonbTransient // resolve circular reference issues in REST APIs
    @OneToMany(mappedBy="customer")
    private List<Review> reviews;
    
    @JsonbTransient // resolve circular reference issues in REST APIs
    @OneToMany(mappedBy="customer")
    private List<OrderRecord> orders;
    

    public Customer() {
    }
    
    // TODO : Delete after testing
    public Customer(String name) {
        this.username = name;
    }

    public Customer(String username, String password, String address, String email, String firstName, String lastName, Date dateJoined, String creditCardNos, String creditCardCVV, Date creditCardExpiryDate) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateJoined = dateJoined;
        this.creditCardNos = creditCardNos;
        this.creditCardCVV = creditCardCVV;
        this.creditCardExpiryDate = creditCardExpiryDate;
        this.accountStatus = AccountStatus.ACTIVE;
    }

    public Customer(Long id, String username, String password, String address, String email, String firstName, String lastName, Date dateJoined) {
        this.customerId = id;
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateJoined = dateJoined;
        this.accountStatus = AccountStatus.ACTIVE;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    @XmlTransient
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @XmlTransient
    public List<OrderRecord> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderRecord> orders) {
        this.orders = orders;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getCreditCardNos() {
        return creditCardNos;
    }

    public void setCreditCardNos(String creditCardNos) {
        this.creditCardNos = creditCardNos;
    }

    public String getCreditCardCVV() {
        return creditCardCVV;
    }

    public void setCreditCardCVV(String creditCardCVV) {
        this.creditCardCVV = creditCardCVV;
    }

    public Date getCreditCardExpiryDate() {
        return creditCardExpiryDate;
    }

    public void setCreditCardExpiryDate(Date creditCardExpiryDate) {
        this.creditCardExpiryDate = creditCardExpiryDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerId != null ? customerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the customerId fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.customerId == null && other.customerId != null) || (this.customerId != null && !this.customerId.equals(other.customerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Customer[ id=" + customerId + " ]";
    }
    
}
