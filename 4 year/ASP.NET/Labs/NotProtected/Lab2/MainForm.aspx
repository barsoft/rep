<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="MainForm.aspx.cs" Inherits="Labs.Lab2.MainForm" EnableViewState="true" MasterPageFile="~/LabsMaster.Master" %>

<asp:Content ID="Content2" ContentPlaceHolderID="body" runat="Server">
    <asp:Button ID="ButtonOrder" Style="text-align: center; width: 90px; height: 50px; margin: 5px; margin-right: 15px; float: right;"
        CssClass="simple-button" runat="server" Text="Order" PostBackUrl="ShoppingCartForm.aspx" OnClick="Order_Click" />
        <div style="padding-left: 5px;">
            <asp:Label runat="server" CssClass="text" Text="Choose category and products."></asp:Label>
            <br />
            <asp:Label runat="server" CssClass="text" Text="Category: "></asp:Label>
            <asp:Label ID="LabelCategory" runat="server" CssClass="text" Text="Category"></asp:Label>
        </div>
        <table>
            <tr>
                <td style="vertical-align: top;">
                    <asp:GridView ID="GridViewCategories" Style="width: inherit;" runat="server" DataKeyNames="ID,Name">
                    </asp:GridView>
                </td>
                <td style="vertical-align: top;">
                    <asp:MultiView ID="MultiView" runat="server" ActiveViewIndex="-1">
                    </asp:MultiView>
                </td>
            </tr>
        </table>
</asp:Content>
