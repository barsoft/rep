using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Audio;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.GamerServices;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework.Media;
using System.Windows.Forms;
using System.IO;
using WindowsGame1;

namespace MapEditor
{
    public class Game1 : Microsoft.Xna.Framework.Game
    {
        GraphicsDeviceManager graphics;

        SpriteBatch spriteBatch;

        public List<Sprite> sprites;

        public Texture2D Grass;
        public Texture2D Tree;
        public Texture2D Star;
        public Texture2D Kolobok;

        Form1 form1;

        Form mainForm;

        

        public Game1()
        {
            graphics = new GraphicsDeviceManager(this);
            Content.RootDirectory = "Content";

            sprites = new List<Sprite>();

            mainForm = (Form) Form.FromHandle(Window.Handle);


            form1 = new Form1(this);

            form1.Show();
        }

        protected override void Initialize()
        {
            spriteBatch = new SpriteBatch(GraphicsDevice);

            graphics.PreferredBackBufferHeight = 800;

            graphics.PreferredBackBufferWidth = 1200;

            graphics.ApplyChanges();


            this.IsMouseVisible = true;

            this.Window.AllowUserResizing = true;

            Grass = Texture2D.FromStream(GraphicsDevice, new FileStream(@"data\textures\grass.png", FileMode.Open));
            Tree = Texture2D.FromStream(GraphicsDevice, new FileStream(@"data\textures\tree_1.png", FileMode.Open));
            Star = Texture2D.FromStream(GraphicsDevice, new FileStream(@"data\textures\star.png", FileMode.Open));
            Kolobok = Texture2D.FromStream(GraphicsDevice, new FileStream(@"data\textures\kolobok.png", FileMode.Open));

            for (int i = 0; i < 12; i++)
            {
                for (int j = 0; j < 8; j++)
                {
                    Sprite grass = new Sprite();
                    grass.Texture = Grass;
                    grass.Rectangle = new Rectangle(i * 100, j * 100, 100, 100);
                    sprites.Add(grass);
                }
            }

            base.Initialize();
        }

        MouseState lastMouseState;

        MouseState currentMouseState;

        KeyboardState oldState;

        int curObj=1;

        protected override void Update(GameTime gameTime)
        {
            if (GamePad.GetState(PlayerIndex.One).Buttons.Back == Microsoft.Xna.Framework.Input.ButtonState.Pressed)
                this.Exit();

            foreach (Sprite sprite in sprites)
            {
                sprite.Update();
            }

            lastMouseState = currentMouseState;

            currentMouseState = Mouse.GetState();

            if (lastMouseState.LeftButton == Microsoft.Xna.Framework.Input.ButtonState.Released && currentMouseState.LeftButton == Microsoft.Xna.Framework.Input.ButtonState.Pressed)
            {
                if (currentMouseState.X >= 0 && currentMouseState.X >= 0 && mainForm.Focused==true)
                {
                    Sprite tree = new Sprite();
                    if (curObj==1)
                        tree.Texture = Tree;
                    if (curObj == 2)
                        tree.Texture = Star;
                    if (curObj == 3)
                        tree.Texture = Kolobok;
                    tree.Rectangle = new Rectangle(currentMouseState.X / 100 * 100, currentMouseState.Y / 100 * 100, 100, 100);
                    sprites.Add(tree);
                }
            }

            KeyboardState newState = Keyboard.GetState();

            if (newState.IsKeyDown(Microsoft.Xna.Framework.Input.Keys.D1))
            {
                // If not down last update, key has just been pressed.
                if (!oldState.IsKeyDown(Microsoft.Xna.Framework.Input.Keys.D1))
                {
                    curObj = 1;
                }
            }

            if (newState.IsKeyDown(Microsoft.Xna.Framework.Input.Keys.D2))
            {
                // If not down last update, key has just been pressed.
                if (!oldState.IsKeyDown(Microsoft.Xna.Framework.Input.Keys.D2))
                {
                    curObj = 2;
                }
            }

            if (newState.IsKeyDown(Microsoft.Xna.Framework.Input.Keys.D3))
            {
                // If not down last update, key has just been pressed.
                if (!oldState.IsKeyDown(Microsoft.Xna.Framework.Input.Keys.D3))
                {
                    curObj = 3;
                }
            }

            base.Update(gameTime);
        }

        protected override void Draw(GameTime gameTime)
        {
            GraphicsDevice.Clear(Color.Gray);

            spriteBatch.Begin();

            foreach (Sprite sprite in sprites)
            {
                sprite.Draw(spriteBatch);
            }

            spriteBatch.End();

            base.Draw(gameTime);
        }
    }
}
