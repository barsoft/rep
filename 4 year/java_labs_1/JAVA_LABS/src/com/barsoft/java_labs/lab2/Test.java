package com.barsoft.java_labs.lab2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Test {

    public static void main(String[] args) {
        Complex c = new Complex(2, 0);

        System.out.println("Simple: " + c.toString());

        double val = 2;

        System.out.print("Compare: " + c.toString() + "==" + val);

        if (c.equals(val)) {
            System.out.println(" true");
        } else {
            System.out.println(" false");
        }

        ComplexExtended ce = new ComplexExtended(c);

        System.out.println("Extended (Trigonometrical): " + ce.toString());

        ComplexExtended2 ce2 = new ComplexExtended2(c);

        System.out.println("Extended 2 (array) (Trigonometrical): "
                + ce.toString());

        ArrayList<ComplexExtended2> arr = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            Random r = new Random();
            double x = r.nextDouble();
            double y = r.nextDouble();

            ComplexExtended2 tmp = new ComplexExtended2(x, y);
            arr.add(tmp);
        }

        System.out.println("Array: " + arr);

        Collections.sort(arr);

        System.out.println("Sorted array: " + arr);

    }
}
