using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using System.ComponentModel;

namespace Robo
{
    public class Base
    {
        protected Game1 game1;
        [Category("Base")]
        public String Name { get; set; }
        private Texture2D texture;
        public Vector2 resultOrigin;
        [Category("Base")]
        public Vector2 Origin { get; set; }
        [Category("Base")]
        public float ratio { get; set; }
        [Category("Base")]
        public Texture2D Texture
        {
            get
            {
                return texture;
            }
            set
            {
                texture = value;
                if (texture == null)
                {
                    Origin = Vector2.Zero;
                    ratio = 0;
                }
                else
                {
                    Origin = new Vector2(texture.Width / 2, texture.Height / 2);
                    ratio = texture.Width / ParentDependentRect.Width;
                }
                UpdatePosition();
            }
        }
        [Category("Base")]
        public Rectangle ParentDependentRect { get; set; }
        private Rectangle displayRect;
        [Category("Base")]
        public Rectangle DisplayRect { get { return displayRect; } }
        [Category("Base")]
        public Color Color { get; set; }
        [Category("Base")]
        public float Angle { get; set; }
        [Category("Base")]
        public Base Parent { get; set; }
        [Category("Base")]
        public Base ParentRotater { get; set; }
        [Category("Base")]
        public PhysicsObject PhysicsObject { get; set; }
        [Category("Base")]
        public bool PhysicsVisible { get; set; }
        [Category("Base")]
        public SpriteEffects SpriteEffects { get; set; }
        public Base(Game1 game1, Base parent,Base parentRotater, String name, Rectangle rect, Texture2D texture, Color color)
        {
            this.game1 = game1;
            this.ParentDependentRect = rect;
            this.Texture = texture;
            this.Color = color;
            this.Name = name;
            this.Parent = parent;
            this.ParentRotater = parentRotater;
            game1.World.Objects.Add(this);
            PhysicsVisible = false;
            SpriteEffects = SpriteEffects.None;
            UpdatePosition();
        }
        virtual public void Draw()
        {
            if (PhysicsObject != null && PhysicsVisible)
                PhysicsObject.Draw();
            DrawTexture(Texture);
        }
        virtual public void Update()
        {
            UpdatePosition();
            if (PhysicsObject != null)
                PhysicsObject.Update();
        }
        protected void UpdatePosition()
        {
            if (Parent != null)
            {
                this.displayRect = new Rectangle(ParentDependentRect.X + Parent.DisplayRect.X, ParentDependentRect.Y + Parent.DisplayRect.Y, ParentDependentRect.Width, ParentDependentRect.Height);
                this.SpriteEffects = Parent.SpriteEffects;
            }
            else this.displayRect = new Rectangle(ParentDependentRect.X, ParentDependentRect.Y, ParentDependentRect.Width, ParentDependentRect.Height);
            if (ParentRotater != null)
            {
                this.Angle = ParentRotater.Angle;
            }
            if (SpriteEffects == SpriteEffects.None)
            {
                resultOrigin = Origin;
            }
            else
            {
                resultOrigin = new Vector2(texture.Width - Origin.X, Origin.Y);
            }
        }
        protected void DrawTexture(Texture2D texture)
        {
            game1.spriteBatch.Draw(texture, new Rectangle(displayRect.X  /*(- int)(Origin.X/ratio)*/, displayRect.Y/*- (int)(Origin.Y/ratio)*/, displayRect.Width, displayRect.Height), texture.Bounds, Color, Angle, resultOrigin, SpriteEffects, 1);
        }
    }
}