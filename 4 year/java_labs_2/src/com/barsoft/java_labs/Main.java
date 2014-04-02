package com.barsoft.java_labs;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.UIManager;

import com.barsoft.java_labs.core.forms.ImagePanel;
import com.barsoft.java_labs.lab1.DatabaseFrame;
import com.barsoft.java_labs.lab2.client.CheckersFrame;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Main extends JFrame {

	private JPanel contentPane;

	private static Main main;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			javax.swing.UIManager.setLookAndFeel(UIManager
					.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				main = new Main();
				main.setVisible(true);
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 663, 493);

		contentPane = new ImagePanel(Main.class.getResource(
				"/com/barsoft/java_labs/background.jpg").getPath());
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0 };
		gbl_contentPane.rowWeights = new double[] { 1.0 };
		contentPane.setLayout(gbl_contentPane);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Leisure");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);

		JButton buttonLeisure = new JButton("");
		buttonLeisure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.setVisible(false);
				final CheckersFrame f = new CheckersFrame() {
					{
						JMenuItem jMenuItemClose = new JMenuItem();
						jMenuItemClose.setText("Close");
						jMenuItemClose
								.addActionListener(new java.awt.event.ActionListener() {
									public void actionPerformed(
											java.awt.event.ActionEvent evt) {
										setVisible(false);
										main.setBounds(getX(), getY(),
												getWidth(), getHeight());
										main.setVisible(true);
									}
								});
						mnFile.add(jMenuItemClose);
					}
				};
				f.setVisible(true);
				f.setBounds(getX(), getY(), getWidth(), getHeight());
			}
		});
		buttonLeisure.setIcon(new ImageIcon(Main.class
				.getResource("/com/barsoft/java_labs/shashki_logo.jpg")));
		panel.add(buttonLeisure, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		contentPane.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblBusiness = new JLabel("Business");
		lblBusiness.setForeground(Color.BLACK);
		lblBusiness.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblBusiness, BorderLayout.SOUTH);

		JButton buttonBuisness = new JButton("");
		buttonBuisness.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.setVisible(false);
				final DatabaseFrame f = new DatabaseFrame() {
					{
						JMenuItem jMenuItemClose = new JMenuItem();
						jMenuItemClose.setText("Close");
						jMenuItemClose
								.addActionListener(new java.awt.event.ActionListener() {
									public void actionPerformed(
											java.awt.event.ActionEvent evt) {
										setVisible(false);
										main.setBounds(getX(), getY(),
												getWidth(), getHeight());
										main.setVisible(true);
									}
								});
						jMenuFile.add(jMenuItemClose);
					}
				};
				f.setVisible(true);
				f.setBounds(getX(), getY(), getWidth(), getHeight());
			}
		});
		buttonBuisness.setIcon(new ImageIcon(Main.class
				.getResource("/com/barsoft/java_labs/buisness.jpg")));
		panel_1.add(buttonBuisness, BorderLayout.NORTH);
	}

}
