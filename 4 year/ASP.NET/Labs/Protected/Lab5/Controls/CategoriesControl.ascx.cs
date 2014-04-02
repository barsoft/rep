using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.Caching;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Labs.Lab5
{
    public partial class CategoriesControl : System.Web.UI.UserControl
    {
        public int CurrentCategoryIndex
        {
            get { return GridViewCategories.SelectedIndex; }
            set
            {
                GridViewCategories.SelectedIndex = value;
                OnCategoryIndexChanged(new EventArgs());
            }
        }
        public event EventHandler DataBound;

        public event EventHandler CategoryIndexChanged;

        protected virtual void OnDataBound(EventArgs e)
        {
            if (DataBound != null)
                DataBound(this, e);
        }

        protected virtual void OnCategoryIndexChanged(EventArgs e)
        {
            if (CategoryIndexChanged != null)
                CategoryIndexChanged(this, e);
        }

        protected override void OnInit(EventArgs args)
        {
            base.OnInit(args);
            GridViewCategories.DataBound += GridViewCategories_DataBound;
            GridViewCategories.SelectedIndexChanging += GridViewCategories_SelectedIndexChanging;

            String connectionString = @"Data Source=(LocalDB)\v11.0;AttachDbFilename=|DataDirectory|\Products.mdf;Integrated Security=True";
            SqlCacheDependencyAdmin.EnableTableForNotifications(connectionString, "Category");
        }

        protected void GridViewCategories_DataBound(object sender, EventArgs e)
        {
            OnDataBound(e);
        }

        protected void GridViewCategories_SelectedIndexChanging(object sender, GridViewSelectEventArgs e)
        {
            CurrentCategoryIndex = e.NewSelectedIndex;
        }

        public string GetCategory(int i)
        {
            if (GridViewCategories.DataKeys.Count > 0)
                return GridViewCategories.DataKeys[i].Values[1].ToString();
            return "";
        }
    }
}