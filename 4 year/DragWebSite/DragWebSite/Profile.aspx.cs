using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DragWebSite
{
    public partial class Profile : System.Web.UI.Page
    {
        public AsynchronousClient client;
        protected void Page_Load(object sender, EventArgs e)
        {
            client = new AsynchronousClient(Session);

            if (client.CurrentUser != null)
            {
                UserNameLabel.Text = client.CurrentUser.username;
                EmailLabel.Text = client.CurrentUser.email;
                NameLabel.Text = client.CurrentUser.name;
                SurnameLabel.Text = client.CurrentUser.surname;
                AgeLabel.Text = client.CurrentUser.age;
                AdressLabel.Text = client.CurrentUser.adress;
            }
        }
    }
}