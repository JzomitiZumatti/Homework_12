import java.io.*;
import java.util.*;

public class Statistics {
    private final String bookTitle;
    private String bookStatistic;
    private final int outputWords = 10;

    public Statistics(String bookName) {
        this.bookTitle = bookName;
    }

    public void statisticsRecord(Parser parser) {
        bookStatistic = "src/" + bookTitle + "_statistics.txt";
        List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(parser.repeatedWords().entrySet());
        sortedWords.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(bookStatistic))) {
            int count = 0;
            for (Map.Entry<String, Integer> entry : sortedWords) {
                if (count < outputWords) {
                    writer.write(entry.getKey() + ": " + entry.getValue() + " times");
                    writer.newLine();
                    count++;
                } else {
                    break;
                }
            }
            writer.write("Unique words with 3 or more characters: " + sortedWords.size());
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("-Result was successfully recorded to the " + bookStatistic + ".");
    }

    public void showStatistics() {
        System.out.println("-The most popular words(with 3 or more characters) in the book are:");
        try (BufferedReader reader = new BufferedReader(new FileReader(bookStatistic))) {
            String raw;
            while ((raw = reader.readLine()) != null) {
                System.out.println("\t" + raw);
            }
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
    }
}
