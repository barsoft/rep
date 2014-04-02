<%@ Control Language="C#" AutoEventWireup="true" CodeBehind="CategoriesControl.ascx.cs" Inherits="Labs.Lab6.CategoriesControl" %>


<link rel="stylesheet" href="/style.css" />



<asp:UpdatePanel ID="UpdatePanel1" runat="server" >
    <ContentTemplate>
        <asp:GridView ID="GridViewCategories" CssClass="simple-table" Style="Width: 200px" runat="server" DataKeyNames="Id,Name" OnSelectedIndexChanging="GridViewCategories_SelectedIndexChanging" AutoGenerateColumns="False" DataSourceID="LinqDataSource">
            <Columns>
                <asp:ButtonField DataTextField="Name" HeaderText="Categories" SortExpression="Name" CommandName="Select" />
            </Columns>
            <SelectedRowStyle Font-Bold="True" ForeColor="#0000cc"/>
        </asp:GridView>

        <asp:LinqDataSource ID="LinqDataSource" runat="server" ContextTypeName="Labs.Entities.DataClasses.DataClassesDataContext" TableName="Category">
        </asp:LinqDataSource>
    </ContentTemplate>

</asp:UpdatePanel>


