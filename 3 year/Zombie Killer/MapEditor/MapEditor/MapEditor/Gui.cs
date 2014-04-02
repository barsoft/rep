using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Audio;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.GamerServices;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework.Media;
using System.Windows.Forms;
using System.ComponentModel;
using System.IO;
using System.Drawing;

namespace MapEditor
{
    class HorizRullerPiece : Base
    {
        Game1 game1;

        protected int cellSize;
        protected Microsoft.Xna.Framework.Rectangle start;
        protected int size;
        public HorizRullerPiece(Game1 game1, Texture2D texture, int x, int y, int size)
            : base(game1)
        {
            this.game1 = game1;
            this.cellSize = game1.map.gui.CellSize;
            Texture = texture;
            rect = new Microsoft.Xna.Framework.Rectangle(x, y, cellSize, size);
            start = rect;
            this.size = size;
        }

        public override void Update(GameTime gameTime)
        {
            base.Update(gameTime);

            float z = game1.map.camera.Zoom / 100.0f;

            float x = game1.map.camera.X;
            float y = game1.map.camera.Y;

            disprect.X = (int)(rect.Left * z + x * z);
            disprect.Y = (int)(rect.Top);

            disprect.Width = (int)(rect.Width * z);
            disprect.Height = (int)(rect.Height);

            this.cellSize = game1.map.gui.CellSize;
            rect = new Microsoft.Xna.Framework.Rectangle((int)(start.X / start.Width * cellSize), start.Y, cellSize, size);
        }

        virtual public void Draw()
        {
            game1.spriteBatch.Draw(Texture, disprect, Microsoft.Xna.Framework.Color.White);

            Vector2 pos = new Vector2(disprect.Left, disprect.Top);

            game1.spriteBatch.DrawString(game1.map.Font1, "" + rect.Left, pos, Microsoft.Xna.Framework.Color.White);
        }
    }
    class VertRullerPiece : HorizRullerPiece
    {
        public VertRullerPiece(Game1 game1, Texture2D texture, int x, int y, int size)
            : base(game1, texture, x, y, size)
        {
            this.cellSize = game1.map.gui.CellSize;
            rect = new Microsoft.Xna.Framework.Rectangle(x, y, size, cellSize);
            start = rect;
            this.size = size;
        }
        public override void Update(GameTime gameTime)
        {
            float z = game1.map.camera.Zoom / 100.0f;
            float x = game1.map.camera.X;
            float y = game1.map.camera.Y;

            disprect.X = (int)(rect.Left);
            disprect.Y = (int)(rect.Top * z + y * z);

            disprect.Width = (int)(rect.Width);
            disprect.Height = (int)(rect.Height * z);

            this.cellSize = game1.map.gui.CellSize;
            rect = new Microsoft.Xna.Framework.Rectangle(start.X, (int)(start.Y / start.Height * cellSize), size, cellSize);
        }

        public override void Draw()
        {
            //game1.spriteBatch.Draw(Texture, disprect, null, Color.White, (float)Math.PI / 2, new Vector2(Texture.Bounds.Width / 2, Texture.Bounds.Height / 2),SpriteEffects.None,0);
            game1.spriteBatch.Draw(Texture, disprect, Microsoft.Xna.Framework.Color.White);
            Vector2 pos = new Vector2(disprect.Left, disprect.Top);

            game1.spriteBatch.DrawString(game1.map.Font1, "" + rect.Top, pos, Microsoft.Xna.Framework.Color.White);
        }
    }
    public class Ruller : Base
    {
        List<HorizRullerPiece> horizruller;
        List<VertRullerPiece> vertruller;
        public Texture2D TextureHoriz { get; set; }
        public Texture2D TextureVert { get; set; }
        public int cellSize;
        public Ruller(Game1 game1, string _textureNameHoriz, string _textureNameVert)
            : base(game1)
        {
            this.cellSize = game1.map.gui.CellSize;
            TextureHoriz = game1.Content.Load<Texture2D>(_textureNameHoriz);
            TextureVert = game1.Content.Load<Texture2D>(_textureNameVert);
            horizruller = new List<HorizRullerPiece>();
            vertruller = new List<VertRullerPiece>();
            for (int i = 0; i < game1.map.Width / cellSize; i++)
            {
                horizruller.Add(new HorizRullerPiece(game1, TextureHoriz, i * cellSize, 0, 30));
            }
            for (int i = 0; i < game1.map.Height / cellSize; i++)
            {
                vertruller.Add(new VertRullerPiece(game1, TextureVert, 0, i * cellSize, 30));
            }
        }

