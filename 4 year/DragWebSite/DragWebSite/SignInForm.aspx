<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="SignInForm.aspx.cs" Inherits="DragWebSite.SignInForm" MasterPageFile="~/Drag.Master" %>

<asp:Content ID="Content2" ContentPlaceHolderID="content" runat="Server">
    <div style="padding-left: 5px;">
        <asp:Label ID="Label1" runat="server" CssClass="text" Text="To get access to Protected folder full authentication required."></asp:Label>
        <br />
    </div>

    <div style="width: 500px; color: #232323; font-size: large; background: #BEC2C0; margin-top: 10px;" class="text">
        <table>
            <tr>
                <td>Username:</td>
                <td>
                    <input id="txtUserName" type="text" runat="server" /></td>
                <td>
                    <asp:RequiredFieldValidator ControlToValidate="txtUserName"
                        Display="Dynamic" ErrorMessage="Cannot be empty." runat="server"
                        ID="vUserName" EnableClientScript="false" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td>
                    <input id="txtUserPass" type="password" runat="server" /></td>
                <td>
                    <asp:RequiredFieldValidator ControlToValidate="txtUserPass"
                        Display="Dynamic" ErrorMessage="Cannot be empty." runat="server"
                        ID="vUserPass" EnableClientScript="false" />
                </td>
            </tr>
            <tr>
                <td>Remember me?:</td>
                <td>
                    <asp:CheckBox ID="chkPersistCookie" runat="server" AutoPostBack="false" /></td>
                <td></td>
            </tr>
        </table>
    </div>

    <asp:Button ID="Ok" CssClass="simple-button" OnClick="Ok_Click" Style="margin-top: 10px;" Text="Login"
        runat="server" />
    <p>
        <asp:Label ID="Msg" CssClass="text" ForeColor="red" runat="server" />
    </p>

</asp:Content>
