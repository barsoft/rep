/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs.lab5.extended.managers;

import com.barsoft.java_labs.lab5.extended.Texture;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Maks
 */
public class TextureManager {

    private List<Texture> textures;
    public static TextureManager instance = new TextureManager();

    public TextureManager() {
        textures = new ArrayList<>();
        ////////////////////////////////////////////////////////////////////////
        // MOON/////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////
        Ellipse2D.Double stem = new Ellipse2D.Double();
        stem.setFrame(0, 0, 100, 100);
        Area st1 = new Area(stem);
        stem.setFrame(0 + 100 / 5, 0, 100, 100);
        Area st2 = new Area(stem);
        st1.subtract(st2);

        Point2D start = new Point2D.Float(0, 0);
        Point2D end = new Point2D.Float(1, 5);
        float[] dist = {0.0f, 0.2f, 1.0f};
        Color[] colors = {Color.GRAY, Color.WHITE, Color.YELLOW};
        LinearGradientPaint p =
                new LinearGradientPaint(start, end, dist, colors, MultipleGradientPaint.CycleMethod.REPEAT);

        BufferedImage image = new BufferedImage(st1.getBounds().width, st1.getBounds().height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.GRAY);
        g2d.setPaint(p);
        g2d.setStroke(new BasicStroke(5));
        g2d.fill(st1);
        g2d.dispose();

        Texture tex = new Texture();
        tex.setImage(image);
        tex.setName("moon");
        textures.add(tex);
        ////////////////////////////////////////////////////////////////////////
        // PHYSICS RECTANGLE ///////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////
        image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        g2d = image.createGraphics();
        g2d.setColor(new Color(0, 255, 0, 50));
        g2d.fillRect(0, 0, 100, 100);
        g2d.dispose();

        tex = new Texture();
        tex.setImage(image);
        tex.setName("physics");
        textures.add(tex);

        ////////////////////////////////////////////////////////////////////////
        // STAR ////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////
        double points[][] = {
            {0, 85}, {75, 75}, {100, 10}, {125, 75},
            {200, 85}, {150, 125}, {160, 190}, {100, 150},
            {40, 190}, {50, 125}, {0, 85}
        };
        GeneralPath star = new GeneralPath();
        star.moveTo(0, 85);
        for (int k = 1; k < points.length; k++) {
            star.lineTo(points[k][0], points[k][1]);
        }
        star.closePath();

        image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        g2d = image.createGraphics();
        g2d.setColor(Color.YELLOW);
        g2d.fill(star);
        g2d.dispose();

        tex = new Texture();
        tex.setImage(image);
        tex.setName("star");
        textures.add(tex);

        ////////////////////////////////////////////////////////////////////////
        // SANTA ///////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////
        try {
            image = ImageIO.read(new File(Texture.class.getResource("santa.png").getFile()));
        } catch (IOException ex) {
            Logger.getLogger(TextureManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        g2d = image.createGraphics();
        g2d.dispose();

        tex = new Texture();
        tex.setImage(image);
        tex.setName("santa");
        textures.add(tex);

        ////////////////////////////////////////////////////////////////////////
        // REINDEER ////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////
        try {
            image = ImageIO.read(new File(Texture.class.getResource("reindeer.png").getFile()));
        } catch (IOException ex) {
            Logger.getLogger(TextureManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        g2d = image.createGraphics();
        g2d.dispose();

        tex = new Texture();
        tex.setImage(image);
        tex.setName("reindeer");
        textures.add(tex);

        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        image = op.filter(image, null);

        tex = new Texture();
        tex.setImage(image);
        tex.setName("reindeer_flipped");
        textures.add(tex);
    }

    public static TextureManager getInstance() {
        return instance;
    }

    public List<Texture> getTextures() {
        return Collections.unmodifiableList(textures);
    }

    public Texture getTexture(String name) {
        for (int i = 0; i < textures.size(); i++) {
            if (textures.get(i).getName().equals(name)) {
                return textures.get(i);
            }
        }
        return null;
    }
}
