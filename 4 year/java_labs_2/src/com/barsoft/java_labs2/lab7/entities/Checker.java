package com.barsoft.java_labs2.lab7.entities;

import java.awt.Color;

public class Checker {
	private CheckerState checkerState;
	private int x;
	private int y;

	public static enum CheckerState {
		Black, White
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public CheckerState getCheckerState() {
		return checkerState;
	}

	public void setCheckerState(CheckerState checkerState) {
		this.checkerState = checkerState;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return x + " " + y + " " + getCheckerState();
	}
}
