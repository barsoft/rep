using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace MapEditor
{
    public partial class PositionForm : Form
    {
        Game1 game1;
        public PositionForm(Game1 game1)
        {
            this.game1 = game1;
            InitializeComponent();
            this.button1.Click += new EventHandler(button1_Click);
            this.button2.Click += new EventHandler(button2_Click);
            this.textBox1.TextChanged += new EventHandler(textBox1_TextChanged);
        }

        void button2_Click(object sender, EventArgs e)
        {
            this.Close();
        }
        void button1_Click(object sender, EventArgs e)
        {
            /*if (game1.toolBox.tabControl1.SelectedIndex == 0)
                game1.map.barrels.Add(new Barrel(game1, game1.toolBox.entityTable[0].CurrentRow.Cells[1].Value.ToString(), Convert.ToInt32(this.textBox1.Text), Convert.ToInt32(this.textBox2.Text), "barrel" + game1.map.barrelsHistory));
            if (game1.toolBox.tabControl1.SelectedIndex == 1)
                game1.map.grounds.Add(new Ground(game1, game1.toolBox.entityTable[1].CurrentRow.Cells[1].Value.ToString(), Convert.ToInt32(this.textBox1.Text), Convert.ToInt32(this.textBox2.Text), "ground" + game1.map.groundsHistory));*/
            if (game1.toolBox.tabControl1.SelectedIndex == 0)
                game1.map.objects.Add(new Barrel(game1, game1.toolBox.entityTable[0].CurrentRow.Cells[1].Value.ToString(), Convert.ToInt32(this.textBox1.Text), Convert.ToInt32(this.textBox2.Text), "barrel" + game1.map.barrelsHistory));
            if (game1.toolBox.tabControl1.SelectedIndex == 1)
                game1.map.objects.Add(new Ground(game1, game1.toolBox.entityTable[1].CurrentRow.Cells[1].Value.ToString(), Convert.ToInt32(this.textBox1.Text), Convert.ToInt32(this.textBox2.Text), "ground" + game1.map.groundsHistory));
            this.Close();
        }
        void textBox1_TextChanged(object sender, EventArgs e)
        {
            if (this.textBox1.Text == "")
                this.button1.Enabled = false;
            else
                this.button1.Enabled = true;
        }
    }
}
