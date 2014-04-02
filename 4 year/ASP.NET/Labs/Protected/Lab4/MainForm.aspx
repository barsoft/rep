<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="MainForm.aspx.cs" Inherits="Labs.Protected.Lab4.MainForm" MasterPageFile="~/LabsMaster.Master" %>

<%@ Register TagPrefix="barsoft" TagName="CategoriesControl" Src="Controls/CategoriesControl.ascx" %>
<%@ Register TagPrefix="barsoft" TagName="ProductsControl" Src="Controls/ProductsControl.ascx" %>
<%@ Register TagPrefix="barsoft" TagName="ToolbarControl" Src="Controls/ToolbarControl.ascx" %>

<asp:Content ID="Content2" ContentPlaceHolderID="body" runat="Server">
    <barsoft:ToolbarControl runat="server" />
    <div style="padding-left: 10px; padding-right: 10px; padding-bottom: 30px; padding-top: 5px;">
        <div style="padding-left: 5px;">
            <asp:Label runat="server" CssClass="text" Text="Choose category and products."></asp:Label>
            <br />
            <asp:Label runat="server" CssClass="text" Text="Category: "></asp:Label>
            <asp:Label ID="LabelCategory" runat="server" CssClass="text" Text="Label"></asp:Label>
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
    </div>
</asp:Content>
