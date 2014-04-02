package com.example.lab3proj;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

public class Base {
	private float[] uv1 = new float[2];
	private float[] uv0 = new float[2];

	private int bitmapWidth;
	private int bitmapHeight;
	public Bitmap bitmap;

	private FloatBuffer vertexBuffer;
	private float vertices[];

	private FloatBuffer uvCoordBuffer;
	private float uvCoord[];

	protected int[] textures = new int[1];
	public float width;
	public float height;
	public float x;
	public float y;
	
	protected MainActivity activity;

	public Base(float f, float g, float zoomX, float zoomY, Bitmap bitmap, MainActivity activity) {

		vertices = new float[] { 
				0.0f, 1.0f, 0.0f,
				0.0f, 0.0f, 0.0f,
				1.0f,0.0f, 0.0f, 
				1.0f, 1.0f, 0.0f, };
		uvCoord = new float[] { 
				uv1[0], uv0[1],
				uv1[0], uv0[0],
				uv1[1], uv0[0],
				uv1[1], uv0[1], };

		uvCoord[0] = 0.0f; uvCoord[1] = 1.0f;
		uvCoord[2] = 0.0f; uvCoord[3] = 0.0f;
		uvCoord[4] = 1.0f; uvCoord[5] = 0.0f;
		uvCoord[6] = 1.0f; uvCoord[7] = 1.0f;

		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		vertexBuffer = byteBuffer.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);

		byteBuffer = ByteBuffer.allocateDirect(uvCoord.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		uvCoordBuffer = byteBuffer.asFloatBuffer();
		uvCoordBuffer.put(uvCoord);
		uvCoordBuffer.position(0);

		this.bitmap = bitmap;
		this.x=f;
		this.y=g;
		this.width=zoomX;
		this.height=zoomY;
		this.activity=activity;
	}

	public void loadFromAsset(GL10 gl, Context context) {
		bitmapWidth = bitmap.getWidth();
		bitmapHeight = bitmap.getHeight();

		gl.glGenTextures(1, textures, 0);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
				GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
				GL10.GL_LINEAR);

		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

		bitmap.recycle();
	}

	/** The draw method for the square with the GL context */
	public void draw(GL10 gl) {
		gl.glLoadIdentity();
		gl.glTranslatef(x, y, 0);
		gl.glScalef(width, height, 0);
		gl.glRotatef(0, 0, 0, 1);
		
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, uvCoordBuffer.position(0));
		
		gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA); 
		gl.glEnable(GL10.GL_BLEND);
		
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, vertices.length/3);
	}
}