        public override void Update(GameTime gameTime)
        {
            base.Update(gameTime);
            foreach (HorizRullerPiece rp in horizruller)
            {
                rp.Update(gameTime);
            }
            foreach (VertRullerPiece rp in vertruller)
            {
                rp.Update(gameTime);
            }
            //cellSize = game1.map.gui.cellSize;
        }

        public void Draw()
        {
            foreach (HorizRullerPiece rp in horizruller)
            {
                rp.Draw();
            }
            foreach (VertRullerPiece rp in vertruller)
            {
                rp.Draw();
            }
        }
    }
    class GridPiece : Base
    {
        Game1 game1;
        int cellSize;
        Microsoft.Xna.Framework.Rectangle start;
        public GridPiece(Game1 game1, Texture2D texture, int x, int y)
            : base(game1)
        {
            this.cellSize = game1.map.gui.CellSize;
            this.game1 = game1;
            Texture = texture;
            rect = new Microsoft.Xna.Framework.Rectangle(x, y, cellSize, cellSize);
            start = rect;

        }
        public override void Update(GameTime gameTime)
        {
            base.Update(gameTime);
            this.cellSize = game1.map.gui.CellSize;
            rect = new Microsoft.Xna.Framework.Rectangle((int)(start.X / start.Width * cellSize), (int)(start.Y / start.Height * cellSize), cellSize, cellSize);
        }
        public virtual void Draw()
        {
            game1.spriteBatch.Draw(Texture, disprect, Microsoft.Xna.Framework.Color.White);
        }
    }
    public class Grid : Base
    {
        List<GridPiece> grid;
        public Texture2D TextureHoriz { get; set; }

        public Texture2D TextureVert { get; set; }
        public int cellSize;
        public int aligncellSize;
        public Grid(Game1 game1, string _textureName)
            : base(game1)
        {
            this.cellSize = game1.map.gui.CellSize;
            this.aligncellSize = game1.map.gui.AlignRadius;
            Texture = game1.Content.Load<Texture2D>(_textureName);
            grid = new List<GridPiece>();
            for (int i = 0; i < game1.map.Width / cellSize; i++)
                for (int j = 0; j < game1.map.Height / cellSize; j++)
                {
                    grid.Add(new GridPiece(game1, Texture, i * cellSize, j * cellSize));
                }
        }

        public override void Update(GameTime gameTime)
        {
            base.Update(gameTime);
            foreach (GridPiece rp in grid)
            {
                rp.Update(gameTime);
            }
            this.aligncellSize = game1.map.gui.AlignRadius;
            this.cellSize = game1.map.gui.CellSize;
        }

        public void Draw()
        {
            foreach (GridPiece rp in grid)
            {
                rp.Draw();
            }
        }
        public void Align(ref Microsoft.Xna.Framework.Rectangle rect)
        {
            if (rect.X % cellSize > cellSize - aligncellSize)
                rect.X = rect.X - rect.X % cellSize + cellSize;

            if (rect.X % cellSize < aligncellSize && rect.X % cellSize > 0)
                rect.X = rect.X - rect.X % cellSize;

            if (rect.Y % cellSize > cellSize - aligncellSize)
                rect.Y = rect.Y - rect.Y % cellSize + cellSize;

            if (rect.Y % cellSize < aligncellSize && rect.Y % 100 > 0)
                rect.Y = rect.Y - rect.Y % cellSize;
        }
    }
    public class Gui
    {
        Game1 game1;
        public Ruller ruller;
        public Grid grid;
        [Category("Editor")]
        public int CellSize { get { return cellSize; } set { cellSize = value; } }
        private int cellSize;
        [Category("Editor")]
        public int AlignRadius { get; set; }

