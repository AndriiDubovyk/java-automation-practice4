import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ReviewCSVManager {
    public static void write(List<Review> objects, String filePath) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Write header
            writer.writeNext(new String[]{"Author", "Content", "Rating"});

            // Write data
            for (Review obj : objects) {
                String[] rowData = new String[]{obj.getAuthor(), obj.getContent(), String.valueOf(obj.getRating())};
                writer.writeNext(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Review> read(String filePath) {
        List<Review> objects = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;

            // Skip the header
            reader.readNext();

            // Read data
            while ((nextLine = reader.readNext()) != null) {
                String author = nextLine[0];
                String content = nextLine[1];
                int rating = Integer.parseInt(nextLine[2]);

                Review obj = new Review(author, content, rating);
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
