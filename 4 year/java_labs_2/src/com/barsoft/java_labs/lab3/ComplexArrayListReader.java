/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs.lab3;

import com.barsoft.java_labs.lab2.Complex;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Maks
 */
public class ComplexArrayListReader {

    public static ArrayList<Complex> readCSV(String path) {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        ArrayList<Complex> arr = null;
        try {
            br = new BufferedReader(new FileReader(path));
            arr = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] country = line.split(cvsSplitBy);
                double x = Integer.parseInt(country[0]);
                double y = Integer.parseInt(country[1]);
                Complex complex = new Complex(x, y);
                arr.add(complex);
            }
            System.out.println("File read succeeded!");
        } catch (FileNotFoundException e) {
            System.out.println("File read error! " + e.getMessage());
        } catch (IOException e) {
            System.out.println("File read error! " + e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return arr;
        }
    }

    public static ArrayList<Complex> readBinary(String path) {
        FileInputStream fis;
        ObjectInputStream oin;
        ArrayList<Complex> arr = null;
        try {
            fis = new FileInputStream(path);
            oin = new ObjectInputStream(fis);
            arr = (ArrayList<Complex>) oin.readObject();
            System.out.println("File read succeeded!");
        } catch (FileNotFoundException e) {
            System.out.println("File not found!" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            // System.out.println("File read error!" + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Fatal error! " + e.getMessage());
        } finally {
            return arr;
        }
    }

    public static ArrayList<Complex> readConsole(Scanner scanner) {
        ArrayList<Complex> arr = new ArrayList<>();
        int arg = 0;
        int i = 0;
        double x = 0, y = 0;
        while (true) {
            try {
                if (arg == 1) {
                    arg = 0;
                    y = scanner.nextDouble();
                    arr.add(new Complex(x, y));
                    i++;
                } else {
                    System.out.println("Input element " + i);
                    x = scanner.nextDouble();
                    arg = 1;
                }
            } catch (NoSuchElementException e) {
                scanner.nextLine();
                break;
            }
        }
        System.out.println("Input succeeded!");
        return arr;
    }
}
