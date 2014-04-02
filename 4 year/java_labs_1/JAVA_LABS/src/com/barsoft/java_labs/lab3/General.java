/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs.lab3;

import com.barsoft.java_labs.lab2.Complex;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Maks
 */
public class General {

    /**
     * @param args the command line arguments
     */
    private static Scanner newscan;
    private static ArrayList<Complex> array;

    public static void main(String[] args) {

        while (true) {
            System.out.println("Bad boys! What u gonna do?");
            System.out.println("1. Read CSV");
            System.out.println("2. Read file");
            System.out.println("3. Input data");
            System.out.println("4. Write file");
            System.out.println("5. Show data");

            newscan = new Scanner(System.in);

            try {
                int menuItem = newscan.nextInt();
                newscan = new Scanner(System.in);

                switch (menuItem) {
                    case 1: {
                        System.out.println("Input CSV file path:");
                        String path = newscan.nextLine();
                        array = ComplexArrayListReader.readCSV(path);
                        if (array != null) {
                            ComplexArrayListWriter.writeConsole(array);
                        }
                        break;
                    }
                    case 2: {
                        System.out.println("Input file path:");
                        String path = newscan.nextLine();
                        array = ComplexArrayListReader.readBinary(path);
                        if (array != null) {
                            ComplexArrayListWriter.writeConsole(array);
                        }
                        break;
                    }
                    case 3: {
                        System.out.println("Input array of complex values:");
                        array = ComplexArrayListReader.readConsole(newscan);
                        break;
                    }
                    case 4: {
                        System.out.println("Output file path:");
                        String path = newscan.nextLine();
                        if (array != null) {
                            ComplexArrayListWriter.writeBinary(array, path);
                        }
                        break;
                    }
                    case 5: {
                        ComplexArrayListWriter.writeConsole(array);
                        break;
                    }
                    default: {
                        System.exit(0);
                        break;
                    }
                }
            } catch (InputMismatchException ex) {
                System.out.println("Input mismatch! " + ex.getMessage());
            } finally {
                System.out.println("------------------------------------------------------");
                continue;
            }
        }
    }
}
