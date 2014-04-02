using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DragWebSite
{
    public partial class HeaderControl : System.Web.UI.UserControl
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            AsynchronousClient client = new AsynchronousClient(Session);

            Label1.Text = client.GetOnlinePlayersCount().ToString();
        }

          

        protected void HeaderLinkButton_Click(object sender, EventArgs e)
        {
            Response.Redirect("/Index.aspx");
        }

        protected void LogoutLinkButton_Click(object sender, EventArgs e)
        {
            HttpCookie rFormsCookie = new HttpCookie(FormsAuthentication.FormsCookieName, "");
            rFormsCookie.Expires = DateTime.Now.AddYears(-1);
            Page.Response.Cookies.Add(rFormsCookie);
            Response.Redirect("/", false);

            Session.RemoveAll();
            Session.Abandon();
        }

        protected void SignInLinkButton_Click(object sender, EventArgs e)
        {
            Response.Redirect("SignInForm.aspx", false);
        }

        protected void SignUpLinkButton_Click(object sender, EventArgs e)
        {
            Response.Redirect("SignUpForm.aspx", false);
        }

        protected void ProfileLinkButton_Click(object sender, EventArgs e)
        {
            Response.Redirect("Profile.aspx", false);
        }

        protected void StatisticsLinkButton_Click(object sender, EventArgs e)
        {
            Response.Redirect("StatisticsForm.aspx", false);
        }
    }
}