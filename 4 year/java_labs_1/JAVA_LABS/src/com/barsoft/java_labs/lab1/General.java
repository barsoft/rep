package com.barsoft.java_labs.lab1;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class General {

    private static Scanner newscan;

    public static void main(String[] args) throws InterruptedException {
        double res = 0.0;
        newscan = new Scanner(System.in);
        while (true) {

            String s = newscan.nextLine();

            Pattern bigPattern = Pattern.compile("(\\d+\\.?\\d*)\\s*(.)\\s*(\\d+\\.?\\d*)");
            Matcher bigMatcher = bigPattern.matcher(s);

            Pattern smallPattern = Pattern.compile("\\s*(.)\\s*(\\d+\\.?\\d*)");
            Matcher smallMatcher = smallPattern.matcher(s);

            Pattern quitPattern = Pattern.compile("(quit|exit)");
            Matcher quitMatcher = quitPattern.matcher(s);

            double a = 0;
            double b = 0;

            String sign = null;

            if (bigMatcher.matches()) {
                String dig1 = bigMatcher.group(1);
                sign = bigMatcher.group(2);
                String dig2 = bigMatcher.group(3);
                a = Double.parseDouble(dig1);
                b = Double.parseDouble(dig2);
            } else if (smallMatcher.matches()) {
                sign = smallMatcher.group(1);
                String dig2 = smallMatcher.group(2);
                a = res;
                b = Double.parseDouble(dig2);
            } else if (quitMatcher.matches()) {
                System.out.println("Application terminated");
                System.exit(0);
            }

            if (bigMatcher.matches() || smallMatcher.matches()) {
                switch (sign) {
                    case "+":
                        res = a + b;
                        break;
                    case "-":
                        res = a - b;
                        break;
                    case "*":
                        res = a * b;
                        break;
                    case "/":
                        res = a / b;
                        break;
                    case "^":
                        res = Math.pow(a, b);
                        break;
                }
            }
            System.out.println(res);
        }
    }
}
