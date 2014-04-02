<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="AccessDeniedForm.aspx.cs" Inherits="Labs.Protected.AccessDeniedForm" MasterPageFile="~/LabsMaster.Master"%>

<asp:Content ID="Content2" ContentPlaceHolderID="body" runat="Server">
    <div style="padding-left: 5px;">
        <asp:Label ID="Label1" runat="server" CssClass="text" Text="Access denied. To get access to Protected folder full authentication required."></asp:Label>
        <br />
    </div>
</asp:Content>
