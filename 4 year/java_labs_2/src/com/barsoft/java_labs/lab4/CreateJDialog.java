/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs.lab4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

/**
 *
 * @author Maks
 */
public class CreateJDialog extends javax.swing.JDialog {

    /**
     * Creates new form SizeJDialog
     */
    public CreateJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        List<Integer> sizes = new ArrayList<Integer>();
        for (int i = 3; i <= 100; ++i) {
            sizes.add(i);
        }

        jComboBoxX.setModel(new DefaultComboBoxModel(sizes.toArray()));
        jComboBoxY.setModel(new DefaultComboBoxModel(sizes.toArray()));

        jComboBoxX.setSelectedIndex(5);
        jComboBoxY.setSelectedIndex(5);

        int x = Integer.parseInt(jComboBoxX.getSelectedItem().toString());
        int y = Integer.parseInt(jComboBoxY.getSelectedItem().toString());

        List<Integer> difficulties = new ArrayList<Integer>();

        for (int i = 2; i <= Math.min(x, y); ++i) {
            difficulties.add(i);
        }
        jComboBoxDifficulty.setModel(new DefaultComboBoxModel(difficulties.toArray()));

        jComboBoxDifficulty.setSelectedIndex(1);

        jComboBoxX.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                comboBoxActionPerformed();
            }
        });
        jComboBoxY.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                comboBoxActionPerformed();
            }
        });
    }

    private void comboBoxActionPerformed() {
        int x = Integer.parseInt(jComboBoxX.getSelectedItem().toString());
        int y = Integer.parseInt(jComboBoxY.getSelectedItem().toString());

        List<Integer> difficulties = new ArrayList<Integer>();

        for (int i = 2; i <= Math.min(x, y); ++i) {
            difficulties.add(i);
        }
        jComboBoxDifficulty.setModel(new DefaultComboBoxModel(difficulties.toArray()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxX = new javax.swing.JComboBox();
        jButtonAccept = new javax.swing.JButton();
        jCheckBoxComputer = new javax.swing.JCheckBox();
        jComboBoxY = new javax.swing.JComboBox();
        jComboBoxDifficulty = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/barsoft/java_labs/lab4/strings_en"); // NOI18N
        setTitle(bundle.getString("select_field_size")); // NOI18N

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("X");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Y");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel1.add(jLabel2, gridBagConstraints);

        jComboBoxX.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel1.add(jComboBoxX, gridBagConstraints);

        jButtonAccept.setText("OK");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        jPanel1.add(jButtonAccept, gridBagConstraints);

        jCheckBoxComputer.setText("Computer");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        jPanel1.add(jCheckBoxComputer, gridBagConstraints);

        jComboBoxY.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jComboBoxY, gridBagConstraints);

        jComboBoxDifficulty.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jComboBoxDifficulty, gridBagConstraints);

        jLabel3.setText(bundle.getString("difficulty")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel1.add(jLabel3, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CreateJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CreateJDialog dialog = new CreateJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAccept;
    private javax.swing.JCheckBox jCheckBoxComputer;
    private javax.swing.JComboBox jComboBoxDifficulty;
    private javax.swing.JComboBox jComboBoxX;
    private javax.swing.JComboBox jComboBoxY;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    public JComboBox getjComboBoxX() {
        return jComboBoxX;
    }

    public JComboBox getjComboBoxY() {
        return jComboBoxY;
    }
    
    public JComboBox getjComboBoxDifficulty() {
        return jComboBoxDifficulty;
    }

    public JButton getjButtonAccept() {
        return jButtonAccept;
    }

    public JCheckBox getjCheckBoxComputer() {
        return jCheckBoxComputer;
    }
}
