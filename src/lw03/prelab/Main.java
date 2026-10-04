package src.lw03.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    static void problem1() {
        System.out.println("===== Problem 1 =====");
        List<String> playlist = new ArrayList<>();

        try (Scanner sc = new Scanner(new File("src/lw03/prelab/playlist.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+", 2);
                String op = parts[0];

                if (op.equals("ADD") && parts.length == 2) {
                    playlist.add(parts[1]);
                } else if (op.equals("INSERT") && parts.length == 2) {
                    String[] ins = parts[1].split("\\s+", 2);
                    if (ins.length == 2) {
                        int index = Integer.parseInt(ins[0]);
                        if (index >= 0 && index <= playlist.size()) {
                            playlist.add(index, ins[1]);
                        }
                    }
                } else if (op.equals("REMOVE") && parts.length == 2) {
                    playlist.remove(parts[1]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File playlist.txt tidak ditemukan.");
            return;
        }

        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    static void problem2() {
        System.out.println("===== Problem 2 =====");
        Set<String> participants = new LinkedHashSet<>(); 
        int duplicates = 0;

        try (Scanner sc = new Scanner(new File("src/lw03/prelab/participants.txt"))) {
            while (sc.hasNextLine()) {
                String name = sc.nextLine().trim();
                if (name.isEmpty()) continue;

                if (!participants.add(name)) {
                    duplicates++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File participants.txt tidak ditemukan.");
            return;
        }

        System.out.println("Unique participants: " + participants.size());
        int no = 1;
        for (String name : participants) {
            System.out.println(no++ + ". " + name);
        }
        System.out.println("Duplicate registrations: " + duplicates);
    }

    static void problem3() {
        System.out.println("===== Problem 3 =====");
        Map<String, Integer> stock = new LinkedHashMap<>();
        int failedSales = 0;

        try (Scanner sc = new Scanner(new File("src/lw03/prelab/inventory.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");
                if (parts.length != 3) continue;

                String type = parts[0];
                String product = parts[1];
                int qty = Integer.parseInt(parts[2]);

                if (type.equals("ADD")) {
                    if (stock.containsKey(product)) {
                        stock.put(product, stock.get(product) + qty);
                    } else {
                        stock.put(product, qty);
                    }
                } else if (type.equals("SELL")) {
                    if (stock.containsKey(product) && stock.get(product) >= qty) {
                        stock.put(product, stock.get(product) - qty);
                    } else {
                        failedSales++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File inventory.txt tidak ditemukan.");
            return;
        }

        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }

    public static void main(String[] args) {
        problem1();
        System.out.println();
        problem2();
        System.out.println();
        problem3();
    }
}