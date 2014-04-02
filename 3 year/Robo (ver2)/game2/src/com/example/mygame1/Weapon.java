package com.example.mygame1;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;

import javax.media.opengl.GLEventListener;

public class Weapon extends Tool {
	// ///////////////////////////////////////////////////////////////
	// STATIC PART////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////
	public static ArrayList<Weapon> arr;

	private Weapon(String name, float x, float y, float width, float height,
			File texture, float laserWidth, float laserHeight, int power,
			Renderer r) {
		super(name, x, y, width, height, texture, r);
		this.power = power;
		this.icon = new Base("icon", 0, 0, 50, 50, texture, r);
		this.laserAimWidth = laserWidth;
		this.laserAimHeight = laserHeight;
	}

	public static void loadTypes(Renderer r) {
		arr = new ArrayList<>();
		arr.add(new Weapon("laserGun", 0.8f, 0.5f, 10, 10, new File(
				"res/tools/weapons/laserGun.tga"), 400, 0.5f, 50, r));
		arr.add(new Weapon("ak47", 0.55f, 0.50f, 35, 15, new File(
				"res/tools/weapons/ak47.tga"), 400, 0.5f, 50, r));
		arr.add(new Weapon("m4a1", 0.55f, 0.45f, 35, 15, new File(
				"res/tools/weapons/m4a1.tga"), 400, 0.5f, 50, r));
	}

	public static Weapon getTypeByName(String name) {
		for (Weapon g : arr) {
			if (g.name == name) {
				return g;
			}
		}
		return null;
	}

	// ///////////////////////////////////////////////////////////////
	// DYNAMIC PART///////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////
	public int power;
	public Weapon type;
	public int countBullets;

	public Weapon(Weapon type, Renderer r) {
		super(type.name, type.inHandX, type.inHandY, type.width, type.height,
				type.im, r);
		this.type = type;
		this.power = type.power;
		this.icon = type.icon;
		this.health = type.health;
		this.laserAimWidth = type.laserAimWidth;
		this.laserAimHeight = type.laserAimHeight;
	}

	@Override
	public void update() {
		super.update();
		
		for (Ground g : ((Renderer) r).grounds) {
			Rectangle2D.Float rec = new Rectangle2D.Float(g.ex, g.ey, g.width,
					g.height);

			g.highlightEnable = false;
		}
		
		if (rightMouseButtonProcess) {
			drawLaser = true;
		} else
			drawLaser = false;

		if (leftMouseButtonProcess) {
			if (parentRotater.anglelocal == 0)
				if (flip)
					parentRotater.anglelocal = 5;
				else
					parentRotater.anglelocal = -5;
		} else
			parentRotater.anglelocal = 0;

		if (parentRotater.anglelocal > 0)
			parentRotater.anglelocal -= 0.5f;
		if (parentRotater.anglelocal < 0)
			parentRotater.anglelocal += 0.5f;

	}

}
