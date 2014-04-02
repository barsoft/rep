package com.barsoft.java_labs.lab2.client;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Cell extends JButton {

	/**
	 * Create the panel.
	 */
	public Cell() {
		Dimension size = new Dimension(50, 50);
	//	setPreferredSize(size);
		setMinimumSize(size);
		//setMaximumSize(size);
		//setSize(size);
	}

}
