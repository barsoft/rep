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
    public partial class ProductsFilter : Window
    {
        MainWindow MainWindow { get; set; }
        public ProductsFilter()
        {
            InitializeComponent();
            MainWindow = (MainWindow)Application.Current.MainWindow;
            this.Loaded += ProductsFilter_Loaded;
        }

        void ProductsFilter_Loaded(object sender, RoutedEventArgs e)
        {
            UpdateCombo();
            this.comboBox.SelectedValue = comboBox.Items.GetItemAt(1);

            this.manufacturerComboBox.SelectedIndex = 0;
        }

        private void Button_filter_Click(object sender, RoutedEventArgs e)
        {
            String name = nameTextBox.Text.Trim();
            String type = typeTextBox.Text.Trim();
            float pricePerInstance = 0;
            Int32 manufacturer_id = (Int32)manufacturerComboBox.SelectedValue;


            String tmp = "filter_products";


            if (name.Trim().Equals(""))
            {
                tmp += " null";
            }
            else
            {
                tmp += " " + name;
            }
            if (type.Trim().Equals(""))
            {
                tmp += " null";
            }
            else
            {
                tmp += " " + type;
            }

            try
            {
                pricePerInstance = float.Parse(pricePerInstanceTextBox.Text.Trim());
                tmp += " " + pricePerInstance;
            }
            catch
            {
                tmp += " null";
            }

            try
            {
                manufacturer_id = (Int32)manufacturerComboBox.SelectedValue;
                tmp += " " + manufacturer_id;
            }
            catch
            {
                tmp += " null";
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

        private void DropDownOpened(object sender, EventArgs e)
        {
            UpdateCombo();
        }

        private void UpdateCombo()
        {
            Binding myBinding = new Binding("");
            myBinding.Source = MainWindow.manufacturersDataGrid.ItemsSource;
            manufacturerComboBox.DisplayMemberPath = "name";
            manufacturerComboBox.SelectedValuePath = "id";
            manufacturerComboBox.SetBinding(ComboBox.ItemsSourceProperty, myBinding);
        }

        private void load(object sender, RoutedEventArgs e)
        {
            DoubleAnimation test = new System.Windows.Media.Animation.DoubleAnimation(0.8, 1, new Duration(TimeSpan.FromSeconds(1)));
            this.BeginAnimation(Canvas.OpacityProperty, test);
        }
    }
}
