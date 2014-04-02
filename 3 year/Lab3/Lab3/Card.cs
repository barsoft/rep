using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Drawing;

namespace Lab3
{
    /// <summary>
    /// Card class
    /// </summary>
    public class Card:PictureBox
    {
        PictureBox sign;
        /// <summary>
        /// Card class constructor
        /// </summary>
        /// <param name="sampleBitmap"></param>
        /// <param name="signFileName"></param>
        /// <param name="x"></param>
        /// <param name="y"></param>
        /// <param name="name"></param>
        public Card(Bitmap sampleBitmap, string signFileName, int x, int y,string name)
        {
            this.Left = x;
            this.Top = y;
            this.Width = 100;
            this.Height = 150;
            this.SizeMode = PictureBoxSizeMode.StretchImage;
            this.Image = sampleBitmap;
            this.Name = name;

            sign = new PictureBox();
            sign.Width = 30;
            sign.Height = 30;
            sign.Left = this.Width/2-sign.Width/2;
            sign.Top = this.Height / 2 - sign.Height / 2;
            sign.Image = new Bitmap(signFileName);
            sign.SizeMode = PictureBoxSizeMode.StretchImage;
            this.Controls.Add(sign);
            this.sign.Enabled = false;
        }

    }
}
