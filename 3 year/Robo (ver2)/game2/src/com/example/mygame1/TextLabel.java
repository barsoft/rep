package com.example.mygame1;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLException;

import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.awt.TextureRenderer;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class TextLabel extends Base {

	String text;
	TextureRenderer renderer;

	public TextLabel(float x, float y, float width, float height, Renderer r) {
		super("", x, y, width, height, null, r);
	}
	
	
	@Override
	public void loadFromAsset(GL2 gl) throws GLException, IOException {

	}


	public void drawString(GL2 gl,String text) {
		renderer = new TextureRenderer(100, 100, true);
		Graphics2D g2 = renderer.createGraphics();

		g2.setBackground(new Color(0, 0, 0, 0)); // transparent background
		g2.clearRect(0, 0, 50, 50); // clear all
		g2.scale(1, -1);
		Font font = new Font("Arial", Font.CENTER_BASELINE, 30);
		g2.setFont(font);

		FontMetrics fm = g2.getFontMetrics(font);
		int width2 = fm.stringWidth(text);

		g2.drawString(text, 75 - width2 / 2, -10);

		renderer.markDirty(0, 0, 50, 50);

		gl.glLoadIdentity();
		Texture t = renderer.getTexture();
		texture = t.getTextureObject(gl);

		super.draw(gl);
	}

}
