using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Labs.Protected
{
    public partial class AccessDeniedForm : System.Web.UI.Page
    {
        protected override void OnLoad(EventArgs e)
        {
            base.OnLoad(e);

            HeaderControl hc = Master.FindControl("HeaderControl") as HeaderControl;
            hc.Title = "Protected";
            hc.Href = "/";
        }
    }
}