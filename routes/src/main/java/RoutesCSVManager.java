import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class RoutesCSVManager {
    public static void write(List<Route> objects, String filePath) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Write header
            writer.writeNext(new String[]{
                    "Name",
                    "Path",
            });

            // Write data
            for (Route obj : objects) {
                String name = obj.getName();
                String path = obj.getPath();

                String[] rowData = new String[]{
                        name, path
                };
                writer.writeNext(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Route> read(String filePath) {
        List<Route> objects = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;

            // Skip the header
            reader.readNext();

            // Read data
            while ((nextLine = reader.readNext()) != null) {
                String name = nextLine[0];
                String path = nextLine[1];
                String[] objectsInRoute = path.split(" - ");
                List<TouristObject> touristObjects = TouristObject.fromCSV(Main.OBJECTS_FILE_NAME);
                Route obj = new Route(name);
                for (String toName : objectsInRoute) {
                    for (TouristObject to : touristObjects) {
                        if (to.getName().equals(toName)) {
                            obj.addTouristObject(to);
                        }
                    }
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
