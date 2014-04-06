package com.barsoft.java_labs2.lab7.entities;

import com.barsoft.java_labs2.lab7.entities.Checker.CheckerState;

public class Player {
	private User user;
	private CheckerState checkerState;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CheckerState getCheckerState() {
		return checkerState;
	}

	public void setCheckerState(CheckerState checkerState) {
		this.checkerState = checkerState;
	}
}
