package src.lw01.prelab.Unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList <PrintJob> jobs = new ArrayList<>();

        try {
            File file = new File("src/lw01/prelab/Unguided/rentals.txt");
            if (!file.exists()) {
                file = new File("rentals.txt"); 
            }

            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String type = scanner.next();
                String id = scanner.next();
                int days = scanner.nextInt();

                if (type.equalsIgnoreCase("Laptop")) {
                    jobs.add(new LaptopRental(id, days));
                } else if (type.equalsIgnoreCase("Projector")) {
                    jobs.add(new ProjectorRental(id, days));
                }
            }
            //dz
            scanner.close();
    } catch (FileNotFoundException e) {
            System.out.println("Error: File rental.txt tidak ditemukan.");
            return;
    }
    for (PrintJob job : jobs) {
            System.out.println(job.summary());
    }
}
}
