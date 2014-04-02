using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.IO;

namespace Lab3
{
    public partial class Form1 : Form
    {
        PictureBox backgcircle;
        /// <summary>
        /// Instances array
        /// </summary>
        public List<Instance> instances = new List<Instance>();
        /// <summary>
        /// Instances on sample array
        /// </summary>
        public List<Instance> instancesOnSample = new List<Instance>();
        /// <summary>
        /// Samples  array
        /// </summary>
        public List<Sample> samples = new List<Sample>();
        /// <summary>
        /// Card stack x position
        /// </summary>
        public int StackX { get; set; }
        /// <summary>
        /// Card stack y position
        /// </summary>
        public int StackY { get; set; }
        /// <summary>
        /// Card stack interval x value
        /// </summary>
        public int StackIntervalX { get; set; }
        /// <summary>
        /// Card stack interval y value
        /// </summary>
        public int StackIntervalY { get; set; }
        Timer timer;
        int timeValue;
        /// <summary>
        /// Game form constructor
        /// </summary>
        public Form1()
        {
            StackIntervalX = 20; StackIntervalY = 0;
            StackX = 100;
            StackY = 350;
            InitializeComponent();
            backgcircle = new PictureBox();
            backgcircle.Image = new Bitmap(@"res\background.bmp");
            backgcircle.SizeMode = PictureBoxSizeMode.StretchImage;
            backgcircle.Left = 0;
            backgcircle.Top = 0;
            backgcircle.Width = this.Width;
            backgcircle.Height = this.Height;
            this.Controls.Add(backgcircle);
            this.playInstructions.Hide();
            this.toolStrip1.Hide();
            UpdateStates();
            timer = new Timer();
            timer.Tick += new EventHandler(timer_Tick);
            
            timer.Interval = 1000;

            this.Activated += new EventHandler(Form1_Activated);
            this.Deactivate += new EventHandler(Form1_Deactivate);

        }

        void Form1_Deactivate(object sender, EventArgs e)
        {
            timer.Stop();
        }

