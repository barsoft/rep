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
using System.ComponentModel;
using System.Drawing.Design;


namespace MapEditor
{
    public class ZombieType
    {
        static public class Types
        {
            static public List<string> Name = new List<string>();
        }

        internal class List2PropertyConverter : StringConverter
        {
            public override bool
                GetStandardValuesSupported(
                ITypeDescriptorContext context)
            {
                return true;
            }

            public override bool
                GetStandardValuesExclusive(
                ITypeDescriptorContext context)
            {
                return true;
            }

            public override StandardValuesCollection
                GetStandardValues(
                ITypeDescriptorContext context)
            {
                return new StandardValuesCollection(
                    ZombieType.Types.Name);
            }
        }

        public ZombieType(Game1 game1, String _textureName, int _width, int _height, int _health, int _power, String _name)
        {
            ZombieType.Types.Name.Add(_name);
            Texture = game1.Content.Load<Texture2D>(_textureName);
            Width = _width;
            Height = _height;
            Height = _height;
            Health = _health;
            Power = _power;
            Name = _name;
        }
        public Texture2D Texture { get; set; }
        public int Width { get; set; }
        public int Height { get; set; }
        public int Health { get; set; }
        public int Power { get; set; }
        public String Name { get; set; }
    }

    public class Zombie : Base
    {
        [TypeConverter(typeof(ZombieType.List2PropertyConverter))]
        [Category("Zombie")]
        public string Type
        {
            get
            {
                return typeName;
            }
            set
            {
                typeName = value;
                foreach (ZombieType bt in game1.map.zombieTypes)
                {
                    if (bt.Name==typeName)
                    {
                        rect = new Rectangle(rect.X, rect.Y, bt.Width, bt.Height);
                        Texture = bt.Texture;
                        health = bt.Health;
                        power = bt.Power;
                    }
                }
                
            }
        }
        private string typeName;
        [Category("Zombie")]
        public int Health { get { return health; } }
        private int health;
        [Category("Zombie")]
        public int Power { get { return power; } }
        private int power;
        
        //public Rectangle Rect { get { return rect; } set { rect = value; } }
        public Zombie(Game1 game1,string typeName, int x, int y, string name)
            : base(game1)
        {
            rect = new Rectangle(x, y, 0, 0);
            Type = typeName;
            
            Name = name;
            game1.map.zombiesHistory++;
        }

        public void Initialize()
        {
        }

        public override void Update(GameTime gameTime)
        {
            // GRID ALLIGN
            game1.map.gui.grid.Align(ref rect);

            base.Update(gameTime);
        }

        public override void Draw()
        {
            game1.spriteBatch.Draw(Texture, disprect, Color.White);
            base.Draw();
        }
    }
}
