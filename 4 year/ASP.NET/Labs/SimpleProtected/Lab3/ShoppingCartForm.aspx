<%@ Page Language="C#"  AutoEventWireup="true" CodeBehind="ShoppingCartForm.aspx.cs" Inherits="Labs.SimpleProtected.Lab3.ShoppingCartForm" MasterPageFile="~/LabsMaster.Master"%>

<%@ PreviousPageType VirtualPath="MainForm.aspx" %>

<%@ Register TagPrefix="barsoft" TagName="ShoppingCartControl" Src="Controls/ShoppingCartControl.ascx" %>

<asp:Content ID="Content2" ContentPlaceHolderID="body" runat="Server">
    <barsoft:ShoppingCartControl ID="ShoppingCartControl" runat="Server" />
</asp:Content>
