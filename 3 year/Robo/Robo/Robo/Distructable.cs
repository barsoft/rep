using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework;
using System.ComponentModel;

namespace Robo
{
    public class Distructable : Base
    {
        public Distructable(Game1 game1, String name, Texture2D texture, Color color, int health, List<PhysicsObject> physicsObjects)
            : base(game1, name, texture, color, physicsObjects)
        {
            this.Health = health;
            this.CurrnentHealth = health;
        }
        [Category("Distructable")]
        public int Health { get; set; }
        [Category("Distructable")]
        public int CurrnentHealth { get; set; }
    }
}
