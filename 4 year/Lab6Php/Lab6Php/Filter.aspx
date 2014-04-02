<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Filter.aspx.cs" Inherits="Lab6Php.Filter" MasterPageFile="~/Lab6Php.Master" %>

<asp:Content ID="Content2" ContentPlaceHolderID="body" runat="Server">
    <table class="simple-table" style="width: 500px; height: 300px;">
        <tr>
            <th>Название книги: </th>
            <td>
                <input id="name" runat="server" class="text-field" />
            </td>
        </tr>
        <tr>
            <th>Тематика: </th>
            <td>
                <input id="subject" runat="server" class="text-field" />
            </td>
        </tr>
        <tr>
            <th>ФИО авторов: </th>
            <td>
                <input id="author" runat="server" class="text-field" />
            </td>
        </tr>
        <tr>
            <th>Год издания: </th>
            <td>
                <input id="year" runat="server" class="text-field" />
            </td>
        </tr>
        <tr>
            <th>Издательство: </th>
            <td>
                <input id="publisher" runat="server" class="text-field" />
            </td>
        </tr>
    </table>
    <asp:Button ID="FilterButton" runat="server" class="button-normal" Style="margin: 20px;" Text="Filter" OnClick="FilterButton_Click" />

    <asp:GridView ID="GridView1" CssClass="simple-table" runat="server" AutoGenerateColumns="False">
        <EmptyDataTemplate>true</EmptyDataTemplate>
        <Columns>
            <asp:BoundField DataField="Id" HeaderText="Id" ControlStyle-Width="50px" Visible="false"/>
            <asp:BoundField DataField="Name" HeaderText="Name" ControlStyle-Width="100px" />
            <asp:BoundField DataField="Year" HeaderText="Year" ControlStyle-Width="50px" />
            <asp:BoundField DataField="Subject" HeaderText="Subject" ControlStyle-Width="100px" />
            <asp:BoundField DataField="Author" HeaderText="Author" ControlStyle-Width="100px" />
            <asp:BoundField DataField="Publisher" HeaderText="Publisher" ControlStyle-Width="100px" />
        </Columns>
    </asp:GridView>

</asp:Content>
