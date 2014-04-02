using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework;

namespace Robo
{
    public class Tool:Distructable
    {
        public Base Icon { get; set; }

        public static int NumberOfInstances { get; set; }
        public static List<Weapon> Types { get; set; }

        protected Tool(Game1 game1, String name, Texture2D texture, Color color, int health, List<PhysicsObject> physicsObjects)
            : base(game1, name, texture, color, health, physicsObjects)
        {
            this.Health = health;
            this.CurrnentHealth = health;
        }

    }
}
