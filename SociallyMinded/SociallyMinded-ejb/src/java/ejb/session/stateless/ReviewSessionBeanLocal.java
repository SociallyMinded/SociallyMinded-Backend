/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Review;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author admin
 */
@Local
public interface ReviewSessionBeanLocal {


    public Review retrieveReviewById(Long reviewId);

    public List<Review> retrieveAllReviews();

    public Long createNewReview(Review review, Long productId, Long customerId);

    public List<Review> retrieveReviewsByCustomerId(Long customerId);

    public List<Review> retrieveReviewsByProductId(Long productId);
    
}
