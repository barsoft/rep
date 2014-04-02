package com.barsoft.java_labs2.lab6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.barsoft.java_labs2.core.ConnectionInfo;
import com.barsoft.java_labs2.core.forms.ConnectionJDialog;
import com.barsoft.java_labs2.lab6.database_connectors.Lab1Connector;

public class DatabaseFrame extends JFrame {

	private javax.swing.JMenuBar jMenuBar1;
	protected javax.swing.JMenu jMenuFile;
	private javax.swing.JMenuItem jMenuItemNew;
	private javax.swing.JMenuItem jMenuItemConnect;
	private BufferedImage wPic = null;
	private DatabaseJPanel lab1JPanel;
	private javax.swing.JPanel jPanelLogo;

	public DatabaseFrame() {
		jMenuFile = new javax.swing.JMenu();
		jMenuItemNew = new javax.swing.JMenuItem();
		jMenuItemConnect = new javax.swing.JMenuItem();

		jMenuBar1 = new javax.swing.JMenuBar();

		jMenuFile.setText("File");

		jMenuItemNew.setText("New");
		jMenuItemNew.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemNewActionPerformed(evt);
			}
		});
		jMenuFile.add(jMenuItemNew);

		jMenuItemConnect.setText("Connect");
		jMenuItemConnect.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemConnectActionPerformed(evt);
			}
		});
		jMenuFile.add(jMenuItemConnect);

		jMenuBar1.add(jMenuFile);

		setJMenuBar(jMenuBar1);

		jPanelLogo = new javax.swing.JPanel();

		// setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jPanelLogo.setPreferredSize(new java.awt.Dimension(769, 25));
		jPanelLogo.setLayout(new java.awt.BorderLayout());
		add(jPanelLogo, java.awt.BorderLayout.SOUTH);

		jMenuBar1 = new javax.swing.JMenuBar();

		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		setBounds((screenSize.width - 781) / 2, (screenSize.height - 467) / 2,
				781, 467);

		try {
			wPic = ImageIO.read(DatabaseFrame.class.getResource("logo.png"));
		} catch (IOException ex) {
			Logger.getLogger(DatabaseFrame.class.getName()).log(Level.SEVERE,
					null, ex);
		}

		final JLabel jl = new JLabel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				float ratio = ((float) wPic.getWidth() / wPic.getHeight());
				g.setColor(Color.black);
				g.fillRect(0, 0, getWidth(), getHeight());
				int height = getHeight() - 5;
				int width = (int) (height * ratio);
				g.drawImage(wPic, 0, (getHeight() - height) / 2, width, height,
						null);
			}
		};
		jPanelLogo.add(jl);
	}

	private void jMenuItemNewActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItemNewActionPerformed
		ConnectionInfo c = ConnectionJDialog.showConnectionDialog(this,
				"Create database");
		if (c == null)
			return;
		Lab1Connector db = Lab1Connector.createConnector(c);

		try {
			if (!db.createDatabase())
				return;

			if (lab1JPanel != null) {
				this.remove(lab1JPanel);
			}
			lab1JPanel = new DatabaseJPanel(db);
			add(lab1JPanel, BorderLayout.CENTER);
			this.revalidate();
		} catch (SQLException ex) {
			Logger.getLogger(DatabaseFrame.class.getName()).log(Level.SEVERE,
					null, ex);
		}

		// .setTitle(c.dataBaseName);
	}// GEN-LAST:event_jMenuItemNewActionPerformed

	private void jMenuItemConnectActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItemOpenActionPerformed
		ConnectionInfo c = ConnectionJDialog.showConnectionDialog(this,
				"Connect to database");
		if (c == null)
			return;
		Lab1Connector db = Lab1Connector.createConnector(c);

		try {
			// if (!db.createConnection())
			// return;

			if (lab1JPanel != null) {
				this.remove(lab1JPanel);
			}
			lab1JPanel = new DatabaseJPanel(db);
			add(lab1JPanel, BorderLayout.CENTER);
			this.revalidate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		// this.setTitle(c.dataBaseName);
	}// GEN-LAST:event_jMenuItemOpenActionPerformed

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {

		try {
			javax.swing.UIManager.setLookAndFeel(UIManager
					.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(DatabaseFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(DatabaseFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(DatabaseFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(DatabaseFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new DatabaseFrame().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables

	// End of variables declaration//GEN-END:variables

}
