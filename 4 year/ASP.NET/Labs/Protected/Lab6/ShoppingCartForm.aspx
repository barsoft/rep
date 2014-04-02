<%@ Page Language="C#" AutoEventWireup="true" %>

<%@ Register TagPrefix="barsoft" TagName="HeaderControl" Src="~/HeaderControl.ascx" %>
<%@ Register TagPrefix="barsoft" TagName="ShoppingCartControl" Src="Controls/ShoppingCartControl.ascx" %>
<%@ Register TagPrefix="barsoft" TagName="ToolbarControl" Src="Controls/ToolbarControl.ascx" %>

<%@ OutputCache Duration="10" VaryByParam ="none" %>


<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1" runat="server">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Lab3</title>
    <link rel="stylesheet" href="/style.css" />
</head>

<body style="background-color: #BEC2C0">
    <form id="form1" runat="server" style="background-color: #999999; margin-left: auto; margin-right: auto; width: 1000px;">

        <asp:ScriptManager ID="ScriptManager1" runat="server"></asp:ScriptManager>


        <barsoft:HeaderControl runat="server" Href="MainForm.aspx" IsIndexLinkVisible="true" Title="Lab5" />

        <barsoft:ToolbarControl runat="server" />

        <barsoft:ShoppingCartControl id="ShoppingCartControl" runat="Server" />

    </form>
</body>
</html>
