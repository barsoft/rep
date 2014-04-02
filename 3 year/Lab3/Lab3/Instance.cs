using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Drawing;

namespace Lab3
{
    /// <summary>
    /// Instance card type, card which we drag
    /// </summary>
    public class Instance : Card
    {
        Form1 form1;
        /// <summary>
        /// Indicates if instance is selected
        /// </summary>
        public bool Selected { get; set; }
        private Boolean dragInProgress = false;
        int MouseDownX = 0;
        int MouseDownY = 0;

        /// <summary>
        /// Instance constructor
        /// </summary>
        /// <param name="sampleBitmap"></param>
        /// <param name="signFileName"></param>
        /// <param name="x"></param>
        /// <param name="y"></param>
        /// <param name="name"></param>
        /// <param name="form1"></param>
        public Instance(Bitmap sampleBitmap, string signFileName, int x, int y, string name, Form1 form1)
            : base(sampleBitmap, signFileName, x, y, name)
        {
            this.MouseDown += new MouseEventHandler(instance_MouseDown);
            this.MouseMove += new MouseEventHandler(instance_MouseMove);
            this.MouseUp += new MouseEventHandler(instance_MouseUp);
            this.MouseDoubleClick += new MouseEventHandler(instance_MouseDown);
            this.form1 = form1;
            this.MouseEnter += new EventHandler(Instance_MouseEnter);
            this.MouseLeave += new EventHandler(Instance_MouseLeave);
        }

        void Instance_MouseLeave(object sender, EventArgs e)
        {
            if (this.Selected)
            {
                form1.Cursor = Cursors.Arrow;
            }
        }

        void Instance_MouseEnter(object sender, EventArgs e)
        {
            if (this.Selected)
            {
                form1.Cursor = Cursors.Hand;
            }
        }


        void instance_MouseDown(object sender, MouseEventArgs e)
        {
            form1.UpdateStates();
            if (this.Selected)
            {
                if (!this.dragInProgress)
                {
                    this.dragInProgress = true;
                    this.MouseDownX = e.X;
                    this.MouseDownY = e.Y;
                }
            }
            return;
        }

        void instance_MouseUp(object sender, MouseEventArgs e)
        {
            if (this.Selected)
            {
                if (e.Button == MouseButtons.Left)
                {
                    this.dragInProgress = false;

                    bool flag = false;
                    foreach (Sample samp in form1.samples)
                    {
                        Rectangle r = new Rectangle(samp.Left, samp.Top, samp.Width, samp.Height);
                        Rectangle r2 = new Rectangle(this.Left, this.Top, this.Width, this.Height);
                        if (r.IntersectsWith(r2) && this.Name == samp.Name)
                        {
                            int last = -1;
                            int i = 0;
                            foreach (Instance inst in form1.instancesOnSample)
                            {
                                if (inst.Name == Name)
                                    last=i;
                                i++;
                            }
                            if (last == -1)
                            {
                                this.Left = r.Left + 10;
                                this.Top = r.Top + 10;
                            }
                            else
                            {
                                this.Left = form1.instancesOnSample[last].Left + 10;
                                this.Top = form1.instancesOnSample[last].Top + 10;
                            }
                            samp.Text = this.Text;
                            this.Selected = false;
                            form1.Cursor = Cursors.Arrow;
                            form1.instances.Remove(this);
                            form1.instancesOnSample.Add(this);
                            form1.UpdateStack();
                            flag = true;
                            break;
                        }

                    }

                    if (!flag)
                    {
                        int i = 0;
                        foreach (Instance inst in form1.instances)
                        {
                            if (inst == this)
                                break;
                            i++;
                        }
                        if (i == 0)
                        {
                            this.Left = form1.StackX;
                            this.Top = form1.StackY;
                        }
                        else
                        {
                            this.Left = form1.instances[i - 1].Left + form1.StackIntervalX;
                            this.Top = form1.instances[i - 1].Top + form1.StackIntervalY;
                        }
                    }

                    form1.UpdateStates();
                }
            }
            return;


        }

        void instance_MouseMove(object sender, MouseEventArgs e)
        {
            if (dragInProgress)
            {
                Point temp = new Point();
                temp.X = this.Location.X + (e.X - MouseDownX);
                temp.Y = this.Location.Y + (e.Y - MouseDownY);
                this.Location = temp;
            }
            return;
        }
    }
}
