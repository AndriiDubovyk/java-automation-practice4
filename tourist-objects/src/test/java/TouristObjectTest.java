import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TouristObjectTest {

    private final String filename = "test_file.csv";

    @Test
    public void defaultTouristObjectCsvConvertTest() {
        // Write to csv
        TouristObject to = new TouristObject(
                "Central Park",
                "USA",
                "New York",
                "An iconic urban park"
        );
        TouristObject.writeToCSV(List.of(to), filename);

        // Read from csv
        List<TouristObject> fromCsv = TouristObject.fromCSV(filename);
        TouristObject toFromCsv = fromCsv.get(0);

        // Compare result with original object
        assertEquals(to.getName(), toFromCsv.getName());
        assertEquals(to.getCountry(), toFromCsv.getCountry());
        assertEquals(to.getLocation(), toFromCsv.getLocation());
        assertEquals(to.getDescription(), toFromCsv.getDescription());
    }

    @Test
    public void museumCsvConvertTest() {
        // Write to csv
        Museum museum = new Museum(
                "Museum name",
                "Some country",
                "Unknown museum location",
                "Museum description",
                55.5
        );
        TouristObject.writeToCSV(List.of(museum), filename);

        // Read from csv
        List<TouristObject> fromCsv = TouristObject.fromCSV(filename);
        Museum museumFromCsv = (Museum) fromCsv.get(0);

        // Compare result with original object
        assertEquals(museum.getName(), museumFromCsv.getName());
        assertEquals(museum.getCountry(), museumFromCsv.getCountry());
        assertEquals(museum.getLocation(), museumFromCsv.getLocation());
        assertEquals(museum.getDescription(), museumFromCsv.getDescription());
        assertNotEquals(museum.getTicketPrice(), museumFromCsv.getTicketPrice()); // TODO: test
    }

    @Test
    public void parkCsvConvertTest() {
        // Write to csv
        Park park = new Park(
                "Park name",
                "Some country",
                "Unknown location",
                "Park description",
                550,
                true
        );
        TouristObject.writeToCSV(List.of(park), filename);

        // Read from csv
        List<TouristObject> fromCsv = TouristObject.fromCSV(filename);
        Park parkFromCsv = (Park) fromCsv.get(0);

        // Compare result with original object
        assertEquals(park.getName(), parkFromCsv.getName());
        assertEquals(park.getCountry(), parkFromCsv.getCountry());
        assertEquals(park.getLocation(), parkFromCsv.getLocation());
        assertEquals(park.getDescription(), parkFromCsv.getDescription());
        assertEquals(park.getArea(), parkFromCsv.getArea());
        assertEquals(park.isHasLake(), parkFromCsv.isHasLake());
    }

    @Test
    public void restaurantCsvConvertTest() {
        // Write to csv
        Restaurant restaurant = new Restaurant(
                "Restaurant name",
                "Italy",
                "Unknown location",
                "Restaurant description",
                "Italian",
                true
        );
        TouristObject.writeToCSV(List.of(restaurant), filename);

        // Read from csv
        List<TouristObject> fromCsv = TouristObject.fromCSV(filename);
        Restaurant restaurantFromCsv = (Restaurant) fromCsv.get(0);

        // Compare result with original object
        assertEquals(restaurant.getName(), restaurantFromCsv.getName());
        assertEquals(restaurant.getCountry(), restaurantFromCsv.getCountry());
        assertEquals(restaurant.getLocation(), restaurantFromCsv.getLocation());
        assertEquals(restaurant.getDescription(), restaurantFromCsv.getDescription());
        assertEquals(restaurant.isHasOutdoorSeating(), restaurantFromCsv.isHasOutdoorSeating());
    }

    @AfterEach
    public void deleteCsvFile() {
        boolean result = new File(filename).delete();
        System.out.println("File has been deleted: " + result);
    }
}

