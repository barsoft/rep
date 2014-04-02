using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Labs.Lab6
{
    public partial class CategoriesControl : System.Web.UI.UserControl
    {
        private ProductsControl ProductsControl
        {
            get { return (ProductsControl)Page.FindControl("ProductsControl"); }
        }

        protected override void OnInit(EventArgs args)
        {
            base.OnInit(args);

            GridViewCategories.DataBound += GridViewCategories_DataBound;
        }

        void GridViewCategories_DataBound(object sender, EventArgs e)
        {
            ProductsControl.CurrentCategoryIndex = 0;
            GridViewCategories.SelectedIndex = 0;
            ProductsControl.Update();
        }

        protected void GridViewCategories_SelectedIndexChanging(object sender, GridViewSelectEventArgs e)
        {
            GridViewCategories.SelectedIndex = e.NewSelectedIndex;
            ProductsControl.CurrentCategoryIndex = e.NewSelectedIndex;
            ProductsControl.Update();
        }

        public string GetCategory(int i)
        {
            return GridViewCategories.DataKeys[i].Values[1].ToString();
        }


    }
}