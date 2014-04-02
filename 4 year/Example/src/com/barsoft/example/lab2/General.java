package com.barsoft.example.lab2;

import java.util.Arrays;

public class General {
	public General() {

	}

	static String a[] = { "1", "2", "3" };

	static String b1[] = a;
	static String b2[] = a.clone();
	static String b3[] = Arrays.copyOf(a, a.length);
	static String b4[] = { "1", "2", "3" };

	public static void main(String[] args) {

		String condition = null;

		for (int i = 0; i < 6; i++) {
			switch (i) {
			case 0:
				condition = "b";
				break;
			case 1:
				condition = "b == a";
				break;
			case 2:
				condition = "b.equals(a)";
				break;
			case 3:
				condition = "Arrays.equals(a,b)";
				break;
			case 4:
				condition = "Arrays.deepEquals(a,b)";
				break;
			case 5:
				condition = "b[0]==a[0]";
				break;
			}

			System.out.printf("%-30s", condition);
		}

		System.out.println();

		for (int i = 0; i < 4; i++) {
			String b[] = null;
			switch (i) {
			case 0:
				condition = "b=a";
				b=b1;
				break;
			case 1:
				condition = "b=a.clone()";
				b=b2;
				break;
			case 2:
				condition = "b=Arrays.copyOf(a)";
				b=b3;
				break;
			case 3:
				condition = "b = {\"1\",\"2\",\"3\"}";
				b=b4;
				break;
			}

			System.out.printf("%-30s", condition);
			writeLine(b);
		}

	}

	private static void writeLine(String[] b) {
		boolean res;

		for (int j = 0; j < 6; j++) {
			switch (j) {
			case 0:
				res = b == a;
				System.out.printf("%-30b", res);
				res = b.equals(a);
				System.out.printf("%-30b", res);
				res = Arrays.equals(a,b);
				System.out.printf("%-30b", res);
				res = Arrays.deepEquals(a,b);
				System.out.printf("%-30b", res);
				res = b[0]==a[0];
				System.out.printf("%-30b", res);
				break;
			}

		}
		System.out.println();
	}
}
