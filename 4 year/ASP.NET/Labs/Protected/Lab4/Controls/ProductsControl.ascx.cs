using Labs.Entities.DataClasses;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.IO;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Labs.Lab4
{
    public partial class ProductsControl : System.Web.UI.UserControl
    {
        public List<Product> OrderedProducts
        {
            get
            {
                if (Session["Products"] == null)
                {
                    Session["Products"] = new List<Product>();
                }
                return Session["Products"] as List<Product>;
            }
            set
            {
                Session["Products"] = value;
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

        public DataClassesDataContext DataContext
        {
            get;
            set;
        }

   
        public delegate void ProductsControlUpdateEventHandler(object source, EventArgs e);

        public event ProductsControlUpdateEventHandler OnUpdate;

        protected override void OnInit(EventArgs args)
        {
            base.OnInit(args);

            DataContext = new DataClassesDataContext();

            LoadProducts();
        }

        public void LoadProducts()
        {
            foreach (Category category in DataContext.Category)
            {
                View v = new View();
                GridView gv = new GridView();

                gv.AutoGenerateColumns = false;
                gv.Style.Add("width", "770px");

                BoundField bof = new BoundField();
                bof.DataField = "Name";
                bof.HeaderText = "Name";
                gv.Columns.Add(bof);

                bof = new BoundField();
                bof.DataField = "Price";
                bof.HeaderText = "Price";
                gv.Columns.Add(bof);

                ButtonField bf = new ButtonField();
                bf.ItemStyle.CssClass = "order-button";
                bf.Text = "Add to list";
                bf.CommandName = "Order";
                gv.Columns.Add(bf);

                gv.RowCommand += gv_RowCommand;

                var query = from Product in DataContext.Product where Product.CategoryID == category.Id select Product;

                gv.CssClass = "simple-table";
                gv.DataSource = query;
                gv.DataBind();
                v.Controls.Add(gv);
                MultiView.Views.Add(v);
            }
        }

        void gv_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if (e.CommandName == "Order")
            {
                int index = Convert.ToInt32(e.CommandArgument);
                GridView gv = e.CommandSource as GridView;
                LinkButton lb = gv.Rows[index].Cells[gv.Rows[index].Cells.Count - 1].Controls[0] as LinkButton;

                string val = gv.Rows[index].Cells[0].Text.ToString();
                List<Product> v = (from p in DataContext.Product where (p.Name == val) select p).ToList<Product>();
                OrderedProducts.Add(v[0]);

                Update();
            }
        }

        public void Update()
        {
            GridView gv = MultiView.Views[MultiView.ActiveViewIndex].Controls[0] as GridView;
            for (int i = 0; i < gv.Rows.Count; i++)
            {
                string val = gv.Rows[i].Cells[0].Text.ToString();
                List<Product> v = (from p in OrderedProducts where (p.Name == val) select p).ToList<Product>();
                LinkButton lb = gv.Rows[i].Cells[gv.Rows[i].Cells.Count - 1].Controls[0] as LinkButton;
                if (v.Count > 0)
                {

                    lb.Text = "Add to list (" + v.Count + ")";

                }
                else lb.Text = "Add to list";
            }

            if (OnUpdate != null)
            {
                OnUpdate(this, new EventArgs());
            }
        }

       
    }
}