        private Microsoft.Xna.Framework.Point diff;
        protected Stream myStream;
        public ContextMenuStrip docMenu;
        public Gui(Game1 game1)
        {
            this.game1 = game1;
            //////////////////////////////////////////////////////////////////////////////////////////
            //////// DEFAULT EDITOR PARAMS ///////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////
            CellSize = 50;
            AlignRadius = 10;
            //////////////////////////////////////////////////////////////////////////////////////////
            //////// VISUAL STYLES ///////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////
            Application.EnableVisualStyles();
            //////////////////////////////////////////////////////////////////////////////////////////
            //////// MAINWINDOW MOUSE EVENTHANDLERS //////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////
            game1.mainWindow.MouseWheel += new MouseEventHandler(mainWindow_MouseWheel);
            game1.mainWindow.MouseDown += new System.Windows.Forms.MouseEventHandler(mainWindow_MouseDown);
            game1.mainWindow.MouseMove += new System.Windows.Forms.MouseEventHandler(mainWindow_MouseMove);
            game1.mainWindow.MouseUp += new MouseEventHandler(mainWindow_MouseUp);
            //////////////////////////////////////////////////////////////////////////////////////////
            //////// TOOLBOX DEFAULT PARAMS //////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////
            game1.toolBox.label1.Text = "Zoom:" + game1.map.camera.Zoom;
            game1.toolBox.propertyGrid1.SelectedObject = this;
            //////////////////////////////////////////////////////////////////////////////////////////
            //////// ENTITY TABLE INTIALIZATION //////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////
            foreach (string type in BarrelType.Types.Name)
            {
                game1.toolBox.entityTable[0].Rows.Add(new Bitmap("res//" + type + ".png"), type);
            }

            foreach (string type in GroundType.Types.Name)
            {
                game1.toolBox.entityTable[1].Rows.Add(new Bitmap("res//" + type + ".png"), type);
            }
            foreach (string type in WallType.Types.Name)
            {
                game1.toolBox.entityTable[2].Rows.Add(new Bitmap("res//" + type + ".png"), type);
            }
            foreach (string type in ZombieType.Types.Name)
            {
                game1.toolBox.entityTable[3].Rows.Add(new Bitmap("res//" + type + ".png"), type);
            }
            //////////////////////////////////////////////////////////////////////////////////////////
            //////// MAIN MENU INTIALIZATION /////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////
            MainMenu mm = new MainMenu();
            MenuItem myMenuItemFolder = new MenuItem("&File");
            myMenuItemFolder.MenuItems.Add("&New");
            myMenuItemFolder.MenuItems[0].Click += new EventHandler(new_Click);
            myMenuItemFolder.MenuItems.Add("&Open");
            myMenuItemFolder.MenuItems[1].Click += new EventHandler(open_Click);
            myMenuItemFolder.MenuItems.Add("&Save");
            myMenuItemFolder.MenuItems[2].Click += new EventHandler(save_Click);
            mm.MenuItems.Add(myMenuItemFolder);
            game1.mainWindow.Menu = mm;
            //////////////////////////////////////////////////////////////////////////////////////////
            //////// CONTEXT MENU INTIALIZATION /////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////
            docMenu = new ContextMenuStrip();

            docMenu.Opened += new EventHandler(docMenu_GotFocus);
            //Create some menu items.
            ToolStripMenuItem openLabel = new ToolStripMenuItem();
            openLabel.Text = "Add";
            openLabel.Click += new EventHandler(openLabel_Click);
            ToolStripMenuItem deleteLabel = new ToolStripMenuItem();
            deleteLabel.Text = "Delete";
            deleteLabel.Click += new EventHandler(deleteLabel_Click);
            //Add the menu items to the menu.
            docMenu.Items.AddRange(new ToolStripMenuItem[]{openLabel, 
            deleteLabel});

            game1.mainWindow.ContextMenuStrip = docMenu;

        }

