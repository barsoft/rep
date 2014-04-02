using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Web;
using System.Web.SessionState;
using System.Xml.Serialization;

namespace DragWebSite
{
    [Serializable]
    public class User
    {
        public String username { get; set; }
        public String password { get; set; }
        public String email { get; set; }
        public String name { get; set; }
        public String surname { get; set; }
        public String adress { get; set; }
        public String age { get; set; }
        public Int64 id { get; set; }

        public override string ToString()
        {
            return username;
        }
    }

    [Serializable]
    public class Statistics
    {
        public String victories { get; set; }
        public String loses { get; set; }
        public String totalscore { get; set; }
        public Int64 id { get; set; }
        public Int64 userid { get; set; }
    }

    public class AsynchronousClient
    {
        private String response = String.Empty;
        private string serverIP = "192.168.0.100";
        private const int port = 11000;
        private HttpSessionState session;

        public User CurrentUser
        {
            get
            {
                return session["curUser"] as User;
            }
            set
            {
                session["curUser"] = value;
            }
        }
        public AsynchronousClient(HttpSessionState session)
        {
            this.session = session;
        }

        public int GetOnlinePlayersCount()
        {
            SendRequest("get_online_users_count<EOF>");
            return Int32.Parse(response.Replace("<EOF>", ""));
        }

        public bool Login(String userName, String password)
        {
            SendRequest("login_site " + userName + "," + password + "<EOF>");
            try
            {
                XmlSerializer ser = new XmlSerializer(typeof(User));
                User user;
                using (StringReader writer = new StringReader(response.Replace("<EOF>", "")))
                {
                    user = ser.Deserialize(writer) as User;
                }

                CurrentUser = user;
            }
            catch (Exception e)
            {
                return false;
            }

            return true;
        }

        public void AddUser(User user)
        {
            SendRequest("add_user " + user.username + "," + user.password + "," + user.email + "," +
                user.name + "," + user.surname + "," + user.adress + "," + user.age + "<EOF>");
        }

        public Statistics GetStatistics(long id)
        {
            SendRequest("get_statistics "+ id + "<EOF>");

            XmlSerializer ser = new XmlSerializer(typeof(Statistics));
            Statistics stats;
            using (StringReader writer = new StringReader(response.Replace("<EOF>", "")))
            {
                stats = ser.Deserialize(writer) as Statistics;
            }
            return stats;
        }

        ///
        private String SendRequest(String content)
        {
            IPAddress ipAddress = IPAddress.Parse(serverIP); //ipHostInfo.AddressList[0];
            //  remoteEP = new IPEndPoint(ipAddress, port);

            TcpClient tcpClient = new TcpClient();
            // tcpClient.Connect(remoteEP);

            IAsyncResult ar = tcpClient.BeginConnect(ipAddress, port, null, null);
            System.Threading.WaitHandle wh = ar.AsyncWaitHandle;


            if (!ar.AsyncWaitHandle.WaitOne(TimeSpan.FromSeconds(5), false))
            {
                tcpClient.Close();
                throw new TimeoutException();
            }

            tcpClient.EndConnect(ar);


            byte[] msg = Encoding.UTF8.GetBytes(content);

            int bytesSent = tcpClient.Client.Send(msg);

            Console.WriteLine("Request sent : {0}", content);

            byte[] buffer = new byte[10240];

            int bytesRec = tcpClient.Client.Receive(buffer);

            response = Encoding.ASCII.GetString(buffer, 0, bytesRec);

            tcpClient.Close();

            Console.WriteLine("Response received : {0}", response);
            return response;

        }





       
    }
}