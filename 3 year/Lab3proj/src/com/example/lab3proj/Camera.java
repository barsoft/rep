package com.example.lab3proj;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Camera {
	float x;
	float y;
	float zoom;

	public Camera(int x, int y, int zoom) {
		this.x=x;
		this.y=y;
		this.zoom=zoom;
	}

	public void Update(GL10 gl)
	{
		gl.glTranslatef(x, y, -zoom);
	}
}