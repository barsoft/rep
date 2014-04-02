using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApplication1
{
    public partial class Form1 : Form
    {
        private int i = 0;
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
        }

        protected override void OnPaint(PaintEventArgs e)
        {
           // base.OnPaint(e);
            Graphics g = e.Graphics;
            int x = Width / 2;
            int y = 400 - i;
            drawBaloon(x - 100, y, 200, 80, g);
            drawBaloon(x, y + 100, 250, 120, g);
            drawBaloon(x + 100, y + 50, 230, 100, g);
            i += 5;
            Invalidate();
        }

        private void drawBaloon(int x, int y, int size, int ropeLength, Graphics g)
        {
            List<PointF> points = new List<PointF>();

            points.Add(new PointF(x, y + size / 2));
            points.Add(new PointF(x + size / 2, y));
            points.Add(new PointF(x, y - size / 2));
            points.Add(new PointF(x - size / 2, y));
            points.Add(new PointF(x, y + size / 2));
            BezierSpline bs = new BezierSpline(points);
            IList<PointF> spline = bs.GenerateSpline(20);
            PointF oldp=PointF.Empty;
            foreach (PointF p in spline)
            {
                if (oldp != PointF.Empty)
                    g.DrawLine(new Pen(Brushes.Black, 2), oldp.X, oldp.Y, p.X, p.Y);
                oldp = p;
            }


            points.Clear();
            points.Add(new PointF(x, y + size / 2));
            points.Add(new PointF(x - 40, y + size / 2 + ropeLength / 3));
            points.Add(new PointF(x + 20, y + size / 2 + ropeLength / 2));
            points.Add(new PointF(x, y + size / 2 + ropeLength));
            bs = new BezierSpline(points);
            spline = bs.GenerateSpline(5);
            oldp = PointF.Empty;
            foreach (PointF p in spline)
            {
                if (oldp != PointF.Empty)
                    g.DrawLine(new Pen(Brushes.Black, 2), oldp.X, oldp.Y, p.X, p.Y);
                oldp = p;
            }
        }
    }
}
