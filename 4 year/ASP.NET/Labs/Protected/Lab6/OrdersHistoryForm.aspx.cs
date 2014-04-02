using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Labs.Entities.DataClasses;
using Microsoft.Reporting.WebForms;

namespace Labs.Lab6
{
    public partial class OrdersHistoryForm : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            ReportViewer1.LocalReport.ReportPath = "Report1.rdlc";

            //this.ReportViewer1.LocalReport.data
            //this.ReportViewer1.;
        }

    }
}