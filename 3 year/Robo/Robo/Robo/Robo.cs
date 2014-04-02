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
    public class Robo : Distructable
    {
        [Category("Robo")]
        public List<AnimatedSprite> BodyParts { get; set; }
        [Category("Robo")]
        public float Speed { get; set; }
        [Category("Robo")]
        public float JumpForce { get; set; }
        private KeyboardState oldKeyState;
        [Category("Robo")]
        public List<Tool> QuickTools { get; set; }
        [Category("Robo")]
        public List<Tool> Tools { get; set; }
        [Category("Robo")]
        public int CurrentTool { get; set; }
        public Robo(Game1 game1, Base parent, Base parentRotater, Rectangle rect, Color color)
            : base(game1, "robo", Texture2D.FromStream(game1.GraphicsDevice, new FileStream("res\\robo\\head.png", FileMode.Open)), color, 100, null)
        {
            BodyParts = new List<AnimatedSprite>();
            BodyParts.Add(new AnimatedSprite(game1, this, this, "leftHand", new Rectangle(0, 0, rect.Width, rect.Height), color));
            BodyParts.Add(new AnimatedSprite(game1, this, this, "rightHand", new Rectangle(0, 0, rect.Width, rect.Height), color));
            BodyParts.Add(new AnimatedSprite(game1, this, null, "leftLeg", new Rectangle(0, 0, rect.Width, rect.Height), color));
            BodyParts.Add(new AnimatedSprite(game1, this, null, "rightLeg", new Rectangle(0, 0, rect.Width, rect.Height), color));
            InitBodyParts();
            List<PhysicsObject> physicsObjects = new List<PhysicsObject>();
            physicsObjects.Add(new PhysicsObject(game1, "roboPhysics", rect, Color.Red, 15.6f, 0.4f, 0.3f));
            PhysicsObjects = physicsObjects;
            this.Speed = 10;
            this.GetMainPhysicsObject().Origin = new Vector2(Texture.Width / 2.3f, Texture.Height / 2.6f);
            this.JumpForce = 60;
            Tools = new List<Tool>();
            foreach (Weapon w in Weapon.Types)
            {

                List<PhysicsObject> physicsObjects2 = new List<PhysicsObject>();
                physicsObjects2.Add(new PhysicsObject(game1, "materialPhysics", new Rectangle(0, 0,
                     (int)w.Size.X, (int)w.Size.Y), w.Color, w.GetMainPhysicsObject().Mass, w.GetMainPhysicsObject().CoefUprug, w.GetMainPhysicsObject().CoefTren));
                Tool buff = new Weapon(game1, w, physicsObjects2);
                buff.Origin = new Vector2(0, 0);
                buff.Rect = w.GetMainPhysicsObject().Rect;
                buff.Parent = this;
                buff.ParentRotater = this;
                Tools.Add(buff);
                
            }

        }
        public override void Draw()
        {
            foreach (AnimatedSprite bodyPart in BodyParts)
            {
                if (bodyPart.Name == "leftHand" || bodyPart.Name == "leftLeg")
                {
                    bodyPart.Draw();

                }
            }
            base.Draw();
            Tools[CurrentTool].Draw();
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
                GetMainPhysicsObject().HorizontalSpeed = Speed;
                GetMainPhysicsObject().SpriteEffects = SpriteEffects.None;
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
                    GetMainPhysicsObject().HorizontalSpeed = -Speed;
                    GetMainPhysicsObject().SpriteEffects = SpriteEffects.FlipHorizontally;
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
                            GetMainPhysicsObject().HorizontalSpeed = 0;
                        }
                    }
            if (keyState.IsKeyDown(Keys.W))
            {
                if (GetMainPhysicsObject().IsOnGround)
                    GetMainPhysicsObject().PushUp(JumpForce);
            }
            oldKeyState = keyState;

            MouseState mouseState = Mouse.GetState();

            if (mouseState.X + game1.World.GetObject("camera").Rect.X - DisplayRect.X - DisplayRect.Width / 2 >= 0)
                GetMainPhysicsObject().SpriteEffects = SpriteEffects.None;
            else
                GetMainPhysicsObject().SpriteEffects = SpriteEffects.FlipHorizontally;
            GetMainPhysicsObject().Angle = (float)(Math.Atan((mouseState.Y + game1.World.GetObject("camera").Rect.Y - DisplayRect.Y - DisplayRect.Height / 2) / (float)(mouseState.X + game1.World.GetObject("camera").Rect.X - DisplayRect.X - DisplayRect.Width / 2)));
            if (GetMainPhysicsObject().Angle < -1.3f) GetMainPhysicsObject().Angle = -1.3f;
            if (GetMainPhysicsObject().Angle > 1.3f) GetMainPhysicsObject().Angle = 1.3f;
            base.Update();
            foreach (AnimatedSprite bodyPart in BodyParts)
            {
                bodyPart.Update();
            }

            game1.World.GetObject("camera").Position = new Vector2(GetMainPhysicsObject().Rect.X - game1.graphics.PreferredBackBufferWidth / 2, GetMainPhysicsObject().Rect.Y - game1.graphics.PreferredBackBufferHeight / 2);

            Tools[CurrentTool].Update();
        }
        public void InitBodyParts()
        {
            foreach (AnimatedSprite bodyPart in BodyParts)
            {
                if (bodyPart.Name == "leftHand")
                {
                    bodyPart.AddAnimation(new Animation(game1, bodyPart, bodyPart, "idle", 1, new Rectangle(0, 0, bodyPart.Rect.Width, bodyPart.Rect.Height), Color));
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
                    bodyPart.AddAnimation(new Animation(game1, bodyPart, bodyPart, "idle", 1, new Rectangle(0, 0, bodyPart.Rect.Width, bodyPart.Rect.Height), Color));
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
                    bodyPart.AddAnimation(new Animation(game1, bodyPart, bodyPart, "idle", 1, new Rectangle(0, 0, bodyPart.Rect.Width, bodyPart.Rect.Height), Color));
                    bodyPart.AddAnimation(new Animation(game1, bodyPart, bodyPart, "walk", 40, new Rectangle(0, 0, bodyPart.Rect.Width, bodyPart.Rect.Height), Color));
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
                    bodyPart.AddAnimation(new Animation(game1, bodyPart, bodyPart, "idle", 1, new Rectangle(0, 0, bodyPart.Rect.Width, bodyPart.Rect.Height), Color));
                    bodyPart.AddAnimation(new Animation(game1, bodyPart, bodyPart, "walk", 40, new Rectangle(0, 0, bodyPart.Rect.Width, bodyPart.Rect.Height), Color));
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
