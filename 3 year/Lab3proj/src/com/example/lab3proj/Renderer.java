package com.example.lab3proj;

import android.opengl.GLU;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.graphics.Shader;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class Renderer implements GLSurfaceView.Renderer {

	Camera cam = new Camera(0, 0, 200);
	MainActivity activity;
	public ArrayList<Sample> samples;
	public ArrayList<Instance> instances;
	public ArrayList<Instance> instancesOnSamples;
	private Base backGround;
	public GoDownButton goDownButton;

	public float zoomX;
	public float zoomY;
	private float stackX;
	private float stackY;

	private int calcRhombes;
	private int calcCircles;
	private int calcSquares;
	private int maxCards;
	private int calcOthers;
	private float intervalX;
	private float intervalY;

	private float intervalOnSampleX;
	private float intervalOnSampleY;

	public Renderer(MainActivity activity) {
		this.activity = activity;

		zoomX = activity.getWindowManager().getDefaultDisplay().getWidth();
		zoomY = activity.getWindowManager().getDefaultDisplay().getHeight();

		stackX = zoomX / 4 - 35;
		stackY = zoomY / 6 * 5 - 100;

		samples = new ArrayList<Sample>();
		samples.add(new Sample("rhomb", zoomX / 4 - 35, zoomY / 6,
				BitmapFactory.decodeResource(activity.getResources(),
						R.drawable.rhomb), activity));
		samples.add(new Sample("circle", zoomX / 2 - 35, zoomY / 6,
				BitmapFactory.decodeResource(activity.getResources(),
						R.drawable.circle), activity));
		samples.add(new Sample("square", zoomX / 4 * 3 - 35, zoomY / 6,
				BitmapFactory.decodeResource(activity.getResources(),
						R.drawable.square), activity));

		instances = new ArrayList<Instance>();
		instancesOnSamples = new ArrayList<Instance>();

		maxCards = 25;

		intervalX = 250 / maxCards;
		intervalY = 0;

		intervalOnSampleX = 3;
		intervalOnSampleY = 0;

		int cardsLeft = maxCards;
		Random random = new Random();

		do {
			calcRhombes = random.nextInt(cardsLeft / 3);

		} while (calcRhombes > cardsLeft);
		cardsLeft -= calcRhombes;

		do {
			calcCircles = random.nextInt(cardsLeft / 3);

		} while (calcCircles > cardsLeft);
		cardsLeft -= calcCircles;

		do {
			calcSquares = random.nextInt(cardsLeft / 3);

		} while (calcSquares > cardsLeft);
		cardsLeft -= calcSquares;

		calcOthers = cardsLeft;

		for (int i = 0; i < calcRhombes; i++) {
			instances.add(new Instance("rhomb", BitmapFactory.decodeResource(
					activity.getResources(), R.drawable.rhomb), activity));
		}

		for (int i = 0; i < calcCircles; i++) {
			instances.add(new Instance("circle", BitmapFactory.decodeResource(
					activity.getResources(), R.drawable.circle), activity));
		}
		for (int i = 0; i < calcSquares; i++) {
			instances.add(new Instance("square", BitmapFactory.decodeResource(
					activity.getResources(), R.drawable.square), activity));
		}
		for (int i = 0; i < calcOthers; i++) {
			int randomShape = random.nextInt(3);
			switch (randomShape) {
			case 0:
				instances.add(new Instance("basket", BitmapFactory
						.decodeResource(activity.getResources(),
								R.drawable.basket), activity));
				break;
			case 1:
				instances.add(new Instance("disket", BitmapFactory
						.decodeResource(activity.getResources(),
								R.drawable.disket), activity));
				break;
			case 2:
				instances.add(new Instance("ellipse", BitmapFactory
						.decodeResource(activity.getResources(),
								R.drawable.ellipse), activity));
				break;
			}
		}

		for (int i = 0; i < instances.size(); i++) {
			Instance tmp = instances.get(0);
			instances.remove(0);
			instances.add(random.nextInt(instances.size()), tmp);
		}

		goDown();
		backGround = new Base(0, 0, zoomX, zoomY, BitmapFactory.decodeResource(
				activity.getResources(), R.drawable.background), activity);
		goDownButton = new GoDownButton(activity, zoomX, zoomY);

	}

	public void updateStack() {
		instances.get(0).x = stackX;
		instances.get(0).y = stackY;

		for (int i = 1; i < instances.size(); i++) {
			instances.get(i).x = instances.get(i - 1).x + intervalX;
			instances.get(i).y = instances.get(i - 1).y + intervalY;
		}
		boolean flag = false;
		for (Instance card : instances) {
			card.selected = false;
		}
		for (Instance card : instances) {
			if (card.name != "square" && card.name != "circle"
					&& card.name != "rhomb")
				flag = true;
			else {
				flag = false;
				break;
			}
		}

		instances.get(instances.size() - 1).selected = true;

		if (flag)
			activity.finishGame();
	}

	public void goDown() {
		Instance buff = instances.get(instances.size() - 1);
		instances.remove(instances.size() - 1);
		instances.add(0, buff);

		updateStack();
	}

	@Override
	public void onDrawFrame(GL10 gl) {

		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		GL_Prepare(gl);

		backGround.draw(gl);
		for (Card card : samples) {
			card.draw(gl);
		}

		for (int i = 0; i < instancesOnSamples.size(); i++) {
			if (instancesOnSamples.get(i) != null) {
				instancesOnSamples.get(i).draw(gl);
			}
		}

		for (int i = 0; i < instances.size(); i++) {
			if (instances.get(i) != null) {
				instances.get(i).draw(gl);
				if (instances.get(i).dragInProcess) {
					goDownButton.draw(gl);
				}
			}
		}

		GL_Finish(gl);
	}

	private void GL_Finish(GL10 gl) {
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}

	private void GL_Prepare(GL10 gl) {
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glFrontFace(GL10.GL_CW);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glEnableClientState(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		cam.Update(gl);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);

		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glScalef(1.f, 1.f, 1.f);

		gl.glOrthof(0, zoomX, zoomY, 0, 0, 500);

		gl.glMatrixMode(GL10.GL_MODELVIEW);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		backGround.loadFromAsset(gl, this.activity);
		goDownButton.loadFromAsset(gl, this.activity);
		for (Card card : samples) {
			card.loadFromAsset(gl, this.activity);
		}

		for (Card card : instances) {
			card.loadFromAsset(gl, this.activity);
		}

		for (Card card : instancesOnSamples) {
			card.loadFromAsset(gl, this.activity);
		}

		gl.glEnable(GL10.GL_TEXTURE_2D); // Enable Texture Mapping ( NEW )
		gl.glShadeModel(GL10.GL_SMOOTH); // Enable Smooth Shading
		gl.glClearColor(0.0f, 0.5f, 0.0f, 0.5f); // Black Background
		gl.glClearDepthf(1.0f); // Depth Buffer Setup
		gl.glEnable(GL10.GL_DEPTH_TEST); // Enables Depth Testing
		gl.glDepthFunc(GL10.GL_LEQUAL); // The Type Of Depth Testing To Do

		// Really Nice Perspective Calculations
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
	}

	public boolean onTouchEvent(MotionEvent event) {
		for (int i = 0; i < instances.size(); i++) {
			if (instances.get(i).selected) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					instances.get(i).startCardTranslateX = event.getX();
					instances.get(i).startCardTranslateY = event.getY();
					instances.get(i).oldCardX = instances.get(i).x;
					instances.get(i).oldCardY = instances.get(i).y;
					if (instances.get(i).startCardTranslateX > instances.get(i).x
							&& instances.get(i).startCardTranslateX < instances
									.get(i).x + instances.get(i).width
							&& instances.get(i).startCardTranslateY > instances
									.get(i).y
							&& instances.get(i).startCardTranslateY < instances
									.get(i).y + instances.get(i).height) {
						instances.get(i).dragInProcess = true;
					}
					break;
				case MotionEvent.ACTION_MOVE:
					if (instances.get(i).dragInProcess) {
						int screenX = activity.getWindowManager()
								.getDefaultDisplay().getWidth();
						int screenY = activity.getWindowManager()
								.getDefaultDisplay().getHeight();
						instances.get(i).x = instances.get(i).oldCardX
								+ (event.getX() - instances.get(i).startCardTranslateX);
						instances.get(i).y = instances.get(i).oldCardY
								+ (event.getY() - instances.get(i).startCardTranslateY);
					}
					break;
				case MotionEvent.ACTION_UP:
					if (instances.get(i).dragInProcess) {
						instances.get(i).dragInProcess = false;
						int j = 0;
						for (Sample samp : activity.renderer.samples) {
							if (samp.name == instances.get(i).name) {
								RectF sampRec = new RectF(samp.x, samp.y,
										samp.x + samp.width, samp.y
												+ samp.height);
								RectF cardRec = new RectF(instances.get(i).x,
										instances.get(i).y, instances.get(i).x
												+ instances.get(i).width,
										instances.get(i).y
												+ instances.get(i).height);

								if (cardRec.intersect(sampRec)) {
									Instance ios = null;
									for (Instance card : instancesOnSamples) {
										if (card.name == instances.get(i).name)
											ios = card;
									}
									if (ios != null) {
										instances.get(i).x = ios.x
												+ intervalOnSampleX;
										instances.get(i).y = ios.y
												+ intervalOnSampleY;
										instances.get(i).width = ios.width;
										instances.get(i).height = ios.height;
									} else {
										instances.get(i).x = samp.x
												+ intervalOnSampleX;
										instances.get(i).y = samp.y
												+ intervalOnSampleY;
										instances.get(i).width = samp.width;
										instances.get(i).height = samp.height;
									}
									activity.renderer.instancesOnSamples
											.add(instances.get(i));
									activity.renderer.instances
											.remove(instances.get(i));
									activity.renderer.updateStack();
									return true;
								}
							}
							j++;
						}

						if (j == 3) {
							Base gdb = activity.renderer.goDownButton.goDownButton;
							RectF goDownRec = new RectF(gdb.x, gdb.y, gdb.x
									+ gdb.width, gdb.y + gdb.height);
							RectF cardRec = new RectF(instances.get(i).x,
									instances.get(i).y, instances.get(i).x
											+ instances.get(i).width,
									instances.get(i).y
											+ instances.get(i).height);
							if (cardRec.intersect(goDownRec)) {
								activity.renderer.goDown();
								return true;
							} else
								activity.renderer.updateStack();
						}

					}
					break;

				}
			}

		}

		return true;
	}
}