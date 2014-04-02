package com.barsoft.example.lab2;

public class ComplexExtended extends Complex {
	
	public ComplexExtended(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public ComplexExtended(Complex c) {
		super(c.x, c.y);
		// TODO Auto-generated constructor stub
	}
	
	private double getArg(){
		return Math.atan(y/x);
	}

	private double getAbsZ(){
		return Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
	}
	
	@Override
	public String toString() {
		return getAbsZ()+"("+Math.cos(getArg())+"+"+Math.sin(getArg())+"i)";
	}

}
