using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Labs.Protected.Lab4
{
    public partial class ShoppingCartForm : System.Web.UI.Page
    {
        protected override void OnLoad(EventArgs e)
        {
            base.OnLoad(e);

            HeaderControl hc = Master.FindControl("HeaderControl") as HeaderControl;
            hc.Title = "Lab4";
            hc.Href = "MainForm.aspx";
        }
    }
}