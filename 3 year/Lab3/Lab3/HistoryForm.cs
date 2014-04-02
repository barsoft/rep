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
    /// History form
    /// </summary>
    public partial class HistoryForm : Form
    {
        List<History> historyList = new List<History>();
        /// <summary>
        /// History form constructor
        /// </summary>
        public HistoryForm()
        {
            InitializeComponent();
            LoadHistory();
            dataGridView1.Update();
        }
        /// <summary>
        /// Load history from file
        /// </summary>
        public void LoadHistory()
        {
            try
            {
                Stream s = new FileStream(@"res\history.hst", FileMode.OpenOrCreate);
                StreamReader sr = new StreamReader(s);

                string line;

                while (!sr.EndOfStream)
                {
                    int number = 0;
                    string time = null;
                    StringBuilder sb = new StringBuilder();
                    int counter = 0;
                    line = sr.ReadLine();
                    Insert(sb, line, ',', ref counter);
                    number = Convert.ToInt32(sb.ToString());
                    Insert(sb, line, ';', ref counter);
                    time = sb.ToString();
                    historyList.Add(new History(number, time));
                }
                sr.Dispose();
                s.Dispose();
                dataGridView1.DataSource = historyList;
                dataGridView1.AutoGenerateColumns = true;
            }
            catch
            {
                MessageBox.Show(this, "Error reading history file!\n Application will be aborted.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                Application.Exit();
            }
        }
        private void Insert(StringBuilder text, string sourceLine, char c, ref  int counter)
        {
            text.Clear();
            while (true)
            {
                if (sourceLine[counter].Equals(c))
                    break;
                text.Append(sourceLine[counter]);
                counter++;
            }
            counter++;
        }
    }
}
