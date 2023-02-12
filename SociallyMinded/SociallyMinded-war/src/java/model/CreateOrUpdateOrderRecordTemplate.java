/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.OrderRecord;

/**
 *
 * @author ongyongen
 */
public class CreateOrUpdateOrderRecordTemplate {
    private OrderRecord record;
    private Long productId;
    private Long customerId;

    public CreateOrUpdateOrderRecordTemplate() {
    }

    public CreateOrUpdateOrderRecordTemplate(OrderRecord record, Long productId, Long customerId) {
        this.record = record;
        this.productId = productId;
        this.customerId = customerId;
    }

    public OrderRecord getRecord() {
        return record;
    }

    public void setRecord(OrderRecord record) {
        this.record = record;
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
