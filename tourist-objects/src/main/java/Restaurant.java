public class Restaurant extends TouristObject {
    private String cuisine;
    private boolean hasOutdoorSeating;

    public Restaurant(String name, String country, String location, String description, String cuisine, boolean hasOutdoorSeating) {
        super(name, country, location, description);
        this.cuisine = cuisine;
        this.hasOutdoorSeating = hasOutdoorSeating;
    }

    public static Restaurant getFromUserInput() {
        return TouristObjectUserInputReader.getRestaurant();
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public boolean isHasOutdoorSeating() {
        return hasOutdoorSeating;
    }

    public void setHasOutdoorSeating(boolean hasOutdoorSeating) {
        this.hasOutdoorSeating = hasOutdoorSeating;
    }

    @Override
    public String toString() {
        return name + ". " +
                country+ ", " + location +
                ". Cuisine: " + cuisine +
                ". Has outdoor seating: " + (hasOutdoorSeating ? "Yes" : "No") + ". " +
                description;
    }
}
