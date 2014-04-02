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
    public partial class ProductsControl : System.Web.UI.UserControl
    {
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

        public int CurrentTableIndex
        {
            get
            {
                return MultiView.ActiveViewIndex;
            }
            set
            {
                MultiView.ActiveViewIndex = value;
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



        public void LoadProducts()
        {
            if (Products == null) Products = new List<Product>();
            if (OrderedProducts == null) OrderedProducts = new List<Product>();

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
        }


        public void BindTablesToCategories(List<Category> categories)
        {
            for (int i = 0; i < categories.Count; i++)
            {
                View view = new View();
                GridView gv = new GridView();
                List<Product> categoriesNew = new List<Product>();
                gv.DataSource = categoriesNew;
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
                GridView gv = GetTableByCategoryId(categories, Products[i].CategoryID);

                List<Product> productsNew = gv.DataSource as List<Product>;

                if (productsNew != null)
                {
                    productsNew.Add(Products[i]);
                    gv.DataSource = productsNew;
                    gv.DataBind();
                }
            }
        }

        private GridView GetTableByCategoryId(List<Category> categories, int id)
        {
            Category category = categories.Where(c => c.Id == id).First();
            return MultiView.Views[categories.IndexOf(category)].Controls[0] as GridView;
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

        public void UpdateOrderedList()
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