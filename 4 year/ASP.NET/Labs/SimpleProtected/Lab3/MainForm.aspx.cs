using Labs.Lab3.Controls;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Labs.SimpleProtected.Lab3
{
    public partial class MainForm : System.Web.UI.Page
    {
        protected override void OnInit(EventArgs e)
        {
            base.OnInit(e);

            CategoriesControl.DataBound += CategoriesControl_DataBound;

            CategoriesControl.CategoryIndexChanged += CategoriesControl_CategoryIndexChanged;

            CategoriesControl.LoadCategories();

            CategoriesControl.Bind();
        }

        protected override void OnLoad(EventArgs e)
        {
            base.OnLoad(e);

            ButtonOrder.Click += Order_Click;

            HeaderControl hc = Master.FindControl("HeaderControl") as HeaderControl;
            hc.Title = "Lab3";
            hc.Href = "MainForm.aspx";
        }

        private void CategoriesControl_CategoryIndexChanged(object sender, EventArgs e)
        {
            ProductsControl.CurrentTableIndex = CategoriesControl.CurrentCategoryIndex;
            LabelCategory.Text = CategoriesControl.Categories[CategoriesControl.CurrentCategoryIndex].Name.ToString();
        }

        private void CategoriesControl_DataBound(object sender, EventArgs e)
        {
            ProductsControl.LoadProducts();
            ProductsControl.BindTablesToCategories(CategoriesControl.Categories);
            CategoriesControl.CurrentCategoryIndex = 0;
        }

        private void Order_Click(object sender, EventArgs e)
        {
            ProductsControl.UpdateOrderedList();
        }
    }
}