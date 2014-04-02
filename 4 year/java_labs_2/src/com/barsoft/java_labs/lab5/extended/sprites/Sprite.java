/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs.lab5.extended.sprites;

import com.barsoft.java_labs.lab5.extended.Game;
import com.barsoft.java_labs.lab5.extended.Texture;
import com.barsoft.java_labs.lab5.extended.Vector2D;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Maks
 */
public class Sprite extends Object implements ISprite {

    protected Vector2D position;
    protected Vector2D size;
    protected Game game;
    protected Texture texture;
    protected String name;

    public Sprite(Game game) {
        this.position = new Vector2D(0, 0);
        this.size = new Vector2D(80, 80);
        this.game = game;
        this.name = this.getClass().getSimpleName().toString() + hashCode();
    }

    @Override
    public void load() {
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (texture != null) {
            float ratioX = game.getViewportWidth() / (float) game.getWorldWidth();
            //float ratioY = game.getViewportHeight() / (float) game.getWorldHeight();
            g2d.drawImage(texture.getImage(),
                    (int) (position.x * ratioX),
                    (int) (position.y * ratioX),
                    (int) (size.x * ratioX),
                    (int) (size.y * ratioX), null);
        }
    }

    @Override
    public void update() {
    }
    // PROPERTIES //////////////////////////////////////////////////////////////

    public final Texture getTexture() {
        return texture;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final void setTexture(Texture texture) {
        this.texture = texture;
    }

    public final float getWidth() {
        return size.x;
    }

    public final float getHeight() {
        return size.y;
    }

    public final float getX() {
        return position.x;
    }

    public final float getY() {
        return position.y;
    }

    public final void setWidth(float width) {
        size.x = width;
    }

    public final void setHeight(float height) {
        size.y = height;
    }

    public final void setX(float x) {
        position.x = x;
    }

    public final void setY(float y) {
        position.y = y;
    }
    // READONLY ////////////////////////////////////////////////////////////////

    public final Rectangle2D getBounds() {
        return new Rectangle2D.Float(position.x, position.y, size.x, size.y);
    }
}