        void Form1_Activated(object sender, EventArgs e)
        {
            timer.Start();
        }
        /// <summary>
        /// Starts game session
        /// </summary>
        public void StartGame()
        {
            timeValue = 0;
            this.playInstructions.Show();
            this.toolStrip1.Show();
            this.exitButton.Hide();
            this.historyButton.Hide();
            this.playButton.Hide();
            this.rulesButton.Hide();

            fileToolStripMenuItem.DropDownItems[0].Enabled = false;
            fileToolStripMenuItem.DropDownItems[1].Enabled = true;
            fileToolStripMenuItem.DropDownItems[2].Enabled = true;

            Bitmap sample = new Bitmap(@"res\sample.bmp");
            Bitmap instance = new Bitmap(@"res\instance.bmp");

            samples.Add(new Sample(sample, @"res\rhomb.bmp", 100, 130, "rhomb"));
            samples.Add(new Sample(sample, @"res\square.bmp", 300, 130, "square"));
            samples.Add(new Sample(sample, @"res\circle.bmp", 500, 130, "circle"));

            foreach (Sample samp in samples)
            {
                this.Controls.Add(samp);
                samp.BringToFront();
            }

            Random r = new Random();

            for (int i = 0; i < 3; i++)
            {
                instances.Insert(r.Next(instances.Count), new Instance(instance, @"res\rhomb.bmp", 0, 0, "rhomb", this));
                instances.Insert(r.Next(instances.Count), new Instance(instance, @"res\square.bmp", 0, 0, "square", this));
                instances.Insert(r.Next(instances.Count), new Instance(instance, @"res\circle.bmp", 0, 0, "circle", this));
                instances.Insert(r.Next(instances.Count), new Instance(instance, @"res\ellipse.bmp", 0, 0, "ellipse", this));
                instances.Insert(r.Next(instances.Count), new Instance(instance, @"res\ellipse2.bmp", 0, 0, "ellipse2", this));
                instances.Insert(r.Next(instances.Count), new Instance(instance, @"res\disket.bmp", 0, 0, "disket", this));
                instances.Insert(r.Next(instances.Count), new Instance(instance, @"res\basket.bmp", 0, 0, "basket", this));
            }
            GoDown();
            UpdateStack();

            instances[instances.Count - 1].Selected = true;

            
            timer.Start();
        }
        /// <summary>
        /// Ends game session
        /// </summary>
        public void EndGame()
        {
            timeValue = 0;
            foreach (Instance inst in instances)
            {
                Controls.Remove(inst);
            }
            foreach (Instance inst in instancesOnSample)
            {
                Controls.Remove(inst);
            }
            foreach (Sample samp in samples)
            {
                Controls.Remove(samp);
            }
            instances.Clear();
            instancesOnSample.Clear();
            samples.Clear();
            UpdateStates();
            this.playInstructions.Hide();
            this.toolStrip1.Hide();
            this.exitButton.Show();
            this.historyButton.Show();
            this.playButton.Show();
            this.rulesButton.Show();
        }
        /// <summary>
        /// Saves last score to history file
        /// </summary>
        public void SaveHistory()
        {
            try
            {
                Stream s = new FileStream(@"res\history.hst", FileMode.OpenOrCreate);
                StreamReader sr = new StreamReader(s);
                string line;
                int i = 0;
                while (!sr.EndOfStream)
                {
                    line = sr.ReadLine();
                    i++;
                }
                sr.Dispose();
                s.Dispose();

                s = new FileStream(@"res\history.hst", FileMode.Append);
                StreamWriter sw = new StreamWriter(s);
                sw.WriteLine(i + " ," + time.Text + ";");
                sw.Dispose();
                s.Dispose();
            }
            catch
            {
                MessageBox.Show(this, "Error writing history file!\n Application will be aborted.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                Application.Exit();
            }
        }
        /// <summary>
        /// Updates instances and score states
        /// </summary>
        public void UpdateStates()
        {
            int i2 = 0;
            int circles = 0;
            int squares = 0;
            int rhombs = 0;
            foreach (Instance inst in instances)
            {
                inst.Selected = false;
                i2++;
            }
            if (i2 > 0)
            {
                instances[i2 - 1].Selected = true;
            }
            foreach (Instance inst in instancesOnSample)
            {
                if (inst.Name == "rhomb")
                    rhombs++;
                if (inst.Name == "circle")
                    circles++;
                if (inst.Name == "square")
                    squares++;
            }

            score.Text = "Rhombs: " + rhombs + " ; Squares: " + squares + " ; Circles: " + circles;

            if (rhombs == 3 && circles == 3 && squares == 3)
            {
                timer.Stop();
                SaveHistory();
                WellDoneForm wf = new WellDoneForm();
                wf.Owner = this;
                wf.ShowDialog();
                EndGame();
            }
        }
        /// <summary>
        /// Puts card to the end of stack
        /// </summary>
        public void GoDown()
        {
            foreach (Instance inst in instances)
            {
                Controls.Remove(inst);
            }

            Instance toEnd = instances[instances.Count - 1];
            this.instances.Remove(toEnd);
            this.instances.Insert(0, toEnd);
            UpdateStack();

            foreach (Instance inst in instances)
            {
                Controls.Add(inst);
                inst.BringToFront();
            }

            UpdateStates();
        }
        /// <summary>
        /// Updates card stack
        /// </summary>
        public void UpdateStack()
        {
            this.instances[0].Left = StackX;
            this.instances[0].Top = StackY;

            for (int i = 1; i < instances.Count; i++)
            {
                instances[i].Left = instances[i - 1].Left + StackIntervalX;
                instances[i].Top = instances[i - 1].Top + StackIntervalY;
                instances[i].BringToFront();
            }
        }
        /// <summary>
        /// timer tick eventhandler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        void timer_Tick(object sender, EventArgs e)
        {
            timeValue++;
            string seconds;
            if (timeValue.ToString().Length == 1)
            {
                seconds = "0" + timeValue % 60;
            }
            else
                seconds = timeValue % 60 + "";
            string minutes;
            if (timeValue.ToString().Length == 1)
            {
                minutes = "0" + timeValue / 60;
            }
            else minutes = timeValue / 60 + "";

            time.Text = minutes + ":" + seconds;

        }
        /// <summary>
        /// toolStrip go down button eventhandler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void toolStripGoDownButton_Click(object sender, EventArgs e)
        {
            GoDown();
        }

        /// <summary>
        /// menu start button eventhandler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void startToolStripMenuItem_Click(object sender, EventArgs e)
        {
            StartGame();
        }
        /// <summary>
        /// menu exit button eventhandler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void exitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }
        /// <summary>
        /// menu restart button eventhandler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void restartToolStripMenuItem_Click(object sender, EventArgs e)
        {
            EndGame();
            StartGame();
        }
        /// <summary>
        /// menu go down button eventhandler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void goDownToolStripMenuItem_Click(object sender, EventArgs e)
        {
            GoDown();
        }
        /// <summary>
        /// menu rules button eventhandler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void rulesToolStripMenuItem_Click(object sender, EventArgs e)
        {
            RulesForm r = new RulesForm();
            r.Owner = this;
            r.ShowDialog();
        }
        /// <summary>
        /// menu history button eventhandler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void historyToolStripMenuItem_Click(object sender, EventArgs e)
        {
            HistoryForm h = new HistoryForm();
            h.Owner = this;
            h.ShowDialog();
        }
        /// <summary>
        /// menu about button eventhandler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void aboutToolStripMenuItem_Click(object sender, EventArgs e)
        {
            AboutBox1 a = new AboutBox1();
            a.Owner = this;
            a.ShowDialog();
        }

        /// <summary>
        /// play button eventhandler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void playButton_Click(object sender, EventArgs e)
        {
            StartGame();
        }
        /// <summary>
        /// rules button eventhandler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void rulesButton_Click(object sender, EventArgs e)
        {
            RulesForm r = new RulesForm();
            r.Owner = this;
            r.ShowDialog();
        }
        /// <summary>
        /// history button eventhandler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void historyButton_Click(object sender, EventArgs e)
        {
            HistoryForm h = new HistoryForm();
            h.Owner = this;
            h.ShowDialog();
        }
        /// <summary>
        /// exit button eventhandler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void exitButton_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }



    }
}
