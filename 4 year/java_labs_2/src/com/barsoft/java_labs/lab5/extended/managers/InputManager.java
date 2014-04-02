/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs.lab5.extended.managers;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author Maks
 */
public class InputManager implements MouseInputListener, KeyListener {

    private static InputManager instance = new InputManager();
    private boolean _mousePressed;
    private Point mousePosition;
    private boolean[] keys = new boolean[222];

    public InputManager() {
        this._mousePressed = false;
        this.mousePosition = new Point();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //       throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this._mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this._mousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //      throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        this.mousePosition = e.getPoint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    public static InputManager getInstance() {
        return instance;
    }

    public boolean isMousePressed() {
        return _mousePressed;
    }

    public Point getMousePosition() {
        return new Point(mousePosition);
    }

    public boolean isKeyPressed(int keyCode) {
        return keys[keyCode];
    }
}
