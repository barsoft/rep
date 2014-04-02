using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using System.IO;
using System.ComponentModel;

namespace Robo
{
    public class Animation : Base
    {
        [Category("Animation")]
        public List<Texture2D> Frames { get; set; }
        [Category("Animation")]
        public float AnimationSpeed { get; set; }
        [Category("Animation")]
        public int CurreentFrame { get; set; }
        [Category("Animation")]
        public int CountFrames { get; set; }
        public Animation(Game1 game1, Base parent,Base parentRotater, String name,int countFrames, Rectangle rect, Color color)
            : base(game1, parent,parentRotater, name, rect, new Texture2D(game1.GraphicsDevice,300,300), color)
        {
            Frames = new List<Texture2D>();
            CurreentFrame = 0;
            CountFrames = countFrames;
        }
        public void AddFrame(String texturePath)
        {
            using (FileStream textureStream = new FileStream(texturePath, FileMode.Open))
            {
                Frames.Add(Texture2D.FromStream(game1.GraphicsDevice, textureStream));
            }
        }
        public override void Draw()
        {
            game1.spriteBatch.Draw(Frames[CurreentFrame], DisplayRect, Frames[CurreentFrame].Bounds, Color, Angle, Origin, SpriteEffects, 1);
        }
        public override void Update()
        {
            base.Update();
        }
    }
}
