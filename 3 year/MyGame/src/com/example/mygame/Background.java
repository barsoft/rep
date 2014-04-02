package com.example.mygame;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL2;
import javax.media.opengl.GLException;

public class Background extends Base {

	public Background(String name,float x, float y, float width, float height,
			File  texture, Renderer r) {
		super(name,x, y, width, height, texture, r);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void loadFromAsset(GL2 gl) throws GLException, IOException {
		// TODO Auto-generated method stub
		super.loadFromAsset(gl);
	}

	@Override
	public void draw(GL2 gl) {
		// TODO Auto-generated method stub
		super.draw(gl);
	}
	
}
