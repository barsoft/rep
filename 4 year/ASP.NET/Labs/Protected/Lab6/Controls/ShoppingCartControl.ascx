<%@ Control Language="C#" AutoEventWireup="true" CodeBehind="ShoppingCartControl.ascx.cs" Inherits="Labs.Lab6.ShoppingCartControl" %>

<link rel="stylesheet" href="/style.css" />

<asp:UpdatePanel ID="UpdatePanel1" runat="server">
    <ContentTemplate>
        <div style="padding-left: 10px; text-align: center; padding-right: 10px; padding-bottom: 30px; padding-top: 5px;">

            <%if (OrderedProducts == null || OrderedProducts.Count <= 0)
              {%>
            <asp:Label runat="server" CssClass="text" Text="Shopping cart is empty."></asp:Label>
            <br />
            <%}
              else
              {%>
            <br />

            <asp:GridView ID="GridViewOrder" Style="margin-left: auto; margin-right: auto; width: 500px;"
                runat="server" DataKeyNames="ID,Name,Price,CategoryID" CssClass="simple-table" AutoGenerateColumns="False" OnRowDeleting="GridViewOrder_RowDeleting">
                <Columns>
                    <asp:BoundField DataField="Name" HeaderText="Name" />
                    <asp:BoundField DataField="Price" HeaderText="Price" />
                    <asp:CommandField ShowDeleteButton="True" />
                </Columns>
            </asp:GridView>

            <asp:Label ID="LabelTotal" runat="server" CssClass="text"></asp:Label>
            <br />
            <asp:Button ID="ButtonSendOrder" CssClass="simple-button" runat="server" Text="SendOrder" OnClick="ButtonSendOrder_Click" />

            <%} %>
        </div>
    </ContentTemplate>
</asp:UpdatePanel>
