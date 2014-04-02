<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="StatisticsForm.aspx.cs" Inherits="DragWebSite.StatisticsForm" MasterPageFile="~/Drag.Master" %>


<asp:Content ID="Content2" ContentPlaceHolderID="content" runat="Server">

    <div style="height: 500px">
        <table>
            <tr>
                <td>Total score: </td>
                <td><asp:Label ID="ScoreLabel" runat="server" Text="Label"></asp:Label></td>
            </tr>
            <tr>
                <td>How many victories: </td>
                <td><asp:Label ID="VictoriesLabel" runat="server" Text="Label"></asp:Label></td>
            </tr>
            <tr>
                <td>How many loses: </td>
                <td><asp:Label ID="LosesLabel" runat="server" Text="Label"></asp:Label></td>
            </tr>
            <tr>
                <td>How many games in total: </td>
                <td><asp:Label ID="GamesLabel" runat="server" Text="Label"></asp:Label></td>
            </tr>
        </table>
    </div>

</asp:Content>
