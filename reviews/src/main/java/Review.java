import java.util.List;

public class Review {
    private String author;
    private String content;
    private int rating; // number from 1 to 10

    public Review(String author, String content, int rating) {
        this.author = author;
        this.content = content;
        setRating(rating);
    }

    public static Review getFromUserInput() {
        return ReviewUserInputReader.getReview();
    }

    public static List<Review> fromCSV(String filepath) {
       return ReviewCSVManager.read(filepath);
    }

    public static void writeToCSV(List<Review> objects, String filepath) {
        ReviewCSVManager.write(objects, filepath);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating < 1) this.rating = 1;
        else this.rating = Math.min(rating, 10);
    }

    @Override
    public String toString() {
        return "Review by " + author +
                ". Rating: " + rating + ". " +
                content;
    }
}