package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static StringBuilder UnicSeq(StringBuilder code) {
        int count = 0;
        StringBuilder code2 = new StringBuilder();
        for (int i = 0; i < code.length(); i++) {
            for (int y = i + 1; y < code.length(); y++) {
                if (!(code.charAt(i) == code.charAt(y))) {
                    count++;
                }
            }
            if (count == code.length() - 1 - i) {
                code2.append(code.charAt(i));
            }
            count = 0;
        }

        return code2;
    }

    public static StringBuilder UnicSeq2(StringBuilder code, StringBuilder codeAlpha, int num, int numAlph
    ) {
        int count = 0;
        StringBuilder code2 = new StringBuilder(code);
        for (int i = 0; i < code.length(); i++) {
            for (int y = i + 1; y < code.length(); y++) {
                if ((code.charAt(i) == code.charAt(y))) {
                    System.out.println(codeAlpha.substring(count, count));
                    code2 = code2.replace(i, i + 1, codeAlpha.substring(count, count + 1));
                    count++;
                    break;
                }
            }

        }

        return code2;
    }

    public static void countBullsAndCows(String code, String b, boolean stop) {
        char[] numbers = code.toCharArray();
        char[] numbers2 = b.toCharArray();
        int cows = 0;
        int bulls = 0;
        for (int i = 0; numbers.length > i; i++) {
            for (int y = 0; numbers2.length > y; y++) {
                if (numbers[i] == numbers2[y]) {
                    cows++;
                    if (i == y) {
                        bulls++;
                        cows--;
                    }
                }
            }
        }
        System.out.print("Grade: ");
        if (cows == 0 && bulls == 0) {
            System.out.println("None.");
        }
        if (cows != 0 && bulls == 0) {
            System.out.println(cows + " cow(s). ");
        }
        if (cows == 0 && bulls != 0) {
            System.out.println(bulls + " bull(s).");
        }
        if (cows != 0 && bulls != 0) {
            System.out.println(bulls + " bull(s) and " + cows + " cow(s).");
        }
        if (bulls == b.length()) {
            System.out.println("Congratulations! You guessed the secret code.");
            stop = true;
        }

    }

    public static int checkNumb(String str) {

        try {
            int num = Integer.parseInt(str);
            return num;

        } catch (Exception e) {
            System.out.println("Error" + str + " isn't a valid number");
            System.exit(0);
        }
        return 0;
    }


    public static void main(String[] args) {
        System.out.println("Please, enter the secret code's length:");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int num = checkNumb(str);
        System.out.println("Input the number of possible symbols in the code:");
        String str2 = scanner.nextLine();
        int numPossible = checkNumb(str2);
        boolean stop = false;

        if (num > 37 || numPossible < num || num == 0 || numPossible == 0) {
            System.out.println("Error: can't generate a secret number with a length of " + num +
                    " because there aren't enough unique digits.");
            System.exit(0);
        }
        if (numPossible > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
        } else {

            StringBuilder code1 = new StringBuilder();
            StringBuilder code2 = new StringBuilder();
            StringBuilder codeAlpha = new StringBuilder("abcdefghijklmnopqrstuvwxyz");

            System.out.print("The secret is prepared: ");
            for (int i = 0; i < num; i++) {
                System.out.print("*");
            }
            if (numPossible < 11) {
                int c = numPossible - 1;
                System.out.print(" (0-" + c + ")");
            } else {
                System.out.print(" (0-9, a-" + codeAlpha.charAt(numPossible - 11) + ")");
            }
            System.out.println();
            System.out.println("Okay, let's start a game!");
            //  **** (0-9, a-f)
            Random random = new Random();
            int code = Math.abs(random.nextInt());
            code1.append(code);
            if (numPossible < 11) {
                code2 = UnicSeq(code1);
                code2.delete(num, code2.length());
            } else {
                code2 = UnicSeq2(code1, codeAlpha, num, numPossible);
                code2.delete(num, code2.length());
            }
            String b = new String();
            int turn = 1;
            while (!code2.toString().equals(b)) {
                System.out.println("Turn " + turn);
                Scanner scann = new Scanner(System.in);
                b = scann.nextLine();
                countBullsAndCows(code2.toString(), b, stop);
                turn++;
            }

        }
    }
}
