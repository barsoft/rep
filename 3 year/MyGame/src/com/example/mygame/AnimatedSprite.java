package com.example.mygame;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLException;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class AnimatedSprite extends Base {

	float currFrame;
	String currentAnimation;
	float animationSpeed;
	ArrayList<Animation> animations;

	public AnimatedSprite(String name,float x, float y, float width, float height, Renderer r) {
		super(name,x, y, width, height, null, r);
		
		currFrame=0;
		animations=new ArrayList<Animation>();
	}

	@Override
	public void loadFromAsset(GL2 gl) throws GLException, IOException {
		gl.glLoadIdentity();

		for (Animation anim : animations) {
			anim.loadFromAsset(gl);
			anim.parent=this;
			anim.parentRotater=this;
			anim.parentLocalRotater=this;
		}
	}

	@Override
	public void draw(GL2 gl) {
		for (Animation anim : animations) {
			if (anim.name==currentAnimation)
			{
				anim.draw(gl,currFrame);
				currFrame+=animationSpeed;
				if (currFrame>=anim.countFrames)
					currFrame=0;
			}
		}
	}
	@Override
	public void update()
	{
		super.update();
		
		for (Animation anim : animations) {
			if (anim.name==currentAnimation)
			{
				anim.update();
			}
		}
	}
	
	public void addAinamation(String name,String texturePath,int countFrames)
	{
		animations.add(new Animation(name,x,y,width,height, texturePath, countFrames, r));
	}
	
	public void setAnimation(String name,float animationSpeed)
	{
		this.animationSpeed=animationSpeed;
		currentAnimation=name;
		currFrame=0;
	}
}
