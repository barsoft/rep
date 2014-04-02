package com.example.mygame1;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLException;

public class Tool extends Distructable implements KeyListener, MouseListener {
	// ////////////////////////////////////////////////////////////////
	// / DYNAMIC PART//////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////
	Tool laserAim;
	public float laserAimWidth;
	public float laserAimHeight;
	public boolean drawLaser;
	public boolean leftMouseButtonProcess;
	public boolean rightMouseButtonProcess;
	public Base icon;
	public float inHandX;
	public float inHandY;

	public Tool(String name, float inHandX, float inHandY, float width,
			float height, File texture, Renderer r) {
		super(name, inHandX * width, inHandY * height, width, height, texture,
				r);
		laserAimWidth = 40;
		laserAimHeight = 0.5f;

		r.g.glcanvas.addKeyListener(this);
		r.g.glcanvas.addMouseListener(this);
		this.inHandX = inHandX;
		this.inHandY = inHandY;
	}

	@Override
	public void loadFromAsset(GL2 gl) throws GLException, IOException {
		// TODO Auto-generated method stub
		super.loadFromAsset(gl);
		laserAim = new Tool("laserAim", 0.4f, 0.5f, laserAimWidth,
				laserAimHeight, r.laser, r);
		if (name == laserAim.name)
			laserAim = null;
		else {
			laserAim.parent = this;
			laserAim.parentRotater = this;
			laserAim.parentLocalRotater = this;
			laserAim.originLocal = this.originLocal;

			laserAim.loadFromAsset(gl);
		}
		
		
	}

	@Override
	public void draw(GL2 gl) {
		if (name != "hand")
			super.draw(gl);
		if (drawLaser && laserAim != null)
			laserAim.draw(gl);
	}

	@Override
	public void update() {
		if (laserAim!= null) {
			if (!flip) {
				laserAim.width = Math.abs(laserAim.width);
			} else {

				laserAim.width = -Math.abs(laserAim.width);
			}
		}

		if (!flip) {
			x = inHandX * parent.width;
			y = inHandY * parent.height;
			if (laserAim != null) {
				laserAim.origin = new Point2D.Float(-15, -5);
			}
		} else {
			x = (1 - inHandX) * parent.width - width;
			y = inHandY * parent.height;
			if (laserAim == null) {
				x = (1 - inHandX) * parent.width;
			}
			if (laserAim != null) {
				laserAim.origin = new Point2D.Float(15, -5);
			}

		}

		super.update();

		if (laserAim != null)
			laserAim.update();
		
		if (laserAim!= null) {
			origin.x = -(x - parent.width / 2);
			origin.y = -(y - parent.height / 2);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			leftMouseButtonProcess = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			leftMouseButtonProcess = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
		if (e.getButton() == MouseEvent.BUTTON1)
			leftMouseButtonProcess = true;
		if (e.getButton() == MouseEvent.BUTTON3)
			rightMouseButtonProcess = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			leftMouseButtonProcess = false;
		if (e.getButton() == MouseEvent.BUTTON3)
			rightMouseButtonProcess = false;
	}

	boolean intersects(Rectangle2D.Float rec, Rectangle2D.Float line) {
		if (!linesIntersect(rec.x, rec.y, rec.x + rec.width, rec.y, line.x,
				line.y, line.x + line.width, line.y + line.height)
				&& !linesIntersect(rec.x + rec.width, rec.y, rec.x + rec.width,
						rec.y + rec.height, line.x, line.y,
						line.x + line.width, line.y + line.height)
				&& !linesIntersect(rec.x, rec.y + rec.height,
						rec.x + rec.width, rec.y + rec.height, line.x, line.y,
						line.x + line.width, line.y + line.height)
				&& !linesIntersect(rec.x, rec.y, rec.x, rec.y + rec.height,
						line.x, line.y, line.x + line.width, line.y
								+ line.height))
			return false;

		return true;

		/*
		 * if (GetLinesIntersection(rec.x, rec.y,rec.x+ rec.width, rec.y,
		 * line.x, line.y, line.x+line.width, line.y+line.height)!=null) return
		 * true; return false;
		 */
	}

	public static boolean linesIntersect(double x1, double y1, double x2,
			double y2, double x3, double y3, double x4, double y4) {
		// Return false if either of the lines have zero length
		if (x1 == x2 && y1 == y2 || x3 == x4 && y3 == y4) {
			return false;
		}
		// Fastest method, based on Franklin Antonio's
		// "Faster Line Segment Intersection" topic "in Graphics Gems III" book
		// (http://www.graphicsgems.org/)
		double ax = x2 - x1;
		double ay = y2 - y1;
		double bx = x3 - x4;
		double by = y3 - y4;
		double cx = x1 - x3;
		double cy = y1 - y3;

		double alphaNumerator = by * cx - bx * cy;
		double commonDenominator = ay * bx - ax * by;
		if (commonDenominator > 0) {
			if (alphaNumerator < 0 || alphaNumerator > commonDenominator) {
				return false;
			}
		} else if (commonDenominator < 0) {
			if (alphaNumerator > 0 || alphaNumerator < commonDenominator) {
				return false;
			}
		}
		double betaNumerator = ax * cy - ay * cx;
		if (commonDenominator > 0) {
			if (betaNumerator < 0 || betaNumerator > commonDenominator) {
				return false;
			}
		} else if (commonDenominator < 0) {
			if (betaNumerator > 0 || betaNumerator < commonDenominator) {
				return false;
			}
		}
		if (commonDenominator == 0) {
			// This code wasn't in Franklin Antonio's method. It was added by
			// Keith Woodward.
			// The lines are parallel.
			// Check if they're collinear.
			double y3LessY1 = y3 - y1;
			double collinearityTestForP3 = x1 * (y2 - y3) + x2 * (y3LessY1)
					+ x3 * (y1 - y2); // see
										// http://mathworld.wolfram.com/Collinear.html
			// If p3 is collinear with p1 and p2 then p4 will also be collinear,
			// since p1-p2 is parallel with p3-p4
			if (collinearityTestForP3 == 0) {
				// The lines are collinear. Now check if they overlap.
				if (x1 >= x3 && x1 <= x4 || x1 <= x3 && x1 >= x4 || x2 >= x3
						&& x2 <= x4 || x2 <= x3 && x2 >= x4 || x3 >= x1
						&& x3 <= x2 || x3 <= x1 && x3 >= x2) {
					if (y1 >= y3 && y1 <= y4 || y1 <= y3 && y1 >= y4
							|| y2 >= y3 && y2 <= y4 || y2 <= y3 && y2 >= y4
							|| y3 >= y1 && y3 <= y2 || y3 <= y1 && y3 >= y2) {
						return true;
					}
				}
			}
			return false;
		}
		return true;
	}
}
