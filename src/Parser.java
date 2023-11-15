import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private final Map<String, Integer> wordsRepeated = new HashMap<>();
    private final File bookPath;
    private final int minWordLength = 3;

    public Parser(File book) {
        this.bookPath = book;
    }

    public Map<String, Integer> repeatedWords() {
        Pattern pattern = Pattern.compile("\\b(?!the\\b)\\w+\\b");
        try (BufferedReader reader = new BufferedReader(new FileReader(bookPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String word = matcher.group().toLowerCase();
                    if (word.length() >= minWordLength) {
                        wordsRepeated.put(word, wordsRepeated.getOrDefault(word, 0) + 1);
                    }
                }
            }
            System.out.println("-File was parsed successfully.");
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
        return wordsRepeated;
    }
}
