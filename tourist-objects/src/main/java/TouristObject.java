import java.util.ArrayList;
import java.util.List;

public class TouristObject {
    protected final List<Review> reviews = new ArrayList<>();
    protected String name;
    protected String country;
    protected String location;
    protected String description;

    public TouristObject(String name, String country, String location, String description) {
        this.name = name;
        this.country = country;
        this.location = location;
        this.description = description;
    }

    public static TouristObject getFromUserInput() {
        return TouristObjectUserInputReader.getTouristObject();
    }

    public static List<TouristObject> fromCSV(String filepath) {
        return TouristObjectCSVManager.read(filepath);
    }

    public static void writeToCSV(List<TouristObject> objects, String filepath) {
        TouristObjectCSVManager.write(objects, filepath);
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public void addReviews(List<Review> reviews) {
        this.reviews.addAll(reviews);
    }

    public List<Review> getAllReviews() {
        return reviews;
    }

    public void removeReview(Review review) {
        reviews.remove(review);
    }

    public void removeAllReviews() {
        reviews.clear();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name + ". " +
                country + ", " + location + ". " +
                description;
    }


}
