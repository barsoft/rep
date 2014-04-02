package com.example.mygame;

import java.awt.Event;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.media.nativewindow.util.Dimension;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLException;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import javax.swing.JFrame;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class Renderer implements GLEventListener {
	public float blockHeight;
	public float blockWidth;
	Robo robo;
	ArrayList<Background> backGround;
	ArrayList<Ground> grounds;
	ArrayList<Ground> stones;

	File ground;
	File stone;
	File highlight;
	File laser;
	File head;
	File lopata;
	File weaponIcon;

	private int t = 0;

	public Hud hud;

	public Game g;
	public Camera cam;
	public float ratio;

	private File background;


	public Renderer(Game g) {
		this.g = g;

		grounds = new ArrayList<Ground>();
		stones = new ArrayList<Ground>();
		backGround= new ArrayList<Background>();
	}

	public void init(GLAutoDrawable arg0) {

		GL2 gl = (GL2) arg0.getGL();

		loadGL(gl);
		
		blockWidth=30;
		blockHeight=30;
		
		Ground.loadTypes(this);
		Weapon.loadTypes(this);
		Distruptive.loadTypes(this);
		
		highlight = new File("res/highlight.tga");

		laser = new File("res/laser.tga");
		head = new File("res/robo/head.tga");
		lopata = new File("res/weapons/lopata.tga");
		background = new File("res/background.tga");
		weaponIcon = new File("res/weaponIcon.tga");

		robo = new Robo(0, 0, this);

		cam = new Camera(this);
		
		ratio = g.mainFrame.bounds().width
				/ (float) g.mainFrame.bounds().height;

		hud = new Hud("hud",-getMapWidth()/2,-getMapHeight()/2, getMapWidth(), getMapHeight(), null, this);

		hud.parent = cam;

		

		for (int i = 0; i < 30; i++) {
		backGround.add( new Background("back",i*(getMapWidth() + 200), 0, getMapWidth() + 200, getMapHeight(),
				background, this));
		}

		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 100; j++) {
				
				grounds.add(new Ground(i * blockWidth, getMapHeight()  + blockHeight * j,Ground.getTypeByName("ground"), this));
			}
		}
		
		Random rnd = new Random();
		for (int i = 0; i < grounds.size() / 4; i++) {
			Ground g = grounds.get(rnd.nextInt(grounds.size()));
			grounds.add(new Ground(g.x, g.y,Ground.getTypeByName("stone"), this));
			grounds.remove(g);
		}
		for (int i = 0; i < grounds.size() / 40; i++) {
			Ground g = grounds.get(rnd.nextInt(grounds.size()));
			grounds.add(new Ground(g.x, g.y,Ground.getTypeByName("iron"), this));
			grounds.remove(g);
		}
		for (int i = 0; i < grounds.size() / 300; i++) {
			Ground g = grounds.get(rnd.nextInt(grounds.size()));
			grounds.add(new Ground(g.x, g.y, Ground.getTypeByName("gold"), this));
			grounds.remove(g);
		}
		for (int i = 0; i < grounds.size() / 300; i++) {
			Ground g = grounds.get(rnd.nextInt(grounds.size()));
			grounds.add(new Ground(g.x, g.y, Ground.getTypeByName("lead"), this));
			grounds.remove(g);
		}
		for (int i = 0; i < grounds.size() / 5; i++) {
			Ground g = grounds.get(rnd.nextInt(grounds.size()));
			grounds.remove(g);
		}
		

		try {
			robo.loadFromAsset(gl);
			for (Background g : backGround) {
				g.loadFromAsset(gl);
			}

			hud.loadFromAsset(gl);

			System.out.println("pass");

			for (Ground g : grounds) {
				g.loadFromAsset(gl);
			}
			for (Ground g : stones) {
				g.loadFromAsset(gl);
			}

		} catch (GLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void prepareGL(GL2 gl) {

		GLU glu = new GLU();
		t++;

		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glEnable(GL.GL_DEPTH_TEST);

		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		java.awt.Dimension dim = toolkit.getScreenSize();

		float ratio = g.mainFrame.bounds().width
				/ (float) g.mainFrame.bounds().height;

		gl.glFrustumf(-ratio, ratio, -1, 1, 1, cam.zoom);

		cam.update(gl);
		gl.glScalef(1, -1, 1);

		gl.glMatrixMode(GL2.GL_MODELVIEW);
	}

	public void display(GLAutoDrawable arg0) {
		ratio = g.mainFrame.bounds().width
				/ (float) g.mainFrame.bounds().height;
		GL2 gl = (GL2) arg0.getGL();
		prepareGL(gl);

		for (Background g : backGround) {
			g.update();
		}
		robo.update();
		for (Ground g : grounds) {
			g.update();
		}
		for (Ground g : stones) {
			g.update();
		}
		hud.update();

		for (Background g : backGround) {
			g.draw(gl);
		}
		robo.draw(gl);
		for (Ground g : grounds) {
			g.draw(gl);
		}
		for (Ground g : stones) {
			g.draw(gl);
		}
		hud.draw(gl);

	}

	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {

	}

	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {

	}

	public void loadGL(GL2 gl) {
		gl.glEnable(GL2.GL_LIGHTING);

		float ambient[] = { 0.2f, 0.2f, 0.2f, 1 };
		gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, ambient, 0);

		gl.glEnable(GL2.GL_LIGHT0);
		float position[] = { -0.4f, 0.5f, 0.7f, 1 };
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, position, 0);
		float intensity[] = { 1, 1, 1, 1 };
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, intensity, 0);

		gl.glEnable(GL2.GL_LIGHT1);
		float position2[] = { 0, -0.8f, 0.3f, 1 };
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, position2, 0);
		float intensity2[] = { 1, 0, 0, 0 };
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, intensity2, 0);
		float specIntensity2[] = { 1, 1, 1, 1 };
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, specIntensity2, 0);

		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		gl.glColorMaterial(GL.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE);
		float specColor[] = { 1, 1, 1, 1 };
		gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, specColor, 0);
		gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL2.GL_SHININESS, 20);

		gl.glDepthFunc(gl.GL_LEQUAL);

		gl.glEnable(GL2.GL_TEXTURE_2D);
		gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_ONE_MINUS_SRC_ALPHA);
		gl.glEnable(gl.GL_BLEND);

		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
	}

	public float getMapWidth() {
		return cam.zoom * 2 * ratio;
	}

	public float getMapHeight() {
		return cam.zoom * 2;
	}
}