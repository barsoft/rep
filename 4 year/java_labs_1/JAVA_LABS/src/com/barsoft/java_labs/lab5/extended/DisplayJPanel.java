/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs.lab5.extended;

import com.barsoft.java_labs.lab5.extended.sprites.Sprite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maks
 */
public class DisplayJPanel extends javax.swing.JPanel {

    private Game game;

    /**
     * Creates new form DisplayJPanel
     */
    public DisplayJPanel() {
        initComponents();
        this.setFocusable(true);
        this.requestFocusInWindow();

        game = new Game(this);

        load();
    }

    private void load() {
        game.load();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    game.update();

                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        thread.start();

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    repaint();
                    validate();
                }
            }
        });
        thread.start();;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        game.draw(g2d);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
