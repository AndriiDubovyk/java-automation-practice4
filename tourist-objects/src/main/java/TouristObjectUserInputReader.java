import java.util.InputMismatchException;
import java.util.Scanner;

class TouristObjectUserInputReader {

    private static final Scanner scanner = new Scanner(System.in);

    public static TouristObject getTouristObject() {
        String name = getString("\nName: ");
        String country = getString("\nCountry: ");
        String location = getString("\nLocation: ");
        String description = getString("\nDescription: ");
        return new TouristObject(
                name, country, location, description
        );
    }

    public static Museum getMuseum() {
        String name = getString("\nName: ");
        String country = getString("\nCountry: ");
        String location = getString("\nLocation: ");
        String description = getString("\nDescription: ");
        double ticketPrice = getDouble("\nTicket price($): ");
        return new Museum(
                name, country, location, description, ticketPrice
        );
    }

    public static Park getPark() {
        String name = getString("\nName: ");
        String country = getString("\nCountry: ");
        String location = getString("\nLocation: ");
        String description = getString("\nDescription: ");
        int area = getInt("\nArea(m^2): ");
        boolean hasLake = getInt("\nHas lake(0-no, 1-yes): ") == 1;
        return new Park(
                name, country, location, description, area, hasLake
        );
    }

    public static Restaurant getRestaurant() {
        String name = getString("\nName: ");
        String country = getString("\nCountry: ");
        String location = getString("\nLocation: ");
        String description = getString("\nDescription: ");
        String cuisine = getString("\nCuisine: ");
        boolean hasOutdoorSeating = getInt("\nHas outdoor seating(0-no, 1-yes): ") == 1;
        return new Restaurant(
                name, country, location, description, cuisine, hasOutdoorSeating
        );
    }

    private static String getString(String str) {
        System.out.print(str);
        return scanner.nextLine();
    }

    private static int getInt(String str) {
        try {
            System.out.print(str);
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            return 0;
        }
    }

    private static double getDouble(String str) {
        try {
            System.out.print(str);
            return scanner.nextDouble();
        } catch (InputMismatchException e) {
            return 0;
        }
    }
}