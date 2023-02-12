/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ongyongen
 */
@Entity
@XmlRootElement
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;
    private BigDecimal price;
    private String description;
    private String imageLink;
    private BigDecimal ratingScore;
    private BigDecimal numRatings;
    
    @JsonbTransient
    @OneToMany(mappedBy="product")
    private List<Review> reviews; 
    
    @JsonbTransient
    @ManyToOne
    private SocialEnterprise socialenterprise;
    
    @JsonbTransient // resolve circular reference issues in REST APIs
    @OneToMany(mappedBy="product")
    private List<OrderRecord> orders;

    public Product() {
    }
    
    // TODO : delete after testing
    public Product(String name) {
        this.name = name;
    }

    public Product(String name, BigDecimal price, String description, String imageLink) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageLink = imageLink;
        this.ratingScore = new BigDecimal(0);
        this.numRatings = new BigDecimal(0);
    }


    @XmlTransient
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public SocialEnterprise getSocialenterprise() {
        return socialenterprise;
    }

    public void setSocialenterprise(SocialEnterprise socialenterprise) {
        this.socialenterprise = socialenterprise;
    }

    @XmlTransient
    public List<OrderRecord> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderRecord> orders) {
        this.orders = orders;
    }
    
    

    public Number getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(BigDecimal ratingScore) {
        this.ratingScore = ratingScore;
    }

    public Number getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(BigDecimal numRatings) {
        this.numRatings = numRatings;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
    
    
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the productId fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Product[ id=" + productId + " ]";
    }
    
}
