package com.example.zombiekiller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.MotionEvent;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.PointF;
import android.view.Menu;

public class Camera
{
    float speed;

    public PointF world;

    public float scale;

    float width;
    float height;

    public Camera(float _width,float _height)
    {
        speed = 4;

        world=new PointF(0,0);

        width = _width;

        height = _height;
        
        scale=100;
    }

    public void Update(int _width,int _height)
    {
        width = _width;
        height = _height;
    }

    public void MoveUp(int speed)
    {
        if (world.y > 0)
            world.y -= 1;
    }

    public void MoveDown(int speed)
    {
        if (world.y < height / 100 * scale)
            world.y += 1;
    }

    public void MoveLeft(int speed)
    {
        if (world.x > 0)
            world.x -= 1;
    }

    public void MoveRight(int speed)
    {
        if (world.x < width / 100 * scale)
            world.x += 1;
    }
}

