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

    public partial class ProductsDataForm : UserControl
    {
        MainWindow MainWindow { get; set; }
        public ProductsDataForm()
        {
            InitializeComponent();

            MainWindow = (MainWindow)Application.Current.MainWindow;

            this.Loaded += ProductsDataForm_Loaded;
        }

        void ProductsDataForm_Loaded(object sender, RoutedEventArgs e)
        {
            if (MainWindow != null)
            {
                if (MainWindow.productsDataGrid != null)
                {
                    MainWindow.productsDataGrid.Loaded += productsDataGrid_Loaded;
                    MainWindow.productsDataGrid.SelectionChanged += productsDataGrid_SelectionChanged;
                }
            }
        }

        void productsDataGrid_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            Update();
        }

        void productsDataGrid_Loaded(object sender, RoutedEventArgs e)
        {
            Update();
        }


        public void Update()
        {
            int selectedItemsCount = MainWindow.productsDataGrid.SelectedItems.Count;
            if (selectedItemsCount == 0)
            {
                nameTextBox.Text = "";
                typeTextBox.Text = "";
                manufacturerComboBox.SelectedValue = null;
                pricePerInstanceTextBox.Text = "";
                
                add_button.IsEnabled = true;
                delete_button.IsEnabled = false;
                edit_button.IsEnabled = false;
            }
            if (selectedItemsCount == 1)
            {
                Object obj = MainWindow.productsDataGrid.SelectedItems[0];

                typeTextBox.Text = (obj as DataRowView).Row.ItemArray[1].ToString().Trim();
                manufacturerComboBox.SelectedValue = (obj as DataRowView).Row.ItemArray[2].ToString().Trim();
                nameTextBox.Text = (obj as DataRowView).Row.ItemArray[3].ToString().Trim();
                pricePerInstanceTextBox.Text = (obj as DataRowView).Row.ItemArray[4].ToString().Trim();
  

                add_button.IsEnabled = true;
                delete_button.IsEnabled = true;
                edit_button.IsEnabled = true;
            }
            if (selectedItemsCount > 1)
            {
                nameTextBox.Text = "";
                typeTextBox.Text = "";
                manufacturerComboBox.SelectedValue = null;
                pricePerInstanceTextBox.Text = "";
                
                add_button.IsEnabled = true;
                delete_button.IsEnabled = true;
                edit_button.IsEnabled = false;
            }
            UpdateCombo();
        }

        private void Add_Button_Click(object sender, RoutedEventArgs e)
        {
            String name = nameTextBox.Text.Trim(); 
            String type = typeTextBox.Text.Trim();
            Int32 manufacturer_id = 0;
            float pricePerInstance = 0;

            try
            {
                manufacturer_id = (Int32)manufacturerComboBox.SelectedValue;
            }
            catch (NullReferenceException ex)
            {
                MessageBox.Show("Fields should not be empty!", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                return;
            }
            try
            {
                pricePerInstance = float.Parse(pricePerInstanceTextBox.Text.Trim());
            }
            catch (FormatException ex)
            {
                MessageBox.Show("Error data format!", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                return;
            }

            if (type == "" || name == "")
            {
                MessageBox.Show("Fields should not be empty!", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
            else
            {
                String tmp = "insert_products";
                tmp += " " + name;
                tmp += " " + type;
                tmp += " " + manufacturer_id;
                tmp += " " + pricePerInstance;

                MainWindow.Client.SendAsync(tmp);
            }
            Update();
        }

        private void Delete_Button_Click(object sender, RoutedEventArgs e)
        {
            int selectedItemsCount = MainWindow.productsDataGrid.SelectedItems.Count;
            List<Object> objectsToDelete = new List<Object>();
            for (int i = 0; i < selectedItemsCount; i++)
            {
                Object obj = MainWindow.productsDataGrid.SelectedItems[i];
                objectsToDelete.Add(obj);

            }
            foreach (Object obj in objectsToDelete)
            {
                String tmp = "delete_products";
                tmp += " " + (obj as DataRowView).Row.ItemArray[0].ToString();
                MainWindow.Client.SendAsync(tmp);
            }
            Update();
        }

        private void Edit_Button_Click(object sender, RoutedEventArgs e)
        {
            String name = nameTextBox.Text.Trim(); ;
            Int32 manufacturer_id = 0;
            String type = typeTextBox.Text.Trim();
            float pricePerInstance = 0;


            try
            {
                manufacturer_id = (Int32)manufacturerComboBox.SelectedValue;
            }
            catch (NullReferenceException ex)
            {
                MessageBox.Show("Fields should not be empty!", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                return;
            }
            try
            {
                pricePerInstance = float.Parse(pricePerInstanceTextBox.Text.Trim());
            }
            catch (FormatException ex)
            {
                MessageBox.Show("Error data format!", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                return;
            }

            if (type == "" || name == "")
            {
                MessageBox.Show("Fields should not be empty!", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }

            Object obj = MainWindow.productsDataGrid.SelectedItems[0];
            String tmp = "update_products";
            tmp += " " + name;
            tmp += " " + type;
            tmp += " " + manufacturer_id;
            tmp += " " + pricePerInstance;
            tmp += " " + (obj as DataRowView).Row.ItemArray[0].ToString().Trim();

            MainWindow.Client.SendAsync(tmp);

            Update();
        }

        private void Search_Button_Click(object sender, RoutedEventArgs e)
        {
            /* String name = nameTextBox.Text.Trim();
             String contact_person = contactPersonTextBox.Text.Trim();
             String contact_number = contactNumberTextBox.Text.Trim();
             String office_adress = officeAdressTextBox.Text.Trim();



             IEnumerable tmp1 = (from manufacturer in MainWindow.StoreEntities.Manufacturers
                                 where (manufacturer.name.Contains(name) && manufacturer.office_adress.Contains(office_adress)
                                 && manufacturer.contact_number.Contains(contact_number) && manufacturer.contact_person.Contains(contact_person))
                                 select manufacturer);

             ICollectionView t = CollectionViewSource.GetDefaultView(MainWindow.manufacturersDataGrid.ItemsSource);


             MainWindow.manufacturersDataGrid.ItemsSource = (IEnumerable)tmp1;*/
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

    }
}
