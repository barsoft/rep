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
using System.ComponentModel;
using System.IO;
using System.Text;


namespace MapEditor
{
    /// <summary>
    /// This is a game component that implements IUpdateable.
    /// </summary>
    public class Map : Microsoft.Xna.Framework.GameComponent
    {
        public List<BarrelType> barrelTypes;
        public List<GroundType> groundTypes;
        public List<WallType> wallTypes;
        public List<ZombieType> zombieTypes;
        public List<Base> objects;

        public SpawnPointType spawnPointType;
        
        public Camera camera;
        public Gui gui;
        public int Width { get; set; }
        public int Height { get; set; }
        public Game1 game1;
        public SpriteFont Font1;

        public Texture2D bluelight;
        public Texture2D greenlight;

        public int barrelsHistory;
        public int groundsHistory;
        public int wallsHistory;
        public int zombiesHistory;

        int i = 0;
        public Map(Game1 game1)
            : base(game1)
        {
            this.game1 = game1;
            Width = 2000;
            Height = 2000;
        }

        public override void Initialize()
        {
            base.Initialize();
            bluelight = game1.Content.Load<Texture2D>("bluelight");
            greenlight = game1.Content.Load<Texture2D>("greenlight");
            barrelTypes = new List<BarrelType>();
            barrelTypes.Add(new BarrelType(game1, "redBarrel", 50, 50, 20, 20, "redBarrel"));
            barrelTypes.Add(new BarrelType(game1, "blueBarrel", 400, 400, 20, 20, "blueBarrel"));

            groundTypes = new List<GroundType>();
            groundTypes.Add(new GroundType(game1, "asphalt", 100, 100, 20, 20, "asphalt"));

            wallTypes = new List<WallType>();
            wallTypes.Add(new WallType(game1, "beton", 25, 100, 20, 20, "beton"));

            zombieTypes = new List<ZombieType>();
            zombieTypes.Add(new ZombieType(game1, "default", 50, 50, 20, 20, "default"));

            spawnPointType = new SpawnPointType(game1, "flag", 50, 50, 20, 20, "flag");

            objects = new List<Base>();
            objects.Add(new Barrel(game1, "blueBarrel", 300, 100, "barrel" + barrelsHistory));
            objects.Add(new Ground(game1, "asphalt", 0, 100, "ground" + groundsHistory));
            objects.Add(new Wall(game1, "beton", 0, 100, "wall" + groundsHistory));
            objects.Add(new SpawnPoint(game1, "flag", 0, 100, "spawnPoint"));
            objects.Add(new Zombie(game1, "default", 0, 300, "zombie"));
            camera = new Camera(game1,200);
            gui = new Gui(game1);
            gui.Initialize();
            Font1 = game1.Content.Load<SpriteFont>("Arial");
        }

        public override void Update(GameTime gameTime)
        {
            base.Update(gameTime);
            foreach (Base b in objects)
            {
                b.Update(gameTime);
            }
            camera.Update(gameTime);
            gui.Update(gameTime);
        }

        public void Draw()
        {
            foreach (Base b in objects)
            {
                if (b.GetType()==typeof(Ground))
                b.Draw();
            }
            foreach (Base b in objects)
            {
                if (b.GetType() == typeof(Wall))
                b.Draw();
            }
            foreach (Base b in objects)
            {
                if (b.GetType() == typeof(Barrel))
                b.Draw();
            }
            foreach (Base b in objects)
            {
                if (b.GetType() == typeof(Zombie))
                    b.Draw();
            }
            foreach (Base b in objects)
            {
                if (b.GetType() == typeof(SpawnPoint))
                    b.Draw();
            }
            gui.Draw();
        }

