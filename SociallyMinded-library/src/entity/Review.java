/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@XmlRootElement
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    
    @Column(columnDefinition="varchar(1000)")
    private String reviewDescription;
    
    private Integer rating;
    
    private boolean isAnonymous;
    
    private String[] reviewImages;
    
    @Temporal(DATE)
    private Date dateOfReview;
    
//    @JsonbTransient // resolve circular reference issues in REST APIs
    @ManyToOne
    private Customer customer;
    
    @JsonbTransient // resolve circular reference issues in REST APIs
    @ManyToOne
    private Product product;

    public Review() {
    }
    
    // TODO : delete after testing
//    public Review(String description) {
//        this.reviewDescription = description;
//        this.dateOfReview = new Date();
//
//    }

    public Review(String reviewDescription, Integer rating, boolean isAnonymous, String[] reviewImages) {
        this.reviewDescription = reviewDescription;
        this.rating = rating;
        this.isAnonymous = isAnonymous;
        this.reviewImages = reviewImages;
        this.dateOfReview = new Date();
    }

    public Review(String reviewDescription, Integer rating, boolean isAnonymous, String[] reviewImages, Date dateOfReview) {
        this.reviewDescription = reviewDescription;
        this.rating = rating;
        this.isAnonymous = isAnonymous;
        this.reviewImages = reviewImages;
        this.dateOfReview = dateOfReview;
    }
    
    
    
//    
//
//    public Review(String reviewDescription, Date dateOfReview) {
//        this.reviewDescription = reviewDescription;
//        this.dateOfReview = dateOfReview;
//    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    
    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public Date getDateOfReview() {
        return dateOfReview;
    }

    public void setDateOfReview(Date dateOfReview) {
        this.dateOfReview = dateOfReview;
    }

    /**
     * @return the rating
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /**
     * @return the isAnonymous
     */
    public boolean isIsAnonymous() {
        return isAnonymous;
    }

    /**
     * @param isAnonymous the isAnonymous to set
     */
    public void setIsAnonymous(boolean isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    /**
     * @return the reviewImages
     */
    public String[] getReviewImages() {
        return reviewImages;
    }

    /**
     * @param reviewImages the reviewImages to set
     */
    public void setReviewImages(String[] reviewImages) {
        this.reviewImages = reviewImages;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reviewId != null ? reviewId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Review)) {
            return false;
        }
        Review other = (Review) object;
        if ((this.reviewId == null && other.reviewId != null) || (this.reviewId != null && !this.reviewId.equals(other.reviewId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Review[ id=" + reviewId + " ]";
    }
    
}
