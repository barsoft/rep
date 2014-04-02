using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Lab6Php
{
    public partial class Filter : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void FilterButton_Click(object sender, EventArgs e)
        {
            DatabaseEntities de = new DatabaseEntities();
            GridView1.DataSource = de.Book.Where(it => it.Name.Contains(name.Value)
                && it.Subject.Contains(subject.Value)
                && it.Author.Contains(author.Value)
                && it.Publisher.Contains(publisher.Value));
            GridView1.DataBind();
            //string query = @"it.Name like '%" + name.Value + @"%'" + @" and  "
            //                     + @"it.Subject like '%" + subject.Value + @"%' and  "
            //                     + @"it.Author like '%" + author.Value + @"%' and  "

            //                     + @"it.Publisher like '%" + publisher.Value + @"%'";
            //EntityDataSource.Where = query;
        }
    }
}