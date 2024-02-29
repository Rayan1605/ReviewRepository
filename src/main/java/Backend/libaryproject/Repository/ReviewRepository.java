package Backend.libaryproject.Repository;

import Backend.libaryproject.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {


Review findByUserEmailAndBookId(String userEmail, Long BookId);
@Modifying
@Query("delete from Review where bookId = :book_id")
void deleteAllByBookId(@Param("book_id") Long bookId);

}
