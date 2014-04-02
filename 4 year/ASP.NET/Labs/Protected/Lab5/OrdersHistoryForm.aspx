<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="OrdersHistoryForm.aspx.cs" Inherits="Labs.Lab5.OrdersHistoryForm" MasterPageFile="Lab5Master.Master" %>

<%@ Register TagPrefix="barsoft" TagName="ToolbarControl" Src="Controls/ToolbarControl.ascx" %>

<asp:Content ID="Content2" ContentPlaceHolderID="body" runat="Server">
    <barsoft:ToolbarControl ID="ToolbarControl1" runat="server" />
    <asp:GridView ID="GridViewOrder" Style="margin-left: auto; margin-right: auto; width: 500px;"
        runat="server" CssClass="simple-table" DataSourceID="LinqDataSource" AutoGenerateColumns="False">
        <Columns>
            <asp:BoundField DataField="ID" HeaderText="ID" />
            <asp:BoundField DataField="Product" HeaderText="Product" />
            <asp:BoundField DataField="Date" HeaderText="Date" DataFormatString="{0:MM/dd/yyyy}" HtmlEncode="false" />
        </Columns>
    </asp:GridView>

    <asp:LinqDataSource ID="LinqDataSource" runat="server" ContextTypeName="Labs.DataClassesDataContext" TableName="Orders"
        OnSelecting="LinqDataSource_Selecting">
    </asp:LinqDataSource>
</asp:Content>

