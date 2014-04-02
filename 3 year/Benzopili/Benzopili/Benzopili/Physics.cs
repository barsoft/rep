using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using System.ComponentModel;

namespace Benzopili
{
    [TypeConverter(typeof(ExpandableObjectConverter))]
    public class Physics : Base
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
        public List<PhysicsObject> PhysicsObjects { get; set; }
        private float oppositeVerticalVelocity;
        public bool IsOnGround
        {
            get
            {
                return verticalVelocity == 0;
            }
        }
        public Physics(Game1 game1, String name, Rectangle rect, float mass, float coefUprug, float coefTren)
            : base(game1, name, rect, new Texture2D(game1.GraphicsDevice, rect.Width, rect.Height), Color.White)
        {
            this.Gravity = game1.World.Gravity;
            this.Mass = mass;
            this.CoefTren = coefTren;
            this.CoefUprug = coefUprug;
            this.PhysicsObjects = new List<PhysicsObject>();
        }
        public override void Update()
        {
            base.Update();
            foreach (PhysicsObject po in PhysicsObjects)
            {
                po.ParentTranslation = this;
                po.Update();
            }
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
        public override void Draw()
        {
            foreach (PhysicsObject po in PhysicsObjects)
            {
                po.Draw();
            }
        }
        public void ColisionCheck(int x, int y, bool vert)
        {
            Rectangle newRect = new Rectangle(x, y, Rect.Width, Rect.Height);

           
                Rect = newRect;

          /*  else
            {
                
            }*/
        }
        
        public void PushUp(float force)
        {
            oppositeVerticalVelocity = force;
        }
        public Physics Clone()
        {
            Physics buf = new Physics(game1, "p", Rect, Mass, CoefUprug, CoefTren);
            foreach (PhysicsObject po in PhysicsObjects)
            {
                buf.PhysicsObjects.Add(new PhysicsObject(game1,"asd",po.Rect,po.Color));
            }
            return buf;
        }
    }

}
