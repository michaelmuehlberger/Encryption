/*
 * Encryption
 * Program working with strings
 * Author: Michael Muehlberger
 * Last Change: 18.12.2022
 */

import java.util.Scanner;

public class Encryption {

    //scanning a positive integer number
    static int scanFunction(Scanner scanner, String purpose) {

        int num = -1;

        while (true) {

            if (purpose.equals("M")) {

                printMenu();
                System.out.print("> ");

            } else {
                System.out.print(purpose);
            }

            if (scanner.hasNextInt()) {

                num = scanner.nextInt();

                if (scanner.nextLine().equals("") && num > 0) {
                    break;
                }
            } else {
                scanner.nextLine();
            }
        }
        return num;
    }

    //prints menu
    static void printMenu() {

        printLine();
        System.out.println("1 - Encrypt text");
        System.out.println("2 - Decrypt text");
        System.out.println("9 - Quit");
        printLine();

    }

    //prints lines
    static void printLine() {

        for (int i = 1; i <= 80; i++) {

            if (i < 80) {
                System.out.print("-");
            } else {
                System.out.println("-");
            }
        }
    }

    //encrypts and prints encrypted text
    static void encryptText(String text, int rotation) {

        int spanA = ('Z' - 'A') + 1; //range A to Z but we want range A to A
        int spanN = 10;
        int division;

        int length = text.length();
        char pointChar;
        char cryptChar;

        System.out.print("  Encrypted text: ");

        //if for efficiency only
        if (rotation > 26) {
            division = rotation / 26; //integer divison, remainder is gone
            rotation = rotation - division * 26;
        }

        for (int i = 0; i < length; i++) {
            pointChar = text.charAt(i);
            cryptChar = (char) (pointChar + rotation);

            if (pointChar >= 'A' && pointChar <= 'Z') {
                while (cryptChar > 'Z') {
                    cryptChar = (char) (cryptChar - spanA);
                }
                System.out.print(cryptChar);

            } else if (pointChar >= 'a' && pointChar <= 'z') {
                while (cryptChar > 'z') {
                    cryptChar = (char) (cryptChar - spanA);
                }
                System.out.print(cryptChar);

            } else if (pointChar >= '0' && pointChar <= '9') {
                while (cryptChar > '9') {
                    cryptChar = (char) (cryptChar - spanN);
                }
                System.out.print(cryptChar);

            } else {
                System.out.print(pointChar);
            }
        }

        System.out.println("");

    }

    //decrypts and prints decrypted text
    static void decryptText(String text, int rotation) {

        char pointChar;
        char cryptChar;

        int division;

        int spanA = ('Z' - 'A') + 1;
        int spanN = 10;

        int length = text.length();

        System.out.print("  Decrypted text: ");

        //if for efficiency only
        if (rotation > 26) {
            division = rotation / 26; //integer divison, remainder is gone
            rotation = rotation - division * 26;
        }

        for (int i = 0; i < length; i++) {
            pointChar = text.charAt(i);
            cryptChar = (char) (pointChar - rotation);

            if (pointChar >= 'A' && pointChar <= 'Z') {
                while (cryptChar < 'A') {
                    cryptChar = (char) (cryptChar + spanA);
                }
                System.out.print(cryptChar);

            } else if (pointChar >= 'a' && pointChar <= 'z') {
                while (cryptChar < 'a') {
                    cryptChar = (char) (cryptChar + spanA);
                }
                System.out.print(cryptChar);

            } else if (pointChar >= '0' && pointChar <= '9') {
                while (cryptChar < '0') {
                    cryptChar = (char) (cryptChar + spanN);
                }
                System.out.print(cryptChar);

            } else {
                System.out.print(pointChar);
            }
        }

        System.out.println("");

    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int inputMenu;
        String inputText;
        int rotation;

        while (true) {

            String rotString = "  Enter rotation: ";
            inputMenu = scanFunction(scanner, "M");

            if (inputMenu == 1) {

                rotation = scanFunction(scanner, rotString);

                System.out.print("  Enter text to encrypt: ");
                inputText = scanner.nextLine();

                encryptText(inputText, rotation);

            }

            if (inputMenu == 2) {

                rotation = scanFunction(scanner, rotString);

                System.out.print("  Enter text to decrypt: ");
                inputText = scanner.nextLine();

                decryptText(inputText, rotation);

            }

            if (inputMenu == 9) {
                break;
            }
        }
    }
}