package com.example.mygame1;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;

public class MaterialCreatorTool extends Tool {
	// ///////////////////////////////////////////////////////////////
	// STATIC PART////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////
	public static ArrayList<MaterialCreatorTool> arr;

	private MaterialCreatorTool(String name, float x, float y, float width,
			float height, File texture, float laserWidth, float laserHeight,
			int power, Renderer r) {
		super(name, x, y, width, height, texture, r);
		this.power = power;
		this.icon = new Base("icon", 0, 0, 50, 50, texture, r);
		this.laserAimWidth = laserWidth;
		this.laserAimHeight = laserHeight;
	}

	public static void loadTypes(Renderer r) {
		arr = new ArrayList<>();
		for (Ground g : Ground.arr) {
			arr.add(new MaterialCreatorTool(g.name, 0.8f, 0.5f, 10, 10,g.im, 400, 0.5f, 50,
					r));
		}
	}

	public static MaterialCreatorTool getTypeByName(String name) {
		for (MaterialCreatorTool g : arr) {
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
	public MaterialCreatorTool type;
	public int count;
	public MaterialCreatorTool(MaterialCreatorTool type, Renderer r) {
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
	}

}