        public String LoadMap(Stream myStream)
        {
            StreamReader buffreader = new StreamReader(myStream);
            string line;
            string line2 = "";
            StringBuilder text = new StringBuilder();

            while (!buffreader.ReadLine().Equals("{"))
            {
            }

            while (!buffreader.ReadLine().Equals("SpawnPoint"))
            {
            }

            while (!buffreader.ReadLine().Equals("{"))
            {
            }

            while (!(line = buffreader.ReadLine()).Equals("}"))
            {
                text.Clear();
                for (int i = 0; i < line.Length; i++)
                {
                    if (!(line[i].Equals(' ')))
                        text.Append(line[i]);
                }
                line = text.ToString();

                int counter = 0;
                int x;
                int y;
                String type;

                line2 += line;

                text.Clear();
                while (true)
                {
                    if (line[counter].Equals(','))
                        break;
                    text.Append(line[counter]);
                    counter++;
                }
                x = Convert.ToInt32(text.ToString());
                counter++;

                text.Clear();
                while (true)
                {
                    if (line[counter].Equals(';'))
                        break;
                    text.Append(line[counter]);
                    counter++;
                }
                y = Convert.ToInt32(text.ToString());
                counter++;

                objects.Add(new SpawnPoint(game1, "spawnPoint", x, y, "spawnpoint"));

            }

            while (!buffreader.ReadLine().Equals("Barrels"))
            {
            }

            while (!buffreader.ReadLine().Equals("{"))
            {
            }

            while (!(line = buffreader.ReadLine()).Equals("}"))
            {
                text.Clear();
                for (int i = 0; i < line.Length; i++)
                {
                    if (!(line[i].Equals(' ')))
                        text.Append(line[i]);
                }
                line = text.ToString();

                int counter = 0;
                int x;
                int y;
                String type;

                line2 += line;

                text.Clear();
                while (true)
                {
                    if (line[counter].Equals(','))
                        break;
                    text.Append(line[counter]);
                    counter++;
                }
                x = Convert.ToInt32(text.ToString());
                counter++;

                text.Clear();
                while (true)
                {
                    if (line[counter].Equals(','))
                        break;
                    text.Append(line[counter]);
                    counter++;
                }
                y = Convert.ToInt32(text.ToString());
                counter++;

                text.Clear();
                while (!line[counter].Equals(';'))
                {
                    text.Append(line[counter]);
                    counter++;
                }
                type = text.ToString();

                objects.Add(new Barrel(game1, type, x, y, "barrel" + game1.map.barrelsHistory));

            }

            while (!buffreader.ReadLine().Equals("Grounds"))
            {
            }

            while (!buffreader.ReadLine().Equals("{"))
            {
            }

            while (!(line = buffreader.ReadLine()).Equals("}"))
            {
                text.Clear();
                for (int i = 0; i < line.Length; i++)
                {
                    if (!(line[i].Equals(' ')))
                        text.Append(line[i]);
                }
                line = text.ToString();

                int counter = 0;
                int x;
                int y;
                String type;

                line2 += line;

                text.Clear();
                while (true)
                {
                    if (line[counter].Equals(','))
                        break;
                    text.Append(line[counter]);
                    counter++;
                }
                x = Convert.ToInt32(text.ToString());
                counter++;

                text.Clear();
                while (true)
                {
                    if (line[counter].Equals(','))
                        break;
                    text.Append(line[counter]);
                    counter++;
                }
                y = Convert.ToInt32(text.ToString());
                counter++;

                text.Clear();
                while (!line[counter].Equals(';'))
                {
                    text.Append(line[counter]);
                    counter++;
                }
                type = text.ToString();

                objects.Add(new Ground(game1, type, x, y, "ground" + game1.map.barrelsHistory));

            }
            while (!buffreader.ReadLine().Equals("Walls"))
            {
            }

            while (!buffreader.ReadLine().Equals("{"))
            {
            }

            while (!(line = buffreader.ReadLine()).Equals("}"))
            {
                text.Clear();
                for (int i = 0; i < line.Length; i++)
                {
                    if (!(line[i].Equals(' ')))
                        text.Append(line[i]);
                }
                line = text.ToString();

                int counter = 0;
                int x;
                int y;
                String type;

                line2 += line;

                text.Clear();
                while (true)
                {
                    if (line[counter].Equals(','))
                        break;
                    text.Append(line[counter]);
                    counter++;
                }
                x = Convert.ToInt32(text.ToString());
                counter++;

                text.Clear();
                while (true)
                {
                    if (line[counter].Equals(','))
                        break;
                    text.Append(line[counter]);
                    counter++;
                }
                y = Convert.ToInt32(text.ToString());
                counter++;

                text.Clear();
                while (!line[counter].Equals(';'))
                {
                    text.Append(line[counter]);
                    counter++;
                }
                type = text.ToString();

                objects.Add(new Wall(game1, type, x, y, "wall" + game1.map.wallsHistory));

            }

            while (!buffreader.ReadLine().Equals("Zombies"))
            {
            }

            while (!buffreader.ReadLine().Equals("{"))
            {
            }

            while (!(line = buffreader.ReadLine()).Equals("}"))
            {
                text.Clear();
                for (int i = 0; i < line.Length; i++)
                {
                    if (!(line[i].Equals(' ')))
                        text.Append(line[i]);
                }
                line = text.ToString();

                int counter = 0;
                int x;
                int y;
                String type;

                line2 += line;

                text.Clear();
                while (true)
                {
                    if (line[counter].Equals(','))
                        break;
                    text.Append(line[counter]);
                    counter++;
                }
                x = Convert.ToInt32(text.ToString());
                counter++;

                text.Clear();
                while (true)
                {
                    if (line[counter].Equals(','))
                        break;
                    text.Append(line[counter]);
                    counter++;
                }
                y = Convert.ToInt32(text.ToString());
                counter++;

                text.Clear();
                while (!line[counter].Equals(';'))
                {
                    text.Append(line[counter]);
                    counter++;
                }
                type = text.ToString();

                objects.Add(new Zombie(game1, type, x, y, "zombie" + game1.map.zombiesHistory));

            }
            return text.ToString();
        }

