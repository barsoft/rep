/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs.lab5.extended;

import java.awt.Image;
import java.awt.Point;

/**
 *
 * @author Maks
 */
public class Texture {

    private Image image;
    private String name;
    private Vector2D size;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
        this.size = new Vector2D(image.getWidth(null), image.getHeight(null));
    }

    public Vector2D getSize() {
        return new Vector2D(size);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
