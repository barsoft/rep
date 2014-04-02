using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace WpfApplication1
{
    /// <summary>
    /// Логика взаимодействия для MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        ServiceReference1.Service1Client client;
        public MainWindow()
        {
            InitializeComponent();
            client = new ServiceReference1.Service1Client();

            this.ServerTimeLabel.Content = client.GetTime();
            DriveInfo[] drvinfo = client.LoadDrives();
            //      foreach(DriveInfo d in drvinfo ){

            //    }
            // drvinfo[0].

            ListBoxPartitions.DataContext = drvinfo;

            ListBoxCores.DataContext = client.GetCPUCoresInfo();
            //  CPUCoresCount.Content = client.GetCPUCoresInfo();
        }
    }
}
