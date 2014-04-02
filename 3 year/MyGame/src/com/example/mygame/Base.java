package com.example.mygame;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.IntBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLException;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class Base extends MapObject implements Cloneable {

	public Base clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (Base) super.clone();
	}

	public BufferedImage bitmap;

	Renderer r;
	String texturePath;

	private int texture;

	File im;
	String name;

	public Base(String name, float x, float y, float width, float height,
			File texture, Renderer r) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.name = name;
		this.r = r;
		origin = new Point2D.Float();
		origin.x = width / 2;
		origin.y = height / 2;

		originLocal = new Point2D.Float();
		originLocal.x = width / 2;
		originLocal.y = height / 2;

		this.im=texture;

	}

	public void loadFromAsset(GL2 gl) throws GLException, IOException {
		gl.glLoadIdentity();
		Texture t = TextureIO.newTexture(im, true);
		texture = t.getTextureObject(gl);

	}

	public void draw(GL2 gl) {
		gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE,
				GL2.GL_REPLACE);

		gl.glLoadIdentity();

		gl.glTranslatef(ex + origin.x, ey + origin.y, 0);
		gl.glRotatef(angle, 0, 0, 1);
		gl.glTranslatef(-origin.x, -origin.y, 0);
		gl.glTranslatef(originLocal.x, originLocal.y, 0);
		gl.glRotatef(anglelocal, 0, 0, 1);
		gl.glTranslatef(-originLocal.x, -originLocal.y, 0);

		gl.glBindTexture(GL.GL_TEXTURE_2D, texture);

		gl.glBegin(GL2.GL_QUADS);

		if (flip) {
			gl.glVertex3f(0 + width, 0, 0.0f);
			// gl.glTexCoord2f(0, 0);
			gl.glTexCoord2f(0, 0);
			gl.glVertex3f(0 + width, 0 + height, 0.0f);
			// gl.glTexCoord2f(1, 0);
			gl.glTexCoord2f(1, 0);
			gl.glVertex3f(0, 0 + height, 0.0f);
			// gl.glTexCoord2f(1, 1);
			gl.glTexCoord2f(1, 1);
			gl.glVertex3f(0, 0, 0.0f);
			// gl.glTexCoord2f(0, 1);
			gl.glTexCoord2f(0, 1);
		} else {
			gl.glVertex3f(0, 0, 0.0f);
			// gl.glTexCoord2f(0, 0);
			gl.glTexCoord2f(0, 0);
			gl.glVertex3f(0, 0 + height, 0.0f);
			// gl.glTexCoord2f(1, 0);
			gl.glTexCoord2f(1, 0);
			gl.glVertex3f(0 + width, 0 + height, 0.0f);
			// gl.glTexCoord2f(1, 1);
			gl.glTexCoord2f(1, 1);
			gl.glVertex3f(0 + width, 0, 0.0f);
			// gl.glTexCoord2f(0, 1);
			gl.glTexCoord2f(0, 1);
		}

		gl.glEnd();
	}

	public void update() {
		if (parent != null) {
			ex = parent.ex + x;
			ey = parent.ey + y;
			flip = parent.flip;
		} else {
			ex = x;
			ey = y;
		}
		if (parentSizer != null) {
			width = parentSizer.width;
			height = parentSizer.height;
		}
		if (parentRotater != null) {
			angle = parentRotater.angle;
		}
		if (parentLocalRotater != null) {
			anglelocal = parentLocalRotater.anglelocal;
		}
	}

}