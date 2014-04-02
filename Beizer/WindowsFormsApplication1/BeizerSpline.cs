using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsApplication1
{
    /// <summary>
    /// Class to generate a Bezier Curve from a set of seed points.  
    /// Note that this is a best fit curve through a set of points 
    /// and a Bezier does NOT require the curve to pass through the points.
    /// </summary>
    public class BezierSpline
    {
        /// <summary>
        /// Default Constructor
        /// </summary>
        public BezierSpline()
        {
            SeedPoints = new List<PointF>();
        }

        /// <summary>
        /// Constructs a BezierSpline object using SeedPoints
        /// </summary>
        /// <param name="seedPoints"></param>
        public BezierSpline(IList<PointF> seedPoints)
            : this()
        {
            SeedPoints = new List<PointF>(seedPoints);
        }

        /// <summary>
        /// Set of SeedPoints to run the spline through
        /// </summary>
        public List<PointF> SeedPoints { get; set; }

        /// <summary>
        /// Generates a smooth curve through a series of points
        /// </summary>
        /// <param name="numberPointsToGenerate">Number of points to generate between P0 and Pn</param>
        /// <returns>IList of Points along the curve</returns>
        public IList<PointF> GenerateSpline(int numberPointsToGenerate)
        {
            List<float> ps = new List<float>();
            List<PointF> np = new List<PointF>();
            for (int i = 0; i < SeedPoints.Count; i++)
            {
                ps.Add(SeedPoints[i].X);
                ps.Add(SeedPoints[i].Y);
            }

            float[] newpts = Bezier2D(ps.ToArray(), numberPointsToGenerate);
            for (int i = 0; i < newpts.Length; i += 2)
                np.Add(new PointF(newpts[i], newpts[i + 1]));

            return np;
        }

        private float[] Bezier2D(float[] b, int cpts)
        {
            float[] p = new float[cpts * 2];
            int npts = (b.Length) / 2;
            int icount, jcount;
            float step, t;

            // Calculate points on curve
            icount = 0;
            t = 0;
            step = (float)1 / (cpts - 1);

            for (int i1 = 0; i1 < cpts; i1++)
            {
                if ((1.0 - t) < 5e-6)
                    t = 1.0f;

                jcount = 0;
                p[icount] = 0.0f;
                p[icount + 1] = 0.0f;
                for (int i = 0; i != npts; i++)
                {
                    float basis = Bernstein(npts - 1, i, t);
                    p[icount] += basis * b[jcount];
                    p[icount + 1] += basis * b[jcount + 1];
                    jcount += 2;
                }

                icount += 2;
                t += step;
            }
            return p;
        }

        private double Ni(int n, int i)
        {
            return Factorial(n) / (Factorial(i) * Factorial(n - i));
        }

        /// <summary>
        /// Bernstein basis formula
        /// </summary>
        /// <param name="n">n</param>
        /// <param name="i">i</param>
        /// <param name="t">t</param>
        /// <returns>Bernstein basis</returns>
        private float Bernstein(int n, int i, double t)
        {
            float basis;
            double ti; /* t^i */
            double tni; /* (1 - t)^i */

            if (t == 0.0 && i == 0)
                ti = 1.0;
            else
                ti = System.Math.Pow(t, i);

            if (n == i && t == 1.0)
                tni = 1.0;
            else
                tni = System.Math.Pow((1 - t), (n - i));

            //Bernstein basis
            basis = (float)(Ni(n, i) * ti * tni);
            return basis;
        }


        public static int Factorial(int n)
        {
            int f = 1;

            for (int i = n; i > 1; i--)
                f *= i;
            return f;
        }
    }
}
