using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework;

namespace Robo
{
    public class Weapon:Tool
    {
        public int power;
        public float speed;
        public int countBullets;
       // public List<Bullet> arrBullet;

        protected Weapon(Game1 game1, String name, Texture2D texture, Color color, int health, List<PhysicsObject> physicsObjects)
            : base(game1, name, texture, color, health, physicsObjects)
        {
        }
        
        public static void LoadTypes(Game1 game1)
        {
            Types = new List<Weapon>();
            List<PhysicsObject> physicsObjects = new List<PhysicsObject>();
            physicsObjects.Add(new PhysicsObject(game1,"materialPhysics",new Rectangle(0,0,80,40),Color.Blue,20,0,0));
            Types.Add(new Weapon(game1, "ak47", Texture2D.FromStream(game1.GraphicsDevice, new FileStream("res/tools/weapons/ak47.png", FileMode.Open)), Color.White, 10, physicsObjects));
            Types.Add(new Weapon(game1, "laserGun", Texture2D.FromStream(game1.GraphicsDevice, new FileStream("res/tools/weapons/laserGun.png", FileMode.Open)), Color.White, 10, physicsObjects));
            Types.Add(new Weapon(game1, "m4a1", Texture2D.FromStream(game1.GraphicsDevice, new FileStream("res/tools/weapons/m4a1.png", FileMode.Open)), Color.White, 10, physicsObjects));
        }
        private static Weapon FindMaterial(String name)
        {
            foreach (Weapon type in Types)
            {
                if (type.Name == name)
                    return type;
            }
            return null;
        }
        public static Weapon Ak47 { get { return FindMaterial("ak47"); } }
        public static Weapon M4a1 { get { return FindMaterial("laserGun"); } }
        public static Weapon LaserGun { get { return FindMaterial("m4a1"); } }


        public Weapon(Game1 game1, Weapon type, List<PhysicsObject> physicsObjects)
            : base(game1, type.Name + NumberOfInstances, type.Texture, type.Color, type.Health, physicsObjects)
        {
            NumberOfInstances++;
            //this.PhysicsObjects[0].Position = new Vector2(x, y);
        }
    }
}
