using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Labs.Entities.Simple
{
    [Serializable]
    public class Product
    {
        public int Price { get; set; }

        public int Id { get; set; }

        public string Name { get; set; }

        public int CategoryID { get; set; }
    }
}
