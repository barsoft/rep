using Labs.Entities.Simple;
using System;
using System.Collections.Generic;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Labs.Lab2
{
    public partial class MainForm : System.Web.UI.Page
    {

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

        public List<Product> Products
        {
            get
            {
                return ViewState["Product"] as List<Product>;
            }
            set
            {
                ViewState["Product"] = value;
            }
        }

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


        protected override void OnInit(EventArgs args)
        {
            base.OnInit(args);

            if (Categories == null) Categories = new List<Category>();
            if (Products == null) Products = new List<Product>();
            if (OrderedProducts == null) OrderedProducts = new List<Product>();

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

            try
            {
                using (StreamReader sr2 = new StreamReader(Server.MapPath(@"~/App_Data/Products.txt")))
                {
                    string pattern1 = @"(\d+),([a-zA-Z0-9_\- ]+),(\d+),(\d+)";
                    while (!sr2.EndOfStream)
                    {
                        String line1 = sr2.ReadLine();
                        Match m1 = Regex.Match(line1, pattern1);

                        if (m1.Success)
                        {
                            Product product = new Product();
                            product.Id = Int32.Parse(m1.Groups[1].Value);
                            product.Name = m1.Groups[2].Value;
                            product.CategoryID = Int32.Parse(m1.Groups[3].Value);
                            product.Price = Int32.Parse(m1.Groups[4].Value);
                            Products.Add(product);
                        }
                    }
                }
            }
            catch (IOException exception)
            {
                Response.Write(exception.Message);
            }

            GridViewCategories.DataSource = Categories;

            GridViewCategories.CssClass = "simple-table";
            GridViewCategories.Style.Add("width", "200px");
            GridViewCategories.AutoGenerateColumns = false;

            ButtonField bof = new ButtonField();
            bof.DataTextField = "Name";
            bof.HeaderText = "Category";
            bof.CommandName = "Select";
            GridViewCategories.Columns.Add(bof);


            GridViewCategories.DataBound += GridViewCategories_DataBound;

            GridViewCategories.DataBind();

            GridViewCategories.SelectedIndexChanging += GridViewCategories_SelectedIndexChanging;


            LabelCategory.Text = GridViewCategories.DataKeys[0].Values[1].ToString();
        }

        protected override void OnLoad(EventArgs e)
        {
            base.OnLoad(e);

            HeaderControl hc = Master.FindControl("HeaderControl") as HeaderControl;
            hc.Title = "Lab2";
            hc.Href = "MainForm.aspx";
        }

        void GridViewCategories_DataBound(object sender, EventArgs e)
        {
            for (int i = 0; i < GridViewCategories.DataKeys.Count; i++)
            {
                View view = new View();
                GridView gv = new GridView();
                List<Product> CategoryNew = new List<Product>();
                gv.DataSource = CategoryNew;
                gv.CssClass = "simple-table";
                gv.Style.Add("width", "770px");
                gv.AutoGenerateColumns = false;

                BoundField bof = new BoundField();
                bof.DataField = "ID";
                bof.HeaderText = "ID";
                gv.Columns.Add(bof);

                bof = new BoundField();
                bof.DataField = "Name";
                bof.HeaderText = "Name";
                gv.Columns.Add(bof);

                bof = new BoundField();
                bof.DataField = "Price";
                bof.HeaderText = "Price";
                gv.Columns.Add(bof);

                bof = new BoundField();
                bof.DataField = "CategoryID";
                bof.HeaderText = "CategoryID";
                gv.Columns.Add(bof);

                ButtonField bf = new ButtonField();
                bf.ItemStyle.CssClass = "order-button";
                bf.Text = "Add to list";
                bf.CommandName = "Order";
                gv.Columns.Add(bf);

                gv.RowCommand += gv_RowCommand;

                view.Controls.Add(gv);
                MultiView.Views.Add(view);
            }

            for (int i = 0; i < Products.Count; i++)
            {
                GridView gv = GetTableByCategoryID(Products[i].CategoryID);

                List<Product> ProductNew = gv.DataSource as List<Product>;

                if (ProductNew != null)
                {
                    ProductNew.Add(Products[i]);
                    gv.DataSource = ProductNew;
                    gv.DataBind();
                }
            }

            MultiView.ActiveViewIndex = 0;
        }

        void GridViewCategories_SelectedIndexChanging(object sender, GridViewSelectEventArgs e)
        {
            GridViewCategories.SelectedIndex = e.NewSelectedIndex;
            MultiView.ActiveViewIndex = e.NewSelectedIndex;
            LabelCategory.Text = GridViewCategories.DataKeys[e.NewSelectedIndex].Values[1].ToString();
        }

        void gv_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if (e.CommandName == "Order")
            {
                int index = Convert.ToInt32(e.CommandArgument);
                GridView gv = e.CommandSource as GridView;
                LinkButton lb = gv.Rows[index].Cells[gv.Rows[index].Cells.Count - 1].Controls[0] as LinkButton;
                if (lb.Text.Equals("Delete from list"))
                {
                    lb.Text = "Add to list";
                    lb.Style.Add("color", "#666");
                }
                else
                {
                    lb.Text = "Delete from list";
                    lb.Style.Add("color", "#ff006e");
                }
            }
        }


        private GridView GetTableByCategoryID(int id)
        {
            for (int i = 0; i < GridViewCategories.DataKeys.Count; i++)
            {
                int categoryid = Int32.Parse(GridViewCategories.DataKeys[i].Value.ToString());

                if (categoryid == id)
                {
                    return MultiView.Views[i].Controls[0] as GridView;
                }
            }
            return null;
        }

        protected void Order_Click(object sender, EventArgs e)
        {
            foreach (View v in MultiView.Views)
            {
                GridView gv = v.Controls[0] as GridView;
                for (int i = 0; i < gv.Rows.Count; i++)
                {
                    LinkButton lb = gv.Rows[i].Cells[4].Controls[0] as LinkButton;
                    if (lb.Text.Equals("Delete from list"))
                    {
                        int id = Int32.Parse(gv.Rows[i].Cells[0].Text);

                        var products = (from p in Products where p.Id == id select p).ToList();
                        OrderedProducts.Add(products[0]);
                    }
                }
            }
        }

    }
}