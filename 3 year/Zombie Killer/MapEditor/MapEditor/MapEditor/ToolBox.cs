using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using Microsoft.Xna.Framework.Input;

namespace MapEditor
{
    public partial class ToolBox : Form
    {
        private TabPage[] entityTablePage;
        public DataGridView[] entityTable;
        PositionForm positionForm;
        public void InitData()
        {
            this.entityTable = new System.Windows.Forms.DataGridView[10];
            this.entityTablePage = new TabPage[10];

            for (int i = 0; i < 4; i++)
            {
                this.entityTable[i] = new DataGridView();
                this.entityTable[i].AllowUserToAddRows = false;
                this.entityTable[i].AllowUserToDeleteRows = false;
                this.entityTable[i].AllowUserToResizeColumns = false;
                this.entityTable[i].AllowUserToResizeRows = false;
                this.entityTable[i].ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
                this.entityTable[i].ColumnHeadersVisible = false;
                this.entityTable[i].Location = new System.Drawing.Point(0, 0);
                this.entityTable[i].MultiSelect = false;
                this.entityTable[i].Name = "dataGridView1";
                this.entityTable[i].ReadOnly = true;
                this.entityTable[i].RowHeadersVisible = false;
                this.entityTable[i].SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
                this.entityTable[i].Size = new System.Drawing.Size(256, 143);
                this.entityTable[i].TabIndex = i;
                DataGridViewImageColumn img = new DataGridViewImageColumn();
                img.Width = 100;
                img.ImageLayout = DataGridViewImageCellLayout.Stretch;
                img.HeaderText = "Image";
                img.Name = "img";
                DataGridViewTextBoxColumn descript = new DataGridViewTextBoxColumn();
                descript.Width = 200;
                descript.HeaderText = "Description";
                descript.Name = "descript";
                descript.ReadOnly = true;
                DataGridViewRow temp = new DataGridViewRow();
                temp.Height = 100;
                this.entityTable[i].RowTemplate = temp;
                this.entityTable[i].Columns.Add(img);
                this.entityTable[i].Columns.Add(descript);

                this.entityTablePage[i] = new TabPage();
                this.entityTablePage[i].Controls.Add(this.entityTable[i]);
                this.entityTablePage[i].Location = new System.Drawing.Point(4, 22);
                this.entityTablePage[i].Name = "tabPage1";
                this.entityTablePage[i].Padding = new System.Windows.Forms.Padding(3);
                this.entityTablePage[i].Size = new System.Drawing.Size(256, 216);
                this.entityTablePage[i].TabIndex = i;
                this.entityTablePage[i].UseVisualStyleBackColor = true;
            }
            this.entityTablePage[0].Text = "Barrels";
            this.entityTablePage[1].Text = "Grounds";
            this.entityTablePage[2].Text = "Walls";
            this.entityTablePage[3].Text = "Zombies";
            this.tabControl1.Controls.Add(this.entityTablePage[0]);
            this.tabControl1.Controls.Add(this.entityTablePage[1]);
            this.tabControl1.Controls.Add(this.entityTablePage[2]);
            this.tabControl1.Controls.Add(this.entityTablePage[3]);

            game1.mainWindow.CursorChanged += new EventHandler(mainWindow_CursorChanged);
        }
        //////////////////////////////////////////////////////////////////////////////////////////
        //////// TOOLBOX BUTTONS EVENTHANDLERS ///////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////
        void buttonAdd_MouseDown(object sender, MouseEventArgs e)
        {
            foreach (Base b in game1.map.objects)
            {
                b.Selected = false;
            }
            if (e.Button == MouseButtons.Left)
            game1.mainWindow.Cursor = Cursors.Hand;
            if (e.Button == MouseButtons.Right)
            {
                positionForm = new PositionForm(game1);
                positionForm.StartPosition = FormStartPosition.CenterScreen;
                positionForm.ShowDialog();
            }
        }

        void mainWindow_CursorChanged(object sender, EventArgs e)
        {
            if (game1.mainWindow.Cursor == Cursors.Hand)
            {
                this.Enabled = false;
                game1.mainWindow.Activate();
            }
        }

        private void buttonDelete_Click(object sender, EventArgs e)
        {
            foreach (Base b in game1.map.objects)
            {
                if (b.GetType()!=typeof(SpawnPoint))
                if (b.Selected)
                {
                    game1.map.objects.Remove(b);
                    game1.toolBox.propertyGrid1.SelectedObject = game1.map.gui;
                    return;
                }
            }
        }
    }
}
