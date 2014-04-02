using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Audio;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.GamerServices;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework.Media;
using System.Windows.Forms;
using System.ComponentModel;

namespace MapEditor
{
    /// <summary>
    /// This is a game component that implements IUpdateable.
    /// </summary>
    /// 
   
    public class Base 
    {
        [Category("MapObject")]
        public String Name { get; set; }
        [Category("MapObject")]
        public Point Position { get { return new Point(rect.X, rect.Y); } set { rect.X = value.X; rect.Y = value.Y; } }
        [Category("MapObject")]
        [ReadOnly(true)]
        public Point Size { get { return new Point(rect.Width, rect.Height); } set { rect.Width = value.X; rect.Height = value.Y; } }
        [Browsable(false)]
        public Rectangle Rect { get { return rect; } set { Position=new Point(value.X,value.Y); Size=new Point(value.Width,value.Height); } }
        protected Rectangle rect;
        protected Rectangle disprect;
        [Browsable(false)]
        public Texture2D Texture { get; set; }
        [Browsable(false)]
        public Texture2D BlueLight { get; set; }
        [Browsable(false)]
        public Texture2D GreenLight { get; set; }
        public Game1 game1;
        [Browsable(false)]
        public bool Selected { get; set; }
        [Browsable(false)]
        public bool Focused
        {
            get
            {
                MouseState ms = Mouse.GetState();
                if (ms.X > disprect.X && ms.X < disprect.Right && ms.Y > disprect.Y && ms.Y < disprect.Bottom &&game1.mainWindow.Focused)
                    return true;
                return false;
            }
        }
        public string Type
        {
            get;
            set
            ;
        }

        public Base(Game1 game1)
        {
            this.game1 = game1;
            BlueLight = game1.map.bluelight;
            GreenLight = game1.map.greenlight;
            Selected=false;
        }
        public virtual void Update(GameTime gameTime)
        {
            float z = game1.map.camera.Zoom / 100.0f;

            float x = game1.map.camera.X;
            float y = game1.map.camera.Y;

            disprect.X = (int)(rect.Left * z + x * z);
            disprect.Y = (int)(rect.Top * z + y * z);

            disprect.Width = (int)(rect.Width * z);
            disprect.Height = (int)(rect.Height * z);
        }
        public virtual void Draw()
        {
            if (Selected)
            {
                game1.spriteBatch.Draw(BlueLight, disprect, Color.White);
            }
            else
                if (Focused)
                {
                    game1.spriteBatch.Draw(GreenLight, disprect, Color.White);
                }
        }
    }
}
