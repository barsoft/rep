/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs.lab5.extended.sprites;

import com.barsoft.java_labs.lab5.extended.Game;
import java.awt.Graphics2D;

/**
 *
 * @author Maks
 */
public class SinMoon extends Dynamic {

    protected int amplitude;

    public SinMoon(Game game) {
        super(game);
        this.amplitude = 100;

    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
    }

    @Override
    public void update() {
        velocity.y = (float)Math.sin(position.x/50) ;
        super.update();
    }

    public int getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(int amplitude) {
        this.amplitude = amplitude;
    }
}
