using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using System.ComponentModel;

namespace Benzopili
{
    public class Base : MapObject
    {
        protected Game1 game1;
        private Texture2D texture;
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
            }
        }
        [Category("Base")]
        public Color Color { get; set; }
        [Category("Base")]
        public Base ParentTranslation { get; set; }
        [Category("Base")]
        public Base ParentRotation { get; set; }
        [Category("Base")]
        public Base ParentSize { get; set; }
        [Category("Base")]
        public SpriteEffects SpriteEffects { get; set; }

        public Base(Game1 game1, String name, Rectangle rect, Texture2D texture, Color color)
        {
            this.game1 = game1;
            this.Rect = rect;
            this.Texture = texture;
            this.Color = color;
            this.Name = name;
            this.SpriteEffects = SpriteEffects.None;
            this.Origin = new Vector2(0, 0);
            UpdatePosition();
        }

        public override void Draw()
        {
            DrawTexture(Texture);
        }
        public override void Update()
        {
            UpdatePosition();
        }
        protected void UpdatePosition()
        {
            if (ParentTranslation != null)
            {
                DisplayRect = new Rectangle(Rect.X + ParentTranslation.DisplayRect.X, Rect.Y + ParentTranslation.DisplayRect.Y, Rect.Width, Rect.Height);

            }
            else DisplayRect = Rect;
            if (ParentRotation != null)
            {
                Angle = ParentRotation.Angle;
                SpriteEffects = ParentRotation.SpriteEffects;
            }
        }
        protected void DrawTexture(Texture2D texture)
        {
            game1.SpriteBatch.Draw(texture, DisplayRect, Texture.Bounds, Color, Angle, Origin, SpriteEffects, 1);
        }
    }
}