        public void SaveMap(Stream myStream)
        {
            StreamWriter buffwriter = new StreamWriter(myStream);
            buffwriter.WriteLine("Angry Bear Map File. Max Prysyazhnyy 2012©");
            buffwriter.WriteLine();
            buffwriter.WriteLine("Map");
            buffwriter.WriteLine("{");
            buffwriter.WriteLine("SpawnPoint");
            buffwriter.WriteLine("{");
            foreach (Base b in objects)
            {
                if (b.GetType() == typeof(SpawnPoint))
                    buffwriter.WriteLine("    " + b.Position.X + "," + b.Position.Y  + ";");
            }
            buffwriter.WriteLine("}");
            buffwriter.WriteLine("Barrels");
            buffwriter.WriteLine("{");
            foreach (Base b in objects)
            {
                if (b.GetType() == typeof(Barrel))
                    buffwriter.WriteLine("    " + b.Position.X + "," + b.Position.Y + "," + ((Barrel)b).Type + ";");
            }
            buffwriter.WriteLine("}");
            buffwriter.WriteLine("Grounds");
            buffwriter.WriteLine("{");
            foreach (Base g in objects)
            {
                if (g.GetType() == typeof(Ground))
                    buffwriter.WriteLine("    " + g.Position.X + "," + g.Position.Y + "," + ((Ground)g).Type + ";");
            }
            
            buffwriter.WriteLine("}");
            buffwriter.WriteLine("Walls");
            buffwriter.WriteLine("{");
            foreach (Base g in objects)
            {
                if (g.GetType() == typeof(Wall))
                    buffwriter.WriteLine("    " + g.Position.X + "," + g.Position.Y + "," + ((Wall)g).Type + ";");
            }

            buffwriter.WriteLine("}");
            buffwriter.WriteLine("Zombies");
            buffwriter.WriteLine("{");
            foreach (Base g in objects)
            {
                if (g.GetType() == typeof(Zombie))
                    buffwriter.WriteLine("    " + g.Position.X + "," + g.Position.Y + "," + ((Zombie)g).Type + ";");
            }

            buffwriter.WriteLine("}");
            buffwriter.WriteLine("}");
            buffwriter.Close();
        }
    }
}
