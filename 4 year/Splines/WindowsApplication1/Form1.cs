using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Drawing.Imaging;
using System.Drawing.Drawing2D;
using System.IO;

namespace WindowsApplication1
{
    public partial class Form1 : Form
    {
        public double[] splinex = new double[1001];
        public double[] spliney = new double[1001];
        public Point[] pt = new Point[6];
        public int no_of_points = 0;
        int[] a1 = new int[12];
        int[] b1 = new int[12];

        public Form1()
        {
            InitializeComponent();

            drawPoint(50, 50);
            drawPoint(70, 70);
            drawPoint(50, 90);
            drawPoint(30, 70);
            drawPoint(50, 50);
        }

        public struct Point
        {
            public int x;
            public int y;

            public void setxy(int i, int j)
            {
                x = i;
                y = j;
            }
        }

        // Prints a dot at the place whrere the mouseup event occurs 
        private void pictureBox1_MouseUp(object sender, MouseEventArgs e)
        {
            Graphics g = pictureBox1.CreateGraphics();
            Color cl = Color.White;
            g.DrawLine(new Pen(cl, 2), e.X, e.Y, e.X + 1, e.Y + 1);

        }

        // At each mousedown event the the no of points is calculated if the value is more than 3
        //then the curve is drawn
        private void pictureBox1_MouseDown(object sender, MouseEventArgs e)
        {
            int ex = e.X;
            int ey = e.Y;

            drawPoint(ex, ey);
        }

        public void drawPoint(int ex, int ey)
        {
            if (no_of_points > 3)
            {
                pt[0] = pt[1]; pt[1] = pt[2]; pt[2] = pt[3];
                pt[3].setxy(ex, ey);
                double temp = Math.Sqrt(Math.Pow(pt[2].x - pt[1].x, 2F) + Math.Pow(pt[2].y - pt[1].y, 2F));
                int interpol = System.Convert.ToInt32(temp);
                bsp(pt[0], pt[1], pt[2], pt[3], interpol);
                int i;
                int width = 2;
                Graphics g = pictureBox1.CreateGraphics();
                Color cl = Color.Blue;
                int x, y;
                // the lines are drawn
                for (i = 0; i <= interpol - 1; i++)
                {
                    x = System.Convert.ToInt32(splinex[i]);
                    y = System.Convert.ToInt32(spliney[i]);
                    g.DrawLine(new Pen(cl, width), x - 1, y, x + 1, y);
                    g.DrawLine(new Pen(cl, width), x, y - 1, x, y + 1);
                }
            }
            else
            {
                pt[no_of_points].setxy(ey, ey);
            }
            no_of_points = no_of_points + 1;
        }

        // calculating the values using the algorithm 
        public void bsp(Point p1, Point p2, Point p3, Point p4, int divisions)
        {
            double[] a = new double[5];
            double[] b = new double[5];
            a[0] = (-p1.x + 3 * p2.x - 3 * p3.x + p4.x) / 6.0;
            a[1] = (3 * p1.x - 6 * p2.x + 3 * p3.x) / 6.0;
            a[2] = (-3 * p1.x + 3 * p3.x) / 6.0;
            a[3] = (p1.x + 4 * p2.x + p3.x) / 6.0;
            b[0] = (-p1.y + 3 * p2.y - 3 * p3.y + p4.y) / 6.0;
            b[1] = (3 * p1.y - 6 * p2.y + 3 * p3.y) / 6.0;
            b[2] = (-3 * p1.y + 3 * p3.y) / 6.0;
            b[3] = (p1.y + 4 * p2.y + p3.y) / 6.0;

            splinex[0] = a[3];
            spliney[0] = b[3];

            int i;
            for (i = 1; i <= divisions - 1; i++)
            {
                float t = System.Convert.ToSingle(i) / System.Convert.ToSingle(divisions);
                splinex[i] = (a[2] + t * (a[1] + t * a[0])) * t + a[3];
                spliney[i] = (b[2] + t * (b[1] + t * b[0])) * t + b[3];
            }
        }

    }
}