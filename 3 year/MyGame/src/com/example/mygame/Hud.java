package com.example.mygame;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLException;

public class Hud extends Base {
	private class ToolIcon extends Base {
		public ToolIcon(Base form, Base icon, float width, float height,
				float iconWidth, float iconHeight, Renderer r) {
			super("tool", 0, 0, width, height, null, r);
			this.form = form;
			this.icon = icon;
			xRatio = iconWidth / width;
			yRatio = iconHeight / height;
		}

		float xRatio;
		float yRatio;

		Base form;
		Base icon;

		@Override
		public void loadFromAsset(GL2 gl) throws GLException, IOException {
			this.form.parent = parent;
			form.loadFromAsset(gl);
			this.icon.parent = parent;
			icon.loadFromAsset(gl);
		}

		@Override
		public void draw(GL2 gl) {
			form.draw(gl);
			icon.draw(gl);
		}

		@Override
		public void update() {
			form.width = width;
			form.height = height;
			icon.width = width * xRatio;
			icon.height = height * yRatio;
			form.x = x;
			form.y = y;
			icon.x = x + width / 2 - icon.width/2;
			icon.y = y + height / 2 - icon.height/2;
			form.update();
			icon.update();
		}
	}

	ArrayList<ToolIcon> weaponIcon;

	public Hud(String name, float x, float y, float width, float height,
			File texture, Renderer r) {
		super(name, x, y, width, height, texture, r);
		weaponIcon = new ArrayList<>();
	}

	@Override
	public void loadFromAsset(GL2 gl) throws GLException, IOException {
		Base toolForm = new Base("weaponIcon", 0, 0, 50, 50, r.weaponIcon, r);
		try {

			for (Tool t : r.robo.quickUseToolsCollection) {

				weaponIcon.add(new ToolIcon(toolForm.clone(), t.icon.clone(),
						50, 50, t.width, t.height, r));
			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (ToolIcon g : weaponIcon) {
			g.parent = parent;
			g.loadFromAsset(gl);
		}
	}

	@Override
	public void draw(GL2 gl) {
		for (ToolIcon g : weaponIcon) {
			g.draw(gl);
		}
	}

	@Override
	public void update() {
		super.update();
		/*
		 * for (int i = 0; i < weaponIcon.size(); i++) { if
		 * (r.robo.quickUseToolsCollection.get(i) == r.robo.getCurrentTool()) {
		 * 
		 * } }
		 */
		for (int i = 0; i < weaponIcon.size(); i++) {
			if (i == r.robo.currToolNum) {
				weaponIcon.get(i).width = 50;
				weaponIcon.get(i).height = 50;
			} else {
				weaponIcon.get(i).width = 40;
				weaponIcon.get(i).height = 40;

			}
			if (i > 0) {
				weaponIcon.get(i).x = weaponIcon.get(i - 1).x
						- weaponIcon.get(i).width;
			} else {
				weaponIcon.get(i).x = r.getMapWidth() / 2
						- weaponIcon.get(i).width * (i + 1);
			}
			weaponIcon.get(i).y = r.getMapHeight() / 2
					- weaponIcon.get(i).height;
			weaponIcon.get(i).update();
		}
	}
}
