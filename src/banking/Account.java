package banking;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Account {

    private static String cardNumber;
    private static String pin;
    private static List<String> accountList = new ArrayList<>();

    public Account(String cardNumber, String pin) {
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

        String bin = "400000";
        long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        int pin = new Random().nextInt((9999 - 100) + 1) + 10;
        String cardNumber = bin + String.valueOf(number);
        Account mainObject = new Account(cardNumber,String.valueOf(pin));
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

    static boolean checkLuhn(String cardNumber) {
        int numberDigits = cardNumber.length();

        int numberSum = 0;
        boolean isSecond = false;
        for (int i = numberDigits - 1; i >= 0; i--) {

            int d = cardNumber.charAt(i) - '0';

            if (isSecond == true)
                d = d * 2;

            numberSum += d / 10;
            numberSum += d % 10;

            isSecond = !isSecond;
        }
        return (numberSum % 10 == 0);
    }



    public static void exitAccount() {
        System.out.println("Bye!");
        System.exit(0);
    }
}