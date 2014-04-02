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
    public partial class ManufacturersFilter : Window
    {
        MainWindow MainWindow { get; set; }
        public ManufacturersFilter()
        {
            InitializeComponent();
            this.Loaded += ManufacturersFilter_Loaded;
            MainWindow = (MainWindow)Application.Current.MainWindow;
        }

        void ManufacturersFilter_Loaded(object sender, RoutedEventArgs e)
        {
            this.comboBox.SelectedValue = comboBox.Items.GetItemAt(2);
        }


        private void Button_filter_Click(object sender, RoutedEventArgs e)
        {
            String name = nameTextBox.Text.Trim();
            String contact_person = contactPersonTextBox.Text.Trim();
            String contact_number = contactNumberTextBox.Text.Trim();
            String office_adress = officeAdressTextBox.Text.Trim();

            String tmp = "filter_manufacturers";

            if (name.Trim().Equals(""))
            {
                tmp += " null";
            }
            else
            {
                tmp += " " + name;
            }
            if (contact_person.Trim().Equals(""))
            {
                tmp += " null";
            }
            else
            {
                tmp += " " + contact_person;
            }
            if (contact_number.Trim().Equals(""))
            {
                tmp += " null";
            }
            else
            {
                tmp += " " + contact_number;
            }
            if (office_adress.Trim().Equals(""))
            {
                tmp += " null";
            }
            else
            {
                tmp += " " + office_adress;
            }

            tmp += " " + name;
            tmp += " " + contact_person;
            tmp += " " + contact_number;
            tmp += " " + office_adress;
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
