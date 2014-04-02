package com.example.mygame1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.GLException;

public class Ground extends Distructable {
	// ///////////////////////////////////////////////////////////////
	// STATIC PART////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////
	public static ArrayList<Ground> arr;

	private Ground(String name, float x, float y, float width, float height,
			File texture, int health, Renderer r) {
		super(name, x, y, width, height, texture, r);
		this.health = health;
		highlight = new Base("h", 0, 0, width, height, r.highlight, r);
		highlight.parent = this;
	}

	public static void loadTypes(Renderer r) {
		arr = new ArrayList<>();
		arr.add(new Ground("ground", 0, 0, r.blockWidth, r.blockHeight,
				new File("res/materials/ground.tga"), 150, r));
		arr.add(new Ground("stone", 0, 0, r.blockWidth, r.blockHeight,
				new File("res/materials/stone.tga"), 250, r));
		arr.add(new Ground("iron", 0, 0, r.blockWidth, r.blockHeight, new File(
				"res/materials/iron.tga"), 300, r));
		arr.add(new Ground("gold", 0, 0, r.blockWidth, r.blockHeight, new File(
				"res/materials/gold.tga"), 400, r));
		arr.add(new Ground("lead", 0, 0, r.blockWidth, r.blockHeight, new File(
				"res/materials/lead.tga"), 800, r));
	}

	public static Ground getTypeByName(String name) {
		for (Ground g : arr) {
			if (g.name == name) {
				return g;
			}
		}
		return null;
	}

	// ////////////////////////////////////////////////////////////////
	// / DYNAMIC PART//////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////
	public Ground type;
	public boolean highlightEnable;
	Base highlight;

	public Ground(float x, float y, Ground type, Renderer r) {
		super(type.name, x, y, type.width, type.height, type.im, r);
		highlight = new Base("h", 0, 0, width, height, r.highlight, r);
		highlight.parent = this;
		health = type.health;
		this.type = type;
	}

	@Override
	public void loadFromAsset(GL2 gl) throws GLException, IOException {
		// TODO Auto-generated method stub
		super.loadFromAsset(gl);
		highlight.loadFromAsset(gl);
	}

	@Override
	public void draw(GL2 gl) {
		// TODO Auto-generated method stub
		if (r.cam.ex + r.getMapWidth() / 2 + 100 > x
				&& r.cam.ex - r.getMapWidth() / 2 - 100 < x
				&& r.cam.y - r.getMapHeight() / 2 - 100 < y
				&& r.cam.y + r.getMapHeight() / 2 > y) {
			super.draw(gl);
			if (highlightEnable)
				highlight.draw(gl);
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		highlight.update();
	}

}
