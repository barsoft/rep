/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.graph;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Maks
 */
public class Lab1Panel extends javax.swing.JPanel {

    /**
     * Creates new form MainPanel
     */
    public Lab1Panel() {
        initComponents();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        int initialWidth = (int) (getWidth() * 0.7);
        int count = 20;

        int ang = 6;

        Point start = new Point(getWidth() / 2 + initialWidth / 2, 0);
        int sum = 0;

        for (int i = 0; i < count; i++) {
            ang = 180 - ang;
            double angle = ang * Math.PI / 180;
            int width = (int) (initialWidth * (float) (getHeight() - start.y) / getHeight());
            sum += Math.abs(Math.tan(ang * Math.PI / 180) * width);

            Point vec = new Point(start.x + width, start.y);
            Point finalVec = new Point();
            finalVec.x = (int) (start.x + (vec.x - start.x) * Math.cos(angle) - (vec.y - start.y) * Math.sin(angle));
            finalVec.y = (int) (start.y + (vec.x - start.x) * Math.sin(angle) - (vec.y - start.y) * Math.cos(angle));

            int arrowSize = 10;
            Point arr1 = new Point(finalVec.x + arrowSize, finalVec.y);
            Point finalarr1 = new Point();
            double arr1Angle = (160 + ang) * Math.PI / 180;
            finalarr1.x = (int) (finalVec.x + (arr1.x - finalVec.x) * Math.cos(arr1Angle) - (arr1.y - finalVec.y) * Math.sin(arr1Angle));
            finalarr1.y = (int) (finalVec.y + (arr1.x - finalVec.x) * Math.sin(arr1Angle) - (arr1.y - finalVec.y) * Math.cos(arr1Angle));

            g2.drawLine(finalVec.x, finalVec.y, finalarr1.x, finalarr1.y);

            Point arr2 = new Point(finalVec.x + arrowSize, finalVec.y);
            Point finalarr2 = new Point();
            double arr2Angle = (200 + ang) * Math.PI / 180;
            finalarr2.x = (int) (finalVec.x + (arr2.x - finalVec.x) * Math.cos(arr2Angle) - (arr2.y - finalVec.y) * Math.sin(arr2Angle));
            finalarr2.y = (int) (finalVec.y + (arr2.x - finalVec.x) * Math.sin(arr2Angle) - (arr2.y - finalVec.y) * Math.cos(arr2Angle));
            g2.drawLine(finalVec.x, finalVec.y, finalarr2.x, finalarr2.y);
            
            g2.drawLine(start.x, start.y, finalVec.x, finalVec.y);
            start = new Point((int) (finalVec.x ), (int) (finalVec.y +10));
        }
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
