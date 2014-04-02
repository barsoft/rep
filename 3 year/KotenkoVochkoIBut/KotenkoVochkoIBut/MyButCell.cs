using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Drawing;

namespace KotenkoVochkoIBut
{
    public class MyButCell:Button
    {
        private Boolean dragInProgress = false;

        
        //
        public MyButCell(string сh,int x,int y,Form1 f)
        {
            this.MouseDown += new MouseEventHandler(textBox1_MouseDown);
            this.MouseMove += new MouseEventHandler(textBox1_MouseMove);
            this.MouseUp += new MouseEventHandler(textBox1_MouseUp);
            this.Width = 50;
            this.Height = 50;
            //this.Text = сh.ToString();
            //this.Font = new Font("arial",30,FontStyle.Regular);
            this.Left = x;
            this.Top = y;
            this.Enabled = false;
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
