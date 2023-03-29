/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Product;


/**
 *
 * @author ongyongen
 */
public class CreateOrUpdateProductTemplate {
    private Product product;
    private Long socialEnterpriseId;

    public CreateOrUpdateProductTemplate() {
    }

    public CreateOrUpdateProductTemplate(Product product, Long socialEnterpriseId) {
        this.product = product;
        this.socialEnterpriseId = socialEnterpriseId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getSocialEnterpriseId() {
        return socialEnterpriseId;
    }

    public void setSocialEnterpriseId(Long socialEnterpriseId) {
        this.socialEnterpriseId = socialEnterpriseId;
    }

}
