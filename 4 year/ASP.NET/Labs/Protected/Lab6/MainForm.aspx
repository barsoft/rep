<%@ Page Language="C#" AutoEventWireup="true" %>

<%@ Register TagPrefix="barsoft" TagName="HeaderControl" Src="~/HeaderControl.ascx" %>
<%@ Register TagPrefix="barsoft" TagName="CategoriesControl" Src="Controls/CategoriesControl.ascx" %>
<%@ Register TagPrefix="barsoft" TagName="ProductsControl" Src="Controls/ProductsControl.ascx" %>
<%@ Register TagPrefix="barsoft" TagName="ToolbarControl" Src="Controls/ToolbarControl.ascx" %>

<%@ OutputCache Duration="60" VaryByParam="*" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1" runat="server">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Lab3</title>
    <link rel="stylesheet" href="/style.css" />
</head>

<body style="background-color: #BEC2C0;">
    <form id="form1" runat="server" style="background-color: #999999; margin-left: auto; margin-right: auto; width: 1000px;">

        <asp:ScriptManager ID="ScriptManager1" runat="server"></asp:ScriptManager>

        <barsoft:HeaderControl id ="HeaderControl" runat="server" Href="MainForm.aspx" IsIndexLinkVisible="true" Title="Lab6" />

        <barsoft:ToolbarControl runat="server" />


        <div style="padding-left: 10px; padding-right: 10px; padding-bottom: 30px; padding-top: 5px;">
            <table>
                <tr>
                    <td style="vertical-align: top;">
                        <barsoft:CategoriesControl ID="CategoriesControl" runat="server" />
                    </td>
                    <td style="vertical-align: top;">
                        <barsoft:ProductsControl ID="ProductsControl" runat="server" />
                    </td>
                </tr>
            </table>
        </div>
    </form>
</body>
</html>



