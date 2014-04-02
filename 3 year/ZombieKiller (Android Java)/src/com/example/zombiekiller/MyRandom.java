package com.example.zombiekiller;

import java.util.Random;

public class MyRandom {
	private static Random random = new Random();

    static int generateRandom(int n) {
        return Math.abs(random.nextInt()) % n;
    }
}
