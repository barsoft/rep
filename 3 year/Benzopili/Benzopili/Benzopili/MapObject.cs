using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.ComponentModel;
using Microsoft.Xna.Framework;

namespace Benzopili
{
    public class MapObject
    {
        [Category("MapObject")]
        public Object Type { get { return this.GetType(); } }
        [Category("MapObject")]
        public String Name { get; set; }
        [Category("MapObject")]
        public float Angle { get; set; }
        [Category("MapObject")]
        public Vector2 Origin { get; set; }
        private int x;
        private int y;
        private int width;
        private int height;
        [Category("MapObject")]
        public Vector2 Position
        {
            get { return new Vector2(x, y); }
            set
            {
                if (value.X >= 0)
                    x = (int)value.X;
                y = (int)value.Y;
            }
        }
        [Category("MapObject")]
        public Vector2 Size
        {
            get { return new Vector2(width, height); }
            set { width = (int)value.X; height = (int)value.Y; }
        }
        [Category("MapObject")]
        [ReadOnly(true)]
        public Rectangle Rect
        {
            get { return new Rectangle((int)Position.X, (int)Position.Y, (int)Size.X, (int)Size.Y); }
            set
            {
                Position = new Vector2(value.X, value.Y);
                Size = new Vector2(value.Width, value.Height);
            }
        }
        [Category("MapObject")]
        [ReadOnly(true)]
        public Rectangle DisplayRect
        {
            get;
            set;
        }
        virtual public void Draw()
        {

        }
        virtual public void Update()
        {

        }
    }
}
