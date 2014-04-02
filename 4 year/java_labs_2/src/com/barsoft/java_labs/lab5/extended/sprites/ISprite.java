/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs.lab5.extended.sprites;

import java.awt.Graphics2D;

/**
 *
 * @author Maks
 */
public interface ISprite {

    public void load();

    public void draw(Graphics2D g2d);

    public void update();
}
