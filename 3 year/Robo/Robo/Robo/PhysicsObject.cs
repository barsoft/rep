using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using System.ComponentModel;
using System.Windows.Forms;

namespace Robo
{
    public class PhysicsObject : Base
    {
        public float HorizontalSpeed { get; set; }
        public float VerticalSpeed { get; set; }
        public float CoefUprug { get; set; }
        public float CoefTren { get; set; }
        public float Mass { get; set; }
        private float horizontalVelocity;
        public float HorizontalVelocity { get { return horizontalVelocity; } }
        private float verticalVelocity;
        public float VerticalVelocity { get { return verticalVelocity; } }
        public float Gravity { get; set; }
        public float Inertion { get; set; }
        public bool IsOnGround
        {
            get
            {
                return verticalVelocity == 0;
            }
        }
        private float oppositeVerticalVelocity;

        public PhysicsObject(Game1 game1, String name, Rectangle rect, Color color, float mass, float coefUprug, float coefTren)
            : base(game1, null, null, name, rect, null, color)
        {
            this.Texture = new Texture2D(game1.GraphicsDevice, 100, 100);
            this.Gravity = game1.World.Gravity;
            this.Mass = mass;
            this.CoefTren = coefTren;
            this.CoefUprug = coefUprug;
            Color[] color2 = new Color[100 * 100];
            for (int i = 0; i < color2.Length; i++)
            {
                color2[i] = Color.Red;
            }
            Texture.SetData(color2);
            UpdatePosition();
        }
        public override void Update()
        {
            base.Update();
            verticalVelocity += (float)Mass * Gravity / 100;

            verticalVelocity -= oppositeVerticalVelocity * CoefUprug;
            oppositeVerticalVelocity = 0;

            horizontalVelocity = HorizontalSpeed;

            Inertion -= CoefTren / 50.0f;
            if (Inertion < 0)
                Inertion = 0;

            ColisionCheck(Rect.X, Rect.Y + (int)VerticalVelocity, true);


            ColisionCheck(Rect.X + (int)HorizontalVelocity, Rect.Y, false);
        }
        public void ColisionCheck(int x, int y, bool vert)
        {
            Rectangle newRect = new Rectangle(x, y, Rect.Width, Rect.Height);
            bool intersectsMaterial = false;
            foreach (MapObject obj in game1.World.Objects)
            {
                if (obj is Material)
                {

                    foreach (PhysicsObject po in (obj as Material).PhysicsObjects)
                    {
                        Rectangle objRect = po.DisplayRect;
                        if (objRect.Intersects(newRect))
                        {
                            intersectsMaterial = true;
                            // MessageBox.Show("a");
                            break;
                        }
                    }

                    if (intersectsMaterial)
                        break;
                }
                intersectsMaterial = false;
            }
            if (!intersectsMaterial)
                Rect = newRect;
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
        public void PushUp(float force)
        {
            oppositeVerticalVelocity = force;
        }
    }
}
