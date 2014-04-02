using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using System.ComponentModel;
using Microsoft.Xna.Framework.Input;
using System.Windows.Forms;
using Microsoft.Xna.Framework.Graphics;
using System.IO;

namespace Benzopili
{
    public class World
    {
        Game1 game1;
        public int Width { get; set; }
        [Category("World")]
        public int Height { get; set; }
        [Category("World")]
        public float Gravity { get; set; }
        public Rectangle VisionZone { get; set; }
        public int VisionZoneMargin { get; set; }
        public Timer timer;
        public Timer timer2;
        public List<MapObject> Objects;
        private List<String> ObjectNames
        {
            get
            {
                List<String> names = new List<string>();
                foreach (MapObject obj in Objects)
                {
                    names.Add(obj.Name);
                }
                return names;
            }
        }

        public World(Game1 game1, int width, int height)
        {
            this.game1 = game1;
            Gravity = 9.8f;
            this.Width = width;
            this.Height = height;
            VisionZoneMargin = 150;
        }
        public void Init()
        {
            Weapon.LoadTypes(game1);
            Cake.LoadTypes(game1);
            StartScene();
        }

       
        public void StartScene()
        {
            Objects = new List<MapObject>();
            Objects.Add(new Camera("camera", game1.GraphicsDevice.Viewport, game1.Graphics.PreferredBackBufferWidth, game1.Graphics.PreferredBackBufferHeight, 1));
            Objects.Add(new Base(game1,"ground",new Rectangle(0,580,1920,500),Texture2D.FromStream(game1.GraphicsDevice,new FileStream("res/ground.png",FileMode.Open)),Color.White));
            Objects.Sort((MapObject a, MapObject b) => (String.Compare(a.Name, b.Name)));
            game1.TestPanel.comboBox1.DataSource = ObjectNames;
            game1.TestPanel.comboBox1.SelectionChangeCommitted += new EventHandler(comboBox1_SelectionChangeCommitted);
            timer = new Timer();
            timer.Start();
            timer.Interval = 1000;
            timer.Tick += new EventHandler(timer_Tick);
            timer2 = new Timer();
            timer2.Start();
            timer2.Interval = 2000;
            timer2.Tick += new EventHandler(timer2_Tick);
        }

        void timer2_Tick(object sender, EventArgs e)
        {
            Random rnd = new Random();
            Objects.Add(new Cake(game1, new Vector2((1600 - rnd.Next(1600)) + 200, 1000), Cake.Types[rnd.Next(Cake.Types.Count)]));
        }
        void timer_Tick(object sender, EventArgs e)
        {
            Random rnd = new Random();
            Objects.Add(new Weapon(game1, new Vector2(rnd.Next(1600)+200, 1000), Weapon.Types[rnd.Next(Weapon.Types.Count)]));
        }

        void comboBox1_SelectionChangeCommitted(object sender, EventArgs e)
        {
            game1.TestPanel.propertyGrid1.SelectedObject = GetObject((String)game1.TestPanel.comboBox1.SelectedItem);
        }
        public void Update()
        {
            foreach (MapObject obj in Objects)
            {
                if (IsInVisionZone(obj))
                    obj.Update();
                
            }
            for (int i = 0; i < Objects.Count; i++)
            {
                if (Objects[i] is Weapon)
                {
                    if ((Objects[i] as Weapon).IsOnDestroy())
                        Objects.RemoveAt(i);
                }
            }
            if (game1.MainForm.Focused)
                game1.TestPanel.propertyGrid1.Refresh();

            
        }
        public void Draw()
        {
            foreach (MapObject obj in Objects)
            {
                if (IsInVisionZone(obj))
                    obj.Draw();
            }
        }
        public MapObject GetObject(string name)
        {
            foreach (MapObject obj in Objects)
            {
                if (obj.Name == name)
                    return obj;
            }
            return null;
        }
        public bool IsInVisionZone(MapObject obj)
        {
            Rectangle cameraZone = GetObject("camera").DisplayRect;
            VisionZone = new Rectangle(cameraZone.X - VisionZoneMargin, cameraZone.Y - VisionZoneMargin, cameraZone.Width + VisionZoneMargin * 2, cameraZone.Height + VisionZoneMargin * 2);
            if (VisionZone.Contains(obj.DisplayRect))
                return true;
            return false;
        }
    }
}
