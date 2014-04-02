using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MapEditor
{
    public class ObjectCollectiion
    {
        List<Base> basel;

        public ObjectCollectiion()
        {
            
        }
        public void Add(Base b)
        {
            basel.Add(b);
        }
    }
}
