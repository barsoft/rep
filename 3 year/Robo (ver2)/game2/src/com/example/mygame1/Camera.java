package com.example.mygame1;

import javax.media.opengl.GL2;

public class Camera extends MapObject {
	
	public float zoom;
	public float minx;
	
	Renderer r;
	public Camera(Renderer r) {
		zoom = 200.0f;
		this.r=r;
		x=zoom;
		y=zoom;
	}
	public void update(GL2 gl)
	{

		gl.glTranslatef(-x*r.ratio,y, -zoom);
		ex=x*r.ratio;
		ey=y;
	}
}
