using System;
using System.Windows.Forms;

namespace MapEditor
{
    static class Program
    {
        /// <summary> 
        /// The main entry point for the application. 
        /// </summary> 
        [STAThreadAttribute]
        static void Main(string[] args)
        {
            
           // Form1 form = new Form1();
            //form.Show();
            Game1 game = new Game1();
            game.Run();
        }

    }
}