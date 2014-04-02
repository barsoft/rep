package com.example.mygame1;

import java.awt.Font;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLException;

import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.awt.TextureRenderer;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;

public class Hud extends Base {
	private class ToolIcon extends Base {

		public ToolIcon(Base form, Base icon, Tool t, float width,
				float height, float iconWidth, float iconHeight, Renderer r) {
			super("tool", 0, 0, width, height, null, r);
			this.form = form;
			this.icon = icon;
			ratio = iconWidth / iconHeight;
			this.tool = t;
			progressBar = new Base("progressBar", 5, 0, 50, 10,
					Hud.this.progressBar, r);
			progressBarStrip = new Base("progressBarStrip", 5, 0, 50, 10,
					Hud.this.progressBarStrip, r);

			value = new TextLabel(6, 5, 50, 50, r);

		}

		float ratio;

		public boolean showProgressBar;
		public boolean showDigit;

		Base form;
		Base icon;
		Tool tool;
		Base progressBar;
		Base progressBarStrip;
		public TextLabel value;

		@Override
		public void loadFromAsset(GL2 gl) throws GLException, IOException {
			this.form.parent = parent;
			form.loadFromAsset(gl);
			this.icon.parent = parent;
			icon.loadFromAsset(gl);
			progressBar.loadFromAsset(gl);
			progressBar.parent = form;
			progressBarStrip.loadFromAsset(gl);
			progressBarStrip.parent = form;
			value.loadFromAsset(gl);
			value.parent = form;
			
		}

		@Override
		public void draw(GL2 gl) {

			form.draw(gl);
			icon.draw(gl);
			progressBar.draw(gl);
			progressBarStrip.draw(gl);
			if (tool.getClass()==Weapon.class)
			value.drawString(gl, ((Weapon)tool).countBullets+"");
			if (tool.getClass()==MaterialCreatorTool.class)
				value.drawString(gl, ((MaterialCreatorTool)tool).count+"");
		}

		@Override
		public void update() {
			form.width = width;
			form.height = height;
			if (icon.width > icon.height) {
				icon.width = (width - 20);
				icon.height = (height - 20) / ratio;
			} else {
				icon.width = (width - 20) * ratio;
				icon.height = (height - 20);
			}
			form.x = x;
			form.y = y;
			icon.x = x + width / 2 - icon.width / 2;
			icon.y = y + height / 2 - icon.height / 2;
			form.update();
			icon.update();
			progressBar.update();
			progressBar.width = form.width - 10;
			progressBarStrip.update();
			if (tool instanceof Distruptive)
				progressBarStrip.width = (form.width - 10)
						/ (float) (((Distruptive)tool).type).health * ((Distruptive)tool).type.health;
			else
				progressBarStrip.width = 0;
			value.update();
			value.width = form.width - 10;
			value.height = form.width - 10;
		}
	}

	ArrayList<ToolIcon> toolIcons;

	File toolIcon;

	File progressBar;
	File progressBarStrip;

	File highlight;
	Base toolForm ;

	public Hud(String name, float x, float y, float width, float height,
			File texture, Renderer r) {
		super(name, x, y, width, height, texture, r);
		toolIcons = new ArrayList<>();
		highlight = new File("res/highlight.tga");
		toolIcon = new File("res/toolIcon.tga");
		progressBar = new File("res/progressBar.tga");
		progressBarStrip = new File("res/progressBarStrip.tga");
	}

	@Override
	public void loadFromAsset(GL2 gl) throws GLException, IOException {

		toolForm = new Base("toolIcon", 0, 0, 50, 50, toolIcon, r);
		updateHUD(gl);
	}
	
	public void updateHUD(GL2 gl)
	{
		toolIcons.clear();
		for (Tool t : r.robo.quickUseToolsCollection) {

			try {
				toolIcons.add(new ToolIcon(toolForm.clone(), t.icon.clone(), t,
						50, 50, t.width, t.height, r));
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (ToolIcon g : toolIcons) {
			g.parent = parent;
			try {
				g.loadFromAsset(gl);
			} catch (GLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void draw(GL2 gl) {
		for (ToolIcon g : toolIcons) {

			g.draw(gl);
		}
	}

	@Override
	public void update() {
		super.update();
		for (int i = 0; i < toolIcons.size(); i++) {
			if (i == r.robo.currToolNum) {
				toolIcons.get(i).width = 50;
				toolIcons.get(i).height = 50;
			} else {
				toolIcons.get(i).width = 40;
				toolIcons.get(i).height = 40;

			}
			if (i > 0) {
				toolIcons.get(i).x = toolIcons.get(i - 1).x
						- toolIcons.get(i).width;
			} else {
				toolIcons.get(i).x = r.getMapWidth() / 2
						- toolIcons.get(i).width * (i + 1);
			}
			toolIcons.get(i).y = r.getMapHeight() / 2 - toolIcons.get(i).height;
			toolIcons.get(i).update();
		}
	}
}