        void docMenu_GotFocus(object sender, EventArgs e)
        {
            foreach (Base b in game1.map.objects)
            {
                if (b.Selected)
                {
                    docMenu.Items[1].Enabled = true;
                    docMenu.Items[0].Enabled = false;
                    return;
                }
            }
            docMenu.Items[1].Enabled = false;
            docMenu.Items[0].Enabled = true;
        }

        void deleteLabel_Click(object sender, EventArgs e)
        {
            foreach (Base b in game1.map.objects)
            {
                if (b.GetType() != typeof(SpawnPoint))
                if (b.Selected)
                {
                    game1.map.objects.Remove(b);
                    game1.toolBox.propertyGrid1.SelectedObject = game1.map.gui;
                    return;
                }
            }
        }

        void openLabel_Click(object sender, EventArgs e)
        {
            MouseState ms = Mouse.GetState();
            int y = ms.Y * 100 / game1.map.camera.Zoom;
            int dy = game1.map.camera.Y;
            int x = ms.X * 100 / game1.map.camera.Zoom;
            int dx = game1.map.camera.X;

            if (game1.toolBox.tabControl1.SelectedIndex == 0)
                game1.map.objects.Add(new Barrel(game1, game1.toolBox.entityTable[0].CurrentRow.Cells[1].Value.ToString(), x-dx,y  -dy , "barrel" + game1.map.barrelsHistory));
            if (game1.toolBox.tabControl1.SelectedIndex == 1)
                game1.map.objects.Add(new Ground(game1, game1.toolBox.entityTable[1].CurrentRow.Cells[1].Value.ToString(),  x-dx,y  -dy , "ground" + game1.map.groundsHistory));
            if (game1.toolBox.tabControl1.SelectedIndex == 2)
                game1.map.objects.Add(new Wall(game1, game1.toolBox.entityTable[2].CurrentRow.Cells[1].Value.ToString(),x-dx,y  -dy , "wall" + game1.map.wallsHistory));
            if (game1.toolBox.tabControl1.SelectedIndex == 3)
                game1.map.objects.Add(new Zombie(game1, game1.toolBox.entityTable[3].CurrentRow.Cells[1].Value.ToString(), x - dx, y - dy, "zombie" + game1.map.zombiesHistory));

        }
        //////////////////////////////////////////////////////////////////////////////////////////
        //////// MAINWINDOW MOUSE EVENTHANDLERS //////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////
        void mainWindow_MouseUp(object sender, MouseEventArgs e)
        {
            game1.toolBox.propertyGrid1.Refresh();
        }
        void mainWindow_MouseMove(object sender, System.Windows.Forms.MouseEventArgs e)
        {

            if (e.Button == System.Windows.Forms.MouseButtons.Left)
            {
                //-------------------------MOVEMENT///////////-------------------------------//
                foreach (Base b in game1.map.objects)
                {
                    if (b.Selected)
                    {

                        float z = 100.0f / game1.map.camera.Zoom;
                        b.Rect = new Microsoft.Xna.Framework.Rectangle((int)(e.X * z - diff.X), (int)(e.Y * z - diff.Y), b.Rect.Width, b.Rect.Height);
                        return;
                    }
                }
            }
        }
        void mainWindow_MouseDown(object sender, System.Windows.Forms.MouseEventArgs e)
        {
            if (game1.mainWindow.Cursor == Cursors.Hand)
            {
                game1.mainWindow.Cursor = Cursors.Arrow;
                int y = e.Y * 100 / game1.map.camera.Zoom;
                int dy = game1.map.camera.Y;
                int x = e.X * 100 / game1.map.camera.Zoom;
                int dx = game1.map.camera.X;

                if (game1.toolBox.tabControl1.SelectedIndex == 0)
                    game1.map.objects.Add(new Barrel(game1, game1.toolBox.entityTable[0].CurrentRow.Cells[1].Value.ToString(), x - dx, y - dy, "barrel" + game1.map.barrelsHistory));
                if (game1.toolBox.tabControl1.SelectedIndex == 1)
                    game1.map.objects.Add(new Ground(game1, game1.toolBox.entityTable[1].CurrentRow.Cells[1].Value.ToString(), x - dx, y - dy, "ground" + game1.map.groundsHistory));
                if (game1.toolBox.tabControl1.SelectedIndex == 2)
                    game1.map.objects.Add(new Wall(game1, game1.toolBox.entityTable[2].CurrentRow.Cells[1].Value.ToString(), x - dx, y - dy, "wall" + game1.map.wallsHistory));
                if (game1.toolBox.tabControl1.SelectedIndex == 3)
                    game1.map.objects.Add(new Zombie(game1, game1.toolBox.entityTable[3].CurrentRow.Cells[1].Value.ToString(), x - dx, y - dy, "zombie" + game1.map.zombiesHistory));
                game1.toolBox.Enabled = true;
            }
            else
            {
                bool empty = true;
                //---------------SELECTION CHECK (DO NOT SELECT IF SELECTED)-----------------//
                foreach (Base g in game1.map.objects)
                {
                    if (g.Selected)
                    {
                        if (g.Focused)
                        {
                            float z = 100.0f / game1.map.camera.Zoom;
                            diff.X = (int)(e.X * z - g.Rect.X);
                            diff.Y = (int)(e.Y * z - g.Rect.Y);
                            return;
                        }
                    }
                }
                //--------------------------RESET--------------------------------------------//
                foreach (Base b in game1.map.objects)
                {
                    b.Selected = false;
                }
                //----------------------SELECTION--------------------------------------------//
                foreach (Base b in game1.map.objects)
                {
                    if (b.GetType() == typeof(SpawnPoint))
                        if (b.Focused)
                        {
                            game1.toolBox.propertyGrid1.SelectedObject = b;
                            b.Selected = true;
                            empty = false;
                            float z = 100.0f / game1.map.camera.Zoom;
                            diff.X = (int)(e.X * z - b.Rect.X);
                            diff.Y = (int)(e.Y * z - b.Rect.Y);
                            return;
                        }
                }
                foreach (Base b in game1.map.objects)
                {
                    if (b.GetType() == typeof(Zombie))
                        if (b.Focused)
                        {
                            game1.toolBox.propertyGrid1.SelectedObject = b;
                            b.Selected = true;
                            empty = false;
                            float z = 100.0f / game1.map.camera.Zoom;
                            diff.X = (int)(e.X * z - b.Rect.X);
                            diff.Y = (int)(e.Y * z - b.Rect.Y);
                            return;
                        }
                }
                foreach (Base b in game1.map.objects)
                {
                    if (b.GetType() == typeof(Barrel))
                        if (b.Focused)
                        {
                            game1.toolBox.propertyGrid1.SelectedObject = b;
                            b.Selected = true;
                            empty = false;
                            float z = 100.0f / game1.map.camera.Zoom;
                            diff.X = (int)(e.X * z - b.Rect.X);
                            diff.Y = (int)(e.Y * z - b.Rect.Y);
                            return;
                        }
                }
                foreach (Base b in game1.map.objects)
                {
                    if (b.GetType() == typeof(Wall))
                        if (b.Focused)
                        {
                            game1.toolBox.propertyGrid1.SelectedObject = b;
                            b.Selected = true;
                            empty = false;
                            float z = 100.0f / game1.map.camera.Zoom;
                            diff.X = (int)(e.X * z - b.Rect.X);
                            diff.Y = (int)(e.Y * z - b.Rect.Y);
                            return;
                        }
                }
                foreach (Base b in game1.map.objects)
                {
                    if (b.GetType() == typeof(Ground))
                    if (b.Focused)
                    {
                        game1.toolBox.propertyGrid1.SelectedObject = b;
                        b.Selected = true;
                        empty = false;
                        float z = 100.0f / game1.map.camera.Zoom;
                        diff.X = (int)(e.X * z - b.Rect.X);
                        diff.Y = (int)(e.Y * z - b.Rect.Y);
                        return;
                    }
                }
                
                if (empty)
                    game1.toolBox.propertyGrid1.SelectedObject = this;
            }
        }
        void mainWindow_MouseWheel(object sender, MouseEventArgs e)
        {
            game1.toolBox.LabelText = "Zoom:" + game1.map.camera.Zoom;
        }
        //////////////////////////////////////////////////////////////////////////////////////////
        //////// MAIN MENU EVENTHANDLERS /////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////
        void new_Click(object sender, EventArgs e)
        {
            game1.map.objects.Clear();
            game1.map.objects.Add(new SpawnPoint(game1, "flag", 0, 0, "spawnPoint"));
        }
        void open_Click(object sender, EventArgs e)
        {
            if (game1.map.objects.Count != 0)
            {
                var res = MessageBox.Show("Map has been modified.\nDo you want to save your changes?", "MapEditor", MessageBoxButtons.YesNoCancel, System.Windows.Forms.MessageBoxIcon.Warning, MessageBoxDefaultButton.Button1, MessageBoxOptions.ServiceNotification);
                if (res == DialogResult.Cancel)
                    return;
                if (res == DialogResult.Yes)
                {
                    SaveFileDialog sf = new SaveFileDialog();
                    sf.Filter = "City Traveller Map (*.abmap)| *.abmap";
                    sf.FileName = "map";
                    sf.Title = "Save the City Traveller map";
                    sf.RestoreDirectory = true;

                    var res2 = sf.ShowDialog();
                    if (res2 == DialogResult.OK)
                    {
                        if ((myStream = sf.OpenFile()) != null)
                        {
                            myStream.Close();
                        }
                    }

                    if (res2 == DialogResult.Cancel)
                    {
                        return;
                    }
                }
            }
            OpenFileDialog of = new OpenFileDialog();
            of.Filter = "City Traveller Map (*.abmap)| *.abmap";
            of.FileName = "";
            of.Title = "Open the City Traveller map";
            of.RestoreDirectory = true;

            DialogResult dr = of.ShowDialog();

            if (dr == DialogResult.OK)
            {

                if ((myStream = of.OpenFile()) != null)
                {
                    game1.map.objects.Clear();
                    game1.map.LoadMap(myStream);
                    myStream.Close();
                }
            }

        }
        void save_Click(object sender, EventArgs e)
        {
            SaveFileDialog sf = new SaveFileDialog();
            sf.Filter = "City Traveller Map (*.abmap)| *.abmap";
            sf.FileName = "map";
            sf.Title = "Save the City Traveller map";
            sf.RestoreDirectory = true;

            if (sf.ShowDialog() == DialogResult.OK)
            {
                if ((myStream = sf.OpenFile()) != null)
                {
                    game1.map.SaveMap(myStream);
                    myStream.Close();
                }
            }
        }

        public void Initialize()
        {
            ruller = new Ruller(game1, "rullerHoriz", "rullerVert");
            grid = new Grid(game1, "grid");
        }

        public void Update(GameTime gameTime)
        {
            ruller.Update(gameTime);
            grid.Update(gameTime);

            KeyboardState newState = Keyboard.GetState();
            if (newState.IsKeyDown(Microsoft.Xna.Framework.Input.Keys.Delete))
            {
                foreach (Base b in game1.map.objects)
                {
                    if (b.GetType() != typeof(SpawnPoint))
                    if (b.Selected)
                    {
                        game1.map.objects.Remove(b);
                        game1.toolBox.propertyGrid1.SelectedObject = this;
                        return;
                    }
                }
            }
        }

        public void Draw()
        {
            ruller.Draw();
            grid.Draw();
        }
    }
}
