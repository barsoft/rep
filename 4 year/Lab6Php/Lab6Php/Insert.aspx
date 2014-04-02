<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Insert.aspx.cs" Inherits="Lab6Php.Insert" MasterPageFile="~/Lab6Php.Master" %>

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
    <asp:Button ID="InsertButton" runat="server" class="button-normal" Style="margin-top: 20px;" Text="Insert" OnClick="InsertButton_Click" />
</asp:Content>
