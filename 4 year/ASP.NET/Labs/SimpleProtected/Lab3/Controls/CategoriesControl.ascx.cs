using Labs.Entities.Simple;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Labs.Lab3.Controls
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

        public List<Category> Categories
        {
            get
            {
                return ViewState["Categories"] as List<Category>;
            }
            set
            {
                ViewState["Categories"] = value;
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
        }

        public void Bind()
        {
            GridViewCategories.Columns.Clear();

            GridViewCategories.DataSource = Categories;

            GridViewCategories.CssClass = "simple-table";
            GridViewCategories.Style.Add("width", "200px");
            GridViewCategories.AutoGenerateColumns = false;

            ButtonField bof = new ButtonField();
            bof.DataTextField = "Name";
            bof.HeaderText = "Category";
            bof.CommandName = "Select";
            GridViewCategories.Columns.Add(bof);

            GridViewCategories.DataBind();
        }

        private void GridViewCategories_DataBound(object sender, EventArgs e)
        {
            OnDataBound(e);
        }

        public void LoadCategories()
        {
            if (Categories == null) Categories = new List<Category>();
            Categories.Clear();
            try
            {
                using (StreamReader sr = new StreamReader(Server.MapPath(@"~/App_Data/Categories.txt")))
                {
                    string pattern = @"(\d+),([a-zA-Z0-9_ ]+)";
                    while (!sr.EndOfStream)
                    {
                        String line = sr.ReadLine();
                        Match m = Regex.Match(line, pattern);

                        if (m.Success)
                        {
                            Category category = new Category();
                            category.Id = Int32.Parse(m.Groups[1].Value);
                            category.Name = m.Groups[2].Value;
                            Categories.Add(category);
                        }
                    }
                }
            }
            catch (IOException exception)
            {
                Response.Write(exception.Message);
            }
        }

        protected void GridViewCategories_SelectedIndexChanging(object sender, GridViewSelectEventArgs e)
        {
            CurrentCategoryIndex = e.NewSelectedIndex;
        }
    }
}