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
import javax.ejb.Local;

/**
 *
 * @author admin
 */
@Local
public interface ReviewSessionBeanLocal {

    public Review retrieveReviewById(Long reviewId) throws ReviewNotFoundException;

    public List<Review> retrieveAllReviews();
    
    public List<Review> retrieveReviewsByCustomerId(Long customerId);

    public List<Review> retrieveReviewsByProductId(Long productId);

    public void deleteReview(Long reviewId) throws ReviewNotFoundException;
    
    public Long createNewReview(Review review, Long productId, String customerFirebaseUid) throws ProductNotFoundException, CustomerNotFoundException, InputDataValidationException;

    public void updateReviewDetails(Review newReview, Long productId, String custFirebaseUid) throws InputDataValidationException, ProductNotFoundException, CustomerNotFoundException;

}
