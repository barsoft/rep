/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modlule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 *
 * @author Maks
 */
public class Modlule {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        int n = 0, m = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.println("Input n:");
                n = Integer.parseInt(br.readLine());
                break;
            } catch (Exception e) {
                System.out.println("Format error!" + e.getLocalizedMessage());
            }
        }
        while (true) {
            try {
                System.out.println("Input m:");
                m = Integer.parseInt(br.readLine());
                break;
            } catch (Exception e) {
                System.out.println("Format error!" + e.getLocalizedMessage());
            }
        }

        Random r = new Random();
        int array[][] = new int[n][m];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = r.nextInt(20) + 9;
            }
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.print("\n");
        }

        System.out.print("\n");

        for (int j = 0; j < array.length; j++) {
            System.out.print(max(array, j) + " ");
        }

        System.out.print("\n");
        System.out.print("\n");

        bubblesort(array);

        for (int j = 0; j < array.length; j++) {
            System.out.print(max(array, j) + " ");
        }

        System.out.print("\n");
        System.out.print("\n");
        
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static int max(int[][] arr, int j) {
        int max = 0;
        for (int i = 0; i < arr[j].length; i++) {
            if (arr[i][j] > max) {
                max = arr[i][j];
            }
        }
        return max;
    }

    public static void swap(int[][] arr, int i1, int i2) {
        for (int i = 0; i < arr.length; i++) {
            int t = arr[i][i1];
            arr[i][i1] = arr[i][i2];
            arr[i][i2] = t;
        }
    }

    public static void bubblesort(int[][] arr) {
        for (int i1 = arr.length - 1; i1 > 0; i1--) {
            for (int i2 = 0; i2 < i1; i2++) {
                if (max(arr, i2) > max(arr, i2 + 1)) {
                    swap(arr, i2, i2 + 1);
                }
            }
        }
    }
}
