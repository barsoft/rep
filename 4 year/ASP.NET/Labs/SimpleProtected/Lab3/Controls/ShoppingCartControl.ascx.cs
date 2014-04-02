using Labs.Entities;
using Labs.Entities.Simple;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Labs.Lab3.Controls
{
    public partial class ShoppingCartControl : System.Web.UI.UserControl
    {
        public List<Product> OrderedProducts
        {
            get
            {
                if (ViewState["OrderedProducts"] == null) ViewState["OrderedProducts"] = new List<Product>();
                return ViewState["OrderedProducts"] as List<Product>;
            }
            set
            {
                ViewState["OrderedProducts"] = value;
            }
        }

        public void Bind()
        {
            GridViewOrder.DataSource = OrderedProducts;
            GridViewOrder.DataBind();

            CalcTotal();
        }

        protected void GridViewOrder_RowDeleting(object sender, GridViewDeleteEventArgs e)
        {
            OrderedProducts.RemoveAt(e.RowIndex);
            GridViewOrder.DataSource = OrderedProducts;
            GridViewOrder.DataBind();

            CalcTotal();
        }


        private void CalcTotal()
        {
            int sum = 0;
            foreach (Product product in OrderedProducts)
            {
                sum += product.Price;
            }
            TotalLabel.Text = "Total: " + OrderedProducts.Count + " items, Total price:" + sum;
        }
    }
}