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
    /// <summary>
    /// Rules form
    /// </summary>
    public partial class RulesForm : Form
    {
        /// <summary>
        /// Rules form constructor
        /// </summary>
        public RulesForm()
        {
            InitializeComponent();
            LoadRules();
        }
        /// <summary>
        /// Loading rules file
        /// </summary>
        public void LoadRules()
        {
            try
            {
                Stream s = new FileStream(@"res\rules.rul", FileMode.Open);
                StreamReader sr = new StreamReader(s);
                string line;
                while (!sr.EndOfStream)
                {
                    line = sr.ReadLine();
                    textBox1.Text += line+"\n";
                }
                sr.Dispose();
                s.Dispose();
            }
            catch
            {
                MessageBox.Show(this,"Error reading rules file!\n Application will be aborted.","Error",MessageBoxButtons.OK,MessageBoxIcon.Error);
                Application.Exit();
            }
        }
    }
}
