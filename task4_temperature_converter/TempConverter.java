import java.util.Scanner;

public class TempConverter {
    static double celsiusToFahrenheit(double c) { return c * 9 / 5 + 32; }
    static double fahrenheitToCelsius(double f) { return (f - 32) * 5 / 9; }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String again;
        do {
            System.out.println("\n===== TEMPERATURE CONVERTER =====");
            System.out.println("1. Celsius to Fahrenheit");
            System.out.println("2. Fahrenheit to Celsius");
            System.out.print("Choose direction (1/2): ");
            String choice = sc.nextLine().trim();

            if (!choice.equals("1") && !choice.equals("2")) {
                System.out.println("Invalid choice.");
            } else {
                System.out.print("Enter temperature: ");
                try {
                    double temp = Double.parseDouble(sc.nextLine().trim());
                    if (choice.equals("1")) {
                        System.out.printf("%.2f C = %.2f F%n", temp, celsiusToFahrenheit(temp));
                    } else {
                        System.out.printf("%.2f F = %.2f C%n", temp, fahrenheitToCelsius(temp));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number. Please enter digits only.");
                }
            }
            System.out.print("Convert another? (y/n): ");
            again = sc.nextLine().trim();
        } while (again.equalsIgnoreCase("y"));
        System.out.println("Goodbye!");
    }
}
