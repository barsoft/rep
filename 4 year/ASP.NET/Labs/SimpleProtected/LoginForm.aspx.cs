using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Labs.Lab3
{
    public partial class LoginForm : System.Web.UI.Page
    {
        protected override void OnInit(EventArgs e)
        {
            base.OnInit(e);
            if (HttpContext.Current.User.Identity.IsAuthenticated)
            {
                string[] s = Roles.GetRolesForUser(HttpContext.Current.User.Identity.Name);

                String returnUrl = Request["ReturnUrl"];

                if (!HttpContext.Current.User.IsInRole("User"))
                {
                    Response.Redirect("/SimpleProtected/AccessDeniedForm.aspx", true);
                }
                else if (!String.IsNullOrEmpty(returnUrl))
                {
                    Response.Redirect(returnUrl, false);
                }
                else
                {
                    Response.Redirect("Lab3/MainForm.aspx");
                }
            }
        }

        protected override void OnLoad(EventArgs e)
        {
            base.OnLoad(e);

            HeaderControl hc = Master.FindControl("HeaderControl") as HeaderControl;
            hc.Title = "Simple Protected";
            hc.Href = "MainForm.aspx";
        }

        protected void Ok_Click(object sender, EventArgs e)
        {
            FormsAuthenticationTicket ticket = new FormsAuthenticationTicket(1, UserName.Text, DateTime.Now, DateTime.Now.AddMinutes(50),
                RememberMe.Checked, "user", FormsAuthentication.FormsCookiePath);
            string hashCookies = FormsAuthentication.Encrypt(ticket);
            HttpCookie cookie = new HttpCookie(FormsAuthentication.FormsCookieName, hashCookies);
            Response.Cookies.Add(cookie);

            if (!Roles.RoleExists("User"))
            {
                Roles.CreateRole("User");
            }

            if (!Roles.IsUserInRole(UserName.Text, "User"))
            {
                Roles.AddUserToRole(UserName.Text, "User");
            }

            FormsAuthentication.RedirectFromLoginPage(UserName.Text, RememberMe.Checked);
        }
    }
}