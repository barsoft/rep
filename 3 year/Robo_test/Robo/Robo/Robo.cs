using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using System.IO;
using System.ComponentModel;
using Microsoft.Xna.Framework.Input;

namespace Robo
{
    public class Robo : Base
    {
        [Category("Robo")]
        public List<AnimatedSprite> BodyParts { get; set; }
        [Category("Robo")]
        public float Speed { get; set; }
        private KeyboardState oldKeyState;
        public Robo(Game1 game1, Base parent, Base parentRotater, Rectangle rect, Color color)
            : base(game1, parent, parentRotater, "robo", new Rectangle(0, 0, rect.Width, rect.Height), Texture2D.FromStream(game1.GraphicsDevice, new FileStream("res\\robo\\head.png", FileMode.Open)), color)
        {
            BodyParts = new List<AnimatedSprite>();
            BodyParts.Add(new AnimatedSprite(game1, this,this, "leftHand", new Rectangle(0, 0, ParentDependentRect.Width, ParentDependentRect.Height), color));
            BodyParts.Add(new AnimatedSprite(game1, this,this, "rightHand", new Rectangle(0, 0, ParentDependentRect.Width, ParentDependentRect.Height), color));
            BodyParts.Add(new AnimatedSprite(game1, this,null, "leftLeg", new Rectangle(0, 0, ParentDependentRect.Width, ParentDependentRect.Height), color));
            BodyParts.Add(new AnimatedSprite(game1, this,null, "rightLeg", new Rectangle(0, 0, ParentDependentRect.Width, ParentDependentRect.Height), color));
            InitBodyParts();
            Speed = 10;
            PhysicsObject = new PhysicsObject(game1, null, null, "roboPhysics", rect, Color.Red);
            this.Parent = PhysicsObject;
            PhysicsObject.Mass = 15.6f;
            PhysicsObject.CoefUprug = 0.4f;
            PhysicsObject.CoefTren = 0.3f;
            this.Origin=new Vector2(Texture.Width/3f,Texture.Height/2);
        }
        public override void Draw()
        {
            foreach (AnimatedSprite bodyPart in BodyParts)
            {
                if (bodyPart.Name == "leftHand" || bodyPart.Name == "leftLeg")
                    bodyPart.Draw();
            }
            base.Draw();
            foreach (AnimatedSprite bodyPart in BodyParts)
            {
                if (bodyPart.Name == "rightHand" || bodyPart.Name == "rightLeg")
                    bodyPart.Draw();
            }
        }
        public override void Update()
        {
            
           

            KeyboardState keyState = Keyboard.GetState();

            if (keyState.IsKeyDown(Keys.D) && oldKeyState.IsKeyUp(Keys.D))
            {
                PhysicsObject.Speed = Speed;
                PhysicsObject.SpriteEffects = SpriteEffects.None;
                foreach (AnimatedSprite bodyPart in BodyParts)
                {
                    if (bodyPart.Name == "rightLeg")
                    {
                        bodyPart.CurrrentAnimation = 1;
                    }
                    if (bodyPart.Name == "leftLeg")
                    {
                        bodyPart.CurrrentAnimation = 1;
                    }
                }
            }
            else
                if (keyState.IsKeyDown(Keys.A) && oldKeyState.IsKeyUp(Keys.A))
                {
                    PhysicsObject.Speed = -Speed;
                    PhysicsObject.SpriteEffects = SpriteEffects.FlipHorizontally;
                    foreach (AnimatedSprite bodyPart in BodyParts)
                    {
                        if (bodyPart.Name == "rightLeg")
                        {
                            bodyPart.CurrrentAnimation = 1;
                            bodyPart.Animations[bodyPart.CurrrentAnimation].CurreentFrame = 21;
                        }
                        if (bodyPart.Name == "leftLeg")
                        {
                            bodyPart.CurrrentAnimation = 1;
                            bodyPart.Animations[bodyPart.CurrrentAnimation].CurreentFrame = 21;
                        }
                    }
                }
                else
                    if ((keyState.IsKeyUp(Keys.A) && oldKeyState.IsKeyDown(Keys.A)) || (keyState.IsKeyUp(Keys.D) && oldKeyState.IsKeyDown(Keys.D)))
                    {
                        foreach (AnimatedSprite bodyPart in BodyParts)
                        {
                            if (bodyPart.Name == "rightLeg")
                            {
                                bodyPart.CurrrentAnimation = 0;
                            }
                            if (bodyPart.Name == "leftLeg")
                            {
                                bodyPart.CurrrentAnimation = 0;
                            }
                            PhysicsObject.Speed = 0;
                        }
                    }
            oldKeyState = keyState;

            MouseState mouseState = Mouse.GetState();

            if (mouseState.X - DisplayRect.X >= 0)
                PhysicsObject.SpriteEffects = SpriteEffects.None;
            else
                PhysicsObject.SpriteEffects = SpriteEffects.FlipHorizontally;
            Angle = (float)(Math.Atan((mouseState.Y - DisplayRect.Y) / (float)(mouseState.X - DisplayRect.X)));
            if (Angle < -1.3f) Angle = -1.3f;
            if (Angle > 1.3f) Angle = 1.3f;
            base.Update();
            foreach (AnimatedSprite bodyPart in BodyParts)
            {
                bodyPart.Update();
            }
        }
        public void InitBodyParts()
        {
            foreach (AnimatedSprite bodyPart in BodyParts)
            {
                if (bodyPart.Name == "leftHand")
                {
                    bodyPart.AddAnimation(new Animation(game1, bodyPart, bodyPart, "idle", 1, new Rectangle(0, 0, ParentDependentRect.Width, ParentDependentRect.Height), Color));
                    foreach (Animation animation in bodyPart.Animations)
                    {
                        if (animation.Name == "idle")
                        {
                            for (int i = 0; i < animation.CountFrames; i++)
                            {
                                animation.AddFrame("res/robo/leftHand/idle/lefthand (" + (i + 1) + ").png");
                            }
                        }
                    }
                }
                if (bodyPart.Name == "rightHand")
                {
                    bodyPart.AddAnimation(new Animation(game1, bodyPart, bodyPart, "idle", 1, new Rectangle(0, 0, ParentDependentRect.Width, ParentDependentRect.Height), Color));
                    foreach (Animation animation in bodyPart.Animations)
                    {
                        if (animation.Name == "idle")
                        {
                            for (int i = 0; i < animation.CountFrames; i++)
                            {
                                animation.AddFrame("res/robo/rightHand/idle/rightHand (" + (i + 1) + ").png");
                            }
                        }
                    }
                }
                if (bodyPart.Name == "leftLeg")
                {
                    bodyPart.AddAnimation(new Animation(game1, bodyPart, bodyPart, "idle", 1, new Rectangle(0, 0, ParentDependentRect.Width, ParentDependentRect.Height), Color));
                    bodyPart.AddAnimation(new Animation(game1, bodyPart, bodyPart, "walk", 40, new Rectangle(0, 0, ParentDependentRect.Width, ParentDependentRect.Height), Color));
                    foreach (Animation animation in bodyPart.Animations)
                    {
                        if (animation.Name == "idle")
                        {
                            for (int i = 0; i < animation.CountFrames; i++)
                            {
                                animation.AddFrame("res/robo/leftLeg/idle/leftLeg (" + (i + 1) + ").png");
                            }
                        }
                        if (animation.Name == "walk")
                        {
                            for (int i = 0; i < animation.CountFrames; i++)
                            {
                                animation.AddFrame("res/robo/leftLeg/walk/leftLeg (" + (i + 1) + ").png");
                            }
                        }
                    }
                }
                if (bodyPart.Name == "rightLeg")
                {
                    bodyPart.AddAnimation(new Animation(game1, bodyPart, bodyPart, "idle", 1, new Rectangle(0, 0, ParentDependentRect.Width, ParentDependentRect.Height), Color));
                    bodyPart.AddAnimation(new Animation(game1, bodyPart, bodyPart, "walk", 40, new Rectangle(0, 0, ParentDependentRect.Width, ParentDependentRect.Height), Color));
                    foreach (Animation animation in bodyPart.Animations)
                    {
                        if (animation.Name == "idle")
                        {
                            for (int i = 0; i < animation.CountFrames; i++)
                            {
                                animation.AddFrame("res/robo/rightLeg/idle/rightLeg (" + (i + 1) + ").png");
                            }
                        }
                        if (animation.Name == "walk")
                        {
                            for (int i = 0; i < animation.CountFrames; i++)
                            {
                                animation.AddFrame("res/robo/rightLeg/walk/rightLeg (" + (i + 1) + ").png");
                            }
                        }
                    }
                }
            }
            foreach (AnimatedSprite bodyPart in BodyParts)
            {
                if (bodyPart.Name == "rightLeg")
                {
                    bodyPart.CurrrentAnimation = 0;
                }
                if (bodyPart.Name == "leftLeg")
                {
                    bodyPart.CurrrentAnimation = 0;
                }
            }
        }
    }
}
