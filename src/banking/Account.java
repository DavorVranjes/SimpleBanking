package banking;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Account {

    private static String cardNumber;
    private static String pin;
    private static List<String> accountList = new ArrayList<>();

    public Account() {
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    public static void menu() {
        bankLogin();

    }

    public static void bankLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");

        int action = scanner.nextInt();

        switch(action) {
            case 1:
                createAccount();
                break;

            case 2:
                logIntoAccount(cardNumber, pin);
                break;
            case 0:
                exitAccount();
                break;
        }

    }

    public static void createAccount() {

        Random random = new Random();

        String bin = "400000";
        long number = random.nextInt(999_999_999 - 100_000_000 + 1) + 100_000_000;
        int pin = new Random().nextInt((9999 - 100) + 1) + 10;
        String cardNumbers = bin + number;

        int sum = 0;
        boolean second = false;

        for (int i = cardNumbers.length() - 1; i >= 0; --i) {
            int digit = Character.getNumericValue(cardNumbers.charAt(i));
            digit = (second = !second) ? (digit * 2) : digit;
            digit = (digit > 9) ? (digit - 9) : digit;
            sum += digit;
        }
        int lastDigit = (sum * 9) % 10;

        cardNumber = cardNumbers + lastDigit;


        System.out.println("Your card has been created");
        System.out.println("Your card number:");
        System.out.println(cardNumber);
        System.out.println("Your card PIN:");
        System.out.println(pin);
        storeCardsAndPins(cardNumber, String.valueOf(pin));
        bankLogin();

    }

    public static void storeCardsAndPins(String cardNumber, String pin) {
        accountList.add(cardNumber);
        accountList.add(pin);
    }

    public static void logIntoAccount(String cardNumber, String pin) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input card number:");
        String card = scanner.next();
        System.out.println("Input PIN:");
        String cardPin = scanner.next();

        for (String number : accountList) {
            if (number.equals(card) && accountList.get(accountList.indexOf(number)+1).equals(cardPin)) {
                System.out.println("You have successfully logged in!");
                logInSuccess();
            } else {
                System.out.println("Wrong card number or PIN!");
                bankLogin();
            }
        }
    }

    public static void logInSuccess() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Balance");
        System.out.println("2. Log out");
        System.out.println("0. Exit");
        int action = scanner.nextInt();

        switch(action) {
            case 1:
                System.out.println("Balance: 0");
                logInSuccess();
                break;
            case 2:
                System.out.println("You have successfully logged out!");
                bankLogin();
                break;
            case 0:
                exitAccount();
                break;

        }
    }
    public static void exitAccount() {
        System.out.println("Bye!");
        System.exit(0);
    }
}