/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs.lab3;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maks
 */
/*Задано имя файла "name" и целое число N. Написать программу, 
 которая разбивает заданный файл на фрагменты длины не более N байт. 
 Каждый фрагмент сохранять в отдельном файле c именем "name-xx", где хх 
 – номер фрагмента порядку. Вывести имена создаваемых файлов и их размеры  
 на экран и в текстовый файл.*/
public class Individ3 {

    private static Scanner newscan;

    public static void main(String[] args) {
        while (true) {
            try {
                newscan = new Scanner(System.in);

                System.out.println("Input file path:");
                String path = newscan.nextLine();

                File f = new File(path);

                if (!f.exists()) {
                    throw new FileNotFoundException("File not found!");
                }

                System.out.println("Input n:");
                int n = newscan.nextInt();

                System.out.println("Files contains text or binary data?");
                System.out.println("1. Text");
                System.out.println("2. Binary data");
                int dataTyp = newscan.nextInt();

                if (dataTyp == 1) {
                    splitTextFile(f, n);
                    return;
                } else if (dataTyp == 2) {
                    splitFile(f, n);
                    return;
                }

                System.out.println("------------------------------------------------------");
                continue;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void splitTextFile(File f, int n) throws IOException {
        BufferedReader bis = new BufferedReader(
                new FileReader(f));
        FileWriter out;
        String name = getFileNameWithoutExtension(f.getName());
        int partCounter = 1;
        int sizeOfFiles = n;
        char[] buffer = new char[sizeOfFiles];
        int tmp = 0;
        String parentDir = getParentDirectory(f);

        while ((tmp = bis.read(buffer)) > 0) {
            File newFile = new File(parentDir + name + "-" + String.format("%03d", partCounter++) + "." + getFileExtension(f.getName()));
            newFile.createNewFile();
            out = new FileWriter(newFile);
            out.write(buffer, 0, tmp);
            out.close();

            System.out.println(newFile.getPath() + " - " + newFile.length() + " chars");
        }
    }

    private static void splitFile(File f, int n) throws FileNotFoundException, IOException {
        BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(f));
        FileOutputStream out;
        String name = getFileNameWithoutExtension(f.getName());
        int partCounter = 1;
        int sizeOfFiles = n;
        byte[] buffer = new byte[sizeOfFiles];
        int tmp = 0;
        String parentDir = getParentDirectory(f);

        while ((tmp = bis.read(buffer)) > 0) {
            File newFile = new File(parentDir + name + "-" + String.format("%03d", partCounter++) + "." + getFileExtension(f.getName()));
            newFile.createNewFile();
            out = new FileOutputStream(newFile);
            out.write(buffer, 0, tmp);
            out.close();

            System.out.println(newFile.getPath() + " - " + newFile.length() + " bytes");
        }
    }

    public static String getParentDirectory(File f) {
        Path path = Paths.get(f.getAbsolutePath());
        String parentDir = path.getParent().toString() + "\\";
        return parentDir;
    }

    public static String getFileNameWithoutExtension(String name) {
        int pos = name.lastIndexOf(".");
        if (pos > 0) {
            name = name.substring(0, pos);
        }
        return name;
    }

    public static String getFileExtension(String name) {
        String extension = "";

        int i = name.lastIndexOf('.');
        if (i > 0) {
            extension = name.substring(i + 1);
        }
        return extension;
    }
}
