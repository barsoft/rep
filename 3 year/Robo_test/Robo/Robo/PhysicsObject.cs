using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using System.ComponentModel;

namespace Robo
{
    public class PhysicsObject : Base
    {
        public float Speed { get; set; }
        public float CoefUprug { get; set; }
        public float CoefTren { get; set; }
        public float Mass { get; set; }
        private float horizontalVelocity;
        public float HorizontalVelocity { get { return horizontalVelocity; } }
        private float verticalVelocity;
        public float VerticalVelocity { get { return verticalVelocity; } }
        public float Gravity { get; set; }
        public float Inertion { get; set; }
        private float oppositeVerticalVelocity;
        public PhysicsObject(Game1 game1, PhysicsObject parent,PhysicsObject parentRotater, String name, Rectangle rect, Color color)
            : base(game1, parent,parentRotater, name, rect, null, color)
        {
            this.Texture = new Texture2D(game1.GraphicsDevice, 100, 100);
            Color[] color2 = new Color[100 * 100];
            for (int i = 0; i < color2.Length; i++)//loop through all the colors setting them to whatever values we want
            {
                color2[i] = Color.Red;
            }
            Texture.SetData(color2);
            UpdatePosition();
            Gravity = game1.World.Gravity;
        }
        public override void Update()
        {
            base.Update();
            verticalVelocity += (float)Mass * Gravity / 100;

            verticalVelocity -= oppositeVerticalVelocity * CoefUprug;
            oppositeVerticalVelocity = 0;

            horizontalVelocity = Speed;

            Inertion -= CoefTren / 50.0f;
            if (Inertion < 0)
                Inertion = 0;

            ColisionCheck(ParentDependentRect.X, ParentDependentRect.Y + (int)VerticalVelocity, true);


            ColisionCheck(ParentDependentRect.X + (int)HorizontalVelocity, ParentDependentRect.Y, false);
        }
        public void ColisionCheck(int x, int y, bool vert)
        {
            Rectangle newRect = new Rectangle(x, y, ParentDependentRect.Width, ParentDependentRect.Height);
            Rectangle ground = new Rectangle(0, game1.graphics.PreferredBackBufferHeight - 100, game1.graphics.PreferredBackBufferWidth, 100);
            if (!newRect.Intersects(ground))
                ParentDependentRect = newRect;
            else
            {
                if (vert)
                {
                    oppositeVerticalVelocity = VerticalVelocity;
                    verticalVelocity = 0;
                }
                Inertion = 0;
            }
        }
    }
}
