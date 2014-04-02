using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DragWebSite
{
    public partial class SignUpForm : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Ok_Click(object sender, EventArgs e)
        {
            String strs = Request["ReturnUrl"];

            AsynchronousClient client = new AsynchronousClient(Session);
            User user = new User();
            user.username = txtAdress.Value;
            user.password = txtUserPass.Value;
            user.email = txtEmail.Value;
            user.name = txtName.Value;
            user.surname = txtSurname.Value;
            user.age = txtAge.Value;
            user.adress = txtAdress.Value;

            client.AddUser(user);
        }
    }
}