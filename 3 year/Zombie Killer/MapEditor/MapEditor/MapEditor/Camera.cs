using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Audio;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.GamerServices;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework.Media;
using System.Windows.Forms;
namespace MapEditor
{
    public class Camera : Microsoft.Xna.Framework.GameComponent
    {
        public int Zoom { get; set; }
        public int X { get; set; }
        public int Y { get; set; }
        public Game1 game1;
        private Point diff;
        public Camera(Game1 game1,int zoom)
            : base(game1)
        {
            Zoom = zoom;
            X = 0;
            Y = 0;
            this.game1 = game1;
            
            game1.mainWindow.MouseMove += new MouseEventHandler(mainWindow_MouseMove);
            game1.mainWindow.MouseDown += new MouseEventHandler(mainWindow_MouseDown);
            game1.mainWindow.MouseWheel += new MouseEventHandler(mainWindow_MouseWheel);
            
        }

        public override void Update(GameTime gameTime)
        {
            base.Update(gameTime);
            MouseState newMouseState = Mouse.GetState();
        }

        void mainWindow_MouseWheel(object sender, MouseEventArgs e)
        {
            int nextZoom = Zoom + (int)(e.Delta / 20.0f);
            if (nextZoom <= 300 && nextZoom >= 1)
                Zoom = nextZoom;
        }

        void mainWindow_MouseMove(object sender, MouseEventArgs e)
        {
            if (e.Button == System.Windows.Forms.MouseButtons.Middle)
            {
                int newx = e.X - diff.X;
                int newy = e.Y - diff.Y;
                if (newx < 0)
                    X = newx;
                if (newy < 0)
                    Y = newy;
            }
        }

        void mainWindow_MouseDown(object sender, MouseEventArgs e)
        {
            diff.X = e.X - X;
            diff.Y = e.Y - Y;
        }
    }
}
