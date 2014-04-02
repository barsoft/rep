<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="MainForm.aspx.cs" Inherits="Labs.SimpleProtected.Lab3.MainForm" MasterPageFile="~/LabsMaster.Master" %>

<%@ Register TagPrefix="barsoft" TagName="CategoriesControl" Src="Controls/CategoriesControl.ascx" %>
<%@ Register TagPrefix="barsoft" TagName="ProductsControl" Src="Controls/ProductsControl.ascx" %>


<asp:Content ID="Content2" ContentPlaceHolderID="body" runat="Server">
    <asp:Button ID="ButtonOrder" Style="text-align: center; width: 90px; height: 50px; margin: 5px; margin-right: 15px; float: right;"
        CssClass="simple-button" runat="server" Text="Order" PostBackUrl="ShoppingCartForm.aspx" />

    <div style="padding-left: 5px;">
        <asp:Label runat="server" CssClass="text" Text="Choose category and products."></asp:Label>
        <br />
        <asp:Label runat="server" CssClass="text" Text="Category: "></asp:Label>
        <asp:Label ID="LabelCategory" runat="server" CssClass="text" Text="Category"></asp:Label>
    </div>

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
</asp:Content>
