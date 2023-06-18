import java.util.Scanner;

class RouteUserInputReader {

    private static final Scanner scanner = new Scanner(System.in);

    public static Route getRoute() {
        String name = getString("\nName: ");
        return new Route(name);
    }

    private static String getString(String str) {
        System.out.print(str);
        return scanner.nextLine();
    }

}
