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
import java.util.*;

public class EntityTypes
{
    public EntityTypes(String _classname)
    {
        classname = _classname;
    }
    public void Add(String _path)
    {
        EntityType buff = new EntityType(_path);
        entityTypesList.add(buff);
    }
    public void LoadContent()
    {
        for (int i = 0; i < entityTypesList.size();i++ )
        {
            entityTypesList.get(i).LoadContent();


        }
    }

    public EntityType GetByName(String name)
    {
        for (int i = 0; i < entityTypesList.size(); i++)
        {
            if (entityTypesList.get(i).name == name)
                return entityTypesList.get(i);
        }
        return null;
    }
    private String classname;
    public ArrayList<EntityType> entityTypesList=new ArrayList<EntityType>();
}