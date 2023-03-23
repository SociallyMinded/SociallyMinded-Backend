/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import enumeration.OrderStatus;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.json.bind.annotation.JsonbTransient;
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
 * @author ongyongen
 */
@Entity
@XmlRootElement
public class OrderRecord implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderRecordId;
    
    @NotNull
    private BigDecimal quantity;
    private BigDecimal totalPrice;
    private OrderStatus orderStatus;
    private String orderTitle;
    
    @Temporal(DATE)
    private Date dateOfOrder;
    
    //@JsonbTransient // resolve circular reference issues in REST APIs
    @ManyToOne
    private Product product;
    
    //@JsonbTransient // resolve circular reference issues in REST APIs
    @ManyToOne
    private Customer customer;

    public OrderRecord() {
    }
    
    // TODO : Delete after testing
    public OrderRecord(BigDecimal quantity) {
        this.quantity = quantity;
    }
    
    public OrderRecord(BigDecimal quantity, BigDecimal totalPrice, String orderTitle) {
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderStatus = OrderStatus.PENDING_APPROVAL;
        this.dateOfOrder = new Date();
        this.orderTitle = orderTitle;
    }
    

    public OrderRecord(BigDecimal quantity, BigDecimal totalPrice, OrderStatus orderStatus, Date dateOfOrder) {
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.dateOfOrder = dateOfOrder;
    }
    
    public Long getOrderRecordId() {
        return orderRecordId;
    }

    public void setOrderRecordId(Long orderRecordId) {
        this.orderRecordId = orderRecordId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }
    
 
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderRecordId != null ? orderRecordId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderRecord)) {
            return false;
        }
        OrderRecord other = (OrderRecord) object;
        if ((this.orderRecordId == null && other.orderRecordId != null) || (this.orderRecordId != null && !this.orderRecordId.equals(other.orderRecordId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Order[ id=" + orderRecordId + " ]";
    }
    
}
