<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="ShoppingCartForm.aspx.cs" Inherits="Labs.Lab2.ShoppingCartForm" MasterPageFile="~/LabsMaster.Master" %>

<%@ PreviousPageType VirtualPath="MainForm.aspx" %>

<asp:Content ID="Content2" ContentPlaceHolderID="body" runat="Server">
    <%if (OrderedProducts.Count <= 0)
      {%>
    <asp:Label runat="server" CssClass="text" Text="Shopping cart is empty."></asp:Label>
    <br />
    <%}
      else
      {%>
    <br />
    <div style="text-align: center;">
        <asp:GridView ID="GridViewOrder" Style="margin-left: auto; margin-right: auto; width: 500px;"
            runat="server" DataKeyNames="ID,Name,Price,CategoryID" CssClass="simple-table" AutoGenerateColumns="False" OnRowDeleting="GridViewOrder_RowDeleting">
            <Columns>
                <asp:BoundField DataField="Name" HeaderText="Name" />
                <asp:BoundField DataField="Price" HeaderText="Price" />
                <asp:CommandField ShowDeleteButton="True" />
            </Columns>
        </asp:GridView>

        <asp:Label ID="TotalLabel" runat="server" CssClass="text"></asp:Label>
        <br />
    </div>
    <%} %>
</asp:Content>
