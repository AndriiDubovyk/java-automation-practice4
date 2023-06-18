import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final String OBJECTS_FILE_NAME = "objects.csv";
    private static final String ROUTES_FILE_NAME = "routes.csv";

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Route> routes = new ArrayList<>();
    private static final List<TouristObject> touristObjects = new ArrayList<>();

    public static void main(String[] args) {
        load();
        System.out.println("Welcome!");
        while (true) {
            showMainMenu();
            int choice = getUserChoiceInt();
            switch (choice) {
                case 1 -> showRoutesMenu();
                case 2 -> showTouristObjectsMenu();
                case 0 -> {
                    save();
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void load() {
        if ((new File(OBJECTS_FILE_NAME).exists())) {
            touristObjects.addAll(TouristObject.fromCSV(OBJECTS_FILE_NAME));
        }
        if ((new File(ROUTES_FILE_NAME).exists())) {
            routes.addAll(Route.fromCSV(ROUTES_FILE_NAME));
        }
        // Left only one instance of every tourist object
        for (Route route: routes) {
            List<TouristObject> routeObjects = new ArrayList<>(route.getAllTouristObjects());
            for (TouristObject toRoute: routeObjects) {
                for (TouristObject to: touristObjects) {
                    if (toRoute.getName().equals(to.getName())) {
                        route.removeTouristObject(toRoute);
                        route.addTouristObject(to);
                        break;
                    }
                }
            }
        }
    }

    private static void save() {
        // delete previous files
        deleteAllCsvFiles();
        TouristObject.writeToCSV(touristObjects, OBJECTS_FILE_NAME);
        Route.writeToCSV(routes, ROUTES_FILE_NAME);
    }

    private static void deleteAllCsvFiles() {
        File[] files = (new File(".").listFiles());
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".csv")) {
                    // Delete the file
                    if (!file.delete()) System.out.println("Can't delete file " + file.getName());
                }
            }
        }
    }

    private static void printSeparator() {
        System.out.println("--------------------------------------------");
    }

    private static void showMainMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Routes");
        System.out.println("2. Tourist objects");
        System.out.println("0. Save and Exit");
    }


    private static void showRoutesMenu() {
        printSeparator();
        showAllRoutes();
        System.out.println("--- Functions");
        System.out.println("-1 | Add new");
        System.out.println("-2 | Remove all");
        System.out.println(" 0 | Back");
        int choice = getUserChoiceInt();
        switch (choice) {
            case -2 -> {
                routes.clear();
                System.out.println("All routes have been deleted");
            }
            case -1 -> addNewRoute();
            case 0 -> {}
            default -> {
                try {
                    showRouteDetails(routes.get(choice - 1));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    private static void showRouteDetails(Route route) {
        int choice = -1;
        while (choice != 0) {
            printSeparator();
            System.out.println(route.toString());
            System.out.println("--- Functions");
            System.out.println("-1 | Add tourist object to this route");
            System.out.println("-2 | Remove tourist object from this route");
            System.out.println("-3 | Remove all tourist object from this");
            System.out.println(" 0 | Back");
            choice = getUserChoiceInt();
            switch (choice) {
                case -1 -> {
                   showAllTouristObjects();
                   int selected = getUserChoiceInt();
                   try {
                      TouristObject to = touristObjects.get(selected - 1);
                      if (!route.contains(to)) {
                          route.addTouristObject(to);
                          System.out.println("Successfully added");
                      } else {
                          System.out.println("This object is already in this route");
                      }
                   } catch (IndexOutOfBoundsException e) {
                       System.out.println("Invalid choice. Please try again.");
                   }
                }
                case -2 -> deleteObjectFromRouteWithUserInput(route);
                case -3 -> {
                    route.removeAllTouristObjects();
                    System.out.println("All object have been removed from this route");
                }
                default -> choice = 0;
            }
        }
    }

    private static void addNewRoute() {
        printSeparator();
        routes.add(Route.getFromUserInput());
        System.out.println("Successfully added");
    }

    private static void showAllTouristObjects() {
        System.out.println("\nTourist objects:");
        for (int i = 0; i < touristObjects.size(); i++) {
            int number = i + 1;
            TouristObject touristObject = touristObjects.get(i);
            System.out.println(number + " | " + touristObject.toString());
        }
    }

    private static void showTouristObjectsMenu() {
        int choice = -1;
        while (choice != 0) {
            printSeparator();
            showAllTouristObjects();
            System.out.println("--- Functions");
            System.out.println("-1 | Add new");
            System.out.println("-2 | Remove all tourist object");
            System.out.println(" 0 | Back");
            choice = getUserChoiceInt();
            switch (choice) {
                case -1 -> addNewTouristObject();
                case -2 -> {
                    touristObjects.clear();
                    System.out.println("All objects have been deleted");
                }
                case 0 -> {}
                default -> {
                    try {
                        showTouristObjectDetails(touristObjects.get(choice - 1));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Invalid choice. Please try again.");
                    }
                }
            }
        }
    }

    private static void showTouristObjectDetails(TouristObject to) {
        int choice = -1;
        while (choice != 0) {
            printSeparator();
            System.out.println(to.toString());
            showAllReviews(to);
            System.out.println("--- Functions");
            System.out.println("-1 | Add review");
            System.out.println("-2 | Remove review");
            System.out.println("-3 | Remove all reviews");
            System.out.println("-4 | Remove this tourist object");
            System.out.println(" 0 | Back");
            choice = getUserChoiceInt();
            switch (choice) {
                case -1 -> {
                    to.addReview(Review.getFromUserInput());
                    System.out.println("Successfully added");
                }
                case -2 -> deleteReviewForObjectWithUserInput(to);
                case -3 -> {
                    to.removeAllReviews();
                    System.out.println("All review have been removed");
                }
                case -4 -> {
                    touristObjects.remove(to);
                    // Also remove from all routes
                    for (Route route : routes) route.removeTouristObject(to);
                    System.out.println("This object has been removed");
                }
                default -> {}
            }
        }
    }


    private static void addNewTouristObject() {
        printSeparator();
        int type = getUserChoiceTouristObjectType();
        TouristObject to = switch (type) {
            case 1 -> Museum.getFromUserInput();
            case 2 -> Restaurant.getFromUserInput();
            case 3 -> Park.getFromUserInput();
            default -> TouristObject.getFromUserInput();
        };
        touristObjects.add(to);
        System.out.println("Successfully added");
    }


    private static void deleteReviewForObjectWithUserInput(TouristObject to) {
        showAllReviews(to);
        int deleteChoice = getUserChoiceInt();
        try {
            to.removeReview(to.getAllReviews().get(deleteChoice - 1));
            System.out.println("Successfully removed");
        } catch (Exception e) {
            System.out.println("Review with this index doesn't exist");
        }
    }


    private static void deleteObjectFromRouteWithUserInput(Route route) {
        showAllObjectsForRoute(route);
        int deleteChoice = getUserChoiceInt();
        try {
            route.removeTouristObject(route.getAllTouristObjects().get(deleteChoice - 1));
            System.out.println("Successfully removed");
        } catch (Exception e) {
            System.out.println("Tourist object with this index doesn't exist for this route");
        }
    }

    private static void showAllReviews(TouristObject to) {
        System.out.println("\nReviews:");
        List<Review> reviews = to.getAllReviews();
        for (int i = 0; i < reviews.size(); i++) {
            int number = i + 1;
            Review review = reviews.get(i);
            System.out.println(number + " | " + review.toString());
        }
    }

    private static void showAllRoutes() {
        System.out.println("\nRoutes:");
        for (int i = 0; i < routes.size(); i++) {
            int number = i + 1;
            Route route = routes.get(i);
            System.out.println(number + " | " + route.toString());
        }
    }

    private static void showAllObjectsForRoute(Route route) {
        System.out.println("\nTourist object for " + route.getName() + ": ");
        List<TouristObject> tos = route.getAllTouristObjects();
        for (int i = 0; i < tos.size(); i++) {
            int number = i + 1;
            TouristObject to = tos.get(i);
            System.out.println(number + " | " + to.toString());
        }
    }


    private static int getUserChoiceTouristObjectType() {
        printSeparator();
        System.out.println("1. Museum");
        System.out.println("2. Restaurant");
        System.out.println("3. Park");
        System.out.println("0. Default");
        return getUserChoiceInt();
    }

    private static int getUserChoiceInt() {
        try {
            System.out.print("\nEnter your choice: ");
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            return 0;
        }
    }

}
