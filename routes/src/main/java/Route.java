import java.util.ArrayList;
import java.util.List;

public class Route {
    private final List<TouristObject> touristObjects;
    private String name;

    public Route(String name) {
        this.name = name;
        this.touristObjects = new ArrayList<>();
    }

    public static Route getFromUserInput() {
        return RouteUserInputReader.getRoute();
    }

    public static List<Route> fromCSV(String filepath) {
        return RoutesCSVManager.read(filepath);
    }

    public static void writeToCSV(List<Route> objects, String filepath) {
        RoutesCSVManager.write(objects, filepath);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTouristObject(TouristObject touristObject) {
        touristObjects.add(touristObject);
    }

    public void removeTouristObject(TouristObject touristObject) {
        touristObjects.remove(touristObject);
    }

    public List<TouristObject> getAllTouristObjects() {
        return touristObjects;
    }

    public Boolean contains (TouristObject touristObject) {
        return touristObjects.contains(touristObject);
    }

    public void removeAllTouristObjects() {
        touristObjects.clear();
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder(name);
        strBuilder.append(": ");
        if (touristObjects.isEmpty()) {
            strBuilder.append("empty");
        } else {
            strBuilder.append(getPath());
        }
        strBuilder.append(".");
        return strBuilder.toString();
    }

    public String getPath() {
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < touristObjects.size(); i++) {
            TouristObject to = touristObjects.get(i);
            strBuilder.append(to.getName());
            if (i < touristObjects.size() - 1) {
                strBuilder.append(" - ");
            }
        }
        return strBuilder.toString();
    }
}