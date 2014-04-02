<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="OrdersHistoryForm.aspx.cs" Inherits="Labs.Lab6.OrdersHistoryForm" %>

<%@ Register Assembly="Microsoft.ReportViewer.WebForms, Version=11.0.0.0, Culture=neutral, PublicKeyToken=89845dcd8080cc91" Namespace="Microsoft.Reporting.WebForms" TagPrefix="rsweb" %>

<%@ Register TagPrefix="barsoft" TagName="HeaderControl" Src="~/HeaderControl.ascx" %>
<%@ Register TagPrefix="barsoft" TagName="ShoppingCartControl" Src="Controls/ShoppingCartControl.ascx" %>
<%@ Register TagPrefix="barsoft" TagName="ToolbarControl" Src="Controls/ToolbarControl.ascx" %>

<%@ OutputCache Duration="10" VaryByParam="*" %>



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


        <barsoft:HeaderControl ID="HeaderControl1" runat="server" Href="MainForm.aspx" IsIndexLinkVisible="true" Title="Lab6" />
        <barsoft:ToolbarControl runat="server" />

        <div style="padding-left: 10px; text-align: center; padding-right: 10px; padding-bottom: 30px; padding-top: 5px; ">
            <rsweb:ReportViewer ID="ReportViewer1" runat="server" Width="977px">
                
            </rsweb:ReportViewer>
        </div>


    </form>
</body>
</html>
