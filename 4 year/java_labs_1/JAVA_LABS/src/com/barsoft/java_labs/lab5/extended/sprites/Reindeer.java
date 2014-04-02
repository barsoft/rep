/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs.lab5.extended.sprites;

import com.barsoft.java_labs.lab5.extended.Game;
import com.barsoft.java_labs.lab5.extended.Texture;
import com.barsoft.java_labs.lab5.extended.managers.InputManager;
import com.barsoft.java_labs.lab5.extended.managers.TextureManager;
import java.awt.event.KeyEvent;

/**
 *
 * @author Maks
 */
public class Reindeer extends Dynamic {

    private Texture textureFlipped;
    private Texture textureNormal;

    public Reindeer(Game game) {
        super(game);
    }

    @Override
    public void update() {
        if (InputManager.getInstance().isKeyPressed(KeyEvent.VK_LEFT)) {
            this.texture = textureFlipped;
            velocity.x = -0.5f;
        }
        if (InputManager.getInstance().isKeyPressed(KeyEvent.VK_RIGHT)) {
            this.texture = textureNormal;
            velocity.x = 0.5f;
        }
        super.update();
    }

    public Texture getTextureFlipped() {
        return textureFlipped;
    }

    public void setTextureFlipped(Texture textureFlipped) {
        this.textureFlipped = textureFlipped;
    }

    public Texture getTextureNormal() {
        return textureNormal;
    }

    public void setTextureNormal(Texture textureNormal) {
        this.textureNormal = textureNormal;
    }
}
