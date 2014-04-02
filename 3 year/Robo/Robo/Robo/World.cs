using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using System.ComponentModel;
using Microsoft.Xna.Framework.Input;

namespace Robo
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
            Material.LoadTypes(game1);
            Weapon.LoadTypes(game1);
            StartScene();
        }
        public void StartScene()
        {
            Objects = new List<MapObject>();
            Objects.Add(new Robo(game1, null, null, new Rectangle(0, 200, 100, 100), Color.White));
            Objects.Add(new Camera("camera", game1.GraphicsDevice.Viewport, game1.graphics.PreferredBackBufferWidth, game1.graphics.PreferredBackBufferHeight, 1));
            Random rnd = new Random();
            for (int i = 0; i < 20; i++)
                for (int j = 0; j < 20; j++)
                {
                    int probab = rnd.Next(400);
                    Material buff = null;
                    if (probab < 200)
                        buff = Material.Ground;
                    if (probab > 200 && probab < 300)
                        buff = Material.Stone;
                    if (probab > 300 && probab < 350)
                        buff = Material.Iron;
                    if (probab > 350 && probab < 370 && j > 10)
                        buff = Material.Gold;
                    if (probab > 370 && probab < 400 && j > 10)
                        buff = Material.Lead;
                    if (buff != null)
                    {
                        List<PhysicsObject> physicsObjects = new List<PhysicsObject>();
                        physicsObjects.Add(new PhysicsObject(game1, "materialPhysics", new Rectangle(i * (int)(buff.Size.X),
                            game1.graphics.PreferredBackBufferHeight - (int)(buff.Size.Y) + j * (int)(buff.Size.Y), (int)buff.Size.X, (int)buff.Size.Y), buff.Color, buff.GetMainPhysicsObject().Mass, buff.GetMainPhysicsObject().CoefUprug, buff.GetMainPhysicsObject().CoefTren));
                        Objects.Add(new Material(game1, buff, physicsObjects));
                    }
                }
            Objects.Sort((MapObject a, MapObject b) => (String.Compare(a.Name, b.Name)));
            game1.TestPanel.comboBox1.DataSource = ObjectNames;
            game1.TestPanel.comboBox1.SelectionChangeCommitted += new EventHandler(comboBox1_SelectionChangeCommitted);
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
