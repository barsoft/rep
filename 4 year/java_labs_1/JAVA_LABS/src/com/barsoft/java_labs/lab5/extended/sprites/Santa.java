/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs.lab5.extended.sprites;

import com.barsoft.java_labs.lab5.extended.Game;
import com.barsoft.java_labs.lab5.extended.Vector2D;
import com.barsoft.java_labs.lab5.extended.managers.InputManager;

/**
 *
 * @author Maks
 */
public class Santa extends Dynamic {

    public Santa(Game game) {
        super(game);
    }

    @Override
    public void update() {

        Vector2D mousePos = Vector2D.fromViewport(InputManager.getInstance().getMousePosition(), game);
        if (InputManager.getInstance().isMousePressed()) {
            if (getX() + getWidth() / 2 < mousePos.x) {
                velocity.x = 1.1f;
            }
            if (getX() + getWidth() / 2 > mousePos.x) {
                velocity.x = -1.1f;
            }
            if (getY() + getHeight() / 2 < mousePos.y) {
                velocity.y = 1.1f;
            }
            if (getY() + getHeight() / 2 > mousePos.y) {
                velocity.y = -1.1f;
            }

        }
        super.update();
    }
}
