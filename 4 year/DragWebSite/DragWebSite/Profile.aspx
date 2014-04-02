<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Profile.aspx.cs" Inherits="DragWebSite.Profile" MasterPageFile="~/Drag.Master" %>


<asp:Content ID="Content2" ContentPlaceHolderID="content" runat="Server">

    <div style="height: 500px">
        <table>
            <tr>
                <td>UserName: </td>
                <td><asp:Label ID="UserNameLabel" runat="server" Text="Label"></asp:Label></td>
            </tr>
            <tr>
                <td>E-mail: </td>
                <td><asp:Label ID="EmailLabel" runat="server" Text="Label"></asp:Label></td>
            </tr>
             <tr>
                <td>Name: </td>
                <td><asp:Label ID="NameLabel" runat="server" Text="Label"></asp:Label></td>
            </tr>
            <tr>
                <td>Surname: </td>
                <td><asp:Label ID="SurnameLabel" runat="server" Text="Label"></asp:Label></td>
            </tr>
            <tr>
                <td>Adress: </td>
                <td><asp:Label ID="AdressLabel" runat="server" Text="Label"></asp:Label></td>
            </tr>
            <tr>
                <td>Age: </td>
                <td><asp:Label ID="AgeLabel" runat="server" Text="Label"></asp:Label></td>
            </tr>
        </table>
    </div>

</asp:Content>
