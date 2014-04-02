/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs.lab4;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.EventObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Maks
 */
public class Individ2 extends javax.swing.JFrame {

    public Individ2() {
        initComponents();
        setLocationRelativeTo(null);
        updateMenu();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupLanguage = new javax.swing.ButtonGroup();
        jPanelMain = new javax.swing.JPanel();
        jToolBarMain = new javax.swing.JToolBar();
        jButtonNew = new javax.swing.JButton();
        jButtonOpen = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButtonSaveAs = new javax.swing.JButton();
        jButtonAbout = new javax.swing.JButton();
        jPanelGameContainer = new javax.swing.JPanel();
        jPanelStatus = new javax.swing.JPanel();
        jLabelStatus = new javax.swing.JLabel();
        jMenuBarMain = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemNew = new javax.swing.JMenuItem();
        jMenuItemOpen = new javax.swing.JMenuItem();
        jMenuItemSave = new javax.swing.JMenuItem();
        jMenuItemSaveAs = new javax.swing.JMenuItem();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuItemAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelMain.setLayout(new java.awt.BorderLayout());

        jToolBarMain.setRollover(true);

        jButtonNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/barsoft/java_labs/lab4/new.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/barsoft/java_labs/lab4/strings_en"); // NOI18N
        jButtonNew.setText(bundle.getString("new")); // NOI18N
        jButtonNew.setActionCommand(bundle.getString("new")); // NOI18N
        jButtonNew.setFocusable(false);
        jButtonNew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonNew.setPreferredSize(new java.awt.Dimension(0, 0));
        jButtonNew.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewActionPerformed(evt);
            }
        });
        jToolBarMain.add(jButtonNew);

        jButtonOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/barsoft/java_labs/lab4/open.png"))); // NOI18N
        jButtonOpen.setText(bundle.getString("open")); // NOI18N
        jButtonOpen.setFocusable(false);
        jButtonOpen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonOpen.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpenActionPerformed(evt);
            }
        });
        jToolBarMain.add(jButtonOpen);

        jButtonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/barsoft/java_labs/lab4/save.png"))); // NOI18N
        jButtonSave.setText(bundle.getString("save")); // NOI18N
        jButtonSave.setFocusable(false);
        jButtonSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        jToolBarMain.add(jButtonSave);

        jButtonSaveAs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/barsoft/java_labs/lab4/save.png"))); // NOI18N
        jButtonSaveAs.setText(bundle.getString("save_as")); // NOI18N
        jButtonSaveAs.setFocusable(false);
        jButtonSaveAs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSaveAs.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveAsActionPerformed(evt);
            }
        });
        jToolBarMain.add(jButtonSaveAs);

        jButtonAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/barsoft/java_labs/lab4/about.png"))); // NOI18N
        jButtonAbout.setText(bundle.getString("about")); // NOI18N
        jButtonAbout.setFocusable(false);
        jButtonAbout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonAbout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAboutActionPerformed(evt);
            }
        });
        jToolBarMain.add(jButtonAbout);

        jPanelMain.add(jToolBarMain, java.awt.BorderLayout.NORTH);

        jPanelGameContainer.setBackground(new java.awt.Color(204, 204, 204));
        jPanelGameContainer.setPreferredSize(new java.awt.Dimension(0, 300));
        jPanelGameContainer.setLayout(new java.awt.GridBagLayout());
        jPanelMain.add(jPanelGameContainer, java.awt.BorderLayout.CENTER);

        jPanelStatus.setPreferredSize(new java.awt.Dimension(0, 25));
        jPanelStatus.setLayout(new javax.swing.BoxLayout(jPanelStatus, javax.swing.BoxLayout.LINE_AXIS));

        jLabelStatus.setText("Game");
        jPanelStatus.add(jLabelStatus);

        jPanelMain.add(jPanelStatus, java.awt.BorderLayout.SOUTH);

        jMenuFile.setText(bundle.getString("file")); // NOI18N

        jMenuItemNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemNew.setText(bundle.getString("new")); // NOI18N
        jMenuItemNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNewActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemNew);

        jMenuItemOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemOpen.setText(bundle.getString("open")); // NOI18N
        jMenuItemOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOpenActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemOpen);

        jMenuItemSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSave.setText(bundle.getString("save")); // NOI18N
        jMenuItemSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSaveActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemSave);

        jMenuItemSaveAs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSaveAs.setText(bundle.getString("save_as")); // NOI18N
        jMenuItemSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSaveAsActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemSaveAs);

        jMenuItemExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemExit.setText(bundle.getString("exit")); // NOI18N
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemExit);

        jMenuBarMain.add(jMenuFile);

        jMenuHelp.setText(bundle.getString("help")); // NOI18N
        jMenuHelp.setActionCommand(bundle.getString("help")); // NOI18N

        jMenuItemAbout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAbout.setText(bundle.getString("about")); // NOI18N
        jMenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAboutActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItemAbout);

        jMenuBarMain.add(jMenuHelp);

        setJMenuBar(jMenuBarMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewActionPerformed
        jMenuItemNewActionPerformed(evt);
    }//GEN-LAST:event_jButtonNewActionPerformed

    private void jButtonOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOpenActionPerformed
        jMenuItemOpenActionPerformed(evt);
    }//GEN-LAST:event_jButtonOpenActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        jMenuItemSaveActionPerformed(evt);
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveAsActionPerformed
        jMenuItemSaveAsActionPerformed(evt);
    }//GEN-LAST:event_jButtonSaveAsActionPerformed

    private void jButtonAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAboutActionPerformed
        jMenuItemAboutActionPerformed(evt);
    }//GEN-LAST:event_jButtonAboutActionPerformed

    private void jMenuItemNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNewActionPerformed
        final CreateJDialog sd = new CreateJDialog(this, true);
        sd.setLocationRelativeTo(this);
        sd.getjButtonAccept().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sd.dispose();
                createNewGame(Integer.parseInt(sd.getjComboBoxX().getSelectedItem().toString()),
                        Integer.parseInt(sd.getjComboBoxY().getSelectedItem().toString()), 
                        sd.getjCheckBoxComputer().isSelected(),
                        Integer.parseInt(sd.getjComboBoxDifficulty().getSelectedItem().toString()));
                currPath = "";
            }
        });
        sd.show();
    }//GEN-LAST:event_jMenuItemNewActionPerformed

    private void jMenuItemOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOpenActionPerformed
        JFileChooser ofd = new JFileChooser();
        ofd.setFileFilter(new FileNameExtensionFilter("Game files", "game"));
        ofd.showOpenDialog(this);
        File f = ofd.getSelectedFile();
        if (f != null) {
            loadGame(f.getAbsolutePath());
            currPath = f.getAbsolutePath();
        }
    }//GEN-LAST:event_jMenuItemOpenActionPerformed

    private void jMenuItemSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSaveActionPerformed
        if (currPath.isEmpty()) {
            jMenuItemSaveAsActionPerformed(evt);
        } else {
            saveGame(currPath);
        }
    }//GEN-LAST:event_jMenuItemSaveActionPerformed

    private void jMenuItemSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSaveAsActionPerformed
        JFileChooser ofd = new JFileChooser();
        ofd.setFileFilter(new FileNameExtensionFilter("Game files (*.game)", "game"));
        File f1 = new File(currPath);
        ofd.setSelectedFile(f1);
        ofd.showSaveDialog(this);
        File f = ofd.getSelectedFile();
        String fileName = f.getAbsolutePath();
        if (!fileName.endsWith(".game")) {
            fileName += ".game";
        }
        if (f != null) {
            saveGame(fileName);
            currPath = fileName;
        }
    }//GEN-LAST:event_jMenuItemSaveAsActionPerformed

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void jMenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAboutActionPerformed
        AboutJDialog ad = new AboutJDialog(this, true);
        ad.setLocationRelativeTo(this);
        ad.show();
    }//GEN-LAST:event_jMenuItemAboutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Individ2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Individ2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Individ2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Individ2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Individ2().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupLanguage;
    private javax.swing.JButton jButtonAbout;
    private javax.swing.JButton jButtonNew;
    private javax.swing.JButton jButtonOpen;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSaveAs;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JMenuBar jMenuBarMain;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItemAbout;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemNew;
    private javax.swing.JMenuItem jMenuItemOpen;
    private javax.swing.JMenuItem jMenuItemSave;
    private javax.swing.JMenuItem jMenuItemSaveAs;
    private javax.swing.JPanel jPanelGameContainer;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JPanel jPanelStatus;
    private javax.swing.JToolBar jToolBarMain;
    // End of variables declaration//GEN-END:variables
    private GameJPanel gameJPanel;
    private Game game;
    private String currPath = "";

    private void createNewGame(int width, int height, boolean computer, int difficulty) {

        if (gameJPanel != null) {
            gameJPanel.setVisible(false);
            gameJPanel = null;
        }
        gameJPanel = new GameJPanel();
        game = new Game();
        initGame(game);

        game.create(width, height, computer, difficulty);
        gameJPanel.setGame(game);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;

        this.jPanelGameContainer.add(gameJPanel, c);

        validate();
        updateMenu();
    }

    private void loadGame(String filePath) {
        if (gameJPanel != null) {
            gameJPanel.setVisible(false);
            gameJPanel = null;
        }
        gameJPanel = new GameJPanel();
        game = new Game();
        initGame(game);

        try {
            game.load(filePath);
        } catch (IOException ex) {
            Logger.getLogger(Individ2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Individ2.class.getName()).log(Level.SEVERE, null, ex);
        }

        gameJPanel.setGame(game);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;

        this.jPanelGameContainer.add(gameJPanel, c);

        validate();
        updateMenu();
    }

    private void initGame(final Game g) {

        g.addEventListener(new Game.GameListener() {
            @Override
            public void gameFinished(EventObject e) {
                gameJPanel.disableField();
                if (g.getWinner() != null) {
                    JOptionPane.showMessageDialog(gameJPanel, g.getWinner().toString() + " wins!");
                    jLabelStatus.setText(g.getWinner().toString() + " wins!");
                } else {
                    JOptionPane.showMessageDialog(gameJPanel, "Draw!");
                    jLabelStatus.setText("Draw!");
                }
            }

            @Override
            public void currentCharacterSet(EventObject e) {
                jLabelStatus.setText(g.getCurrentCharacter().toString() + " turn");
            }

            @Override
            public void steppedUp(EventObject e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

    private void updateMenu() {
        if (gameJPanel == null) {
            jButtonSave.setEnabled(false);
            jButtonSaveAs.setEnabled(false);
            jMenuItemSave.setEnabled(false);
            jMenuItemSaveAs.setEnabled(false);
        } else {
            jButtonSave.setEnabled(true);
            jButtonSaveAs.setEnabled(true);
            jMenuItemSave.setEnabled(true);
            jMenuItemSaveAs.setEnabled(true);
        }
    }

    private void saveGame(String absolutePath) {
        try {
            game.save(absolutePath);
        } catch (IOException ex) {
            Logger.getLogger(Individ2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}