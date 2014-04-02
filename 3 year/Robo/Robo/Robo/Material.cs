using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using System.IO;

namespace Robo
{
    public class Material : Distructable
    {
        public static int NumberOfInstances { get; set; }
        private Material(Game1 game1, String name, Texture2D texture, Color color, int health, List<PhysicsObject> physicsObjects)
            : base(game1, name, texture, color, health, physicsObjects)
        {
            this.Health = health;
            this.CurrnentHealth = health;
        }

        static List<Material> Types { get; set; }
        public static void LoadTypes(Game1 game1)
        {
            Types = new List<Material>();
            List<PhysicsObject> physicsObjects = new List<PhysicsObject>();
            physicsObjects.Add(new PhysicsObject(game1,"materialPhysics",new Rectangle(0,0,120,120),Color.Blue,0,0,0));
            Types.Add(new Material(game1, "ground", Texture2D.FromStream(game1.GraphicsDevice, new FileStream("res/materials/ground.png", FileMode.Open)), Color.White, 10, physicsObjects));
            Types.Add(new Material(game1, "gold", Texture2D.FromStream(game1.GraphicsDevice, new FileStream("res/materials/gold.png", FileMode.Open)), Color.White, 10, physicsObjects));
            Types.Add(new Material(game1, "iron", Texture2D.FromStream(game1.GraphicsDevice, new FileStream("res/materials/iron.png", FileMode.Open)), Color.White, 10, physicsObjects));
            Types.Add(new Material(game1, "stone", Texture2D.FromStream(game1.GraphicsDevice, new FileStream("res/materials/stone.png", FileMode.Open)), Color.White, 10, physicsObjects));
            Types.Add(new Material(game1, "lead", Texture2D.FromStream(game1.GraphicsDevice, new FileStream("res/materials/lead.png", FileMode.Open)), Color.White, 10, physicsObjects));
        }
        private static Material FindMaterial(String name)
        {
            foreach (Material type in Types)
            {
                if (type.Name == name)
                    return type;
            }
            return null;
        }
        public static Material Ground { get { return FindMaterial("ground"); } }
        public static Material Gold { get { return FindMaterial("gold"); } }
        public static Material Iron { get { return FindMaterial("iron"); } }
        public static Material Stone { get { return FindMaterial("stone"); } }
        public static Material Lead { get { return FindMaterial("lead"); } }


        public Material(Game1 game1, Material type,List<PhysicsObject> physicsObjects)
            : base(game1, type.Name + NumberOfInstances, type.Texture, type.Color, type.Health, physicsObjects)
        {
            NumberOfInstances++;
            //this.PhysicsObjects[0].Position = new Vector2(x, y);
        }
    }
}
