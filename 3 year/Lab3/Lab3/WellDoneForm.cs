using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace Lab3
{
    /// <summary>
    /// Well done form
    /// </summary>
    public partial class WellDoneForm : Form
    {
        /// <summary>
        /// Well done form constructor
        /// </summary>
        public WellDoneForm()
        {
            InitializeComponent();
            this.checkBox1.Checked = true;
        }
        /// <summary>
        /// button ok welldone form eventhandler
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonok_Click(object sender, EventArgs e)
        {
            if (checkBox1.Checked == true)
            {
                HistoryForm h = new HistoryForm();
                h.Owner = this;
                h.ShowDialog();
            }
            this.Close();
        }
    }
}
