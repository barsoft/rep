<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="ShoppingCartForm.aspx.cs" Inherits="Labs.Protected.Lab5.ShoppingCartForm" MasterPageFile="Lab5Master.Master" %>

<%@ Register TagPrefix="barsoft" TagName="ShoppingCartControl" Src="Controls/ShoppingCartControl.ascx" %>
<%@ Register TagPrefix="barsoft" TagName="ToolbarControl" Src="Controls/ToolbarControl.ascx" %>

<asp:Content ID="Content2" ContentPlaceHolderID="body" runat="Server">
    <barsoft:ToolbarControl ID="ToolbarControl" runat="server" />
    <barsoft:ShoppingCartControl ID="ShoppingCartControl" runat="Server" />
</asp:Content>
