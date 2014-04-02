using MapEditor;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.IO;

namespace WindowsGame1
{
    public partial class Form1 : Form
    {
        Game1 game1;
        public Form1(Game1 game1)
        {
            InitializeComponent();
            this.game1 = game1;
        }

        private void saveToolStripMenuItem_Click(object sender, EventArgs e)
        {
            saveFileDialog1.ShowDialog();
        }

        private void saveFileDialog1_FileOk(object sender, CancelEventArgs e)
        {
            FileStream fs = new FileStream((sender as SaveFileDialog).FileName, FileMode.Create);
            StreamWriter sw = new StreamWriter(fs);

            sw.WriteLine("Level file , Barsoft, 2013");
            sw.WriteLine("Trees");
            sw.WriteLine("{");

            foreach (Sprite sprite in game1.sprites)
            {
                if (sprite.Texture == game1.Tree)
                    sw.WriteLine(sprite.Rectangle.Left / 100 + "," + sprite.Rectangle.Top / 100 + ";");
            }
            sw.WriteLine("}");
            sw.WriteLine("Stars");
            sw.WriteLine("{");

            foreach (Sprite sprite in game1.sprites)
            {
                if (sprite.Texture == game1.Star)
                    sw.WriteLine(sprite.Rectangle.Left / 100 + "," + sprite.Rectangle.Top / 100 + ";");
            }
            sw.WriteLine("}");
            sw.WriteLine("Kolobok");
            sw.WriteLine("{");

            foreach (Sprite sprite in game1.sprites)
            {
                if (sprite.Texture == game1.Kolobok)
                    sw.WriteLine(sprite.Rectangle.Left / 100 + "," + sprite.Rectangle.Top / 100 + ";");
            }
            sw.WriteLine("}");
            sw.Dispose();
            fs.Dispose();
        }

        private void openFileDialog1_FileOk(object sender, CancelEventArgs e)
        {
            FileStream fs = new FileStream((sender as OpenFileDialog).FileName, FileMode.Open);
            StreamReader sr = new StreamReader(fs);

            int i = 0;
            String str;

            do str = sr.ReadLine();
            while (!str.Equals("Trees"));
            do str = sr.ReadLine();
            while (!str.Equals("{"));
            do
            {
                i = 0;
                str = sr.ReadLine();
                if (str.Equals("}"))
                    break;

                StringBuilder sb = new StringBuilder();

                while (str[i] != ',')
                {
                    sb.Append(str[i]);
                    i++;
                }
                int x = Int32.Parse(sb.ToString());

                sb.Clear();
                i++;

                while (str[i] != ';')
                {
                    sb.Append(str[i]);
                    i++;
                }
                int y = Int32.Parse(sb.ToString());

                Sprite tree = new Sprite();
                tree.Texture = game1.Tree;
                tree.Rectangle = new Microsoft.Xna.Framework.Rectangle(x * 100, y * 100, 100, 100);
                game1.sprites.Add(tree);
            }
            while (true);

            do str = sr.ReadLine();
            while (!str.Equals("Stars"));
            do str = sr.ReadLine();
            while (!str.Equals("{"));
            do
            {
                i = 0;
                str = sr.ReadLine();
                if (str.Equals("}"))
                    break;

                StringBuilder sb = new StringBuilder();

                while (str[i] != ',')
                {
                    sb.Append(str[i]);
                    i++;
                }
                int x = Int32.Parse(sb.ToString());

                sb.Clear();
                i++;

                while (str[i] != ';')
                {
                    sb.Append(str[i]);
                    i++;
                }
                int y = Int32.Parse(sb.ToString());

                Sprite star = new Sprite();
                star.Texture = game1.Star;
                star.Rectangle = new Microsoft.Xna.Framework.Rectangle(x * 100, y * 100, 100, 100);
                game1.sprites.Add(star);

            }
            while (true);

            do str = sr.ReadLine();
            while (!str.Equals("Kolobok"));
            do str = sr.ReadLine();
            while (!str.Equals("{"));
            do
            {
                i = 0;
                str = sr.ReadLine();
                if (str.Equals("}"))
                    break;

                StringBuilder sb = new StringBuilder();

                while (str[i] != ',')
                {
                    sb.Append(str[i]);
                    i++;
                }
                int x = Int32.Parse(sb.ToString());

                sb.Clear();
                i++;

                while (str[i] != ';')
                {
                    sb.Append(str[i]);
                    i++;
                }
                int y = Int32.Parse(sb.ToString());

                Sprite kolobok = new Sprite();
                kolobok.Texture = game1.Kolobok;
                kolobok.Rectangle = new Microsoft.Xna.Framework.Rectangle(x * 100, y * 100, 100, 100);
                game1.sprites.Add(kolobok);

            }
            while (true);
        }

        private void loadToolStripMenuItem_Click(object sender, EventArgs e)
        {
            openFileDialog1.ShowDialog();
        }
    }
}
