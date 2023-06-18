public class Park extends TouristObject {
    private int area;
    private boolean hasLake;

    public Park(String name, String country, String location, String description, int area, boolean hasLake) {
        super(name, country, location, description);
        this.area = area;
        this.hasLake = hasLake;
    }

    public static Park getFromUserInput() {
        return TouristObjectUserInputReader.getPark();
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public boolean isHasLake() {
        return hasLake;
    }

    public void setHasLake(boolean hasLake) {
        this.hasLake = hasLake;
    }

    @Override
    public String toString() {
        return name + ". " +
                country+ ", " + location +
                ". Area(m^2): " + area +
                ". Has lake: " + (hasLake ? "Yes" : "No") + ". " +
                description;
    }
}