using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DragWebSite
{
    public partial class StatisticsForm : System.Web.UI.Page
    {
        public AsynchronousClient client;
        protected void Page_Load(object sender, EventArgs e)
        {
            client = new AsynchronousClient(Session);

            if (client.CurrentUser != null)
            {
                Statistics s = client.GetStatistics(client.CurrentUser.id);
                ScoreLabel.Text = s.totalscore;
                LosesLabel.Text = s.loses;
                VictoriesLabel.Text = s.victories;
                GamesLabel.Text = s.loses + s.victories;
            }
        }
    }
}