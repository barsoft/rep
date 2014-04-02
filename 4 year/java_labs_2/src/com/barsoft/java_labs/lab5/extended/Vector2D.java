/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs.lab5.extended;

import java.awt.Point;

/**
 *
 * @author Maks
 */
public class Vector2D {

    public float x;
    public float y;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D vec) {
        this.x = vec.x;
        this.y = vec.y;
    }

    public Vector2D multiply(float num) {
        return new Vector2D(x * num, y * num);
    }

    public Vector2D divide(float num) {
        return new Vector2D(x / num, y / num);
    }

    public Vector2D add(float num) {
        return new Vector2D(x + num, y + num);
    }

    public Vector2D add(Vector2D vec) {
        return new Vector2D(x + vec.x, y + vec.y);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public static Vector2D fromViewport(Point p, Game game) {
        float ratioX = game.getViewportWidth() / (float) game.getWorldWidth();
        return new Vector2D(p.x / ratioX, p.y / ratioX);
    }
}
