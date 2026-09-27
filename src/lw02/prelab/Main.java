package src.lw01.prelab.lw02.prelab;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        
        LinkedList<String[]> transactionList = new LinkedList<>();

        LinkedList<String[]> customerList = new LinkedList<>();

        Queue<String[]> transactionQueue = new LinkedList<>();

        Stack<String[]> failedTransactions = new Stack<>();

        File file = findTransactionsFile();
        if (file == null || !file.exists()) {
            System.out.println("Error: File transactions.txt tidak ditemukan!");
            System.out.println("Pastikan file transactions.txt berada di dalam folder: src/lw01/prelab/lw02/prelab/");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(" ");
                transactionList.add(parts);

                String customerName = parts[0];
                boolean isAlreadyCustomer = false;

                for (String[] cust : customerList) {
                    if (cust[0].equals(customerName)) {
                        isAlreadyCustomer = true;
                        break;
                    }
                }

        
                if (!isAlreadyCustomer) {
                    customerList.add(new String[]{customerName, "0"});
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Gagal membaca file: " + e.getMessage());
            return;
        }

        transactionQueue.addAll(transactionList);

        while (!transactionQueue.isEmpty()) {
            String[] currentTx = transactionQueue.poll(); 
            String name = currentTx[0];
            String type = currentTx[1];
            int amount = Integer.parseInt(currentTx[2]);


            String[] targetCustomer = null;
            for (String[] cust : customerList) {
                if (cust[0].equals(name)) {
                    targetCustomer = cust;
                    break;
                }
            }

            if (targetCustomer != null) {
                int currentBalance = Integer.parseInt(targetCustomer[1]);

                if (type.equals("DEPOSIT")) {
                    currentBalance += amount;
                    targetCustomer[1] = String.valueOf(currentBalance);
                } else if (type.equals("WITHDRAW ")) {
                    if (amount > currentBalance) {
                        
                        failedTransactions.push(currentTx);
                    } else {
                        currentBalance -= amount;
                        targetCustomer[1] = String.valueOf(currentBalance);
                    }
                }
            }
        }

        
        System.out.println("=== Final Balances ===");
        for (String[] cust : customerList) {
            System.out.println(cust[0] + ": " + cust[1]);
        }

        System.out.println("=== Failed Transactions ===");
        while (!failedTransactions.isEmpty()) {
            String[] failedTx = failedTransactions.pop(); 
            System.out.println(failedTx[0] + " " + failedTx[1] + " " + failedTx[2]);
        }
    }

    
    private static File findTransactionsFile() {
        String[] possiblePaths = {
            "src/lw01/prelab/lw02/prelab/transactions.txt",
            "lw02/prelab/transactions.txt",
            "transactions.txt"
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