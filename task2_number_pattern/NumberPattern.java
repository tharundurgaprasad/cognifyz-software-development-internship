import java.util.Scanner;

public class NumberPattern {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;

        while (true) {
            System.out.print("Enter number of rows (1 to 7): ");

            if (!sc.hasNextInt()) {
                System.out.println("Error: Enter numbers only!");
                sc.next();   // wrong input ni skip chestundi
            } else {
                n = sc.nextInt();
                if (n >= 1 && n <= 7) {
                    break;   // correct number, loop aagipothundi
                }
                System.out.println("Invalid! Enter a number between 1 and 7.");
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int s = 1; s <= n - i; s++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}