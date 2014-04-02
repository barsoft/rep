<%@ Control Language="C#" AutoEventWireup="true" CodeBehind="CategoriesControl.ascx.cs" Inherits="Labs.Lab3.Controls.CategoriesControl" %>

<asp:GridView ID="GridViewCategories" CssClass="simple-table" Style="width: inherit;" runat="server" DataKeyNames="ID,Name" onselectedindexchanging="GridViewCategories_SelectedIndexChanging" >
</asp:GridView>
