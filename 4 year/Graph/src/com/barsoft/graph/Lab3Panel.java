/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.graph;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Maks
 */
public class Lab3Panel extends javax.swing.JPanel {

    private Rectangle viewPort = new Rectangle();

    public Lab3Panel() {
        initComponents();

        viewPort.x = 0;
        viewPort.y = 0;
        viewPort.width = 400;
        viewPort.height = 400;

        this.setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        int x1 = 0;
        int y1 = 0;

        int cx = 100;
        int cy = 100;
        int r1 = 100;
        int r2 = 50;
        // g2.drawLine(x1, y1, x1, y1);

        for (int theta = 0; theta < 360; theta += 1) {
            x1 = (int) (cx + r1 * Math.cos(theta));
            y1 = (int) (cy - r2 * Math.sin(theta));
            g2.drawLine(x1, y1, x1, y1);
        }
        g2.drawRect(viewPort.x, viewPort.y, viewPort.width, viewPort.height);
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