<%@ Control Language="C#" AutoEventWireup="true" CodeBehind="ToolbarControl.ascx.cs" Inherits="Labs.Lab6.ToolbarControl" %>


<link rel="stylesheet" href="/style.css" />

<script type="text/javascript">
    var ShoppingCart = document.getElementsByName('ShoppingCart')[0];
    var OrdersHistory = document.getElementsByName('OrdersHistory')[0];
</script>

<asp:UpdatePanel ID="UpdatePanel1" runat="server">
    <ContentTemplate>

        <div style="text-align: Left; height: 50px; padding: 5px; padding-right: 15px;">
            <table>
                <tr>


                    <td>
                        <asp:LinkButton ID="ShoppingCartLinkButton" CssClass="text" Style="font-size: small" runat="server" OnClick="ShoppingCartLinkButton_Click"  >
                            <div onmouseover=" ShoppingCart.src = '/images/bin-active.png'" onmouseout="ShoppingCart.src = '/images/bin.png'">
                                <table border="0">
                                    <tr>
                                        <td>
                                            <img id="bin" name="ShoppingCart" style="width: 30px; height: 30px; margin-right: 0px;" runat="server" src="/images/bin.png" /></td>

                                    </tr>
                                    <tr>
                                        <td>Shopping cart</td>
                                        <td>
                                            <asp:Label ID="LabelCountOrders" runat="server" Text="Label"></asp:Label></td>
                                    </tr>
                                </table>
                            </div>
                        </asp:LinkButton>

                    </td>


                    <td>
                        <asp:LinkButton ID="OrdersHistoryLinkButton" CssClass="text" Style="font-size: small" runat="server" OnClick="OrdersHistoryLinkButton_Click" >
                            <div onmouseover=" OrdersHistory.src = '/images/bin-active.png'" onmouseout="OrdersHistory.src = '/images/bin.png'">
                                <table border="0">
                                    <tr>
                                        <td>
                                            <img id="Img1" name="OrdersHistory" style="width: 30px; height: 30px; margin-right: 0px;" runat="server" src="/images/bin.png" /></td>

                                    </tr>
                                    <tr>
                                        <td>Orders history</td>
                                </table>
                            </div>
                        </asp:LinkButton>
                    </td>

                </tr>
            </table>
        </div>
    </ContentTemplate>
</asp:UpdatePanel>
