package src.SetsnMaps;

import java.io.*;
import java.util.*;

public class removeDuplicates {
     public static void main(List<Integer> list) {
        Set<Integer> seen = new HashSet<>();
        Iterator<Integer> it = list.iterator();

        while (it.hasNext()) {
            Integer n = it.next();
            if (seen.contains(n)) {
                it.remove();     
            } else {
                seen.add(n);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("src/SetsnMaps/duplicate.txt"));
        List<Integer> list = new ArrayList<>();

        while (input.hasNextInt()) {
            list.add(input.nextInt());
        }
        input.close();

        main(list);
        System.out.println(list);
    }
}
