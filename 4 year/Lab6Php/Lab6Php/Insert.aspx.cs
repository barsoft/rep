using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Lab6Php
{
    public partial class Insert : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void InsertButton_Click(object sender, EventArgs e)
        {
            DatabaseEntities da = new DatabaseEntities();
            Book book = new Book();
            book.Name = name.Value;
            book.Publisher = publisher.Value;
            book.Year = Int32.Parse(year.Value);
            book.Author = author.Value;
            book.Subject = subject.Value;
            da.Book.AddObject(book);
            da.SaveChanges();
            Response.Redirect("/");
        }
    }
}