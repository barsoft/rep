/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.graph;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Maks
 */
public class Lab2Panel extends javax.swing.JPanel {

    private Rectangle viewPort = new Rectangle();
    private int startX = 0;
    private int startY = 0;
    private int vX = 0;
    private int vY = 0;
    private boolean dragProcess = false;

    public Lab2Panel() {
        initComponents();

        viewPort.x = 0;
        viewPort.y = 0;
        viewPort.width = 400;
        viewPort.height = 100;

        MouseAdapter m = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (!dragProcess) {
                    startX = e.getX();
                    startY = e.getY();
                    vX = viewPort.x;
                    vY = viewPort.y;
                    dragProcess = true;
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (dragProcess) {
                    viewPort.x = vX + e.getX() - startX;
                    viewPort.y = vY + e.getY() - startY;

                    if (viewPort.x < 0) {
                        viewPort.x = 0;
                    }
                    if (viewPort.y < 0) {
                        viewPort.y = 0;
                    }
                    if (viewPort.x > getWidth() - viewPort.width) {
                        viewPort.x = getWidth() - viewPort.width;
                    }
                    if (viewPort.y > getHeight() - viewPort.height) {
                        viewPort.y = getHeight() - viewPort.height;
                    }
                }
                invalidate();
                repaint();
                System.out.println(viewPort.x);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                dragProcess = false;
            }
        };

        this.addMouseListener(m);
        this.addMouseMotionListener(m);

        this.setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        int interval = 30;

        int i = 0;

        int xu = 0;
        int yu = 0;
        int xd = 0;
        int yd = getHeight();

        do {
            xu = (i + 1) * interval;
            xd = (int) (xu - Math.sin(Math.toRadians(45)) * getHeight());

            Line line = сohenSutherlandLineClip(viewPort, xu, yu, xd, yd);

            if (line != null) {
                g2.drawLine(line.x0, line.y0, line.x1, line.y1);
            }

            i++;
        } while (xd < getWidth());

        g2.drawRect(viewPort.x, viewPort.y, viewPort.width, viewPort.height);
    }
    final int INSIDE = 0; // 0000
    final int LEFT = 1;   // 0001
    final int RIGHT = 2;  // 0010
    final int BOTTOM = 4; // 0100
    final int TOP = 8;    // 1000

    private class Line {

        int x0;
        int y0;
        int x1;
        int y1;

        private Line(int x0, int y0, int x1, int y1) {
            this.x0 = x0;
            this.y0 = y0;
            this.x1 = x1;
            this.y1 = y1;
        }
    }
    // Compute the bit code for a point (x, y) using the clip rectangle
    // bounded diagonally by (xmin, ymin), and (xmax, ymax)
    // ASSUME THAT xmax, xmin, ymax and ymin are global constants.

    private int сomputeOutCode(Rectangle rect, double x, double y) {
        int xmin = rect.x;
        int ymin = rect.y;
        int xmax = rect.x + rect.width;
        int ymax = rect.y + rect.height;
        int code;
        code = INSIDE;   // initialised as being inside of clip window
        if (x < xmin) // to the left of clip window
        {
            code |= LEFT;
        } else if (x > xmax) // to the right of clip window
        {
            code |= RIGHT;
        }
        if (y < ymin) // below the clip window
        {
            code |= BOTTOM;
        } else if (y > ymax) // above the clip window
        {
            code |= TOP;
        }
        return code;
    }

    // Cohen–Sutherland clipping algorithm clips a line from
    // P0 = (x0, y0) to P1 = (x1, y1) against a rectangle with 
    // diagonal from (xmin, ymin) to (xmax, ymax).
    private Line сohenSutherlandLineClip(Rectangle rect, double x0, double y0, double x1, double y1) {
        int xmin = rect.x;
        int ymin = rect.y;
        int xmax = rect.x + rect.width;
        int ymax = rect.y + rect.height;

        int outcode0 = сomputeOutCode(rect, x0, y0);
        int outcode1 = сomputeOutCode(rect, x1, y1);
        boolean accept = false;

        while (true) {
            if ((outcode0 | outcode1) == 0) { // Bitwise OR is 0. Trivially accept and get out of loop
                accept = true;
                break;
            } else if ((outcode0 & outcode1) != 0) { // Bitwise AND is not 0. Trivially reject and get out of loop
                break;
            } else {
                // failed both tests, so calculate the line segment to clip
                // from an outside point to an intersection with clip edge
                double x = 0, y = 0;

                // At least one endpoint is outside the clip rectangle; pick it.
                int outcodeOut = (outcode0 != 0) ? outcode0 : outcode1;

                // Now find the intersection point;
                // use formulas y = y0 + slope * (x - x0), x = x0 + (1 / slope) * (y - y0)
                if ((outcodeOut & TOP) != 0) {           // point is above the clip rectangle
                    x = x0 + (x1 - x0) * (ymax - y0) / (y1 - y0);
                    y = ymax;
                } else if ((outcodeOut & BOTTOM) != 0) { // point is below the clip rectangle
                    x = x0 + (x1 - x0) * (ymin - y0) / (y1 - y0);
                    y = ymin;
                } else if ((outcodeOut & RIGHT) != 0) {  // point is to the right of clip rectangle
                    y = y0 + (y1 - y0) * (xmax - x0) / (x1 - x0);
                    x = xmax;
                } else if ((outcodeOut & LEFT) != 0) {   // point is to the left of clip rectangle
                    y = y0 + (y1 - y0) * (xmin - x0) / (x1 - x0);
                    x = xmin;
                }

                // Now we move outside point to intersection point to clip
                // and get ready for next pass.
                if (outcodeOut == outcode0) {
                    x0 = x;
                    y0 = y;
                    outcode0 = сomputeOutCode(rect, x0, y0);
                } else {
                    x1 = x;
                    y1 = y;
                    outcode1 = сomputeOutCode(rect, x1, y1);
                }
            }
        }

        if (accept) {
            return new Line((int) x0, (int) y0, (int) x1, (int) y1);
        } else {
            return null;
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
