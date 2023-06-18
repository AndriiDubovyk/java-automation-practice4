import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class TouristObjectCSVManager {
    public static void write(List<TouristObject> objects, String filePath) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Write header
            writer.writeNext(new String[]{
                    "Name",
                    "Country",
                    "Location",
                    "Description",
                    "Type", // Default, Museum, Park, Restaurant
                    "Ticket Price", // Museum only,
                    "Area", // Park only
                    "Has Lake", // Park only,
                    "Cuisine", // Restaurant only
                    "Has Outdoor Seating", // Restaurant only
            });

            // Write data
            for (TouristObject obj : objects) {
                String name = obj.getName();
                String country = obj.getCountry();
                String location = obj.getLocation();
                String description = obj.getDescription();
                String type = "Default";
                String ticketPrice = "null";
                String area = "null";
                String hasLake = "null";
                String cuisine = "null";
                String hasOutdoorSeating = "null";
                if (obj instanceof Museum museum) {
                    ticketPrice = String.valueOf(museum.getTicketPrice());
                } else if (obj instanceof Park park) {
                    area = String.valueOf(park.getArea());
                    hasLake = String.valueOf(park.isHasLake());
                } else if (obj instanceof Restaurant restaurant) {
                    cuisine = restaurant.getCuisine();
                    hasOutdoorSeating = String.valueOf(restaurant.isHasOutdoorSeating());
                }

                String reviewsFilePath = name + "_reviews.csv";
                Review.writeToCSV(obj.getAllReviews(), reviewsFilePath);

                String[] rowData = new String[]{
                        name, country, location, description, type, ticketPrice,
                        area, hasLake, cuisine, hasOutdoorSeating,
                };
                writer.writeNext(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<TouristObject> read(String filePath) {
        List<TouristObject> objects = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;

            // Skip the header
            reader.readNext();

            // Read data
            while ((nextLine = reader.readNext()) != null) {
                String name = nextLine[0];
                String country = nextLine[1];
                String location = nextLine[2];
                String description = nextLine[3];
                String type = nextLine[4];
                TouristObject obj = switch (type) {
                    case "Museum" -> new Museum(name, country, location, description, Double.parseDouble(nextLine[5]));
                    case "Park" -> new Park(name, country, location, description, Integer.parseInt(nextLine[6]), Boolean.parseBoolean(nextLine[7]));
                    case "Restaurant" -> new Restaurant(name, country, location, description, nextLine[8], Boolean.parseBoolean(nextLine[9]));
                    default -> new TouristObject(name, country, location, description);
                };
                String reviewsFilePath = name + "_reviews.csv";
                if ((new File(reviewsFilePath)).exists()) {
                    obj.addReviews(Review.fromCSV(reviewsFilePath));
                }
                objects.add(obj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return objects;
    }
}
