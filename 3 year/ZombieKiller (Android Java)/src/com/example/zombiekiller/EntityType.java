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

public class EntityType
{
    public String texturePath;
    public EntityType(String _path)
    {
        path = _path;
        //po1 = new List<PhysicsObject>(); 
    }
    //public List<PhysicsObject> po1;
    

    /*public void LoadEntityType()
    {
        FileStream ms = new FileStream(path, FileMode.Open);
         StreamReader sr = new StreamReader(ms);
        string buff;

        buff = sr.ReadLine();
        buff = sr.ReadLine();

        buff = sr.ReadLine();

        StringBuilder sb3 = new StringBuilder();
        StringBuilder sb4 = new StringBuilder();
        sb3.Insert(0, buff);

        int i = 0;
        while (sb3[i].CompareTo(' ') == 0)
        {
            i++;
        }

        while (sb3[i].CompareTo(',') != 0)
        {
            sb4.Append(sb3[i]);

            i++;
        }
        i++;
        ClassName = sb4.ToString();
        sb4.Clear();

        while (sb3[i].CompareTo(' ') == 0)
        {
            i++;
        }

        while (sb3[i].CompareTo(',') != 0)
        {
            sb4.Append(sb3[i]);

            i++;
        }
        i++;
        Name = sb4.ToString();
        sb4.Clear();

        while (sb3[i].CompareTo(' ') == 0)
        {
            i++;
        }

        while (sb3[i].CompareTo(',') != 0)
        {
            sb4.Append(sb3[i]);

            i++;
        }
        i++;
        texturePath = sb4.ToString();
        sb4.Clear();

        while (sb3[i].CompareTo(' ') == 0)
        {
            i++;
        }

        while (sb3[i].CompareTo(',') != 0)
        {
            sb4.Append(sb3[i]);

            i++;
        }
        i++;
        Width = Convert.ToInt32(sb4.ToString());
        sb4.Clear();

        while (sb3[i].CompareTo(' ') == 0)
        {
            i++;
        }

        while (sb3[i].CompareTo('.') != 0)
        {
            sb4.Append(sb3[i]);

            i++;
        }
        i++;
        Height = Convert.ToInt32(sb4.ToString());
        sb4.Clear();

        while (true)
        {
            buff = sr.ReadLine();

            if (buff.IndexOf("Physics:") > -1)
                break;
        }

        while (true)
        {
            buff = sr.ReadLine();

            if (buff.IndexOf("{") > -1)
                break;
        }

        while (true)
        {
            buff = sr.ReadLine();

            if (buff.IndexOf("}") > -1)
                break;

            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            sb.Insert(0, buff);
            i = 0;
            while (sb[i].CompareTo(' ') == 0)
            {
                i++;
            }

            while (sb[i].CompareTo(',') != 0)
            {
                sb2.Append(sb[i]);

                i++;
            }
            i++;
            int mx = Convert.ToInt32(sb2.ToString());
            sb2.Clear();

            while (sb[i].CompareTo(' ') == 0)
            {
                i++;
            }

            while (sb[i].CompareTo(',') != 0)
            {
                sb2.Append(sb[i]);

                i++;
            }
            i++;
            int my = Convert.ToInt32(sb2.ToString());
            sb2.Clear();

            while (sb[i].CompareTo(' ') == 0)
            {
                i++;
            }

            while (sb[i].CompareTo(',') != 0)
            {
                sb2.Append(sb[i]);

                i++;
            }
            i++;
            int mwidth = Convert.ToInt32(sb2.ToString());
            sb2.Clear();

            while (sb[i].CompareTo(' ') == 0)
            {
                i++;
            }

            while (sb[i].CompareTo(';') != 0)
            {
                sb2.Append(sb[i]);

                i++;
            }
            i++;
            int mheight = Convert.ToInt32(sb2.ToString());
            sb2.Clear();

            PhysicsObject buff1 = new PhysicsObject(mx, my, mwidth, mheight);

            po1.Add(buff1);
        }
        sr.Dispose();
        ms.Dispose();
    }*/

  /*  public void SaveEntityType(Stream ms)
    {

        StreamWriter sw = new StreamWriter(ms);
        sw.WriteLine("CITY TRAVELLER ENTITY TYPE FILE , Max Prysyazhnyy 2012 ©.");
        sw.WriteLine();
        sw.WriteLine("" + ClassName + ",  " + Name + ",  " + texturePath + ",  " + width + ",  " + height + ".");

        sw.WriteLine("Physics:");
        sw.WriteLine("{");
        foreach (PhysicsObject buff in po1)
        {
            sw.WriteLine(buff.position.X + "," + buff.position.Y + "," + buff.width + "," + buff.height + ";");
        }

        sw.WriteLine("}");
        sw.Dispose();
        ms.Dispose();
    }*/
    
    public void LoadContent()
    {
        /*for (int i = 0; i < po1.Count; i++)
            po1[i].LoadContent(Content);
        if (texture==null)
        LoadEntityType();
        if (texturePath != null)
        {
            FileStream ms2 = new FileStream(texturePath, FileMode.Open);
            texture = Texture2D.FromStream(g, ms2);
            ms2.Dispose();
        }*/
    }
    public void UpdateTexture()
    {
        /*if (texturePath != null)
        {
            FileStream ms2 = new FileStream(texturePath, FileMode.Open);
            texture = Texture2D.FromStream(g, ms2);
            ms2.Dispose();
        }*/
    }
    public String path;
    public String name;
    public Bitmap texture;
    public int height;
    public int width;
    public String classname;
}