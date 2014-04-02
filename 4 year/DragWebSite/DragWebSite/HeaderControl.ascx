<%@ Control Language="C#" AutoEventWireup="true" CodeBehind="HeaderControl.ascx.cs" Inherits="DragWebSite.HeaderControl" %>

<link rel="stylesheet" href="~/style.css" />
<div style="background-color: #808080; margin-left: auto; margin-right: auto; width: 1000px;">
    <table style="background-color: #808080; width: inherit;">
        <tr>
            <td style="width: 350px;">
                <asp:LinkButton ID="HeaderLinkButton" Style="width: inherit;" runat="server" OnClick="HeaderLinkButton_Click">
                    <asp:Image Style="vertical-align: middle; text-align: center; width: inherit;" ID="Image1" runat="server" ImageUrl="Images/logo.png" />
                </asp:LinkButton>
            </td>
            <td class="simple-table">
                <asp:Label ID="Label2" runat="server" Text="Players online :"></asp:Label>
                <asp:Label ID="Label1" runat="server" Text="Label"></asp:Label>
            </td>
            <td style="vertical-align: top;">
                <%   if (HttpContext.Current.User != null && HttpContext.Current.User.Identity.IsAuthenticated)
                     { %>
                <div class="text" style="text-align: right; font-size: small; padding-right: 10px;">
                    Hello,<%=HttpContext.Current.User.Identity.Name %>
                    <asp:LinkButton ID="LogoutLinkButton" runat="server" OnClick="LogoutLinkButton_Click">Logout</asp:LinkButton>

                    <%  }
                     else
                     {%>
                    <table>
                        <tr>
                            <td>
                                <asp:LinkButton ID="SignInLinkButton" runat="server" OnClick="SignInLinkButton_Click">Sign in</asp:LinkButton>
                            <td>
                                <asp:LinkButton ID="SignUpLinkButton" runat="server" OnClick="SignUpLinkButton_Click">Sign up</asp:LinkButton>
                            </td>
                        </tr>
                    </table>
                    <%} %>
                </div>
            </td>
        </tr>
       
         <tr>
            <td>
                <%   if (HttpContext.Current.User != null && HttpContext.Current.User.Identity.IsAuthenticated)
                     { %>
                  <table>
                        <tr>
                            <td>
                                <asp:LinkButton ID="ProfileLinkButton" runat="server" OnClick="ProfileLinkButton_Click">Profile</asp:LinkButton>
                            <td>
                                <asp:LinkButton ID="StatisticsLinkButton" runat="server" OnClick="StatisticsLinkButton_Click">Statistics</asp:LinkButton>
                            </td>
                        </tr>
                    </table>
                <%} %>
            </td>
        </tr>
    </table>
    
</div>
