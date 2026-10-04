import java.io.*;
import java.util.*;

public class wordCounter {

    public static void main(List<String> lines) {
        Map<String, Integer> freq = new LinkedHashMap<>();

        for (String line : lines) {
            String[] words = line.trim().split("\\s+");
            for (String w : words) {
                if (w.isEmpty()) continue;
                freq.put(w, freq.getOrDefault(w, 0) + 1);
            }
        }

        System.out.println("Frequency of:");
        for (Map.Entry<String, Integer> e : freq.entrySet()) {
            System.out.printf("%-10s : %d%n", e.getKey(), e.getValue());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("fruits.txt"));
        List<String> lines = new ArrayList<>();

        while (input.hasNextLine()) {
            lines.add(input.nextLine());
        }
        input.close();

        main(lines);
    }
}