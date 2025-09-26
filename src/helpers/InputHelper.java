package helpers;

import java.util.Scanner;

public class InputHelper {
    private final static Scanner sc = new Scanner(System.in);

    public static int getInt(String message) {
        int number;
        while (true) {
            System.out.print(message);
            try {
                number = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a valid integer.");
            }
        }
        return number;
    }

    public static double getDouble(String message) {
        double d;
        while (true) {
            System.out.print(message);
            try {
                d = Double.parseDouble(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a valid decimal number.");
            }
        }
        return d;
    }

    public static String getString(String message) {
        String input;
        while (true) {
            System.out.print(message);
            input = sc.nextLine().trim();
            if (!input.isEmpty()) break;
            System.out.println("Input cannot be empty.");
        }
        return input;
    }

    public static boolean getBoolean(String message) {
        while (true) {
            System.out.print(message + " (true/false): ");
            String input = sc.nextLine();
            if (input.equals("true")) return true;
            if (input.equals("false")) return false;
            System.out.println("Invalid input. Enter true or false.");
        }
    }

    public static int getUserChoice(String message, int min, int max) {
        int choice;
        while (true) {
            choice = getInt(message);
            if (choice >= min && choice <= max) break;
            System.out.println("Invalid choice. Enter a number between " + min + " and " + max);
        }
        return choice;
    }


}
