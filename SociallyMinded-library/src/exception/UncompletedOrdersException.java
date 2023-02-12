/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author ongyongen
 */
public class UncompletedOrdersException extends Exception {
    public UncompletedOrdersException() {
    }

    public UncompletedOrdersException(String msg) {
        super(msg);
    }
}
