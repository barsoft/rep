using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using System.ComponentModel;
using System.Windows.Forms;

namespace Benzopili
{
    public class PhysicsObject : Base
    {
        public PhysicsObject(Game1 game1, String name, Rectangle rect, Color color)
            : base(game1, name, rect, new Texture2D(game1.GraphicsDevice, rect.Width, rect.Height), color)
        {
            Color[] color2 = new Color[rect.Width * rect.Height];
            for (int i = 0; i < color2.Length; i++)
            {
                color2[i] = Color.Red;
            }
            Texture.SetData(color2);
           
            UpdatePosition();
        }
        
    }
}
