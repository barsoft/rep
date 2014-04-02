using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Labs.Entities.DataClasses;

namespace Labs.Lab5
{
    public partial class OrdersHistoryForm : System.Web.UI.Page
    {
        protected override void OnLoad(EventArgs e)
        {
            base.OnLoad(e);

            HeaderControl hc = Master.FindControl("HeaderControl") as HeaderControl;
            hc.Title = "Lab5";
            hc.Href = "MainForm.aspx";
        }

        protected void LinqDataSource_Selecting(object sender, LinqDataSourceSelectEventArgs e)
        {
            DataClassesDataContext dataContext = new DataClassesDataContext();
            var results = (from orders in dataContext.Order
                           from products in dataContext.Product
                           where products.Id == orders.ProductID
                           select new { ID = orders.Id, Product = products.Name, Date = orders.Date }).Distinct();
            e.Result = results;
        }
    }
}