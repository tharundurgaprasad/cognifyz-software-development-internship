import java.util.Scanner;

public class TextGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] words = {"python", "coding", "github", "android"};
        String word = words[(int) (Math.random() * words.length)];

        String secret = "";
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (i % 2 == 0) c++;   // +1
            else c--;              // -1
            secret += c;
        }

        System.out.println("=== TextGAME ===");
        System.out.println("Rule: letters are +1, -1, +1, -1 ...");
        System.out.println("Code: " + secret);

        for (int i = 1; i <= 3; i++) {
            System.out.print("Your guess: ");
            String guess = sc.nextLine().toLowerCase();

            if (guess.equals(word)) {
                System.out.println("Correct! You win!");
                return;
            }
            System.out.println("Wrong!");
            if (i == 2) System.out.println("Hint: starts with " + word.charAt(0));
        }
        System.out.println("Game over! The word was: " + word);
    }
}