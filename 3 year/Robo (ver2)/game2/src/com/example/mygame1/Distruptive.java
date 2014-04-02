package com.example.mygame1;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;

import javax.media.opengl.GLEventListener;

public class Distruptive extends Tool {
	// ///////////////////////////////////////////////////////////////
	// STATIC PART////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////
	public static ArrayList<Distruptive> arr;

	private Distruptive(String name, float x, float y, float width,
			float height, File texture, int power, Renderer r) {
		super(name, x, y, width, height, texture, r);
		this.power = power;
		this.icon = new Base("icon", 0, 0, 50, 50, texture, r);
	}

	public static void loadTypes(Renderer r) {
		arr = new ArrayList<>();
		arr.add(new Distruptive("hand", 0.55f, 0.45f, 35, 15, new File(
				"res/tools/distruptive/hand.tga"), 1, r));
		arr.add(new Distruptive("lopata", 0.55f, 0.45f, 35, 15, new File(
				"res/tools/distruptive/lopata.tga"), 2, r));
		arr.add(new Distruptive("pick", 0.6f, 0.3f, 20, 20, new File(
				"res/tools/distruptive/pick.tga"), 3, r));
	}

	public static Distruptive getTypeByName(String name) {
		for (Distruptive g : arr) {
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
	public Distruptive type;

	public Distruptive(Distruptive type, Renderer r) {
		super(type.name, type.inHandX, type.inHandY, type.width, type.height,
				type.im, r);
		this.type = type;
		this.power = type.power;
		this.icon = type.icon;
		this.health = type.health;
		drawLaser = false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();

		if (leftMouseButtonProcess)
			parentRotater.anglelocal -= 5;
		else
			parentRotater.anglelocal = 0;

		if (parentRotater.anglelocal <= -40)
			parentRotater.anglelocal = 0;

		Rectangle2D.Float laserLine;

		if (!flip) {
			laserLine = new Rectangle2D.Float(
					(float) (parent.ex + parent.width / 2),
					(float) (parent.ey + parent.height / 2),

					(float) ((laserAim.width + 15) * Math.cos(Math
							.toRadians(angle))),
					(float) ((laserAim.width + 15) * Math.sin(Math
							.toRadians(angle))));
		} else {
			laserLine = new Rectangle2D.Float(
					(float) (parent.ex + parent.width / 2),
					(float) (parent.ey + parent.height / 2),

					(float) ((laserAim.width - 15) * Math.cos(Math
							.toRadians(angle))),
					(float) ((laserAim.width - 15) * Math.sin(Math
							.toRadians(angle))));
		}

		for (Ground g : ((Renderer) r).grounds) {
			Rectangle2D.Float rec = new Rectangle2D.Float(g.ex, g.ey, g.width,
					g.height);

			g.highlightEnable = false;

			if (intersects(rec, laserLine) ) {
				g.highlightEnable = true;

			}
		}

		for (int i = 0; i < ((Renderer) r).grounds.size(); i++) {

			if (((Renderer) r).grounds.get(i).highlightEnable) {
				if (leftMouseButtonProcess) {
					((Renderer) r).grounds.get(i).health -= power;
					if (((Renderer) r).grounds.get(i).health <= 0) {
						((Renderer) r).grounds.remove(((Renderer) r).grounds
								.get(i));
						i = 0;
					}
				} else
					((Renderer) r).grounds.get(i).health = ((Renderer) r).grounds
							.get(i).type.health;
			} else
				((Renderer) r).grounds.get(i).health = ((Renderer) r).grounds
						.get(i).type.health;

		}
	}

}
