package src.lw01.prelab.lw02.prelab.Unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        
        LinkedList<String[]> request = new LinkedList<>();

        LinkedList<String[]> memberList = new LinkedList<>();

        Queue<String[]> requestQueue = new LinkedList<>();

        Stack<String[]> failedrequest  = new Stack<>();

        File file = findBorrowingFile();
        if (file == null || !file.exists()) {
            System.out.println("Error: File borrowing.txt tidak ditemukan!");
            System.out.println("Pastikan file borrowing.txt berada di dalam folder: src/lw01/prelab/lw02/prelab/unguided/");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(" ");
                request.add(parts);

                String customerName = parts[0];
                boolean isAlreadyCustomer = false;

                for (String[] cust : memberList) {
                    if (cust[0].equals(customerName)) {
                        isAlreadyCustomer = true;
                        break;
                    }
                }

        
                if (!isAlreadyCustomer) {
                    memberList.add(new String[]{customerName, "0"});
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Gagal membaca file: " + e.getMessage());
            return;
        }

        requestQueue.addAll(request);

        while (!requestQueue.isEmpty()) {
            String[] currentTx = requestQueue.poll(); 
            String name = currentTx[0];
            String type = currentTx[1];
            int amount = Integer.parseInt(currentTx[2]);


            String[] targetCustomer = null;
            for (String[] cust : memberList) {
                if (cust[0].equals(name)) {
                    targetCustomer = cust;
                    break;
                }
            }

            if (targetCustomer != null) {
                int currentStock = Integer.parseInt(targetCustomer[1]);

                if (type.equals("REQUEST DENIED")) {
                    currentStock += amount;
                    targetCustomer[1] = String.valueOf(currentStock);
                } else if (type.equals("REQUEST ACCEPTED")) {
                    if (amount > currentStock) {
                        
                        failedrequest.push(currentTx);
                    } else {
                        currentStock -= amount;
                        targetCustomer[1] = String.valueOf(currentStock);
                    }
                }
            }
        }

        
        System.out.println("=== Successfully Processed Requests ===");
        for (String[] cust : memberList) {
            System.out.println(cust[0] + ": " + cust[1]);
        }

        System.out.println("=== Failed Requests ===");
        while (!failedrequest.isEmpty()) {
            String[] failedTx = failedrequest.pop(); 
            System.out.println(failedTx[0] + " " + failedTx[1] + " " + failedTx[2]);
        }
    }

    
    private static File findBorrowingFile() {
        String[] possiblePaths = {
            "src/lw01/prelab/lw02/prelab/Unguided/borrowing.txt",
            "lw02/prelab/Unguided/borrowing.txt",
            "borrowing.txt"
        };

        for (String path : possiblePaths) {
            File file = new File(path);
            if (file.exists()) {
                return file;
            }
        }
        return null;
    }
}
    
    

    

    