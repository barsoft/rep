<%@ Control Language="C#" AutoEventWireup="true" CodeBehind="ProductsControl.ascx.cs" Inherits="Labs.Lab5.ProductsControl" %>

<asp:UpdatePanel ID="UpdatePanel1" runat="server">
    <ContentTemplate>
        <asp:MultiView ID="MultiView" runat="server" ActiveViewIndex="-1" EnableViewState="true">
        </asp:MultiView>
    </ContentTemplate>
</asp:UpdatePanel>
