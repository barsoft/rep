using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using System.ComponentModel;

namespace Robo
{
    public class Base : MapObject
    {
        protected Game1 game1;
        private Texture2D texture;
        public Vector2 resultOrigin;
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
                    ratio = (float)texture.Width / Rect.Width;
                }
                UpdatePosition();
            }
        }


        [Category("Base")]
        public Color Color { get; set; }
        [Category("Base")]
        public Base Parent { get; set; }
        [Category("Base")]
        public Base ParentRotater { get; set; }
        private List<PhysicsObject> physicsObjects;
        [Category("Base")]
        public List<PhysicsObject> PhysicsObjects
        {
            get { return physicsObjects; }
            set
            {
                if (value != null)
                {
                    
                    foreach (PhysicsObject po in value)
                    {
                        physicsObjects.Add(po);
                    }
                    if (physicsObjects.Count > 0)
                    {
                        this.Rect = new Rectangle(0, 0, GetMainPhysicsObject().Rect.Width, GetMainPhysicsObject().Rect.Height);
                        this.Parent = GetMainPhysicsObject();
                        this.ParentRotater = GetMainPhysicsObject();
                    }
                }
            }
        }
        [Category("Base")]
        public bool PhysicsVisible { get; set; }
        [Category("Base")]
        public SpriteEffects SpriteEffects { get; set; }
        public Base(Game1 game1, Base parent, Base parentRotater, String name, Rectangle rect, Texture2D texture, Color color)
        {
            this.game1 = game1;
            this.Rect = rect;
            this.Texture = texture;
            this.Color = color;
            this.Name = name;
            this.Parent = parent;
            this.ParentRotater = parentRotater;
            this.physicsObjects = new List<PhysicsObject>();
            this.PhysicsVisible = false;
            this.SpriteEffects = SpriteEffects.None;
            UpdatePosition();
        }
        public Base(Game1 game1, String name, Texture2D texture, Color color, List<PhysicsObject> physicsObjects)
        {
            this.game1 = game1;

            this.Texture = texture;
            this.Color = color;
            this.Name = name;
            this.physicsObjects = new List<PhysicsObject>();
            this.PhysicsObjects = physicsObjects;

            this.PhysicsVisible = false;
            this.SpriteEffects = SpriteEffects.None;
            UpdatePosition();
        }
        public override void Draw()
        {
            if (PhysicsVisible)
                foreach (PhysicsObject po in PhysicsObjects)
                    po.Draw();
            DrawTexture(Texture);
        }
        public override void Update()
        {
            UpdatePosition();
            foreach (PhysicsObject po in PhysicsObjects)
                po.Update();
        }
        protected void UpdatePosition()
        {
            if (Parent != null)
            {
                this.displayRect = new Rectangle(Rect.X + Parent.DisplayRect.X, Rect.Y + Parent.DisplayRect.Y, Rect.Width, Rect.Height);
                this.SpriteEffects = Parent.SpriteEffects;
            }
            else this.displayRect = new Rectangle(Rect.X, Rect.Y, Rect.Width, Rect.Height);
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
            game1.spriteBatch.Draw(texture, new Rectangle(displayRect.X - (int)((texture.Width / 2 - resultOrigin.X) / ratio) /*+ (int)((texture.Width - resultOrigin.X) / ratio)*/,
                displayRect.Y - (int)((texture.Height / 2 - resultOrigin.Y) / ratio) /*+ (int)((texture.Height - resultOrigin.Y) / ratio)*/, displayRect.Width, displayRect.Height),
                texture.Bounds, Color, Angle, resultOrigin, SpriteEffects, 1);
        }
        public PhysicsObject GetMainPhysicsObject()
        {
            return physicsObjects[0];
        }
    }
}