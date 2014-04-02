using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using System.ComponentModel;

namespace Robo
{
    public class AnimatedSprite : Base
    {
        public AnimatedSprite(Game1 game1, Base parent, Base parentRotater, String name, Rectangle rect, Color color)
            : base(game1, parent,parentRotater, name, rect, new Texture2D(game1.GraphicsDevice, 300, 300), color)
        {
            Animations = new List<Animation>();
        }
        public int currrentAnimation;
        [Category("AnimatedSprite")]
        public int CurrrentAnimation
        {
            get
            {
                return currrentAnimation;
            }
            set
            {
                currrentAnimation = value; Animations[CurrrentAnimation].CurreentFrame = 0;
                Update();
            }
        }
        [Category("AnimatedSprite")]
        public List<Animation> Animations { get; set; }
        public void AddAnimation(Animation animation)
        {
            Animations.Add(animation);
        }
        public override void Draw()
        {
            Animations[CurrrentAnimation].Draw();
        }
        public override void Update()
        {
            base.Update();
            Animations[CurrrentAnimation].Update();
            Animations[CurrrentAnimation].CurreentFrame++;
            if (Animations[CurrrentAnimation].CurreentFrame > Animations[CurrrentAnimation].Frames.Count - 1)
            {
                Animations[CurrrentAnimation].CurreentFrame = 0;
            }
        }
    }
}
