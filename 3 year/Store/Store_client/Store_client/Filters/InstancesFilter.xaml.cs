using System;
using System.Collections;
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
using System.Windows.Shapes;

namespace Store_client
{
    /// <summary>
    /// Логика взаимодействия для Filter.xaml
    /// </summary>
    public partial class InstancesFilter : Window
    {
        MainWindow MainWindow { get; set; }
        public InstancesFilter()
        {
            InitializeComponent();
            MainWindow = (MainWindow)Application.Current.MainWindow;
            this.Loaded += InstancesFilter_Loaded;

        }

        void InstancesFilter_Loaded(object sender, RoutedEventArgs e)
        {
            UpdateCombo();

            this.comboBox.SelectedIndex = 0;

            this.productComboBox.SelectedIndex = 0;
        }

        private void DropDownOpened(object sender, EventArgs e)
        {
            UpdateCombo();
        }

        private void UpdateCombo()
        {
            Binding myBinding = new Binding("");
            myBinding.Source = MainWindow.productsDataGrid.ItemsSource;
            productComboBox.DisplayMemberPath = "name";
            productComboBox.SelectedValuePath = "id";
            productComboBox.SetBinding(ComboBox.ItemsSourceProperty, myBinding);


        }

        private void Button_filter_Click(object sender, RoutedEventArgs e)
        {
            Int32 product_id = 0;
            Int32 count = 0;
            String deliveryDate = deliveryDatePicker.Text.Trim();
            String exportDate = exportDatePicker.Text.Trim();

            String tmp = "filter_instances";
            try
            {
                product_id = (Int32)productComboBox.SelectedValue;
                tmp += " " + product_id;
            }
            catch 
            {
                tmp += " null";
            }

            try
            {
                count = Int32.Parse(countTextBox.Text.Trim());
                tmp += " " + count;
            }
            catch
            {
                tmp += " null";
            }

            if (deliveryDate.Trim().Equals(""))
            {
                tmp += " null";
            }
            else
            {
                tmp += " " + deliveryDate;
            }

            if (exportDate.Trim().Equals(""))
            {
                tmp += " null";
            }
            else
            {
                tmp += " " + exportDate;
            }

            MainWindow.Client.SendAsync(tmp);

   
            this.MainWindow.tabControl.SelectedItem = MainWindow.tabControl.Items.GetItemAt(3);
        }

        private void DropDownClose(object sender, EventArgs e)
        {
            this.Close();
            if (this.comboBox.SelectedValue == comboBox.Items.GetItemAt(0))
            {
                InstancesFilter filter = new InstancesFilter();
                filter.Owner = MainWindow;
                filter.Left = this.Left;
                filter.Top = this.Top;
                filter.Show();

            }
            if (this.comboBox.SelectedValue == comboBox.Items.GetItemAt(1))
            {
                ProductsFilter filter = new ProductsFilter();
                filter.Owner = MainWindow;
                filter.Left = this.Left;
                filter.Top = this.Top;
                filter.Show();

            }
            if (this.comboBox.SelectedValue == comboBox.Items.GetItemAt(2))
            {
                ManufacturersFilter filter = new ManufacturersFilter();
                filter.Owner = MainWindow;
                filter.Left = this.Left;
                filter.Top = this.Top;
                filter.Show();

            }
        }

        private void load(object sender, RoutedEventArgs e)
        {
            DoubleAnimation test = new System.Windows.Media.Animation.DoubleAnimation(0.8, 1, new Duration(TimeSpan.FromSeconds(1)));
            this.BeginAnimation(Canvas.OpacityProperty, test);
        }
    }
}
