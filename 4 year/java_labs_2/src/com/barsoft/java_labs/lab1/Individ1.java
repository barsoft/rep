package com.barsoft.java_labs.lab1;

import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Individ1 {

    private static Scanner newscan;

    public static void main(String[] args) throws InterruptedException {
        newscan = new Scanner(System.in);

        while (true) {
            String s = newscan.nextLine();

            Pattern intPattern = Pattern.compile("(\\d{3,4}$)");
            Matcher intMatcher = intPattern.matcher(s);

            Pattern quitPattern = Pattern.compile("(quit|exit)");
            Matcher quitMatcher = quitPattern.matcher(s);

            if (intMatcher.matches()) {
                int num = Integer.parseInt(s);

                StringBuilder sb = new StringBuilder(s);

                int i = 0;
                int sum = 0;
                while (true) {
                    sum += Integer.parseInt(String.valueOf(sb.charAt(i)));
                    i++;
                    if (i >= sb.length()) {
                        break;
                    }
                }

                System.out.println("(" + num + "-" + sum + ")/9="
                        + ((num - sum) / 9.0) + " - "
                        + (((num - sum) % 9.0 == 0) ? "true" : "false"));
            } else if (quitMatcher.matches()) {
                System.out.println("Application terminated");
                System.exit(0);
            } else {
                System.out
                        .println("Wrong input format! 3-4 character integer expected.");
            }
        }
    }
}
