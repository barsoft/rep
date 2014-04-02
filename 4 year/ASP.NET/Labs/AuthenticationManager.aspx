<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/LabsMaster.Master" %>

<asp:Content ID="Content2" ContentPlaceHolderID="body" runat="Server">
    <%
        String returnUrl = Request["ReturnUrl"];
        if (returnUrl != null)
        {
            if (returnUrl.Contains("SimpleProtected"))
            {
                Response.Redirect("/SimpleProtected/LoginForm.aspx?ReturnUrl=" + returnUrl, true);
            }
            else
                if (returnUrl.Contains("Protected"))
                {
                    Response.Redirect("/Protected/LoginForm.aspx?ReturnUrl=" + returnUrl, true);
                }
        }
        Response.Redirect("/", true);
    %>
</asp:Content>
