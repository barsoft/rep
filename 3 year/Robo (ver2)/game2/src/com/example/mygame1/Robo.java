package com.example.mygame1;

import java.awt.AWTEvent;
import java.awt.Event;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints.Key;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.GLException;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputListener;

import com.jogamp.newt.event.KeyEvent;

import com.jogamp.opengl.util.texture.Texture;

public class Robo extends Distructable implements KeyListener, FocusListener,
		MouseListener, MouseMotionListener, MouseInputListener,
		MouseWheelListener {

	private AnimatedSprite leftLeg;
	private AnimatedSprite rightLeg;

	private AnimatedSprite leftHand;
	private AnimatedSprite rightHand;

	public float speed;
	protected float coefuprug;
	protected float coeftren;
	protected float mass;
	public float horizontalVelocity;
	public float verticalVelocity;
	public float gravity;
	private float oppositeVerticalVelocity;
	private boolean onGround;
	protected boolean pressed;
	private boolean moveleft;
	private boolean moveright;
	private boolean spacePressed;
	private boolean startMove;
	private boolean stopMove;
	private boolean startWalkAnimation;
	private boolean startIdleAnimation;
	public ArrayList<Tool> quickUseToolsCollection;
	public ArrayList<Tool> toolsCollection;

	public float inertion;
	public float mx;
	public float my;
	private Point2D.Float mcoords;
	private Point2D.Float mecoords;
	public float angle2;
	public int currToolNum;

	public Robo(float x, float y, Renderer r) {
		super("robo", x, y, 40, 40, r.head, r);

		leftLeg = new AnimatedSprite("leftLeg", x, y, width, height, r);
		rightLeg = new AnimatedSprite("rightLeg", x, y, width, height, r);

		leftHand = new AnimatedSprite("leftHand", x, y, width, height, r);
		rightHand = new AnimatedSprite("rightHand", x, y, width, height, r);

		leftLeg.addAinamation("walk", "res/robo/leftleg/walk/leftleg", 40);
		rightLeg.addAinamation("walk", "res/robo/rightLeg/walk/rightLeg", 40);
		leftLeg.addAinamation("idle", "res/robo/leftleg/idle/leftleg", 1);
		rightLeg.addAinamation("idle", "res/robo/rightLeg/idle/rightLeg", 1);

		leftHand.addAinamation("walk", "res/robo/leftHand/leftHand", 1);
		rightHand.addAinamation("walk", "res/robo/rightHand/rightHand", 1);
		leftHand.addAinamation("idle", "res/robo/leftHand/leftHand", 1);
		rightHand.addAinamation("idle", "res/robo/rightHand/rightHand", 1);

		leftLeg.setAnimation("idle", 1);
		rightLeg.setAnimation("idle", 1);

		leftHand.setAnimation("idle", 1);
		rightHand.setAnimation("idle", 1);

		gravity = 9.8f;
		mass = 15000.6f;
		coefuprug = 0.4f;
		coeftren = 0.3f;

		leftLeg.parent = this;
		rightLeg.parent = this;

		leftHand.parent = this;
		rightHand.parent = this;
		leftHand.parentRotater = this;
		rightHand.parentRotater = this;
		leftHand.origin = this.origin;
		rightHand.origin = this.origin;

		speed = 1.9f;

		r.g.glcanvas.addKeyListener(this);

		r.g.glcanvas.addFocusListener(this);
		r.g.glcanvas.addMouseListener(this);
		r.g.glcanvas.addMouseMotionListener(this);
		r.g.glcanvas.addMouseWheelListener(this);
		r.g.glcanvas.requestFocus();

		angle = 0;

		mcoords = new Point2D.Float();

		mecoords = new Float(mcoords.x, mcoords.y);

		currToolNum = 0;

		toolsCollection = new ArrayList<>();
		for (Distruptive t : Distruptive.arr) {

			toolsCollection.add(new Distruptive(t, r));
		}
		for (Weapon t : Weapon.arr) {

			toolsCollection.add(new Weapon(t, r));
		}
		for (MaterialCreatorTool t : MaterialCreatorTool.arr) {

			toolsCollection.add(new MaterialCreatorTool(t, r));
		}

		quickUseToolsCollection = new ArrayList<>();
		for (Tool t : toolsCollection) {
			if (t.name == "lopata" || t.name == "pick" || t.name == "hand"
					|| t.name == "laserGun" ||t.name == "ak47" ||t.name == "m4a1" || t.name == "ground" || t.name == "gold")
				quickUseToolsCollection.add(t);
		}

		for (Tool w : quickUseToolsCollection) {
			w.parent = this;
			w.parentRotater = rightHand;
			w.parentLocalRotater = rightHand;
			w.originLocal = w.origin;
		}
	}

	@Override
	public void loadFromAsset(GL2 gl) throws GLException, IOException {
		super.loadFromAsset(gl);
		leftLeg.loadFromAsset(gl);
		rightLeg.loadFromAsset(gl);

		leftHand.loadFromAsset(gl);
		rightHand.loadFromAsset(gl);
		for (Tool w : quickUseToolsCollection) {
			w.loadFromAsset(gl);

		}
	}

	@Override
	public void draw(GL2 gl) {

		leftHand.draw(gl);
		leftLeg.draw(gl);
		super.draw(gl);
		for (int i = 0; i < currToolNum; i++) {
		}
		quickUseToolsCollection.get(currToolNum).draw(gl);
		rightHand.draw(gl);
		rightLeg.draw(gl);

	}

	public Tool getCurrentTool() {
		for (int i = 0; i < currToolNum; i++) {
		}
		return quickUseToolsCollection.get(currToolNum);
	}

	public void update() {

		super.update();

		mecoords = screenToOpengl(mcoords.x, mcoords.y);

		leftLeg.update();
		rightLeg.update();
		leftHand.update();
		rightHand.update();

		verticalVelocity += (float) mass * gravity / 1000000;

		verticalVelocity -= oppositeVerticalVelocity * coefuprug;
		oppositeVerticalVelocity = 0;

		inertion -= coeftren / 50.f;
		if (inertion < 0)
			inertion = 0;

		if (!pressed) {
			if (flip)
				horizontalVelocity = -inertion;
			else
				horizontalVelocity = inertion;
		}

		// leftLeg.setAnimation("idle");
		// rightLeg.setAnimation("idle");
		origin.y = height / 2.5f;
		if (!flip) {
			origin.x = width / 2.5f;

		} else {
			origin.x = width - width / 2.5f;
		}
		if (moveright) {
			horizontalVelocity = speed;

		}
		if (moveleft) {
			horizontalVelocity = -speed;

		}
		if (startMove) {
			startWalkAnimation = true;

			leftLeg.update();
			rightLeg.update();
			startMove = false;
		}
		if (startWalkAnimation) {
			if (leftLeg.currFrame == 0) {
				leftLeg.setAnimation("walk", 1);

				rightLeg.setAnimation("walk", 1);
				leftLeg.update();
				rightLeg.update();
				startWalkAnimation = false;
			}
		}

		if (stopMove) {
			startIdleAnimation = true;

			stopMove = false;

			inertion = coeftren;
		}

		if (startIdleAnimation) {
			if (leftLeg.currFrame == 21) {
				leftLeg.setAnimation("idle", 1);

				rightLeg.setAnimation("idle", 1);
				leftLeg.update();
				rightLeg.update();

				startIdleAnimation = false;
			}
		}

		angle2 = (float) Math.toDegrees((float) Math
				.atan((mecoords.y - ey - height / 2.0)
						/ Math.abs((mecoords.x - ex - (width / 2.0)))));

		if (mecoords.x - ex - (width / 2.0) > 0)
			flip = false;

		if (mecoords.x - ex - (width / 2.0) < 0)
			flip = true;

		if (angle2 < -70)
			angle2 = -70;

		if (angle2 > 80)
			angle2 = 80;
		if (!flip) {

			angle = angle2;
		}

		if (flip) {

			angle = -angle2;
		}

		getCurrentTool().update();

		((Renderer) r).cam.x = x / ((Renderer) r).ratio;
		if (((Renderer) r).cam.x < ((Renderer) r).getMapWidth() / 2
				/ ((Renderer) r).ratio)
			((Renderer) r).cam.x = ((Renderer) r).getMapWidth() / 2
					/ ((Renderer) r).ratio;

		((Renderer) r).cam.y = y;
		if (((Renderer) r).cam.y < ((Renderer) r).getMapHeight() / 2)
			((Renderer) r).cam.y = ((Renderer) r).getMapHeight() / 2;

		ColisionCheck(x, y + verticalVelocity, true);

		if (spacePressed) {
			if (onGround) {
				oppositeVerticalVelocity = 12f;

			}
		}

		ColisionCheck(x + (horizontalVelocity), y, false);

	}

	public void ColisionCheck(float x, float y, boolean vert) {
		Rectangle2D.Float newRect = new Rectangle2D.Float(x, y, width, height);
		Rectangle2D.Float leftRect = new Rectangle2D.Float(-5, 0, 5,
				((Renderer) r).getMapHeight());
		boolean intersectsGround = false;
		for (Ground g : ((Renderer) r).grounds) {
			Rectangle2D.Float rec = new Rectangle2D.Float(g.x, g.y, g.width,
					g.height);

			if (!newRect.intersects(rec))
				intersectsGround = false;
			else {
				intersectsGround = true;
				break;
			}
		}

		if (intersectsGround && this.y < y)
			onGround = true;
		else {
			onGround = false;
		}

		if (!newRect.intersects(leftRect) && !intersectsGround) {
			this.x = x;
			this.y = y;

		} else {
			if (vert) {
				oppositeVerticalVelocity = verticalVelocity;
				verticalVelocity = 0;
			}
			inertion = 0;
		}
	}

	@Override
	public void keyPressed(java.awt.event.KeyEvent arg0) {
		pressed = true;
		if (arg0.getKeyCode() == KeyEvent.VK_W) {
			//System.out.println("space pressed");
			spacePressed = true;

		}
		for (int i = 0; i < 9; i++) {
			if (arg0.getKeyCode() == KeyEvent.VK_1+i) {
				if ((i < quickUseToolsCollection.size()) && i>=0)
				currToolNum = i;
			}
		}
		if (arg0.getKeyCode() == KeyEvent.VK_D) {
			if (!moveright)
				startMove = true;
			moveright = true;

			//System.out.println("right pressed");

		}

		if (arg0.getKeyCode() == KeyEvent.VK_A) {
			if (!moveleft)
				startMove = true;
			moveleft = true;
			//System.out.println("left pressed");

		}

		if (arg0.getKeyCode() == KeyEvent.VK_UP) {
			angle2 += 5;

		}

		if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			angle2 -= 5;
		}
	}

	@Override
	public void keyReleased(java.awt.event.KeyEvent arg0) {

		if (arg0.getKeyCode() == KeyEvent.VK_D) {
			//System.out.println("right released");
			moveright = false;
			if (!moveleft)
				stopMove = true;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_A) {
		//	System.out.println("left released");
			moveleft = false;
			if (!moveright)
				stopMove = true;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_W) {
		//	System.out.println("space released");
			spacePressed = false;
		}

		if (!moveleft && !moveright)
			pressed = false;

	}

	@Override
	public void keyTyped(java.awt.event.KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusGained(FocusEvent e) {
		moveleft = false;
		moveright = false;
		spacePressed = false;
		stopMove = false;
		startMove = false;
		System.out.println("focus gained");
	}

	@Override
	public void focusLost(FocusEvent e) {
		moveleft = false;
		moveright = false;
		spacePressed = false;
		stopMove = false;
		startMove = false;
		System.out.println("focus lost");
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

		// System.out.println();
		// JOptionPane.showMessageDialog(r.g.mainFrame, screenToOpengl(e.getX(),
		// e.getY()));
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mcoords = new Point2D.Float(e.getX(), e.getY());

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mcoords = new Point2D.Float(e.getX(), e.getY());
	}

	public Point2D.Float screenToOpengl(float x, float y) {

		float coef = ((Renderer) r).g.mainFrame.bounds().height
				/ (2 * ((Renderer) r).cam.zoom);
		return new Point2D.Float(x / coef
				+ (((Renderer) r).cam.x - ((Renderer) r).cam.zoom)
				* ((Renderer) r).ratio, y / coef + ((Renderer) r).cam.y
				- ((Renderer) r).cam.zoom);

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() > 0) {
			if (currToolNum < quickUseToolsCollection.size() - 1)
				currToolNum++;
		}
		if (e.getWheelRotation() < 0) {
			if (currToolNum > 0)
				currToolNum--;

		}

		for (Tool t : quickUseToolsCollection) {
			t.update();
		}

	}

}
