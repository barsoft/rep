package com.barsoft.java_labs2.lab7.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.barsoft.java_labs2.Main;
import com.barsoft.java_labs2.lab7.entities.Checker;
import com.barsoft.java_labs2.lab7.entities.Checker.CheckerState;

public class Cell extends JButton {

	private Checker checker;
	private boolean picked;
	private boolean available;
	private boolean onKick;

	public Cell() {
		Dimension size = new Dimension(50, 50);
		setPreferredSize(size);
	}

	public Checker getChecker() {
		return checker;
	}

	public void setChecker(Checker checker) {
		this.checker = checker;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();

		Color c;

		if (isPicked()) {
			c = Color.yellow;
		} else if (isAvailable()) {
			c = Color.orange;
		} else if (isOnKick()) {
			c = Color.red;
		} else {
			c = getBackground();
		}
		g2.setPaint(new GradientPaint(new Point(0, 0), c, new Point(0,
				getHeight()), c.darker()));
		g2.fillRect(0, 0, getWidth(), getHeight());

		Image ic;

		if (checker != null) {
			if (checker.getCheckerState() == CheckerState.Black) {
				ic = new ImageIcon(Main.class.getResource("mud_black.png"))
						.getImage();
			} else {
				ic = new ImageIcon(Main.class.getResource("mud_white.png"))
						.getImage();
			}
			g2.drawImage(ic, 0, 0, getWidth(), getHeight(), 0, 0,
					ic.getWidth(null), ic.getHeight(null), null);
		}
		g2.dispose();
	}

	public boolean isPicked() {
		return picked;
	}

	public void setPicked(boolean picked) {
		this.picked = picked;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public boolean isOnKick() {
		return onKick;
	}

	public void setOnKick(boolean onKick) {
		this.onKick = onKick;
	}

}
