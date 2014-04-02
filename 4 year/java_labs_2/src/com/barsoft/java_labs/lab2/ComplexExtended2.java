package com.barsoft.java_labs.lab2;

public class ComplexExtended2 extends ComplexExtended implements Comparable {

    private double[] value = new double[2];

    public ComplexExtended2() {
        super();
    }

    public ComplexExtended2(Complex c) {
        super(c.x, c.y);
        // TODO Auto-generated constructor stub
    }

    public ComplexExtended2(double x, double y) {
        super(x, y);
        value[0] = Math.atan(y / x);
        value[1] = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    private double[] getValue() {
        return value;
    }

    @Override
    public double getArg() {
        return value[0];
    }

    @Override
    public double getAbsZ() {
        return value[1];
    }

    @Override
    public String toString() {
        return String.format("(%.3f;%.3fi)", x, y);
    }

    @Override
    public int compareTo(Object obj) {
        ComplexExtended2 tmp = (ComplexExtended2) obj;
        if (this.x < tmp.x) {
            return -1;
        } else if (this.x > tmp.x) {
            return 1;
        }
        return 0;
    }
}
