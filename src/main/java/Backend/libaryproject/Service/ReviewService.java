package Backend.libaryproject.Service;

import Backend.libaryproject.Entity.Review;
import Backend.libaryproject.Repository.ReviewRepository;
import Backend.libaryproject.RequestModels.ReviewRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
@AllArgsConstructor
@Transactional //  Commits the transaction after the method completes successfully.
// If an exception is thrown, the transaction will be rolled back.
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public void postReview(String userEmail, ReviewRequest reviewRequest) throws Exception {
        Review Validate = reviewRepository.findByUserEmailAndBookId(userEmail, reviewRequest.getBookId());

        if (Validate != null) throw new Exception("You have already posted a review for this book");
        Review review = new Review();

        review.setBookId(reviewRequest.getBookId());
        review.setRating(reviewRequest.getRating());
        if (reviewRequest.getReviewDescription().isPresent()) { //because remember review description is
            // optional so we first need to check if it is present or null if it is present then we will set the
            // so since reviewRequest is actually  Optional<String>
            // we need to do it differently and use the map function
            // so we will use the map function to convert the Optional<String> to String
            review.setReviewDecription(reviewRequest.getReviewDescription().map(
                    Object::toString).orElse(null));

        }
        review.setDate(Date.valueOf(java.time.LocalDate.now())); //getting the current time
        review.setUserEmail(userEmail);
        reviewRepository.save(review);

    }
    // Going to see if a user already left a review for a book and make it available for react to see

    public Boolean checkIfUserHasAlreadyLeftAReview(String userEmail, Long bookId) {
        Review review = reviewRepository.findByUserEmailAndBookId(userEmail, bookId);
        return review != null;
    }
}
