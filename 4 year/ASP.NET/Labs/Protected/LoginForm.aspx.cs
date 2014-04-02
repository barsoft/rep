using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Labs.Protected
{
    public partial class LoginForm : System.Web.UI.Page
    {
        protected override void OnInit(EventArgs e)
        {
            base.OnInit(e);
            if (HttpContext.Current.User.Identity.IsAuthenticated)
            {
                string[] s = Roles.GetRolesForUser(HttpContext.Current.User.Identity.Name);
                if (!HttpContext.Current.User.IsInRole("Admin"))
                {
                    Response.Redirect("/Protected/AccessDeniedForm.aspx", true);
                }
                else Response.Redirect("/", true);
            }
        }

        protected override void OnLoad(EventArgs e)
        {
            base.OnLoad(e);

            HeaderControl hc = Master.FindControl("HeaderControl") as HeaderControl;
            hc.Title = "Protected";
            hc.Href = "MainForm.aspx";
        }

        private bool ValidateUser(string userName, string passWord)
        {
            SqlConnection conn;
            SqlCommand cmd;
            string lookupPassword = null;

            // Check for invalid userName.
            // userName must not be null and must be between 1 and 15 characters.
            if ((null == userName) || (0 == userName.Length) || (userName.Length > 15))
            {
                System.Diagnostics.Trace.WriteLine("[ValidateUser] Input validation of userName failed.");
                return false;
            }

            // Check for invalid passWord.
            // passWord must not be null and must be between 1 and 25 characters.
            if ((null == passWord) || (0 == passWord.Length) || (passWord.Length > 25))
            {
                System.Diagnostics.Trace.WriteLine("[ValidateUser] Input validation of passWord failed.");
                return false;
            }

            try
            {
                // Consult with your SQL Server administrator for an appropriate connection
                // string to use to connect to your local SQL Server.
                conn = new SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename=|DataDirectory|\Products.mdf;Integrated Security=True");
                conn.Open();

                // Create SqlCommand to select pwd field from users table given supplied userName.
                cmd = new SqlCommand("Select pwd from [dbo].[User] where uname=@userName", conn);
                cmd.Parameters.Add("@userName", SqlDbType.VarChar, 25);
                cmd.Parameters["@userName"].Value = userName;

                // Execute command and fetch pwd field into lookupPassword string.
                lookupPassword = (string)cmd.ExecuteScalar();

                // Cleanup command and connection objects.
                cmd.Dispose();
                conn.Dispose();
            }
            catch (Exception ex)
            {
                // Add error handling here for debugging.
                // This error message should not be sent back to the caller.
                System.Diagnostics.Trace.WriteLine("[ValidateUser] Exception " + ex.Message);
            }

            // If no password found, return false.
            if (lookupPassword == null)
            {
                // You could write failed login attempts here to event log for additional security.
                return false;
            }

            // Compare lookupPassword and input passWord, using a case-sensitive comparison.
            return (0 == string.Compare(lookupPassword, passWord, false));

        }

        protected void Ok_Click(object sender, EventArgs e)
        {
            String strs = Request["ReturnUrl"];
            Validate();
            if (Page.IsValid)
            {

                if (ValidateUser(txtUserName.Value, txtUserPass.Value))
                {
                    string[] arr = new string[] { txtUserName.Value };
                    if (!Roles.RoleExists("Admin"))
                    {
                        Roles.CreateRole("Admin");
                    }
                    if (!Roles.IsUserInRole(txtUserName.Value, "Admin"))
                    {
                        Roles.AddUsersToRole(arr, "Admin");
                    }
                    FormsAuthentication.RedirectFromLoginPage(txtUserName.Value,
                        chkPersistCookie.Checked);
                }
                return;
            }

          //  Response.Redirect("LoginForm.aspx?ReturnUrl=" + strs, true);
        }

        protected void CustomValidator1_ServerValidate(object source, ServerValidateEventArgs args)
        {
            if (txtUserName.Value.Length > 10 || txtUserPass.Value.Length > 10)
            {
                args.IsValid = false;
                return;
            }
            args.IsValid = true;
        }
    }
}