/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs.lab3;

import com.barsoft.java_labs.lab2.Complex;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Maks
 */
public class ComplexArrayListWriter {

    public static boolean writeBinary(ArrayList<Complex> arr, String path) {
        FileOutputStream fos;
        ObjectOutputStream oos;

        File f = new File(path);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("Cannot create file! " + e.getMessage());
                return false;
            }
        }

        try {
            fos = new FileOutputStream(f, false);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(arr);
            System.out.println("Serialization succeeded!");
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File output error! " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println("Cannot write object!" + e.getMessage());
            return false;
        }
    }

    public static void writeConsole(ArrayList<Complex> arr) {
        System.out.println(Arrays.toString(arr.toArray()));
    }
}
