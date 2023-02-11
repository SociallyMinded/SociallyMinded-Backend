/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.OrderRecord;
import enumeration.OrderStatus;
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

    public void deleteOrderRecord(Long oldOrderId);

    public void updateOrderRecord(Long oldOrderId, BigDecimal quantity, BigDecimal totalPrice, OrderStatus orderstatus);

    public List<OrderRecord> retrieveOrderRecordsByCustomerId(Long customerId);

    public OrderRecord retrieveOrderRecordById(Long orderId);

    public List<OrderRecord> retrieveAllOrderRecords();
    
}
