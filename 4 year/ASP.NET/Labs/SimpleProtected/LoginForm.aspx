<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="LoginForm.aspx.cs" Inherits="Labs.Lab3.LoginForm" MasterPageFile="~/LabsMaster.Master" %>

<asp:Content ID="Content2" ContentPlaceHolderID="body" runat="Server">
    <div style="padding-left: 5px;">
        <asp:Label runat="server" CssClass="text" Text="To get access to Simple Protected folder simple authentication required."></asp:Label>
        <br />
    </div>

    <div style="width: 500px; color: #232323; font-size: large; background: #BEC2C0; margin-top: 10px;" class="text">
        <table>
            <tr>
                <td>Username:</td>
                <td>
                    <asp:TextBox ID="UserName" runat="server" /></td>
                <td>
                    <asp:RequiredFieldValidator ControlToValidate="UserName" Display="Dynamic" ErrorMessage="Cannot be empty." runat="server" />
                </td>
            </tr>

            <tr>
                <td>Remember me?</td>
                <td>
                    <asp:CheckBox ID="RememberMe" runat="server" /></td>
            </tr>
        </table>
    </div>

    <asp:Button ID="Ok" CssClass="simple-button" OnClick="Ok_Click" Style="margin-top: 10px;" Text="Login"
        runat="server" />
    <p>
        <asp:Label ID="Msg" CssClass="text" ForeColor="red" runat="server" />
    </p>
</asp:Content>

