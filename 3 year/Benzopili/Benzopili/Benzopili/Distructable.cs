using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using System.ComponentModel;

namespace Benzopili
{
    public class Distructable : Base
    {
        public Distructable(Game1 game1, String name, Rectangle rect, Texture2D texture, Color color, int health)
            : base(game1, name, rect, texture, color)
        {
            this.Health = health;
            this.CurrnentHealth = health;
        }
        public Distructable(Game1 game1, String name, Rectangle rect, Texture2D texture, Color color, int health, Physics physics)
            : base(game1, name, rect, texture, color)
        {
            this.Health = health;
            this.CurrnentHealth = health;
            this.Physics = physics.Clone();
        }
        public override void Draw()
        {
            base.Draw();
            if (Physics != null && ShowPhysics)
                Physics.Draw();
        }
        public override void Update()
        {
            if (Physics != null)
                Physics.Update();
            base.Update();
        }
        public bool IsOnDestroy()
        {
            if (CurrnentHealth <= 0)
                return true;
            return false;
        }
        [Category("Distructable")]
        public int Health { get; set; }
        [Category("Distructable")]
        public int CurrnentHealth { get; set; }
        [Category("Distructable")]
        public Physics Physics { get; set; }
        [Category("Distructable")]
        public bool ShowPhysics { get; set; }
    }
}
