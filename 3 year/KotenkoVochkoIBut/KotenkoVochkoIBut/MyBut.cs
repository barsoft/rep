using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Drawing;

namespace KotenkoVochkoIBut
{
    public class MyBut : Button
    {
        private Boolean dragInProgress = false;

        public bool InCell { get; set; }
        Timer t;
        float gravity;
        float speedFall;
        float speedBack;
        float mass = 2000.6f;
        Form1 f;
        public float coefuprug { get; set; }
        //
        public MyBut(string сh2, int x, int y, Form1 f)
        {
            this.MouseDown += new MouseEventHandler(textBox1_MouseDown);
            this.MouseMove += new MouseEventHandler(textBox1_MouseMove);
            this.MouseUp += new MouseEventHandler(textBox1_MouseUp);
            this.Width = 50;
            this.Height = 50;
            this.Text = сh2;
            this.Font = new Font("arial", 30, FontStyle.Regular);
            this.Left = x;
            this.Top = y;
            this.f = f;
            this.gravity = 2.5f;
            this.coefuprug = 0.9f;
            t = new Timer();
            t.Interval = 1;
            t.Tick += new EventHandler(t_Tick);
            t.Start();
        }

        void t_Tick(object sender, EventArgs e)
        {
            if (!InCell)
            {
                ColisionCheck(new PointF(this.Left, this.Top + (int)speedFall));
                //ColisionCheck(new PointF(x, y + (int)velocity.Y));
                speedFall += (float)mass * gravity / 10000;
                speedFall -= speedBack * coefuprug;
                speedBack = 0;
            }
            
        }

        public void ColisionCheck(PointF newPos)
        {
            Rectangle newRect = new Rectangle((int)newPos.X, (int)newPos.Y, Width, Height);
            Rectangle gr = new Rectangle(0, f.Height - 100, f.Width, 5);
            Rectangle ls = new Rectangle(-5, 0, 5, f.Height);
            Rectangle rs = new Rectangle(f.Width, 0, f.Width + 5, f.Height);
            bool flag=false;
            /*foreach (MyBut mb in f.but)
            {
                Rectangle mbRect = new Rectangle(mb.Left,mb.Top,mb.Width,mb.Height);
                if (newRect.IntersectsWith(mbRect))
                {
                    flag = true;
                    //MessageBox.Show("asd");
                    break;
                }
            }*/
            if (!newRect.IntersectsWith(gr) && !newRect.IntersectsWith(ls) && !newRect.IntersectsWith(rs) && !flag)
            {
                this.Left = (int)newPos.X;
                this.Top = (int)newPos.Y;
            }
            else
            {
                speedBack = speedFall;
                speedFall = 0;
            }
        }

        int MouseDownX = 0;

        int MouseDownY = 0;

        void textBox1_MouseDown(object sender, MouseEventArgs e)
        {

            if (!this.dragInProgress)
            {

                this.dragInProgress = true;

                this.MouseDownX = e.X;

                this.MouseDownY = e.Y;

            }

            return;

        }

        void textBox1_MouseUp(object sender, MouseEventArgs e)
        {

            if (e.Button == MouseButtons.Left)
            {

                this.dragInProgress = false;

                bool flag = false;
                foreach (MyButCell mc in f.butcel)
                {
                    Rectangle r = new Rectangle(mc.Left, mc.Top, mc.Width, mc.Height);
                    Rectangle r2 = new Rectangle(this.Left, this.Top, this.Width, this.Height);
                    if (r.IntersectsWith(r2))
                    {
                        this.Left = r.Left;
                        this.Top = r.Top;
                        mc.Text = this.Text;
                        InCell = true;
                        flag = true;
                        break;
                    }

                }

                if (!flag)
                {
                    InCell = false;
                }

                String s = "";

                for (int i = 0; i < f.butcel.Count; i++)
                {
                    s += f.butcel[i].Text;

                }

                string c = "КОТЕНКОВОЧКОИБУТ";

                if (s == c)
                {
                    MessageBox.Show("You win");
                    f.Close();
                }
            }

            return;


        }

        void textBox1_MouseMove(object sender, MouseEventArgs e)
        {

            if (dragInProgress)
            {

                Point temp = new Point();

                temp.X = this.Location.X + (e.X - MouseDownX);

                temp.Y = this.Location.Y + (e.Y - MouseDownY);

                this.Location = temp;

            }

            return;

        }

        
    }
}
