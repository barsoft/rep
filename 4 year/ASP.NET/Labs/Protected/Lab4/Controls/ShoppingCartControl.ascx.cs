using Labs.Entities.DataClasses;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Labs.Lab4
{
    public partial class ShoppingCartControl : System.Web.UI.UserControl
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

        public DataClassesDataContext DataContext
        {
            get;
            set;
        }

        public delegate void ShoppingCartControlUpdateEventHandler(object source, EventArgs e);

        public event ShoppingCartControlUpdateEventHandler OnUpdate;

        protected override void OnInit(EventArgs e)
        {
            base.OnInit(e);

            DataContext = new DataClassesDataContext();

            GridViewOrder.DataSource = OrderedProducts;
            GridViewOrder.DataBind();

            Update();
        }


        protected void GridViewOrder_RowDeleting(object sender, GridViewDeleteEventArgs e)
        {
            int n=Int32.Parse(GridViewOrder.DataKeys[e.RowIndex].Values[0].ToString()) ;
            List<Product> pr = (from p in OrderedProducts where p.Id == n select p).ToList<Product>();
            OrderedProducts.Remove(pr[0]);

            GridViewOrder.DataSource = OrderedProducts;
            GridViewOrder.DataBind();

            Update();
        }

        protected void ButtonSendOrder_Click(object sender, EventArgs e)
        {
            foreach (Product p in OrderedProducts)
            {
                Order orders= new Order();
                orders.ProductID = p.Id;
                orders.UserName = HttpContext.Current.User.Identity.Name;
                orders.Date =DateTime.Now;
                DataContext.Order.InsertOnSubmit(orders);
                
                DataContext.SubmitChanges();
            }

            OrderedProducts.Clear();

            Update();
        }

        public void Update()
        {
            int sum = 0;
            foreach (Product product in OrderedProducts)
            {
                sum += product.Price;
            }
            LabelTotal.Text = "Total: " + OrderedProducts.Count + " items, Total price:" + sum;

            if (OnUpdate != null)
            {
                OnUpdate(this, new EventArgs());
            }
        }
    }
}