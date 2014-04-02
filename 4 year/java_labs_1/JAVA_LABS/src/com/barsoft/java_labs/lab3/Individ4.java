/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs.lab3;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Maks
 */
/*(Для программистов) Написать программу, которая по заданному имени файла-фрагмента 
 name-xx" ищет остальные фрагменты и объединяет их в один файл с именем "name". 
 Если какой-то из фрагментов отсутствует, выдавать сообщение об ошибке.*/
public class Individ4 {

    private static Scanner newscan;

    public static void main(String[] args) {
        while (true) {
            newscan = new Scanner(System.in);

            final Pattern partPattern = Pattern.compile("(.+)-([0-9]{3})");

            System.out.println("Input file part path:");
            String path = newscan.nextLine();

            final File partFile = new File(path);

            try {
                if (!partFile.exists()) {
                    throw new FileNotFoundException("File not found!");
                }
                final Matcher partPatternMatcher = partPattern.matcher(Individ3.getFileNameWithoutExtension(partFile.getName()));

                if (!partPatternMatcher.matches()) {
                    throw new IllegalArgumentException("Pattern mismatch!");
                }

                final String sourceFileName = partPatternMatcher.group(1);
                //Getting file parts////////////////////////////////////////////////////
                File curDir = new File(Individ3.getParentDirectory(partFile));
                FilenameFilter textFilter = new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        String lowercaseName = name.toLowerCase();
                        Matcher m = partPattern.matcher(Individ3.getFileNameWithoutExtension(lowercaseName));
                        if (Individ3.getFileExtension(lowercaseName).equals(Individ3.getFileExtension(partFile.getName()))
                                && m.matches()
                                && (m.group(1) == null ? sourceFileName == null : m.group(1).equals(sourceFileName))) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                };
                File[] files = curDir.listFiles(textFilter);
                ////////////////////////////////////////////////////////////////////////
                //Getting number of parts///////////////////////////////////////////////
                int maxNum = 0;

                for (File file : files) {
                    Matcher m = partPattern.matcher(Individ3.getFileNameWithoutExtension(file.getName()));
                    if (m.matches()) {
                        int num = Integer.parseInt(m.group(2));
                        if (num > maxNum) {
                            maxNum = num;
                        }
                    }
                }
                ////////////////////////////////////////////////////////////////////////
                //Identifying missing parts/////////////////////////////////////////////
                String ext = Individ3.getFileExtension(partFile.getName());
                for (int i = 1; i <= maxNum; i++) {
                    String s = sourceFileName + "-" + String.format("%03d", i) + "." + ext;
                    File res = findFile(files, s);
                    try {
                        System.out.println(res.getCanonicalPath());
                    } catch (NullPointerException | IOException ex) {
                        throw new FileNotFoundException("File part not found! " + s);
                    }
                }
                ////////////////////////////////////////////////////////////////////////
                //Join parts////////////////////////////////////////////////////////////
                System.out.println("Output file path:");
                String outputPath = newscan.nextLine();

                System.out.println("Save file as text or binary data?");
                System.out.println("1. Text");
                System.out.println("2. Binary data");
                int dataType = newscan.nextInt();

                File outputFile = new File(outputPath);

                if (dataType == 1) {
                    joinTextFiles(outputFile, files);
                    System.out.println("Join succeeded");
                    return;
                } else if (dataType == 2) {
                    joinFiles(outputFile, files);
                    System.out.println("Join succeeded");
                    return;
                }
                ////////////////////////////////////////////////////////////////////////
                return;
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.println("------------------------------------------------------");
            continue;
        }
    }

    public static File findFile(File[] files, String name) {
        for (File file : files) {
            if (file.getName() == null ? name == null : file.getName().equals(name)) {
                return file;
            }
        }
        return null;
    }

    private static void joinFiles(File destination, File[] sources) throws IOException {
        PrintWriter writer = new PrintWriter(destination);
        writer.print("");
        writer.close();
        for (File source : sources) {
            appendFile(destination, source);
        }
    }

    public static void joinTextFiles(File destination, File[] sources)
            throws IOException {
        PrintWriter writer = new PrintWriter(destination);
        writer.print("");
        writer.close();
        for (File source : sources) {
            appendTextFile(destination, source);
        }
    }

    private static void appendTextFile(File output, File source)
            throws IOException {
        String sourceText;
        BufferedReader br = new BufferedReader(new FileReader(source));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append('\n');
                line = br.readLine();
            }
            sourceText = sb.toString();
        } finally {
            br.close();
        }

        FileWriter fw = new FileWriter(output, true);
        fw.append(sourceText);
        fw.close();
    }

    private static void appendFile(File output, File source) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(source));
        FileOutputStream out;

        int length = (int) (source.length());

        System.out.print("length - " + source.length() + "   " + output.length());

        byte[] buffer = new byte[length];

        int tmp = bis.read(buffer);

        out = new FileOutputStream(output, true);
        out.write(buffer);
        out.close();
    }
}
