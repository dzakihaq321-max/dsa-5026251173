package src.lw03.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    static void Enrollment() {
        System.out.println("===== Enrollment Checks =====");
        List<String> register = new ArrayList<>();

        try (Scanner sc = new Scanner(new File("src/lw03/unguided/enrollment.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+", 2);
                String op = parts[0];

                if (op.equals("REGISTER")&& parts.length == 2) {
                    register.add(parts[1]);

    }else if (op.equals("WITHDRAW") && parts.length == 2) {
                    String[] ins = parts[1].split("\\s+", 2);
                    if (ins.length == 2) {
                        int index = Integer.parseInt(ins[0]);
                        if (index >= 0 && index <= register.size()) {
                            register.add(index, ins[1]);
                        }
                    }
                }else if (op.equals("CHECK") && parts.length == 2) {
                    register.remove(parts[1]);
            }
        }
    }catch (FileNotFoundException e) {
            System.out.println("File Enrollment.txt tidak ditemukan.");
            return;
    }
}

    static void fenrollment() {
        System.out.println("===== Final Enrollment =====");
}
    public static void main(String[] args) {
        Enrollment();
        System.out.println();
        fenrollment();
        System.out.println();
        System.out.println("Rejected Operations: " + "3");
}
        

}