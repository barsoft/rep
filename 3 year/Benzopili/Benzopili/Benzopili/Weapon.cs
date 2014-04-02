using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework;
using System.IO;

namespace Benzopili
{
    public class Weapon : Distructable
    {
        public static int NumberOfInstances { get; set; }
        public static List<Weapon> Types { get; set; }
        public int power;
        public float speed;
        public int countBullets;

        protected Weapon(Game1 game1, String name, Rectangle rect, Texture2D texture, Color color, int health, Physics p)
            : base(game1, name, rect, texture, color, health, p)
        {
            this.Health = health;
            this.CurrnentHealth = health;
            ParentTranslation = Physics;
        }

        public static void LoadTypes(Game1 game1)
        {
            Types = new List<Weapon>();
            Rectangle rect = new Rectangle(0, 0, 300, 120);
            Physics p = new Physics(game1, "phys", rect, 5, 0.3f, 0.3f);
            p.PhysicsObjects.Add(new PhysicsObject(game1, "s", new Rectangle(0, 0, rect.Width, rect.Height), Color.Red));
            Types.Add(new Weapon(game1, "benzo", rect, Texture2D.FromStream(game1.GraphicsDevice, new FileStream("res/weapons/benzo.png", FileMode.Open)), Color.White, 10, p));
             p = new Physics(game1, "phys", rect, 4, 0.3f, 0.3f);
            Types.Add(new Weapon(game1, "secira", rect, Texture2D.FromStream(game1.GraphicsDevice, new FileStream("res/weapons/secira.png", FileMode.Open)), Color.White, 10, p));
        }
        private static Weapon FindWeapon(String name)
        {
            foreach (Weapon type in Types)
            {
                if (type.Name == name)
                    return type;
            }
            return null;
        }

        public Weapon(Game1 game1, Vector2 position, Weapon type)
            : base(game1, type.Name + NumberOfInstances, type.Rect, type.Texture, type.Color, type.Health, type.Physics)
        {
            NumberOfInstances++;
            Physics.Position = position;
            ParentTranslation = Physics;
            Random rnd = new Random();
            Physics.PushUp(rnd.Next(10) + 80);
            if (Physics.Position.X < 800)
                Physics.HorizontalSpeed = rnd.Next(5)+5;
            else
                Physics.HorizontalSpeed = rnd.Next(5) - 10+5;
        }

        public override void Update()
        {
            base.Update();
            if (Physics.Position.Y > 1300)
                CurrnentHealth = 0;
            Random rnd = new Random();
            Angle += 1.0f / (5-Physics.HorizontalSpeed);
            this.Origin = new Vector2(Texture.Width / 2, Texture.Height / 2);
        }
    }
}
