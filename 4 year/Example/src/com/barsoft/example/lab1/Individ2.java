package com.barsoft.example.lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.IllegalFormatFlagsException;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.omg.IOP.CodecPackage.FormatMismatch;

public class Individ2 {
	private static Scanner newscan;

	private static int cellSize = 10;

	private static String formatString(String component) {
		StringBuilder f = new StringBuilder(component);
		for (int i = 0; i < cellSize - component.length(); i++) {
			f.insert(0, " ");
		}
		return f.toString();
	}

	public static class Polynom {
		public ArrayList<Integer> coefs;

		public Polynom() {
			coefs = new ArrayList<Integer>();
		}

		public String getPoly() {
			int i = 0;
			StringBuilder p = new StringBuilder();
			while (true) {
				String component = getComponent(i);
				p.insert(0, formatString(component));
				i++;
				if (i >= coefs.size())
					break;
			}
			return p.toString();
		}

		public String getPolyFormatted() {
			int i = 0;
			StringBuilder p = new StringBuilder();
			while (true) {
				String component = getComponent(i);
				p.insert(0, formatString(component));
				i++;
				if (i >= coefs.size())
					break;
			}

			return p.toString();
		}

		public String getComponent(int i) {
			int n = coefs.get(i);
			String component = ((n < 0) ? n : ("+" + n)) + "x^" + i;
			return component;
		}

		public void add(int readPolyCoefNum) {
			coefs.add(readPolyCoefNum);
		}

		public int getPower() {
			return coefs.size();
		}

		public Polynom clone() {
			Polynom poly = new Polynom();
			for (int coef : coefs) {
				poly.coefs.add(coef);
			}
			return poly;
		}

		public Polynom concat(Polynom poly1) {
			Polynom poly = new Polynom();

			while (true) {
				if (getPower() > poly1.getPower()) {
					poly1.add(0);
				} else if (getPower() < poly1.getPower()) {
					this.add(0);
				} else if (poly1.getPower() == getPower())
					break;
			}
			for (int i = 0; i < Math.max(getPower(), poly1.getPower()); i++) {
				poly.add(coefs.get(i) + poly1.coefs.get(i));
			}

			return poly;
		}
	}

	public static int readPolyPower() {
		int num = 0;
		String s = newscan.nextLine();
		Pattern intPattern = Pattern.compile("(\\d{1,3}$)");
		Pattern quitPattern = Pattern.compile("(quit|exit)");
		Matcher intMatcher = intPattern.matcher(s);
		Matcher quitMatcher = quitPattern.matcher(s);
		if (intMatcher.matches()) {
			num = Integer.parseInt(s);
		} else if (quitMatcher.matches()) {
			System.out.println("Application terminated");
			System.exit(0);
		} else {
			throw new NumberFormatException(
					"Wrong input format! 1-3 character positive integer expected.");
		}
		return num;
	}

	public static int readPolyCoefNum() {
		int num = 0;
		String s = newscan.nextLine();
		Pattern intPattern = Pattern.compile("(\\-?\\d{1,3}$)");
		Pattern quitPattern = Pattern.compile("(quit|exit)");
		Matcher intMatcher = intPattern.matcher(s);
		Matcher quitMatcher = quitPattern.matcher(s);
		if (intMatcher.matches()) {
			num = Integer.parseInt(s);
		} else if (quitMatcher.matches()) {
			System.out.println("Application terminated");
			System.exit(0);
		} else {
			throw new NumberFormatException(
					"Wrong input format! 1-3 character integer expected.");
		}
		return num;
	}

	public static void main(String[] args) throws InterruptedException {
		newscan = new Scanner(System.in);

		Polynom poly1;
		Polynom poly2;

		while (true) {

			System.out.println("Polynomial 1 size:");

			int power;
			while (true) {
				try {
					power = readPolyPower();
				} catch (NumberFormatException e) {
					continue;
				}
				break;
			}

			poly1 = new Polynom();

			int i = 0;

			while (true) {
				if (i >= power)
					break;
				try {
					poly1.add(readPolyCoefNum());

				} catch (NumberFormatException e) {
					e.printStackTrace();
					continue;
				}
				i++;
			}

			System.out.println("Polynomial 2 size:");
			while (true) {
				try {
					power = readPolyPower();
				} catch (NumberFormatException e) {
					continue;
				}
				break;
			}

			poly2 = new Polynom();

			i = 0;

			while (true) {
				if (i >= power)
					break;
				try {
					poly2.add(readPolyCoefNum());
				} catch (NumberFormatException e) {
					e.printStackTrace();
					continue;
				}
				i++;
			}

			int maxPower = Math.max(poly1.getPower(), poly2.getPower());

			int columnCount = poly1.getPower() + poly2.getPower() - 1;

			StringBuilder s = new StringBuilder(formatString("P1=       "));
			for (int j = 0; j < columnCount - poly1.getPower(); j++) {
				s.append(formatString(""));
			}
			s.append(poly1.getPolyFormatted());
			System.out.println(s);

			s = new StringBuilder(formatString("P2=       "));
			for (int j = 0; j < columnCount - poly2.getPower(); j++) {
				s.append(formatString(""));
			}
			s.append(poly2.getPolyFormatted());
			System.out.println(s);

			s = new StringBuilder();
			for (int j = 0; j < (columnCount + 1) * cellSize; j++) {
				s.append("-");
			}
			System.out.println(s);

			Polynom oldPoly = poly1;

			Polynom sum = new Polynom();

			for (int j = 0; j < poly2.getPower(); j++) {
				s = new StringBuilder();
				s.insert(0, formatString(poly2.getComponent(j) + "*P1:"));
				Polynom poly = new Polynom();

				for (int k = 0; k < poly1.getPower(); k++) {
					poly.add(poly1.coefs.get(k) * poly2.coefs.get(j));

				}
				for (int k = 0; k < j; k++) {
					poly.coefs.add(0, 0);
				}

				for (int k = 0; k < columnCount - poly.getPower(); k++) {
					s.append(formatString(""));
				}

				s.append(poly.getPolyFormatted());
				System.out.println(s);

				oldPoly = (Polynom) poly.clone();

				sum = sum.concat(poly).clone();
			}

			s = new StringBuilder();
			for (int j = 0; j < (columnCount + 1) * cellSize; j++) {
				s.append("-");
			}

			s = new StringBuilder(formatString("Result:"));
			s.append(sum.getPolyFormatted());
			System.out.println(s);

			System.out.println("Press ENTER to continue...");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try {
				br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
