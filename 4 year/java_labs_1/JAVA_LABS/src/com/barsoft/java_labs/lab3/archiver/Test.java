/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs.lab3.archiver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author Maks
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    /*В Java для чтения и записи zip-архивов применяются классы ZipInputStream 
     и ZipOutputStream. Используя эти классы, напишите программу, которая 
     позволяет архивировать и распаковывать  набор файлов или директорий. 
     Входные данные программа должна получать в виде параметров командной 
     строки.*/
    private static Scanner newscan;
    private static ArrayList<File> files;

    public static void main(String[] args) {
        files = new ArrayList<>();
        while (true) {
            System.out.println("Bad boys! What u gonna do?");
            System.out.println("1. Select file");
            System.out.println("2. Remove selected file");
            System.out.println("3. Show selected files");
            System.out.println("4. Archive selected files");
            System.out.println("5. Unarchive file");

            newscan = new Scanner(System.in);

            try {
                int menuItem = newscan.nextInt();
                newscan = new Scanner(System.in);

                switch (menuItem) {
                    case 1: {
                        System.out.println("Input file path:");
                        String path = newscan.nextLine();

                        File f = new File(path);
                        if (!f.exists()) {
                            throw new FileNotFoundException();
                        }
                        files.add(f);
                        System.out.println("File select succeeded!");
                        break;
                    }
                    case 2: {
                        showFilesList();
                        System.out.println("Input file number:");
                        int num = newscan.nextInt();
                        files.remove(num);
                        System.out.println("File removed!");
                        break;
                    }
                    case 3: {
                        showFilesList();
                        break;
                    }
                    case 4: {
                        System.out.println("Output zip archive path:");
                        String outputArchivePath = newscan.nextLine();
                        archiveFilesList(outputArchivePath);

                        System.out.println("Files archived!");
                        break;
                    }
                    case 5: {
                        System.out.println("Input zip archive path:");
                        String inputArchivePath = newscan.nextLine();

                        File f = new File(inputArchivePath);
                        if (!f.exists() || !f.isFile()) {
                            throw new FileNotFoundException("Input zip archive does not exist!");
                        }

                        System.out.println("Output files directory:");
                        String outputPath = newscan.nextLine();

                        f = new File(outputPath);
                        if (!f.exists() || f.isFile()) {
                            throw new FileNotFoundException("Output folder does not exist!");
                        }

                        unarchiveFile(inputArchivePath, outputPath);

                        System.out.println("Files unarchived!");
                        break;
                    }
                    default: {
                        System.exit(0);
                        break;
                    }
                }
            } catch (InputMismatchException ex) {
                System.out.println("Input mismatch! " + ex.getMessage());
            } catch (FileNotFoundException ex) {
                System.out.println("File not found! " + ex.getMessage());
            } finally {
                System.out.println("------------------------------------------------------");
                continue;
            }
        }
    }

    private static void showFilesList() throws IOException {
        int i = 0;
        System.out.println("Files:");
        for (File f : files) {
            System.out.println(i + ". " + f.getCanonicalPath());
            i++;
        }
    }

    private static void archiveFilesList(String outputArchivePath) throws IOException {
        File dfile = new File(outputArchivePath);

        byte[] buffer = new byte[1024];

        FileOutputStream fos = new FileOutputStream(dfile);

        ZipOutputStream zos = new ZipOutputStream(fos);

        for (File f : files) {
            FileInputStream fis = new FileInputStream(f);
            ZipEntry ze = new ZipEntry(f.getName());
            zos.putNextEntry(ze);
            int length;

            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }
            zos.closeEntry();
            fis.close();
        }

        zos.close();
    }

    private static void unarchiveFile(String inputArchivePath, String outputPath) throws IOException {
        InputStream is = new FileInputStream(inputArchivePath);
        //zip is read by ZipInputStream
        ZipInputStream zis = new ZipInputStream(is);
        // now write zip file in extracted file
        ZipEntry ze;
        byte[] buff = new byte[1024];
        while ((ze = zis.getNextEntry()) != null) {
            // get file name
            FileOutputStream fos = new FileOutputStream(outputPath + "\\" + ze.getName());
            int l = 0;
            // write buffer to file
            while ((l = zis.read(buff)) > 0) {
                fos.write(buff, 0, l);
            }
        }
        zis.close();
    }
}
