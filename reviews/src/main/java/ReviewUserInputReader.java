import java.util.InputMismatchException;
import java.util.Scanner;

class ReviewUserInputReader {

    private static final Scanner scanner = new Scanner(System.in);

    public static Review getReview() {
        String author = getString("\nAuthor: ");
        String content = getString("\nContent: ");
        int rating = getInt("Rating(1-10): ");
        return new Review(
                author, content, rating
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
}