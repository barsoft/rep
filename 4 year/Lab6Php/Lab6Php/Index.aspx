<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Index.aspx.cs" Inherits="Lab6Php.Index" MasterPageFile="~/Lab6Php.Master" %>

<asp:Content ID="Content2" ContentPlaceHolderID="body" runat="Server">
    <asp:GridView ID="GridView1" CssClass="simple-table" runat="server" DataSourceID="EntityDataSource" AutoGenerateColumns="False" >
        <EmptyDataTemplate>true</EmptyDataTemplate>
        <Columns>
            <asp:BoundField DataField="Id" HeaderText="Id"  ControlStyle-Width="50px"/>
            <asp:BoundField DataField="Name" HeaderText="Name" ControlStyle-Width="100px" />
            <asp:BoundField DataField="Year" HeaderText="Year" ControlStyle-Width="50px"/>
            <asp:BoundField DataField="Subject" HeaderText="Subject" ControlStyle-Width="100px"/>
            <asp:BoundField DataField="Author" HeaderText="Author" ControlStyle-Width="100px"/>
            <asp:BoundField DataField="Publisher" HeaderText="Publisher" ControlStyle-Width="100px"/>
            <asp:CommandField ShowDeleteButton="True" ShowEditButton="True" />
        </Columns>
    </asp:GridView>
    <asp:EntityDataSource ID="EntityDataSource" runat="server" ConnectionString="name=DatabaseEntities"
         DefaultContainerName="DatabaseEntities" EnableFlattening="False" EntitySetName="Book"  EnableDelete="true" EnableUpdate="true" >
        
    </asp:EntityDataSource>

    <table>
        <tr>
            <td>
                <input class="button-normal" style="margin-top: 20px;" value="Filter" name="filter" onclick='window.location.replace("Filter.aspx");'/>
            </td>
            <td>
                <input class="button-normal" style="margin-top: 20px;" value="Insert" name="insert" onclick='window.location.replace("Insert.aspx");'/>
            </td>
        </tr>
    </table>
</asp:Content>
