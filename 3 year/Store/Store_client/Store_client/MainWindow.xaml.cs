using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Store_client
{
    /// <summary>
    /// Логика взаимодействия для MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public Client Client { get; set; }

        public MainWindow()
        {
            InitializeComponent();

            ConnectionWindow ent = new ConnectionWindow();
            ent.ShowDialog();

            Client = new Client(this);
            Client.ConnectAsync(ent.ip_adress.Text, int.Parse(ent.port.Text));
        }




        public System.Windows.Data.CollectionViewSource productsViewSource;
        public System.Windows.Data.CollectionViewSource manufacturersViewSource;
        public System.Windows.Data.CollectionViewSource instancesViewSource;

        public void LoadData()
        {
            Client.SendAsync("select_tables");
        }



        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            LoadData();

        }

      
        private void MenuItem_exit_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }



        public InstancesFilter filter;

        private void MenuItem_filter_open_Click(object sender, RoutedEventArgs e)
        {
            filter = new InstancesFilter();
            filter.Owner = this;
            filter.WindowStartupLocation = System.Windows.WindowStartupLocation.CenterOwner;
            filter.Show();
        }

        private void MenuItem_filter_close_Click(object sender, RoutedEventArgs e)
        {
            filter.Close();
        }


        private void gotfocus(object sender, SelectionChangedEventArgs e)
        {
            DoubleAnimation test = new System.Windows.Media.Animation.DoubleAnimation(0.3, 1, new Duration(TimeSpan.FromSeconds(1)));
            ((TabItem)(sender as TabControl).SelectedItem).BeginAnimation(Canvas.OpacityProperty, test);
        }

        private void load(object sender, RoutedEventArgs e)
        {
            DoubleAnimation test = new System.Windows.Media.Animation.DoubleAnimation(0.3, 1, new Duration(TimeSpan.FromSeconds(1)));
            (sender as TabControl).BeginAnimation(Canvas.OpacityProperty, test);
        }


        
    }
}
