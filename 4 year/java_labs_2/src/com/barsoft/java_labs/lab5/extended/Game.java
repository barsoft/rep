/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs.lab5.extended;

import com.barsoft.java_labs.lab5.extended.managers.InputManager;
import com.barsoft.java_labs.lab5.extended.managers.TextureManager;
import com.barsoft.java_labs.lab5.extended.sprites.Dynamic;
import com.barsoft.java_labs.lab5.extended.sprites.Reindeer;
import com.barsoft.java_labs.lab5.extended.sprites.Santa;
import com.barsoft.java_labs.lab5.extended.sprites.SinMoon;
import com.barsoft.java_labs.lab5.extended.sprites.Sprite;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Maks
 */
public class Game {

    private JPanel jPanel;
    private ArrayList< Sprite> sprites;
    private int worldWidth;
    private int worldHeight;
    private boolean physicsVisible;
    private int viewportWidth;
    private int viewportHeight;
    private Random random = new Random();

    public Game(JPanel jPanel) {
        this.jPanel = jPanel;

        this.jPanel.addMouseListener(InputManager.getInstance());
        this.jPanel.addMouseMotionListener(InputManager.getInstance());
        this.jPanel.addKeyListener(InputManager.getInstance());

        this.sprites = new ArrayList<>();

        physicsVisible = false;

        this.worldWidth = 1300;
        this.worldHeight = 700;

        int wallSize = 500;
        int wallVisibleSize = 10;

        Dynamic wall = new Dynamic(this);
        wall.setWidth(wallSize);
        wall.setHeight(getWorldHeight());
        wall.setX(wallVisibleSize - wallSize);
        wall.setY(0);
        wall.setDensity(200);
        wall.setFriction(1);
        sprites.add(wall);

        wall = new Dynamic(this);
        wall.setWidth(wallSize);
        wall.setHeight(getWorldHeight());
        wall.setX(getWorldWidth() - wallVisibleSize);
        wall.setY(0);
        wall.setDensity(200);
        wall.setFriction(1);
        sprites.add(wall);

        wall = new Dynamic(this);
        wall.setWidth(getWorldWidth() - wallVisibleSize * 2);
        wall.setHeight(wallSize);
        wall.setX(wallVisibleSize);
        wall.setY(wallVisibleSize - wallSize);
        wall.setDensity(200);
        wall.setFriction(1);
        sprites.add(wall);

        wall = new Dynamic(this);
        wall.setWidth(getWorldWidth() - wallVisibleSize * 2);
        wall.setHeight(wallSize);
        wall.setX(wallVisibleSize);
        wall.setY(getWorldHeight() - wallVisibleSize);
        wall.setDensity(200);
        wall.setFriction(1);
        sprites.add(wall);

        for (int i = 0; i < getWorldWidth() / 100; i++) {
            for (int j = 0; j < getWorldHeight() / 100; j++) {
                Dynamic star = new Dynamic(this);
                star.setTexture(TextureManager.getInstance().getTexture("star"));
                star.getVelocity().x = 0f;
                star.getVelocity().y = 0f;
                star.setFriction(0.001f);

                int size = random.nextInt(10) + 20;
                star.setWidth(size);
                star.setHeight(size);

                star.setX((i + 1) * 100 + random.nextInt(50));
                star.setY((j + 1) * 100 + random.nextInt(50));

                sprites.add(star);
            }
        }

        SinMoon moon = new SinMoon(this);
        moon.setTexture(TextureManager.getInstance().getTexture("moon"));
        moon.getVelocity().x = 1.1f;
        moon.setFriction(0);
        moon.setAmplitude(getWorldHeight() - 100);
        int size = random.nextInt(50) + 50;
        moon.setHeight(size);
        moon.setWidth(size * 0.7f);
        moon.setDensity(200);
        moon.setX(getWorldWidth() / 2);
        moon.setY(getWorldHeight() / 2);
        sprites.add(moon);

        Santa santa = new Santa(this);
        santa.setTexture(TextureManager.getInstance().getTexture("santa"));
        santa.setX(50);
        santa.setY(50);
        santa.setDensity(200);
        santa.setWidth(santa.getTexture().getSize().x / 5);
        santa.setHeight(santa.getTexture().getSize().y / 5);
        sprites.add(santa);

        Reindeer reindeer = new Reindeer(this);
        reindeer.setTextureNormal(TextureManager.getInstance().getTexture("reindeer"));
        reindeer.setTextureFlipped(TextureManager.getInstance().getTexture("reindeer_flipped"));
        reindeer.setTexture(TextureManager.getInstance().getTexture("reindeer"));
        reindeer.setWidth(reindeer.getTexture().getSize().x / 2);
        reindeer.setHeight(reindeer.getTexture().getSize().y / 2);
        reindeer.setX(0);
        reindeer.setY(getWorldHeight() - reindeer.getHeight());
        reindeer.setDensity(200);

        sprites.add(reindeer);

    }

    public void load() {
//        for (Sprite sprite : sprites) {
//            final Sprite spr = sprite;
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    while (true) {
//                         spr.update();
//                        try {
//                            Thread.sleep(2);
//                        } catch (InterruptedException ex) {
//                            Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    }
//                }
//            });
//
//            thread.start();
//        }
        for (Sprite sprite : sprites) {
            sprite.load();
        }
    }

    public void draw(Graphics2D g2d) {

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getViewportWidth();
        int h = getViewportHeight();
        Color color1 = Color.BLUE;
        Color color2 = Color.GRAY;
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);

        for (Sprite sprite : sprites) {
            sprite.draw(g2d);
        }
    }

    public void update() {
        viewportWidth = jPanel.getWidth();
        viewportHeight = jPanel.getHeight();
        for (Sprite sprite : sprites) {
            sprite.update();
        }
    }

    public void drawString(Graphics2D g2d, String text, int x, int y, Color color) {
        g2d.setColor(color);
        g2d.setFont(new Font(null, Font.PLAIN, 12));
        g2d.drawString(text, x, y);
    }

    public List<Sprite> getSprites() {
        return Collections.unmodifiableList(sprites);
    }

    public boolean isPhysicsVisible() {
        return physicsVisible;
    }

    public final int getWorldWidth() {
        return worldWidth;
    }

    public final int getWorldHeight() {
        return worldHeight;
    }

    public int getViewportWidth() {
        return viewportWidth;
    }

    public int getViewportHeight() {
        return viewportHeight;
    }
}
