<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="SignUpForm.aspx.cs" Inherits="DragWebSite.SignUpForm" MasterPageFile="~/Drag.Master" %>

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
                <td>E-mail:</td>
                <td>
                    <input id="txtEmail" type="password" runat="server" /></td>
                <td>
                    <asp:RequiredFieldValidator ControlToValidate="txtUserPass"
                        Display="Dynamic" ErrorMessage="Cannot be empty." runat="server"
                        ID="RequiredFieldValidator1" EnableClientScript="false" />
                </td>
            </tr>
             <tr>
                <td>Name:</td>
                <td>
                    <input id="txtName" type="password" runat="server" /></td>
                <td>
                    <asp:RequiredFieldValidator ControlToValidate="txtUserPass"
                        Display="Dynamic" ErrorMessage="Cannot be empty." runat="server"
                        ID="RequiredFieldValidator2" EnableClientScript="false" />
                </td>
            </tr>
             <tr>
                <td>Surname:</td>
                <td>
                    <input id="txtSurname" type="password" runat="server" /></td>
                <td>
                    <asp:RequiredFieldValidator ControlToValidate="txtUserPass"
                        Display="Dynamic" ErrorMessage="Cannot be empty." runat="server"
                        ID="RequiredFieldValidator3" EnableClientScript="false" />
                </td>
            </tr>
             <tr>
                <td>Adress:</td>
                <td>
                    <input id="txtAdress" type="password" runat="server" /></td>
                <td>
                    <asp:RequiredFieldValidator ControlToValidate="txtUserPass"
                        Display="Dynamic" ErrorMessage="Cannot be empty." runat="server"
                        ID="RequiredFieldValidator4" EnableClientScript="false" />
                </td>
            </tr>
              <tr>
                <td>Age:</td>
                <td>
                    <input id="txtAge" type="password" runat="server" /></td>
                <td>
                    <asp:RequiredFieldValidator ControlToValidate="txtUserPass"
                        Display="Dynamic" ErrorMessage="Cannot be empty." runat="server"
                        ID="RequiredFieldValidator5" EnableClientScript="false" />
                </td>
            </tr>
        </table>
    </div>

    <asp:Button ID="Ok" CssClass="simple-button" OnClick="Ok_Click" Style="margin-top: 10px;" Text="Login"
        runat="server" />
    <p>
        <asp:Label ID="Msg" CssClass="text" ForeColor="red" runat="server" />
    </p>

</asp:Content>
