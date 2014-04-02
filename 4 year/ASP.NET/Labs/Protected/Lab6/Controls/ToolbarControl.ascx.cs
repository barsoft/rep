using Labs.Entities.DataClasses;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Labs.Lab6
{
    public partial class ToolbarControl : System.Web.UI.UserControl
    {
        private ProductsControl ProductsControl
        {
            get { return (ProductsControl)Page.FindControl("ProductsControl"); }
        }

        private ShoppingCartControl ShoppingCartControl
        {
            get { return (ShoppingCartControl)Page.FindControl("ShoppingCartControl"); }
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            Update();

            if (ProductsControl != null)
                ProductsControl.OnUpdate += ProductsControl_OnUpdate;

            if (ShoppingCartControl != null)
                ShoppingCartControl.OnUpdate += ShoppingCartControl_OnUpdate;
        }

        void ShoppingCartControl_OnUpdate(object source, EventArgs e)
        {
            Update();
        }

        void ProductsControl_OnUpdate(object source, EventArgs e)
        {
            Update();
        }

        public void Update()
        {
            List<Product> OrderedProducts = Session["Products"] as List<Product>;

            LabelCountOrders.Text = "";

            if (OrderedProducts != null && OrderedProducts.Count > 0)
                LabelCountOrders.Text = "(" + OrderedProducts.Count.ToString() + ")";

            if (ProductsControl != null && ProductsControl.OrderedProducts.Count > 0)
                LabelCountOrders.Text = "(" + ProductsControl.OrderedProducts.Count.ToString() + ")";
        }

        protected void ShoppingCartLinkButton_Click(object sender, EventArgs e)
        {
            Response.Redirect("ShoppingCartForm.aspx");
        }

        protected void OrdersHistoryLinkButton_Click(object sender, EventArgs e)
        {
            Response.Redirect("OrdersHistoryForm.aspx");
        }

    }
}