package com.barsoft.example.lab2;

public class ComplexExtended2 extends ComplexExtended implements Comparable {

	private double[] value = new double[2];

	public ComplexExtended2(double x, double y) {
		super(x, y);
		value[0] = Math.atan(y / x);
		value[1] = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	public ComplexExtended2(Complex c) {
		super(c.x, c.y);
		// TODO Auto-generated constructor stub
	}

	private double[] getValue() {
		return value;
	}

	private double getArg() {
		return value[0];
	}

	private double getAbsZ() {
		return value[1];
	}

	@Override
	public String toString() {
		// return value[1] + "(" + Math.cos(value[0]) + "+" + Math.sin(value[0])
		// + "i)";
		// return String.format("%.3f(%.3f+%.3fi)", value[1],
		// Math.cos(value[0]),
		// Math.sin(value[0]));
		// return "("+x + "," + y + "i"+")";
		return String.format("(%.3f;%.3fi)", x, y);
	}

	public int compareTo(Object obj) {
		ComplexExtended2 tmp = (ComplexExtended2) obj;
		if (this.x < tmp.x) {
			/* текущее меньше полученного */
			return -1;
		} else if (this.x > tmp.x) {
			/* текущее больше полученного */
			return 1;
		}
		/* текущее равно полученному */
		return 0;
	}
}
