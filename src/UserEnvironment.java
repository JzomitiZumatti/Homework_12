import java.io.File;
import java.util.Scanner;

public class UserEnvironment {
    Scanner scanner = new Scanner(System.in);
    private final String directory = "src";
    private String bookName;
    private File book;
    private Parser parser;
    private Statistics statistics;

    private void checkBookExistence() {
        System.out.println("Please enter the book name(register doesn't matter):");
        bookName = scanner.nextLine().trim();
        book = new File(directory, bookName + ".txt");
        while (!book.exists()) {
            System.out.println("The book \"" + bookName + "\" not found in directory /" + directory + "." +
                    "Repeat your search input:");
            bookName = scanner.nextLine().trim();
            book = new File(directory, bookName + ".txt");
        }
        System.out.println("-The book \"" + bookName + "\" finds in directory /" + directory + ".");
    }

    public void dialogMenu() {
        boolean isOn = true;
        while (isOn) {
            System.out.println("""
                    Choose the action you want to do:
                        1. Check the book existence;
                          -Parse the book;
                          -Record statistics in the file;
                          -Show statistics;
                        0. EXIT.
                    """);
            System.out.print("> ");
            String action = scanner.nextLine();
            switch (action) {
                case "1":
                    checkBookExistence();
                    parser = new Parser(book);
                    statistics = new Statistics(bookName);
                    statistics.statisticsRecord(parser);
                    statistics.showStatistics();
                    System.out.println();
                    break;
                case "0":
                    isOn = false;
                    System.out.println("See you later!");
                    break;
                default:
                    System.out.println("You chose the wrong option! Try again.\n");
                    break;
            }
        }
    }

}
