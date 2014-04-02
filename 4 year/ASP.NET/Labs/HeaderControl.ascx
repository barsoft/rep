<%@ Control Language="C#" AutoEventWireup="true" CodeBehind="HeaderControl.ascx.cs" Inherits="Labs.HeaderControl" %>

        <link rel="stylesheet" href="~/style.css" />
        <div style="background-color: #808080; margin-left: auto; margin-right: auto; width: 1000px;">
            <table style="background-color: #808080; width: inherit;">
                <tr>
                    <td style="width: 350px;">
                        <asp:LinkButton ID="HeaderLinkButton" Style="width: inherit;" runat="server" OnClick="HeaderLinkButton_Click">
                            <asp:Image Style="vertical-align: middle; text-align: center; width: inherit;" ID="Image1" runat="server" ImageUrl="~/images/logo.png" />
                        </asp:LinkButton>
                    </td>
                    <%
                        if (!String.IsNullOrEmpty(Title))
                        { %>
                    <td style="text-align: left;">
                        <h1 class="text" style="font-size: x-large; padding-left: 10px;">
                            <%=Title %>
                        </h1>
                    </td>
                    <%} %>
                    <td style="vertical-align: top;">
                        <%   if (HttpContext.Current.User != null && HttpContext.Current.User.Identity.IsAuthenticated)
                             { %>
                        <div class="text" style="text-align: right; font-size: small; padding-right: 10px;">
                            Hello,<%=HttpContext.Current.User.Identity.Name %>
                            <asp:LinkButton ID="LogoutLinkButton" runat="server" OnClick="LogoutLinkButton_Click">Logout</asp:LinkButton>
                        </div>
                        <%  }%>
                    </td>
                </tr>
                <%
                    if (IsIndexLinkVisible)
                    { %>
                <tr>
                    <td style="text-align: left; vertical-align: top;">
                        <a href="/" class="text" style="font-size: small; padding-left: 10px;">Go to Index
                        </a>
                    </td>
                </tr>
                <%} %>
            </table>
        </div>

