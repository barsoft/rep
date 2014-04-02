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
using System.Text;
namespace MapEditor
{
    /// <summary>
    /// This is the main type for your game
    /// </summary>
    /// 
    public class Game1 : Microsoft.Xna.Framework.Game
    {
        GraphicsDeviceManager graphics;
        public SpriteBatch spriteBatch;
        public ToolBox toolBox;
        public Form mainWindow;
        

        public Map map;
        
        public Game1(/*IntPtr drawSurface,Form1 form1*/)
        {

            graphics = new GraphicsDeviceManager(this);

            Content.RootDirectory = "Content";
            this.IsMouseVisible = true;
            this.Window.AllowUserResizing = true;

            mainWindow = (Form)Control.FromHandle(Window.Handle);

            toolBox = new ToolBox(this);

            

            toolBox.Show();
            mainWindow.Resize += new EventHandler(mainWindow_Resize);
            mainWindow.LocationChanged += new EventHandler(mainWindow_Resize);
            //mainWindow.LostFocus += new EventHandler(mainWindow_LostFocus);
        }

            
       

        void mainWindow_Resize(object sender, EventArgs e)
        {

            toolBox.Left = mainWindow.Left+mainWindow.Width - 300;
            toolBox.Top = mainWindow.Top+60;
            toolBox.Width = 280;
            toolBox.Height = mainWindow.Height - 80;

            toolBox.Owner = mainWindow;
            //toolBox.TopMost = true;

            toolBox.tabControl1.Height = toolBox.Height - toolBox.propertyGrid1.Height-130;
            toolBox.tabControl1.Refresh();

            for (int i = 0; i < 4; i++)
            {
                toolBox.entityTable[i].Height = toolBox.tabControl1.Height;
                toolBox.entityTable[i].Refresh();
            }
            
        }

        protected override void Initialize()
        {

            base.Initialize();

            map = new Map(this);
            map.Initialize();
        }


        protected override void LoadContent()
        {

            spriteBatch = new SpriteBatch(GraphicsDevice);

        }

        protected override void UnloadContent()
        {

        }

        protected override void Update(GameTime gameTime)
        {

            if (GamePad.GetState(PlayerIndex.One).Buttons.Back == Microsoft.Xna.Framework.Input.ButtonState.Pressed)
                this.Exit();


            base.Update(gameTime);

            map.Update(gameTime);
        }


        protected override void Draw(GameTime gameTime)
        {
            GraphicsDevice.Clear(Color.Gray);



            base.Draw(gameTime);

            spriteBatch.Begin();
            map.Draw();
            spriteBatch.End();
        }

        
    }
}
