/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Review;

/**
 *
 * @author ongyongen
 */
public class CreateOrUpdateReviewTemplate {
    private Review review;
    private Long productId;
    private Long customerId;

    public CreateOrUpdateReviewTemplate() {
    }

    public CreateOrUpdateReviewTemplate(Review review, Long productId, Long customerId) {
        this.review = review;
        this.productId = productId;
        this.customerId = customerId;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

}
