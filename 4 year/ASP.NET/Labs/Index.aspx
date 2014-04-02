<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Index.aspx.cs" Inherits="Labs.Index" MasterPageFile="~/LabsMaster.Master" %>

<asp:Content ID="Content2" ContentPlaceHolderID="body" runat="Server">
    <div style="padding-left: 5px;">
        <asp:Label runat="server" CssClass="text" Text="Choose lab to demonstrate."></asp:Label>
        <br />
    </div>
    <% 
        string lab1 = "http://localhost/ExampleApp/";
    %>
    <table>
        <tr>
            <td style="vertical-align: top;" class="text"><a href="<%=lab1 %>">Lab1 - Обработка Post-запроса и работа с файлами в ASP приложении</a></td>
        </tr>
        <tr>
            <td style="vertical-align: top;" class="text"><a href="NotProtected/Lab2/MainForm.aspx">Lab2 - Работа с формами в ASP.NET</a></td>
        </tr>
        <tr>
            <td style="vertical-align: top;" class="text"><a href="SimpleProtected/Lab3/MainForm.aspx">Lab3 - Пользовательские элементы управления, куки</a></td>
        </tr>
        <tr>
            <td style="vertical-align: top;" class="text"><a href="Protected/Lab4/MainForm.aspx">Lab4 - БД, сесии</a></td>
        </tr>
        <tr>
            <td style="vertical-align: top;" class="text"><a href="Protected/Lab5/MainForm.aspx">Lab5 - AJAX, кэширование</a></td>
        </tr>
        <tr>
            <td style="vertical-align: top;" class="text"><a href="Protected/Lab6/MainForm.aspx">Lab6 - Валидация и отчёты</a></td>
        </tr>
    </table>
</asp:Content>

