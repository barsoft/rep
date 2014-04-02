using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Labs
{
    public partial class HeaderControl : System.Web.UI.UserControl
    {
        private string href = "/";

        public string Href
        {
            get { return href; }
            set { href = value; }
        }

        private bool isHomeLinkVisible = true;

        public bool IsIndexLinkVisible
        {
            get { return isHomeLinkVisible; }
            set { isHomeLinkVisible = value; }
        }

        private string title = "";

        public string Title
        {
            get { return title; }
            set { title = value; }
        }
	
        protected void Page_Load(object sender, EventArgs e)
        {

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

        protected void HeaderLinkButton_Click(object sender, EventArgs e)
        {
            Response.Redirect(href, false);
        }
    }

}