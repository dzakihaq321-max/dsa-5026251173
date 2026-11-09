package lw04.unguided;

import java.io.InputStream;
import java.util.Scanner;

public class Main {
    static void sortCards(int[] arr, int passes) {
        for (int i = 0; i < passes; i++) {
            int idx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[idx]) {
                    idx = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = temp;
        }
}

    static int findCard(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        InputStream in = Main.class.getResourceAsStream("cards.txt");
if (in == null) {
    System.out.println("cards.txt tidak ditemukan");
    return;
}
Scanner scdz = new Scanner(in);

int n = scdz.nextInt();
int[] cards = new int[n];
for (int i = 0; i < n; i++) {
    cards[i] = scdz.nextInt();
}

int m = scdz.nextInt();
int[] requests = new int[m];
for (int i = 0; i < m; i++) {
    requests[i] = scdz.nextInt();
}
scdz.close();

sortCards(cards, 2);

        for (int i = 0; i < n; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(cards[i]);
        }
        System.out.println();

        for (int i = 0; i < m; i++) {
            int idx = findCard(cards, requests[i]);
            if (idx != -1) {
                System.out.println(requests[i] + ": FOUND at index " + idx);
            } else {
                System.out.println(requests[i] + ": NOT FOUND");
            }
        }
    }
}
