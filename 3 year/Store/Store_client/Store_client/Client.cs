using System;
using System.Collections.Generic;
using System.Data;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Xml;

namespace Store_client
{
    public class Client
    {
        private Socket Sock;
        private SocketAsyncEventArgs SockAsyncArgs;
        private byte[] buff;
        private MainWindow mainWindow;
        public Client(MainWindow mainWindow)
        {
            this.mainWindow = mainWindow;
            buff = new byte[30024];
            Sock = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            SockAsyncArgs = new SocketAsyncEventArgs();
            SockAsyncArgs.Completed += SockAsyncArgs_Completed;
        }
        void SockAsyncArgs_Completed(object sender, SocketAsyncEventArgs e)
        {
            switch (e.LastOperation)
            {
                case SocketAsyncOperation.Connect:
                    ProcessConnect(e);
                    break;
                case SocketAsyncOperation.Receive:
                    ProcessReceive(e);
                    break;
                case SocketAsyncOperation.Send:
                    ProcessSend(e);
                    break;
            }
        }
        public void ConnectAsync(string Address, int Port)
        {
            SockAsyncArgs.RemoteEndPoint = new DnsEndPoint(Address, Port);
            ConnectAsync(SockAsyncArgs);
        }
        private void ConnectAsync(SocketAsyncEventArgs e)
        {
            bool willRaiseEvent = Sock.ConnectAsync(e);
            if (!willRaiseEvent)
                ProcessConnect(e);
        }
        private void ProcessConnect(SocketAsyncEventArgs e)
        {
            if (e.SocketError == SocketError.Success)
            {
                SockAsyncArgs.SetBuffer(buff, 0, buff.Length);
            }
            else
            {
                MessageBox.Show("Could not connect to " + e.RemoteEndPoint.ToString() + "");
            }
        }
        public void SendAsync(string data)
        {
            if (Sock.Connected && data.Length > 0)
            {
                byte[] buff = Encoding.UTF8.GetBytes(data);
                SocketAsyncEventArgs e = new SocketAsyncEventArgs();
                e.SetBuffer(buff, 0, buff.Length);
                e.Completed += SockAsyncArgs_Completed;
                SendAsync(e);
            }
        }
        private void SendAsync(SocketAsyncEventArgs e)
        {
            bool willRaiseEvent = Sock.SendAsync(e);
            if (!willRaiseEvent)
                ProcessSend(e);
        }
        private void ProcessSend(SocketAsyncEventArgs e)
        {
            if (e.SocketError == SocketError.Success)
            {
                ReceiveAsync(SockAsyncArgs);
            }
            else
            {
                MessageBox.Show("Connection lost. Could not send data to server.");
            }
        }
        private void ReceiveAsync(SocketAsyncEventArgs e)
        {
            bool willRaiseEvent = Sock.ReceiveAsync(e);
            if (!willRaiseEvent)
                ProcessReceive(e);
        }
        private void ProcessReceive(SocketAsyncEventArgs e)
        {
            if (e.SocketError == SocketError.Success)
            {
                string str = Encoding.UTF8.GetString(e.Buffer, 0, e.BytesTransferred);
                DataSet tables = new DataSet("store");
                MemoryStream inmem = new MemoryStream(e.Buffer);
                try
                {
                    tables.ReadXml(inmem, XmlReadMode.ReadSchema);
                }
                catch (XmlException exception)
                {
                    //MessageBox.Show("Error reading xml! " + exception.Message);
                }
                mainWindow.Dispatcher.BeginInvoke(new Action(delegate
                {
                    if (str.Contains("Instances"))
                        mainWindow.instancesDataGrid.ItemsSource = tables.Tables["Instances"].DefaultView;
                    if (str.Contains("Manufacturers"))
                        mainWindow.manufacturersDataGrid.ItemsSource = tables.Tables["Manufacturers"].DefaultView;
                    if (str.Contains("Products"))
                        mainWindow.productsDataGrid.ItemsSource = tables.Tables["Products"].DefaultView;
                  
                    if (str.Contains("result_query"))
                    {
                        mainWindow.resultsDataGrid.ItemsSource = tables.Tables["result_query"].DefaultView;
                    }
                }));

            }
            else
            {
                MessageBox.Show("Connection lost. Could not recieve data from server.");
            }
        }
    }
}
