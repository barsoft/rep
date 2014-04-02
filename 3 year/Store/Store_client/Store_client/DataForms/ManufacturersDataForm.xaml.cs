using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
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
    public partial class ManufacturersDataForm : UserControl
    {
        MainWindow MainWindow { get; set; }
        public ManufacturersDataForm()
        {
            InitializeComponent();

            MainWindow = (MainWindow)Application.Current.MainWindow;

            this.Loaded += ManufacturersDataForm_Loaded;
        }

        void ManufacturersDataForm_Loaded(object sender, RoutedEventArgs e)
        {
            if (MainWindow != null)
            {
                if (MainWindow.manufacturersDataGrid != null)
                {
                    MainWindow.manufacturersDataGrid.Loaded += manufacturersDataGrid_Loaded;
                    MainWindow.manufacturersDataGrid.SelectionChanged += manufacturersDataGrid_SelectionChanged;
                }
            }
        }

        void manufacturersDataGrid_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            Update();
        }

        void manufacturersDataGrid_Loaded(object sender, RoutedEventArgs e)
        {
            Update();
        }


        public void Update()
        {
            if (MainWindow.manufacturersDataGrid.SelectedItems == null)
            {
                nameTextBox.Text = "";
                contactNumberTextBox.Text = "";
                contactPersonTextBox.Text = "";
                officeAdressTextBox.Text = "";
                add_button.IsEnabled = true;
                delete_button.IsEnabled = false;
                edit_button.IsEnabled = false;
            }
            else
            {
                int selectedItemsCount = MainWindow.manufacturersDataGrid.SelectedItems.Count;
                if (selectedItemsCount == 0)
                {
                    nameTextBox.Text = "";
                    contactNumberTextBox.Text = "";
                    contactPersonTextBox.Text = "";
                    officeAdressTextBox.Text = "";
                    add_button.IsEnabled = true;
                    delete_button.IsEnabled = false;
                    edit_button.IsEnabled = false;
                }
                if (selectedItemsCount == 1)
                {
                    Object obj = MainWindow.manufacturersDataGrid.SelectedItems[0];
                    nameTextBox.Text = (obj as DataRowView).Row.ItemArray[1].ToString().Trim();
                    contactNumberTextBox.Text = (obj as DataRowView).Row.ItemArray[2].ToString().Trim();
                    contactPersonTextBox.Text = (obj as DataRowView).Row.ItemArray[3].ToString().Trim();
                    officeAdressTextBox.Text = (obj as DataRowView).Row.ItemArray[4].ToString().Trim();

                    add_button.IsEnabled = true;
                    delete_button.IsEnabled = true;
                    edit_button.IsEnabled = true;
                }
                if (selectedItemsCount > 1)
                {
                    nameTextBox.Text = "";
                    contactNumberTextBox.Text = "";
                    contactPersonTextBox.Text = "";
                    officeAdressTextBox.Text = "";
                    add_button.IsEnabled = true;
                    delete_button.IsEnabled = true;
                    edit_button.IsEnabled = false;
                }
            }
        }

        private void Add_Button_Click(object sender, RoutedEventArgs e)
        {
            String name = nameTextBox.Text.Trim();
            String contact_person = contactPersonTextBox.Text.Trim();
            String contact_number = contactNumberTextBox.Text.Trim();
            String office_Adress = officeAdressTextBox.Text.Trim();
            if (name == "" || contact_number == "" || contact_person == "" || office_Adress == "")
            {
                MessageBox.Show("Fields should not be empty!", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
            else
            {
                String tmp = "insert_manufacturers";
                tmp += " " + name;
                tmp += " " + contact_number;
                tmp += " " + contact_person;
                tmp += " " + office_Adress;

                MainWindow.Client.SendAsync(tmp);
            }
            Update();
        }

        private void Delete_Button_Click(object sender, RoutedEventArgs e)
        {
            int selectedItemsCount = MainWindow.manufacturersDataGrid.SelectedItems.Count;
            List<Object> objectsToDelete = new List<Object>();
            for (int i = 0; i < selectedItemsCount; i++)
            {
                Object obj = MainWindow.manufacturersDataGrid.SelectedItems[i];
                objectsToDelete.Add(obj);

            }
            foreach (Object obj in objectsToDelete)
            {
                String tmp = "delete_manufacturers";
                tmp += " " + (obj as DataRowView).Row.ItemArray[0].ToString();
                MainWindow.Client.SendAsync(tmp);
            }
            Update();
        }

        private void Edit_Button_Click(object sender, RoutedEventArgs e)
        {
            String name = nameTextBox.Text.Trim();
            String contact_person = contactPersonTextBox.Text.Trim();
            String contact_number = contactNumberTextBox.Text.Trim();
            String office_adress = officeAdressTextBox.Text.Trim();
            if (name == "" || contact_number == "" || contact_person == "" || office_adress == "")
            {
                MessageBox.Show("Fields should not be empty!", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
            Object obj = MainWindow.manufacturersDataGrid.SelectedItems[0];
            String tmp = "update_manufacturers";
            tmp += " " + name;
            tmp += " " + contact_number;
            tmp += " " + contact_person;
            tmp += " " + office_adress;
            tmp += " " + (obj as DataRowView).Row.ItemArray[0].ToString().Trim();


            MainWindow.Client.SendAsync(tmp);
            Update();
        }

        private void Search_Button_Click(object sender, RoutedEventArgs e)
        {

        }
    }
}
