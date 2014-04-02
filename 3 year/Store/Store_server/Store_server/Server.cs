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
    public class Server
    {
        private Socket Sock;
        private SocketAsyncEventArgs AcceptAsyncArgs;
        StoreDataSet db_server;
        StreamWriter log;
        public Server(StoreDataSet db_server)
        {
            this.db_server = db_server;
            Sock = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            AcceptAsyncArgs = new SocketAsyncEventArgs();
            AcceptAsyncArgs.Completed += AcceptCompleted;
        }
        public void Start(int Port)
        {
            Sock.Bind(new IPEndPoint(IPAddress.Any, Port));
            Console.WriteLine("Server started on port {0},IP {1}. Press any key to stop...", Port, Dns.GetHostByName(Dns.GetHostName()).AddressList[0].ToString());
            Sock.Listen(50);
            AcceptAsync(AcceptAsyncArgs);
        }
        private void AcceptAsync(SocketAsyncEventArgs e)
        {
            bool willRaiseEvent = Sock.AcceptAsync(e);
            if (!willRaiseEvent)
                AcceptCompleted(Sock, e);
        }
        private void AcceptCompleted(object sender, SocketAsyncEventArgs e)
        {
            if (e.SocketError == SocketError.Success)
            {
                ClientConnection Client = new ClientConnection(e.AcceptSocket, db_server, log);
            }
            e.AcceptSocket = null;
            AcceptAsync(AcceptAsyncArgs);
        }
        public void Stop()
        {
            Sock.Close();
        }
    }
}
