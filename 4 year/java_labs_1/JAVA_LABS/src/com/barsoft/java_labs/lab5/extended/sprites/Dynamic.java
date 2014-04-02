/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs.lab5.extended.sprites;

import com.barsoft.java_labs.lab5.extended.Game;
import com.barsoft.java_labs.lab5.extended.Vector2D;
import com.barsoft.java_labs.lab5.extended.managers.TextureManager;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Maks
 */
public class Dynamic extends Sprite {

    protected Sprite physics;
    protected Vector2D velocity;
    protected float friction;
    protected float density;
    private float mass;
    private float area;

    public Dynamic(Game game) {
        super(game);
        this.velocity = new Vector2D(0, 0);
        this.friction = 0.1f;
        this.density = 1;
        this.physics = new Sprite(game);
        this.physics.setTexture(TextureManager.getInstance().getTexture("physics"));
    }

    @Override
    public void update() {
        super.update();

        area = getWidth() * getHeight();
        mass = area * density;

        Rectangle2D rect = new Rectangle2D.Float(getX() + velocity.x, getY() + velocity.y, getWidth(), getHeight());
        for (int i = 0; i < game.getSprites().size(); i++) {
            if (game.getSprites().get(i) != this && game.getSprites().get(i) instanceof Dynamic) {
                Dynamic dynamic = (Dynamic) game.getSprites().get(i);
                if (rect.intersects(dynamic.getBounds())) {
                    if (getX() + getWidth() < dynamic.getX() || getX() > dynamic.getX() + dynamic.getWidth()) {
                        float m1 = this.getMass();
                        float m2 = dynamic.getMass();
                        float u1 = this.getVelocity().x;
                        float u2 = dynamic.getVelocity().x;
                        float v1 = 0;
                        float v2 = 0;
                        float c = 1;

                        v1 = (m1 * u1 + m2 * u2 + m2 * c * (u2 - u1)) / (m1 + m2);
                        v2 = (m1 * u1 + m2 * u2 + m1 * c * (u1 - u2)) / (m1 + m2);

                        this.velocity.x = v1;
                        dynamic.velocity.x = v2;
                    } else if (getY() + getHeight() < dynamic.getY() || getY() > dynamic.getY() + dynamic.getHeight()) {
                        float m1 = this.getMass();
                        float m2 = dynamic.getMass();
                        float u1 = this.getVelocity().y;
                        float u2 = dynamic.getVelocity().y;
                        float v1 = 0;
                        float v2 = 0;
                        float c = 1;

                        v1 = (m1 * u1 + m2 * u2 + m2 * c * (u2 - u1)) / (m1 + m2);
                        v2 = (m1 * u1 + m2 * u2 + m1 * c * (u1 - u2)) / (m1 + m2);

                        this.velocity.y = v1;
                        dynamic.velocity.y = v2;
                    } 
                    else {
                        this.velocity.y = 0;
                        dynamic.velocity.y = 0;
                    }
                }
            }
        }

        velocity = velocity.multiply(1 - friction);

        position.x += velocity.x;

        position.y += velocity.y;

        physics.setX(getX());

        physics.setY(getY());

        physics.setWidth(getWidth());

        physics.setHeight(getHeight());

    }

    @Override
    public void draw(Graphics2D g2d) {
        if (game.isPhysicsVisible()) {
            physics.draw(g2d);
        }
        super.draw(g2d);
    }

    // PROPERTIES //////////////////////////////////////////////////////////////
    public final float getDensity() {
        return density;
    }

    public final void setDensity(float density) {
        this.density = density;
    }

    public final float getFriction() {
        return friction;
    }

    public final void setFriction(float friction) {
        this.friction = friction;
    }

    // READONLY ////////////////////////////////////////////////////////////////
    public final float getArea() {
        return area;
    }

    public final float getMass() {
        return mass;
    }

    public final Vector2D getVelocity() {
        return velocity;
    }

    public final Vector2D getPosition() {
        return position;
    }

    public final Vector2D getSize() {
        return size;
    }
}
