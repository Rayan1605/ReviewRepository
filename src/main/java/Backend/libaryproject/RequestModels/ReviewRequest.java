package Backend.libaryproject.RequestModels;

import lombok.Data;

import java.util.Optional;

@Data
public class ReviewRequest {
private Long bookId;
private double rating;
private Optional<String> reviewDescription; //because this is not mandatory
//We will use Optional since It is often used as a return type for methods that may return null.


}
