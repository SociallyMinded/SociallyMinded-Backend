/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Review;
import exception.CustomerNotFoundException;
import exception.InputDataValidationException;
import exception.ProductNotFoundException;
import exception.ReviewNotFoundException;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author admin
 */
@Remote
public interface ReviewSessionBeanRemote {

}
