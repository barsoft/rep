<%@ Control Language="C#" AutoEventWireup="true" CodeBehind="ProductsControl.ascx.cs" Inherits="Labs.Lab6.ProductsControl" %>



<link rel="stylesheet" href="/style.css" />


<asp:UpdatePanel ID="UpdatePanel1" runat="server">
    <ContentTemplate>
        <asp:MultiView ID="MultiView" runat="server" ActiveViewIndex="-1" EnableViewState="true">
        </asp:MultiView>
    </ContentTemplate>
</asp:UpdatePanel>
