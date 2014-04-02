using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Drawing;

namespace Lab3
{
    /// <summary>
    /// Sample card type, card where we drag
    /// </summary>
    public class Sample:Card
    {
        /// <summary>
        /// Sample constructor
        /// </summary>
        /// <param name="sampleBitmap"></param>
        /// <param name="signFileName"></param>
        /// <param name="x"></param>
        /// <param name="y"></param>
        /// <param name="name"></param>
        public Sample(Bitmap sampleBitmap, string signFileName, int x, int y,string name)
            :base(sampleBitmap,signFileName,x,y,name)
        {

        }
    }
}
