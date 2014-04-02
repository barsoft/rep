/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs.lab5.simple;

import com.barsoft.java_labs.lab4.Game;
import com.barsoft.java_labs.lab4.Individ2;
import com.thoughtworks.xstream.XStream;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Maks
 */
public class DisplayJPanel extends javax.swing.JPanel {

    ArrayList< File> files;
    ArrayList<PolyLine> lines;

    /**
     * Creates new form DisplayJPanel
     */
    public DisplayJPanel() {
        initComponents();
        files = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            files.add(new File("poly" + i + ".drw"));
        }
        lines = new ArrayList<>();

        load(0, 3);
    }

    private void load(final int i, final int count) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (i == count) {
                    return;
                }
                XStream xstream = new XStream();
                PolyLine polyLine = (PolyLine) xstream.fromXML(files.get(i));
                lines.add(polyLine);
                DisplayJPanel.this.repaint();
                DisplayJPanel.this.validate();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DisplayJPanel.class.getName()).log(Level.SEVERE, null, ex);
                }

                load(i + 1, count);
            }
        });
        thread.start();
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color1 = Color.WHITE;
        Color color2 = Color.GRAY;
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);

        for (PolyLine p : lines) {
            GeneralPath polylinePrimitive =
                    new GeneralPath(GeneralPath.WIND_EVEN_ODD, p.getPoints().size());

            polylinePrimitive.moveTo(p.getPoints().get(0).getX(), p.getPoints().get(0).getY());

            for (int index = 1; index < p.getPoints().size(); index++) {
                polylinePrimitive.lineTo(p.getPoints().get(index).getX(), p.getPoints().get(index).getY());
            };

            g2d.setColor(p.getColor());
            g2d.setStroke(new BasicStroke(p.getStrokeSize()));
            g2d.draw(polylinePrimitive);
        }

//        PolyLine p=new PolyLine();
//        p.getPoints().add(new Point(2, 2));
//        p.getPoints().add(new Point(20, 20));
//        p.setColor(Color.red);
//        p.setStrokeSize(10);
//        
//        GeneralPath primitive =
//                new GeneralPath(GeneralPath.WIND_EVEN_ODD, p.getPoints().size());
//
//        primitive.moveTo(p.getPoints().get(0).getX(), p.getPoints().get(0).getY());
//
//        for (int index = 1; index < p.getPoints().size(); index++) {
//            primitive.lineTo(p.getPoints().get(index).getX(), p.getPoints().get(index).getY());
//        };
//
//        g2d.setColor(p.getColor());
//        g2d.setStroke(new BasicStroke(p.getStrokeSize()));
//        g2d.draw(primitive);
    }
}
