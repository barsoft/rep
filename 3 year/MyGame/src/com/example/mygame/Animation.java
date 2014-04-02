package com.example.mygame;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLException;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class Animation extends Base {

	int countFrames;
	int textures[];

	public Animation(String name, float x, float y, float width, float height,
			String  texture, int countFrames, Renderer r) {
		super(name,x, y, width, height, null, r);
		this.countFrames = countFrames;
		this.name = name;
		textures = new int[countFrames];
		this.texturePath=texture;
	}

	@Override
	public void loadFromAsset(GL2 gl) throws GLException, IOException {
		for (int i = 0; i < countFrames; i++) {
			File im = new File(texturePath + " (" + (i + 1) + ").tga");
			Texture t = TextureIO.newTexture(im, true);
			textures[i] = t.getTextureObject(gl);
		}
	}

	public void draw(GL2 gl, float currFrame) {
		gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE,
				GL2.GL_REPLACE);

		gl.glLoadIdentity();

		gl.glTranslatef(ex + origin.x, ey + origin.y, 0);
		gl.glRotatef(angle, 0, 0, 1);
		gl.glTranslatef(-origin.x, -origin.y, 0);
		gl.glTranslatef(originLocal.x,originLocal.y,0);
		gl.glRotatef(anglelocal, 0, 0, 1);
		gl.glTranslatef(-originLocal.x,-originLocal.y,0);

		gl.glBindTexture(GL.GL_TEXTURE_2D, textures[(int) currFrame]);

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

}
