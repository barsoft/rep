<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="LoginForm.aspx.cs" Inherits="Labs.Protected.LoginForm" MasterPageFile="~/LabsMaster.Master" %>

<asp:Content ID="Content2" ContentPlaceHolderID="body" runat="Server">
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
                    <asp:CustomValidator ControlToValidate="txtUserName" ValidateEmptyText="True"
                        Display="Dynamic" ErrorMessage="Cannot be empty." runat="server" ID="vUserName"
                        ClientValidationFunction="checkInput" OnServerValidate="CustomValidator1_ServerValidate" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td>
                    <input id="txtUserPass" type="password" runat="server" /></td>
                <td>
                    <asp:CustomValidator ControlToValidate="txtUserPass" ValidateEmptyText="True"
                        Display="Dynamic" ErrorMessage="Cannot be empty." runat="server" ID="vUserPass"
                        ClientValidationFunction="checkInput" OnServerValidate="CustomValidator1_ServerValidate" />
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

    <script type="text/javascript">
        function checkInput(source, arguments) {
          
            var name = document.getElementById("<%=this.txtUserName.ClientID%>").value;
            //           document.form1.error_message.value = "";

            var pass = document.getElementById("<%=this.txtUserPass.ClientID%>").value;
            //           document.form1.error_message.value = "";
           
            if (pass.Length > 0 && name.Length > 0)
                arguments.IsValid = true;
            else arguments.IsValid = false;
        }
    </script>

</asp:Content>

