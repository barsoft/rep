using System;
using System.Collections.Generic;
using System.Data;
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

namespace Store_client
{
    /// <summary>
    /// Логика взаимодействия для DataForm.xaml
    /// </summary>
    public partial class InstancesDataForm : UserControl
    {
        MainWindow MainWindow { get; set; }
        public InstancesDataForm()
        {
            InitializeComponent();
            MainWindow = (MainWindow)Application.Current.MainWindow;

            this.Loaded += InstancesDataForm_Loaded;
        }

        void InstancesDataForm_Loaded(object sender, RoutedEventArgs e)
        {
            if (MainWindow != null)
            {
                if (MainWindow.instancesDataGrid != null)
                {
                    MainWindow.instancesDataGrid.Loaded += instancesDataGrid_Loaded;

                    MainWindow.instancesDataGrid.SelectionChanged += instancesDataGrid_SelectionChanged;
                }
            }
        }

        void instancesDataGrid_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            Update();
        }

        void instancesDataGrid_Loaded(object sender, RoutedEventArgs e)
        {
            Update();
        }


        public void Update()
        {
            int selectedItemsCount = MainWindow.instancesDataGrid.SelectedItems.Count;
            if (selectedItemsCount == 0)
            {
                productComboBox.SelectedValue = null;
                countTextBox.Text = "";
                deliveryDatePicker.SelectedDate = null;
                exportDatePicker.SelectedDate = null;
                add_button.IsEnabled = true;
                delete_button.IsEnabled = false;
                edit_button.IsEnabled = false;
            }
            if (selectedItemsCount == 1)
            {
                Object obj = MainWindow.instancesDataGrid.SelectedItems[0];

                productComboBox.SelectedValue = (obj as DataRowView).Row.ItemArray[1].ToString().Trim();
                countTextBox.Text = (obj as DataRowView).Row.ItemArray[2].ToString().Trim();
                deliveryDatePicker.SelectedDate = DateTime.Parse((obj as DataRowView).Row.ItemArray[3].ToString().Trim());
                exportDatePicker.SelectedDate = DateTime.Parse((obj as DataRowView).Row.ItemArray[4].ToString().Trim());


                add_button.IsEnabled = true;
                delete_button.IsEnabled = true;
                edit_button.IsEnabled = true;
            }
            if (selectedItemsCount > 1)
            {
                productComboBox.SelectedValue = null;
                countTextBox.Text = "";
                deliveryDatePicker.SelectedDate = null;
                exportDatePicker.SelectedDate = null;
                add_button.IsEnabled = true;
                delete_button.IsEnabled = true;
                edit_button.IsEnabled = false;
            }
            UpdateCombo();
        }



        private void Add_Button_Click(object sender, RoutedEventArgs e)
        {
            Int32 product_id = 0;
            Int32 count = 0;
            String deliveryDate = deliveryDatePicker.Text.Trim();
            String exportDate = exportDatePicker.Text.Trim();
            bool presence = false;
            try
            {
                product_id = (Int32)productComboBox.SelectedValue;
            }
            catch (NullReferenceException ex)
            {
                MessageBox.Show("Fields should not be empty!", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                return;
            }
            try
            {
                count = Int32.Parse(countTextBox.Text.Trim());
            }
            catch (FormatException ex)
            {
                MessageBox.Show("Error data format!", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                return;
            }

            if (deliveryDate == "" || exportDate == "")
            {
                MessageBox.Show("Fields should not be empty!", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                return;
            }


            if (deliveryDatePicker.SelectedDate.Value.CompareTo(exportDatePicker.SelectedDate.Value) == 1)
            {
                MessageBox.Show("Delivery date must be earlier than export date!", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                return;
            }
            if (exportDatePicker.SelectedDate.Value.CompareTo(DateTime.Now) == 1 && deliveryDatePicker.SelectedDate.Value.CompareTo(DateTime.Now) == -1)
            {
                presence = true;
            }
            else
            {
                presence = false;
            }


            String tmp = "insert_instances";
            tmp += " " + product_id;
            tmp += " " + count;
            tmp += " " + deliveryDate;
            tmp += " " + exportDate;
            tmp += " " + presence;


            MainWindow.Client.SendAsync(tmp);
            Update();
        }

        private void Delete_Button_Click(object sender, RoutedEventArgs e)
        {
            int selectedItemsCount = MainWindow.instancesDataGrid.SelectedItems.Count;
            List<Object> objectsToDelete = new List<Object>();
            for (int i = 0; i < selectedItemsCount; i++)
            {
                Object obj = MainWindow.instancesDataGrid.SelectedItems[i];
                objectsToDelete.Add(obj);

            }
            foreach (Object obj in objectsToDelete)
            {
                String tmp = "delete_instances";
                tmp += " " + (obj as DataRowView).Row.ItemArray[0].ToString();
                MainWindow.Client.SendAsync(tmp);
            }
            Update();
        }

        private void Edit_Button_Click(object sender, RoutedEventArgs e)
        {
            Int32 product_id = 0;
            Int32 count = 0;
            String deliveryDate = deliveryDatePicker.Text.Trim();
            String exportDate = exportDatePicker.Text.Trim();
            bool presence = false;

            try
            {
                product_id = (Int32)productComboBox.SelectedValue;
            }
            catch (NullReferenceException ex)
            {
                MessageBox.Show("Fields should not be empty!", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                return;
            }
            try
            {
                count = Int32.Parse(countTextBox.Text.Trim());
            }
            catch (FormatException ex)
            {
                MessageBox.Show("Error data format!", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                return;
            }

            if (deliveryDate == "" || exportDate == "")
            {
                MessageBox.Show("Fields should not be empty!", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                return;
            }

            if (deliveryDatePicker.SelectedDate.Value.CompareTo(exportDatePicker.SelectedDate.Value) == 1)
            {
                MessageBox.Show("Delivery date must be earlier than export date!", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                return;
            }

            if (deliveryDatePicker.SelectedDate.Value.CompareTo(exportDatePicker.SelectedDate.Value) == 1)
            {
                MessageBox.Show("Delivery date must be earlier than export date!", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                return;
            }
            if (exportDatePicker.SelectedDate.Value.CompareTo(DateTime.Now) == 1 && deliveryDatePicker.SelectedDate.Value.CompareTo(DateTime.Now) == -1)
            {
                presence = true;
            }
            else
            {
                presence = false;
            }

            Object obj = MainWindow.instancesDataGrid.SelectedItems[0];

            String tmp = "update_instances";
            tmp += " " + product_id;
            tmp += " " + count;
            tmp += " " + deliveryDate;
            tmp += " " + exportDate;
            tmp += " " + presence;
            tmp += " " + (obj as DataRowView).Row.ItemArray[0].ToString().Trim();

            MainWindow.Client.SendAsync(tmp);
            Update();
        }

        private void Search_Button_Click(object sender, RoutedEventArgs e)
        {

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
    }
}
