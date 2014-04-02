using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using System.ComponentModel;

namespace Robo
{
    public class World
    {
        Game1 game1;
        [Category("World")]
        public Camera Camera { get; set; }
        public Robo Robo;
        [Category("World")]
        public int Width { get; set; }
        [Category("World")]
        public int Height { get; set; }
        [Category("World")]
        public float Gravity { get; set; }
        public List<Object> Objects;
        public World(Game1 game1, int width, int height)
        {
            this.game1 = game1;
            Gravity = 9.8f;
            this.Width = width;
            this.Height = height;
        }
        public void Init()
        {
            StartScene();
        }
        public void StartScene()
        {
            Objects = new List<Object>();
            this.Camera = new Camera(game1.GraphicsDevice.Viewport, Width, Height, 1);
            this.Robo = new Robo(game1, null,null, new Rectangle(200, 200, 100, 100), Color.White);
            Objects.Add(this);
            Objects.Add(Camera);
            game1.TestPanel.comboBox1.DataSource = Objects;
            game1.TestPanel.comboBox1.SelectionChangeCommitted += new EventHandler(comboBox1_SelectionChangeCommitted);
        }

        void comboBox1_SelectionChangeCommitted(object sender, EventArgs e)
        {
            game1.TestPanel.propertyGrid1.SelectedObject = game1.TestPanel.comboBox1.SelectedItem;
        }
        public void Update()
        {
            Robo.Update();
        }
        public void Draw()
        {
            Robo.Draw();
        }
    }
}
