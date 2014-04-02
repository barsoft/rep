package com.example.zombiekiller;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.MotionEvent;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Menu;
import java.io.*;

public class Entity extends Base
{
    public boolean physics;
    //public List<PhysicsObject> po1;
    public Entity(String _name, boolean _enablePhysics)
    {
        physics = _enablePhysics;
        name = _name;

        position.x = 0;
        position.y = 0;

        angle = 0f;
      //  po1 = new List<PhysicsObject>(); 
    }
    public Entity(EntityType _type, String _name, boolean _enablePhysics)
    {
        physics = _enablePhysics;
        name = _name;

        position.x = 0;
        position.y = 0;

        texture = _type.texture;

        angle = 0f;

        type = _type;
      //  po1 = new List<PhysicsObject>();
      //  po1 = type.po1;
    }
    public Entity(EntityType _type, String _name, int _x, int _y, float _angle, boolean _enablePhysics)
    {
        physics = _enablePhysics;
        position.x = _x;
        position.y = _y;

        texture = _type.texture;

        angle = _angle;

        name = _name;

        type = _type;

        width = _type.width;
        height = _type.height;

   //     po1 = new List<PhysicsObject>(); 

    //    po1 = type.po1;

    }
    public void Draw(Canvas _canvas)
    {
        Update();
        super.Draw(_canvas);
      /*  if (physics)
        {
            foreach (PhysicsObject po in po1)
            {
                po.Draw(spriteBatch);
            }
        }*/
    }
    public void UpdateTexture()
    {
        texture = type.texture;
        width = type.width;
        height = type.height;
    }
    public void LoadContent()
    {
       /* if (physics)
        {
            po1 = new List<PhysicsObject>();
            foreach (PhysicsObject po in type.po1)
            {
                po1.Add(new PhysicsObject(po.position.X,po.position.Y,po.width,po.height));
            }
            foreach (PhysicsObject po in po1)
            {
                po.LoadContent(Content);
            }
        }*/
    }
    public void Update()
    {
        
        /*if (physics)
        {
            foreach (PhysicsObject po in po1)
            {
                po.Update(ref cam, (int)this.position.X, (int)this.position.Y);
            }
        }
        UpdatePosition(ref cam);
        */
    }
    public Bitmap texture;
    public EntityType type;
}

