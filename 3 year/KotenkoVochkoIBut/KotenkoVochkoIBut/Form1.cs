using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace KotenkoVochkoIBut
{
    public partial class Form1 : Form
    {
        public List<MyBut> but = new List<MyBut>();
        public List<MyButCell> butcel = new List<MyButCell>();
        public Form1()
        {
            InitializeComponent();
           
            Random r = new Random();
            this.Location = new Point(0, 0);

            this.Width = Screen.GetBounds(this).Width;
            this.Height = Screen.GetBounds(this).Height;

            int minY = 200;
            int maxY = this.Height - 200;
            int maxX = this.Width - 100;

            but.Add(new MyBut("К", r.Next(maxX), r.Next(minY, maxY),this));
            but.Add(new MyBut("О", r.Next(maxX), r.Next(minY, maxY), this));
            but.Add(new MyBut("Т", r.Next(maxX), r.Next(minY, maxY), this));
            but.Add(new MyBut("Е", r.Next(maxX), r.Next(minY, maxY), this));
            but.Add(new MyBut("Н", r.Next(maxX), r.Next(minY, maxY), this));
            but.Add(new MyBut("К", r.Next(maxX), r.Next(minY, maxY), this));
            but.Add(new MyBut("О", r.Next(maxX), r.Next(minY, maxY), this));
            but.Add(new MyBut("В", r.Next(maxX), r.Next(minY, maxY), this));
            but.Add(new MyBut("О", r.Next(maxX), r.Next(minY, maxY), this));
            but.Add(new MyBut("Ч", r.Next(maxX), r.Next(minY, maxY), this));
            but.Add(new MyBut("К", r.Next(maxX), r.Next(minY, maxY), this));
            but.Add(new MyBut("О", r.Next(maxX), r.Next(minY, maxY), this));
            but.Add(new MyBut("И", r.Next(maxX), r.Next(minY, maxY), this));
            but.Add(new MyBut("Б", r.Next(maxX), r.Next(minY, maxY), this));
            but.Add(new MyBut("У", r.Next(maxX), r.Next(minY, maxY), this));
            but.Add(new MyBut("Т", r.Next(maxX), r.Next(minY, maxY), this));

            for (int i = 0; i < 16; i++)
            {
                butcel.Add(new MyButCell(but[i].Text,i*60+this.Width/2-480,100,this));
            }

            foreach (Button b in but)
            {
                this.Controls.Add(b);
            }
            foreach (Button b in butcel)
            {
                this.Controls.Add(b);
            }

            this.ClientSizeChanged += new EventHandler(Form1_ClientSizeChanged);

            label1.Left = this.Width / 2 - label1.Width / 2;
        }

        void Form1_ClientSizeChanged(object sender, EventArgs e)
        {
            label1.Left = this.Width / 2 - label1.Width / 2;
        }
    }
}
