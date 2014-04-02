using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Labs.Protected.Lab5
{
    public partial class MainForm : System.Web.UI.Page
    {
        protected override void OnLoad(EventArgs e)
        {
            base.OnLoad(e);
            HeaderControl hc = Master.FindControl("HeaderControl") as HeaderControl;
            hc.Title = "Lab5";
            hc.Href = "MainForm.aspx";


            if (CategoriesControl != null)
            {
                CategoriesControl.CategoryIndexChanged += CategoriesControl_CategoryIndexChanged;
                CategoriesControl.DataBound += CategoriesControl_DataBound;
            }

        }

        private void CategoriesControl_DataBound(object sender, EventArgs e)
        {
            CategoriesControl.CurrentCategoryIndex = 0;
        }

        private void CategoriesControl_CategoryIndexChanged(object sender, EventArgs e)
        {
            ProductsControl.CurrentTableIndex = CategoriesControl.CurrentCategoryIndex;
            LabelCategory.Text = CategoriesControl.GetCategory(CategoriesControl.CurrentCategoryIndex).ToString();
            ProductsControl.Update();
        }
    }
}