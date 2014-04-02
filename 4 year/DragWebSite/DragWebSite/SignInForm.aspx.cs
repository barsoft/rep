using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DragWebSite
{
    public partial class SignInForm : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        private bool ValidateUser(string userName, string passWord)
        {
            AsynchronousClient client = new AsynchronousClient(Session);
            return client.Login(userName, passWord);
        }
        protected void Ok_Click(object sender, EventArgs e)
        {
            String strs = Request["ReturnUrl"];
            if (ValidateUser(txtUserName.Value, txtUserPass.Value))
            {
                string[] arr = new string[] { txtUserName.Value };
                if (!Roles.RoleExists("User"))
                {
                    Roles.CreateRole("User");
                }
                if (!Roles.IsUserInRole(txtUserName.Value, "User"))
                {
                    Roles.AddUsersToRole(arr, "User");
                }
                FormsAuthentication.RedirectFromLoginPage(txtUserName.Value,
                    chkPersistCookie.Checked);
            }
            else
            {
                Response.Redirect("Index.aspx", true);
            }
        }
    }
}