using Labs.Entities.Simple;
using Labs.Lab3.Controls;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Labs.Lab2
{
    public partial class ShoppingCartForm : System.Web.UI.Page
    {
        public List<Product> OrderedProducts
        {
            get
            {
                return ViewState["OrderedProducts"] as List<Product>;
            }
            set
            {
                ViewState["OrderedProducts"] = value;
            }
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                try
                {
                    OrderedProducts = PreviousPage.OrderedProducts;

                    if (OrderedProducts == null)
                    {
                        OrderedProducts = new List<Product>();
                    }

                    GridViewOrder.DataSource = OrderedProducts;
                    GridViewOrder.DataBind();

                    CalcTotal();
                }
                catch (Exception exception)
                {
                    Response.Redirect("MainForm.aspx");
                }
            }
        }

        protected override void OnLoad(EventArgs e)
        {
            base.OnLoad(e);

            HeaderControl hc = Master.FindControl("HeaderControl") as HeaderControl;
            hc.Title = "Lab2";
            hc.Href = "MainForm.aspx";
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