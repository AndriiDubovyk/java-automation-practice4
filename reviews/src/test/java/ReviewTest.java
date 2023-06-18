import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewTest {
    private final String filename = "test_file.csv";

    @Test
    public void reviewCsvConvertTest() {
        // Write to csv
        Review review = new Review(
                "John Smith",
                "Very interesting place",
                9
        );
        Review.writeToCSV(List.of(review), filename);

        // Read from csv
        List<Review> fromCsv = Review.fromCSV(filename);
        Review reviewFromCsv = fromCsv.get(0);

        // Compare result with original object
        assertEquals(review.getAuthor(), reviewFromCsv.getAuthor());
        assertEquals(review.getContent(), reviewFromCsv.getContent());
        assertEquals(review.getRating(), reviewFromCsv.getRating());
    }

    @AfterEach
    public void deleteCsvFile() {
        boolean result = new File(filename).delete();
        System.out.println("File has been deleted: " + result);
    }

}
