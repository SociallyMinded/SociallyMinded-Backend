/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.OrderRecord;
import enumeration.OrderStatus;
import exception.CustomerNotFoundException;
import exception.InputDataValidationException;
import exception.OrderRecordNotFoundException;
import exception.ProductNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author admin
 */
@Local
public interface OrderRecordSessionBeanLocal {

    public List<OrderRecord> retrieveOrderRecordsByProductId(Long productId);

    public void deleteOrderRecord(Long oldOrderId) throws OrderRecordNotFoundException;

    public void updateOrderRecordDetails(OrderRecord newOrderRecord, Long productId, Long customerId) throws ProductNotFoundException, CustomerNotFoundException, InputDataValidationException;

    public List<OrderRecord> retrieveOrderRecordsByCustomerId(Long customerId);

    public OrderRecord retrieveOrderRecordById(Long orderId) throws OrderRecordNotFoundException;

    public List<OrderRecord> retrieveAllOrderRecords();

    public Long createNewOrderRecord(OrderRecord order, Long productId, Long customerId) throws ProductNotFoundException, CustomerNotFoundException, InputDataValidationException;    
}


