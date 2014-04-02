package com.example.mygame1;

import java.awt.geom.Point2D;

public class MapObject {
	public float x;
	public float y;
	public float ex;
	public float ey;
	Point2D.Float origin;
	Point2D.Float originLocal;
	public float angle;
	public boolean flip;
	public MapObject parent;
	public MapObject parentRotater;
	public MapObject parentSizer;
	public MapObject parentLocalRotater;
	public float width;
	public float height;
	public float anglelocal;
}
