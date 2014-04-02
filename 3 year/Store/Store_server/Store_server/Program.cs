using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace Store_server
{
    [Serializable]
    class Program
    {
        static StoreDataSet db_server;
        static void Main(string[] args)
        {
            db_server = new StoreDataSet();
            int Port = 4505;
            Server srv = new Server(db_server);
            srv.Start(Port);
            Console.ReadKey();
            srv.Stop();
        }
    }
   
}
