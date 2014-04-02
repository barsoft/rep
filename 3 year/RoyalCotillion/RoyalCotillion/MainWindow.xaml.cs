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
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using Lib;

namespace RoyalCotillion
{
    /// <summary>
    /// Логика взаимодействия для MainWindow.xaml
    /// </summary>
    /// 

    public partial class MainWindow : Window
    {
        Game game;

        public List<Game> states;

        public MainWindow()
        {
            InitializeComponent();
            game = new Game(this);
            states = new List<Game>();
            this.grid.Children.Add(game);

        }


        private void OnNewClick(object sender, RoutedEventArgs e)
        {
            game.Reset();
        }

        private void OnExitClick(object sender, RoutedEventArgs e)
        {
            Application.Current.Shutdown();
        }

        private void OnUndoClick(object sender, RoutedEventArgs e)
        {
            game.Undo();
        }

        private void OnRedoClick(object sender, RoutedEventArgs e)
        {
            game.Redo();
        }

        private void OnHelpClick(object sender, RoutedEventArgs e)
        {
            MessageBox.Show(Lib.Properties.Resources.title);
        }

        
    }
}
