using Labs.Entities.DataClasses;
using Labs.Lab4;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Labs.Lab4
{
    public partial class ToolbarControl : System.Web.UI.UserControl
    {
        private ProductsControl ProductsControl
        {
            get
            {
                Control placeHolder = Page.Master.FindControl("body");
                return placeHolder.FindControl("ProductsControl") as ProductsControl;
            }
        }

        private ShoppingCartControl ShoppingCartControl
        {

            get
            {
                Control placeHolder = Page.Master.FindControl("body");
                return placeHolder.FindControl("ShoppingCartControl") as ShoppingCartControl;
            }
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

    }
}