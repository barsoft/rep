using Labs.Lab3.Controls;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Labs.SimpleProtected.Lab3
{
    public partial class ShoppingCartForm : System.Web.UI.Page
    {
        protected override void OnInit(EventArgs e)
        {
            base.OnInit(e);
            if (PreviousPage != null)
            {
                if (PreviousPage.IsCrossPagePostBack == true)
                {
                    ShoppingCartControl.OrderedProducts = ProductsControl.OrderedProducts;
                    ShoppingCartControl.Bind();
                }
            }
        }
        protected override void OnLoad(EventArgs e)
        {
            base.OnLoad(e);

            HeaderControl hc = Master.FindControl("HeaderControl") as HeaderControl;
            hc.Title = "Lab3";
            hc.Href = "MainForm.aspx";
        }

        private ProductsControl ProductsControl
        {
            get
            {
                Control placeHolder = PreviousPage.Master.FindControl("body");
                ProductsControl pc = placeHolder.FindControl("ProductsControl") as ProductsControl;
                return pc;
            }
        }
    }
}