using System;
using System.Collections;
using System.Collections.Generic;
using System.Data;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;
using System.Xml.Serialization;

namespace Store_server
{
    class ClientConnection
    {
        private static int clientNumber = 0;
        private Socket socket;
        private SocketAsyncEventArgs sockAsyncEventArgs;
        private byte[] buff;
        private StoreDataSet db_server;
        private StreamWriter log;


        public ClientConnection(Socket AcceptedSocket, StoreDataSet db_server, StreamWriter log)
        {
            this.db_server = db_server;
            this.log = log;
            clientNumber++;
            buff = new byte[10024];
            socket = AcceptedSocket;
            sockAsyncEventArgs = new SocketAsyncEventArgs();
            sockAsyncEventArgs.Completed += SockAsyncEventArgs_Completed;
            sockAsyncEventArgs.SetBuffer(buff, 0, buff.Length);

            ReceiveAsync(sockAsyncEventArgs);
        }

        private void SockAsyncEventArgs_Completed(object sender, SocketAsyncEventArgs e)
        {
            switch (e.LastOperation)
            {
                case SocketAsyncOperation.Receive:
                    ProcessReceive(e);
                    break;
                case SocketAsyncOperation.Send:
                    ProcessSend(e);
                    break;
            }
        }
        private void ReceiveAsync(SocketAsyncEventArgs e)
        {
            bool willRaiseEvent = socket.ReceiveAsync(e);
            if (!willRaiseEvent)
                ProcessReceive(e);
        }
        private void ProcessReceive(SocketAsyncEventArgs e)
        {
            if (e.SocketError == SocketError.Success)
            {
                log = new StreamWriter(new FileStream("log.txt", FileMode.Append, FileAccess.Write, FileShare.ReadWrite));
                string str = Encoding.UTF8.GetString(e.Buffer, 0, e.BytesTransferred);
                string[] tmp = str.Split(' ');
                Console.WriteLine("{2} Client msg from #{0}({3}): {1}", clientNumber, str, DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                log.Write("\n");
                log.Write("{2} Client msg from #{0}({3}): {1}\n", clientNumber, str, DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                MemoryStream outtabl = new MemoryStream();
                switch (tmp[0].Trim())
                {
                    //////////////////////////////////////////////////////////////////////////////////
                    /// SELECT TABLES ////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////
                    case "select_tables":
                        selectTables();
                        db_server.WriteXml(outtabl, XmlWriteMode.WriteSchema);
                        Console.WriteLine("{2} Server msg to #{0}({3}): {1}", clientNumber, "success query select_tables", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        log.Write("{2} Server msg to #{0}({3}): {1}", clientNumber, "success query select_tables", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        break;
                    //////////////////////////////////////////////////////////////////////////////////
                    /// INSERT ///////////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////
                    case "insert_instances":
                        insertInstances(int.Parse(tmp[1].Trim()), int.Parse(tmp[2].Trim()), tmp[3].Trim(), tmp[4].Trim(), bool.Parse(tmp[5].Trim()));
                        selectTables();
                        db_server.WriteXml(outtabl, XmlWriteMode.WriteSchema);
                        Console.WriteLine("{2} Server msg to #{0}({3}): {1}", clientNumber, "success query insert_instances", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        log.Write("{2} Server msg to #{0}({3}): {1}", clientNumber, "success query insert_instances", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        break;
                    case "insert_manufacturers":
                        insertManufacturers(tmp[1].Trim(), tmp[2].Trim(), tmp[3].Trim(), tmp[4].Trim());
                        selectTables();
                        db_server.WriteXml(outtabl, XmlWriteMode.WriteSchema);
                        Console.WriteLine("{2} Server msg to #{0}({3}): {1}", clientNumber, "success query insert_manufacturers", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        log.Write("{2} Server msg to #{0}({3}): {1}", clientNumber, "success query insert_manufacturers", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        break;
                    case "insert_products":
                        insertProducts(tmp[1].Trim(), tmp[2].Trim(), int.Parse(tmp[3].Trim()), double.Parse(tmp[4].Trim()));
                        selectTables();
                        db_server.WriteXml(outtabl, XmlWriteMode.WriteSchema);
                        Console.WriteLine("{2} Server msg to #{0}({3}): {1}", clientNumber, "success query insert_products", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        log.Write("{2} Server msg to #{0}({3}): {1}", clientNumber, "success query insert_products", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        break;
                    //////////////////////////////////////////////////////////////////////////////////
                    /// DELETE ///////////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////
                    case "delete_instances":
                        deleteInstances(int.Parse(tmp[1].Trim()));
                        selectTables();
                        db_server.WriteXml(outtabl, XmlWriteMode.WriteSchema);
                        Console.WriteLine("{2} Server msg to #{0}({3}): {1}", clientNumber, "success query delete_instances", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        log.Write("{2} Server msg to #{0}({3}): {1}", clientNumber, "success query delete_instances", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        break;
                    case "delete_manufacturers":
                        deleteManufacturers(int.Parse(tmp[1].Trim()));
                        selectTables();
                        db_server.WriteXml(outtabl, XmlWriteMode.WriteSchema);
                        Console.WriteLine("{2} Server msg to #{0}({3}): {1}", clientNumber, "success query delete_manufacturers", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        log.Write("{2} Server msg to #{0}({3}): {1}", clientNumber, "success query delete_manufacturers", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        break;
                    case "delete_products":
                        deleteProducts(int.Parse(tmp[1].Trim()));
                        selectTables();
                        db_server.WriteXml(outtabl, XmlWriteMode.WriteSchema);
                        Console.WriteLine("{2} Server msg to #{0}({3}): {1}", clientNumber, "success query delete_products", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        log.Write("{2} Server msg to #{0}({3}): {1}", clientNumber, "success query delete_products", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        log.Close();
                        return;
                    //////////////////////////////////////////////////////////////////////////////////
                    /// UPDATE ///////////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////
                    case "update_instances":
                        updateInstances(int.Parse(tmp[1].Trim()), int.Parse(tmp[2].Trim()), tmp[3].Trim(), tmp[4].Trim(), bool.Parse(tmp[5].Trim()), int.Parse(tmp[6].Trim()));
                        selectTables();
                        db_server.WriteXml(outtabl, XmlWriteMode.WriteSchema);
                        Console.WriteLine("{2} Server msg to #{0}({3}): {1}", clientNumber, "success query update_instances", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        log.Write("{2} Server msg to #{0}({3}): {1}", clientNumber, "success query update_instances", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        break;
                    case "update_manufacturers":
                        updateManufacturers(tmp[1].Trim(), tmp[2].Trim(), tmp[3].Trim(), tmp[4].Trim(), int.Parse(tmp[5].Trim()));
                        selectTables();
                        db_server.WriteXml(outtabl, XmlWriteMode.WriteSchema);
                        Console.WriteLine("{2} Server msg to #{0}({3}): {1}", clientNumber, "success query insert_manufacturers", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        log.Write("{2} Server msg to #{0}({3}): {1}", clientNumber, "success query update_manufacturers", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        break;
                    case "update_products":
                        updateProducts(tmp[1].Trim(), tmp[2].Trim(), int.Parse(tmp[3].Trim()), double.Parse(tmp[4].Trim()), int.Parse(tmp[5].Trim()));
                        selectTables();
                        db_server.WriteXml(outtabl, XmlWriteMode.WriteSchema);
                        Console.WriteLine("{2} Server msg to #{0}({3}): {1}", clientNumber, "success query insert_products", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        log.Write("{2} Server msg to #{0}({3}): {1}", clientNumber, "success query update_products", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        break;
                    //////////////////////////////////////////////////////////////////////////////////
                    /// FILTER ///////////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////
                    case "filter_instances":
                        DataSet dataset = new DataSet("filter_instances");
                        dataset.Tables.Add(filterInstances(tmp[1].Trim(), tmp[2].Trim(), tmp[3].Trim(), tmp[4].Trim()));
                        dataset.WriteXml(outtabl, XmlWriteMode.WriteSchema);
                        Console.WriteLine("{2} Server msg to #{0}({3}): {1}", clientNumber, "success query filter_instances", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        log.Write("{2} Server msg to #{0}({3}): {1}", clientNumber, "success query filter_instances", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        break;
                    case "filter_manufacturers":
                         dataset = new DataSet("filter_manufacturers");
                        dataset.Tables.Add(filterManufacturers(tmp[1].Trim(), tmp[2].Trim(), tmp[3].Trim(), tmp[4].Trim()));
                        dataset.WriteXml(outtabl, XmlWriteMode.WriteSchema);
                        Console.WriteLine("{2} Server msg to #{0}({3}): {1}", clientNumber, "success query filter_manufacturers", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        log.Write("{2} Server msg to #{0}({3}): {1}", clientNumber, "success query filter_manufacturers", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        break;
                    case "filter_products":
                         dataset = new DataSet("filter_products");
                        dataset.Tables.Add(filterProducts(tmp[1].Trim(), tmp[2].Trim(), tmp[3].Trim(), tmp[4].Trim()));
                        dataset.WriteXml(outtabl, XmlWriteMode.WriteSchema);
                        Console.WriteLine("{2} Server msg to #{0}({3}): {1}", clientNumber, "success query filter_products", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        log.Write("{2} Server msg to #{0}({3}): {1}", clientNumber, "success query filter_products", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        break;
                    /* case "select_hardfolder":
                         DataSet dataset = new DataSet("DataSet1");
                         dataset.Tables.Add(select_hardfolder(tmp[1].Trim(),tmp[2].Trim(),tmp[3].Trim()));
                         dataset.WriteXml(outtabl, XmlWriteMode.WriteSchema);
                         Console.WriteLine("{2} Server msg to #{0}({3}): {1}", ClientNumber, "success query select_hardfolder", DateTime.Now, (Sock.RemoteEndPoint as IPEndPoint).Address);
                         log.Write("{2} Server msg to #{0}({3}): {1}", ClientNumber, "success query select_hardfolder", DateTime.Now, (Sock.RemoteEndPoint as IPEndPoint).Address);
                         break;
                     case "select_search_column":
                         DataSet dataset2 = new DataSet("DataSet1");
                         dataset2.Tables.Add(select_search_column(int.Parse(tmp[1].Trim()), int.Parse(tmp[2].Trim()), tmp[3].Trim()));
                         dataset2.WriteXml(outtabl, XmlWriteMode.WriteSchema);
                         Console.WriteLine("{2} Server msg to #{0}({3}): {1}", ClientNumber, "success query select_search_column", DateTime.Now, (Sock.RemoteEndPoint as IPEndPoint).Address);
                         log.Write("{2} Server msg to #{0}({3}): {1}", ClientNumber, "success query select_search_column", DateTime.Now, (Sock.RemoteEndPoint as IPEndPoint).Address);
                         break;
                     case "select_search_combined":
                         DataSet dataset3 = new DataSet("DataSet1");
                         dataset3.Tables.Add(select_search_combined(int.Parse(tmp[1].Trim()), tmp[2].Trim(), tmp[3].Trim(), tmp[4].Trim(), tmp[5].Trim(), tmp[6].Trim(), tmp[7].Trim(), tmp[8].Trim()));
                         dataset3.WriteXml(outtabl, XmlWriteMode.WriteSchema);
                         Console.WriteLine("{2} Server msg to #{0}({3}): {1}", ClientNumber, "success query select_search_combined", DateTime.Now, (Sock.RemoteEndPoint as IPEndPoint).Address);
                         log.Write("{2} Server msg to #{0}({3}): {1}", ClientNumber, "success query select_search_combined", DateTime.Now, (Sock.RemoteEndPoint as IPEndPoint).Address);
                         break;*/
                    default:
                        Console.WriteLine("{2} Server msg to #{0}({3}): {1}", clientNumber, "incorrect query", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        SendAsync("0");
                        log.Write("{2} Server msg to #{0}({3}): {1}", clientNumber, "incorrect query", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                        log.Close();
                        return;
                }
                log.Close();
                SendAsync(outtabl);
            }
        }
        private void SendAsync(string data)
        {
            byte[] buff = Encoding.UTF8.GetBytes(data);
            SocketAsyncEventArgs e = new SocketAsyncEventArgs();
            e.Completed += SockAsyncEventArgs_Completed;
            e.SetBuffer(buff, 0, buff.Length);
            SendAsync(e);
        }
        private void SendAsync(MemoryStream data)
        {
            Byte[] buff = data.ToArray();
            SocketAsyncEventArgs e = new SocketAsyncEventArgs();
            e.Completed += SockAsyncEventArgs_Completed;
            e.SetBuffer(buff, 0, buff.Length);
            SendAsync(e);
        }
        private void SendAsync(SocketAsyncEventArgs e)
        {
            bool willRaiseEvent = socket.SendAsync(e);
            if (!willRaiseEvent)
                ProcessSend(e);
        }
        private void ProcessSend(SocketAsyncEventArgs e)
        {
            if (e.SocketError == SocketError.Success)
                ReceiveAsync(sockAsyncEventArgs);
            else
            {
                Console.WriteLine("{2} Server msg to #{0}({3}): {1}", clientNumber, "Connection lost. Could not send data to client.", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
                log.Write("{2} Server msg to #{0}({3}): {1}", clientNumber, "Connection lost. Could not send data to client.", DateTime.Now, (socket.RemoteEndPoint as IPEndPoint).Address);
            }

        }
        //////////////////////////////////////////////////////////////////////////////////
        /// SELECT TABLES ////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////
        private void selectTables()
        {
            StoreDataSetTableAdapters.InstancesTableAdapter instancesTableAdapter = new StoreDataSetTableAdapters.InstancesTableAdapter();
            StoreDataSetTableAdapters.ManufacturersTableAdapter manufacturersTableAdapter = new StoreDataSetTableAdapters.ManufacturersTableAdapter();
            StoreDataSetTableAdapters.ProductsTableAdapter productsTableAdapter = new StoreDataSetTableAdapters.ProductsTableAdapter();

            instancesTableAdapter.Fill(db_server.Instances);
            manufacturersTableAdapter.Fill(db_server.Manufacturers);
            productsTableAdapter.Fill(db_server.Products);
        }
        //////////////////////////////////////////////////////////////////////////////////
        /// INSERT ///////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////
        private void insertInstances(int product_id, int count, string delivery_date, string export_date, bool presence)
        {
            StoreDataSetTableAdapters.InstancesTableAdapter instancesTableAdapter = new StoreDataSetTableAdapters.InstancesTableAdapter();
            instancesTableAdapter.Insert(product_id, count, delivery_date, export_date, presence);
            instancesTableAdapter.Fill(db_server.Instances);
            db_server.Instances.AcceptChanges();
        }

        private void insertManufacturers(string name, string contact_number, string contact_person, string office_adress)
        {
            StoreDataSetTableAdapters.ManufacturersTableAdapter manufacturersTableAdapter = new StoreDataSetTableAdapters.ManufacturersTableAdapter();
            manufacturersTableAdapter.Insert(name, contact_number, contact_person, office_adress);
            manufacturersTableAdapter.Fill(db_server.Manufacturers);
            db_server.Manufacturers.AcceptChanges();
        }

        private void insertProducts(string name, string type, int manufacturer_id, double price_per_instance)
        {
            StoreDataSetTableAdapters.ProductsTableAdapter productsTableAdapter = new StoreDataSetTableAdapters.ProductsTableAdapter();
            productsTableAdapter.Insert(type, manufacturer_id, name, price_per_instance);
            productsTableAdapter.Fill(db_server.Products);
            db_server.Products.AcceptChanges();
        }
        //////////////////////////////////////////////////////////////////////////////////
        /// DELETE ///////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////
        private void deleteInstances(int Original_id)
        {
            StoreDataSetTableAdapters.InstancesTableAdapter instancesTableAdapter = new StoreDataSetTableAdapters.InstancesTableAdapter();
            instancesTableAdapter.DeleteInstance(Original_id);
            instancesTableAdapter.Fill(db_server.Instances);
            db_server.Instances.AcceptChanges();
        }
        private void deleteManufacturers(int Original_id)
        {
            StoreDataSetTableAdapters.ManufacturersTableAdapter manufacturersTableAdapter = new StoreDataSetTableAdapters.ManufacturersTableAdapter();
            manufacturersTableAdapter.DeleteManufacturer(Original_id);
            manufacturersTableAdapter.Fill(db_server.Manufacturers);
            db_server.Instances.AcceptChanges();
        }
        private void deleteProducts(int Original_id)
        {
            StoreDataSetTableAdapters.ProductsTableAdapter productsTableAdapter = new StoreDataSetTableAdapters.ProductsTableAdapter();
            productsTableAdapter.DeleteProduct(Original_id);
            productsTableAdapter.Fill(db_server.Products);
            db_server.Instances.AcceptChanges();
        }
        //////////////////////////////////////////////////////////////////////////////////
        /// UPDATE ///////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////
        private void updateInstances(int product_id, int count, string delivery_date, string export_date, bool presence, int Original_id)
        {
            StoreDataSetTableAdapters.InstancesTableAdapter instancesTableAdapter = new StoreDataSetTableAdapters.InstancesTableAdapter();
            instancesTableAdapter.UpdateInstance(product_id, count, delivery_date, export_date, presence, Original_id);
            instancesTableAdapter.Fill(db_server.Instances);
            db_server.Instances.AcceptChanges();
        }
        private void updateManufacturers(string name, string contact_number, string contact_person, string office_adress, int Original_id)
        {
            StoreDataSetTableAdapters.ManufacturersTableAdapter manufacturersTableAdapter = new StoreDataSetTableAdapters.ManufacturersTableAdapter();
            manufacturersTableAdapter.UpdateManufacturer(name, contact_number, contact_person, office_adress, Original_id);
            manufacturersTableAdapter.Fill(db_server.Manufacturers);
            db_server.Instances.AcceptChanges();
        }
        private void updateProducts(string name, string type, int manufacturer_id, double price_per_instance, int Original_id)
        {
            StoreDataSetTableAdapters.ProductsTableAdapter productsTableAdapter = new StoreDataSetTableAdapters.ProductsTableAdapter();
            productsTableAdapter.UpdateProduct(type, manufacturer_id, name, price_per_instance, Original_id);
            productsTableAdapter.Fill(db_server.Products);
            db_server.Instances.AcceptChanges();
        }
        //////////////////////////////////////////////////////////////////////////////////
        /// FILTER ///////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////

        private DataTable filterInstances(string product_id, string count, string delivery_date, string export_date)
        {
            StoreDataSetTableAdapters.InstancesTableAdapter instancesTableAdapter = new StoreDataSetTableAdapters.InstancesTableAdapter();

            IEnumerable<StoreDataSet.InstancesRow> query;

            if (delivery_date.Contains("null"))
            {
                delivery_date = "";
            }
            if (export_date.Contains("null"))
            {
                export_date = "";
            }

            if (count.Contains("null"))
            {
                count = "";                
            }
            if (product_id.Contains("null"))
            {
                product_id = "";
            }

            query = (from instance in db_server.Instances
                     where (instance.product_id.ToString().Contains(product_id) && instance.count.ToString().Contains(count)
                     && instance.delivery_date.Contains(delivery_date) && instance.export_date.Contains(export_date))
                     select instance);

            StoreDataSet.InstancesDataTable result = new StoreDataSet.InstancesDataTable();
            result.TableName = "result_query";

            foreach (StoreDataSet.InstancesRow res in query)
            {
                result.Rows.Add(res.id, res.product_id, res.count, res.delivery_date, res.export_date);
            }

            return result;

        }
        private DataTable filterManufacturers(string name, string contact_number, string contact_person, string office_adress)
        {
            StoreDataSetTableAdapters.InstancesTableAdapter instancesTableAdapter = new StoreDataSetTableAdapters.InstancesTableAdapter();

            IEnumerable<StoreDataSet.ManufacturersRow> query;

            if (name.Contains("null"))
            {
                name = "";
            }
            if (contact_number.Contains("null"))
            {
                contact_number = "";
            }
            if (contact_person.Contains("null"))
            {
                contact_person = "";
            }
            if (office_adress.Contains("null"))
            {
                office_adress = "";
            }

            query = (from manufacturer in db_server.Manufacturers
                     where (manufacturer.name.ToString().Contains(name) && manufacturer.contact_number.ToString().Contains(contact_number)
                     && manufacturer.contact_person.Contains(contact_person) && manufacturer.office_adress.Contains(office_adress))
                     select manufacturer);

            StoreDataSet.ManufacturersDataTable result = new StoreDataSet.ManufacturersDataTable();
            result.TableName = "result_query";

            foreach (StoreDataSet.ManufacturersRow res in query)
            {
                result.Rows.Add(res.id, res.name, res.contact_number, res.contact_person, res.office_adress);
            }

            return result;

        }
        private DataTable filterProducts(string name, string type,  string price_per_instance, string manufacturer_id)
        {
            StoreDataSetTableAdapters.InstancesTableAdapter instancesTableAdapter = new StoreDataSetTableAdapters.InstancesTableAdapter();

            IEnumerable<StoreDataSet.ProductsRow> query;

            if (manufacturer_id.Contains("null"))
            {
                manufacturer_id = "";
            }
            if (price_per_instance.Contains("null"))
            {
                price_per_instance = "";
            }
            if (type.Contains("null"))
            {
                type = "";
            }
            if (name.Contains("null"))
            {
                name = "";
            }

            query = (from product in db_server.Products
                     where (product.name.ToString().Contains(name) && product.type.ToString().Contains(type)
                     && product.manufacturer_id.ToString().Contains(manufacturer_id) && product.price_per_instance.ToString().Contains(price_per_instance))
                     select product);

            StoreDataSet.ProductsDataTable result = new StoreDataSet.ProductsDataTable();
            result.TableName = "result_query";

            foreach (StoreDataSet.ProductsRow res in query)
            {
                result.Rows.Add(res.id,res.type , res.manufacturer_id,res.name,  res.price_per_instance);
            }

            return result;

        }

        /*
                private DataTable select_hardfolder(string compName,string local_ip, string folderName)
                {
                    if (compName == "null")
                        compName = "";
                    if (local_ip == "null")
                        local_ip = "";
                    if (folderName == "null")
                        folderName = "";
                    GetTables();
                    DataTable result = new DataTable("result_query");
                    result.Columns.Add("Folder_name");
                    result.Columns.Add("Location");
                    result.Columns.Add("Computer_name");
                    result.Columns.Add("IP_Local");
                    var tmp2 = (from listCompute in db_server.listCompute
                                where (listCompute.name.Contains(compName)) && (listCompute.local_ip.Contains(local_ip))
                                join listfolder in
                                    (from listfolder in db_server.listfolder where listfolder.name.Contains(folderName) select listfolder) on listCompute.id equals listfolder.nameComputer
                                select new Result() { Folder_name = listfolder.name, Location = listfolder.location, Computer_name = listCompute.name, IP_Local = listCompute.local_ip });
                    foreach (Result aa in tmp2)
                    {
                        result.Rows.Add(aa.Folder_name, aa.Location, aa.Computer_name, aa.IP_Local);
                    }
                    return result;
                }
                private DataTable select_search_column(int idtable, int idcolumn, string search)
                {
                    if (search == "null")
                        search = "";
                    GetTables();
                    DataTable result = new DataTable("result_query");

                    switch (idtable)
                    {
                        case 0:
                            var tmp = (from listimgs in db_server.listimgs select listimgs);
                            switch (idcolumn)
                            {
                                case 0:
                                    tmp = (from listimgs in db_server.listimgs where listimgs.name.Contains(search) select listimgs);
                                    result = tmp.CopyToDataTable();
                                    break;
                                case 1:
                                    tmp = (from listimgs in db_server.listimgs where listimgs.size_pixel.Contains(search) select listimgs);
                                    result = tmp.CopyToDataTable();
                                    break;
                                case 2:
                                    tmp = (from listimgs in db_server.listimgs where listimgs.size_file.Contains(search) select listimgs);
                                    result = tmp.CopyToDataTable();
                                    break;
                            }
                            break;
                        case 1:
                            var tmp1 = (from listimgs_format_img in db_server.listimgs_format_img select listimgs_format_img);
                            tmp1 = (from listimgs_format_img in db_server.listimgs_format_img where listimgs_format_img.name.Contains(search) select listimgs_format_img);
                            result = tmp1.CopyToDataTable();
                            break;
                        case 2:
                            var tmp2 = (from listimgs_type in db_server.listimgs_type select listimgs_type);
                            tmp2 = (from listimgs_type in db_server.listimgs_type where listimgs_type.name.Contains(search) select listimgs_type);
                            result = tmp2.CopyToDataTable();
                            break;
                        case 3:
                            var tmp3 = (from listCompute in db_server.listCompute select listCompute);
                            switch (idcolumn)
                            {
                                case 0:
                                    tmp3 = (from listCompute in db_server.listCompute where listCompute.name.Contains(search) select listCompute);
                                    result = tmp3.CopyToDataTable();
                                    break;
                                case 1:
                                    tmp3 = (from listCompute in db_server.listCompute where listCompute.local_ip.Contains(search) select listCompute);
                                    result = tmp3.CopyToDataTable();
                                    break;
                            }
                            break;
                        case 4:
                            var tmp4 = (from listfolder in db_server.listfolder select listfolder);
                            switch (idcolumn)
                            {
                                case 0:
                                    tmp4 = (from listfolder in db_server.listfolder where listfolder.name.Contains(search) select listfolder);
                                    result = tmp4.CopyToDataTable();
                                    break;
                                case 1:
                                    tmp4 = (from listfolder in db_server.listfolder where listfolder.size_folder.Contains(search) select listfolder);
                                    result = tmp4.CopyToDataTable();
                                    break;
                                case 2:
                                    tmp4 = (from listfolder in db_server.listfolder where listfolder.location.Contains(search) select listfolder);
                                    result = tmp4.CopyToDataTable();
                                    break;
                            }
                            break;
                    }
                    result.TableName = "result_query";
                    return result;
                }
                private DataTable select_search_combined(int idtable,string pole,string pole1,string pole2,string pole3,string pole4,string pole5,string pole6)
                {
                    GetTables();
                    DataTable result = new DataTable("result_query");
                    DateTime datatmp = new DateTime();
                    if (pole3 != "null_result")
                    {
                        datatmp = new DateTime(long.Parse(pole3));
                    }
                    switch (idtable)
                    {
                        case 0:
                            var tmp1 = (from listimgs in db_server.listimgs select listimgs);
                            int tmpint;
                            if (pole != "null_result")
                                tmp1 = (from listimgs in tmp1 where listimgs.name.Contains(pole) select listimgs);
                            if (pole1 != "null_result")
                                tmp1 = (from listimgs in tmp1 where listimgs.size_pixel.Contains(pole1) select listimgs);
                            if (pole2 != "null_result")
                                tmp1 = (from listimgs in tmp1 where listimgs.size_file.Contains(pole2) select listimgs);
                            if (pole3 != "null_result")
                                tmp1 = (from listimgs in tmp1 where ((listimgs.data.Year == datatmp.Year) && (listimgs.data.Month == datatmp.Month) && (listimgs.data.Day == datatmp.Day)) select listimgs);
                            if (pole4 != "null_result")
                                {
                                    tmpint = (from listimgs_format_img in db_server.listimgs_format_img select listimgs_format_img.id).ToList().ElementAt(int.Parse(pole4));
                                    tmp1 = (from listimgs in tmp1 where (listimgs.format == tmpint) select listimgs);
                                }
                            if (pole5 != "null_result")
                                {
                                    tmpint = (from listimgs_type in db_server.listimgs_type select listimgs_type.id).ToList().ElementAt(int.Parse(pole5));
                                    tmp1 = (from listimgs in tmp1 where (listimgs.type_img== tmpint) select listimgs);
                                }
                            if (pole6 != "null_result")
                                {
                                    tmpint = (from listfolder in db_server.listfolder select listfolder.id).ToList().ElementAt(int.Parse(pole6));
                                    tmp1 = (from listimgs in tmp1 where (listimgs.folder == tmpint) select listimgs);
                                }
                            result = tmp1.CopyToDataTable();
                            break;
                        case 1:
                            var tmp2 = (from listCompute in db_server.listCompute select listCompute);
                            if (pole != "null_result")
                                tmp2 = (from listCompute in tmp2 where listCompute.name.Contains(pole) select listCompute);
                            if (pole1 != "null_result")
                                tmp2 = (from listCompute in tmp2 where listCompute.local_ip.Contains(pole1) select listCompute);
                                result = tmp2.CopyToDataTable();
                              break;
                        case 2:
                            var tmp3 = (from listfolder in db_server.listfolder select listfolder);
                            if (pole != "null_result")
                                tmp3 = (from listfolder in tmp3 where listfolder.name.Contains(pole) select listfolder);
                            if (pole1 != "null_result")
                                tmp3 = (from listfolder in tmp3 where listfolder.size_folder.Contains(pole1) select listfolder);
                            if (pole2 != "null_result")
                                tmp3 = (from listfolder in tmp3 where listfolder.location.Contains(pole2) select listfolder);
                            if (pole3 != "null_result")
                                tmp3 = (from listfolder in tmp3 where ((listfolder.data_create.Year == datatmp.Year) && (listfolder.data_create.Month == datatmp.Month) && (listfolder.data_create.Day == datatmp.Day)) select listfolder);
                            if (pole4 != "null_result")
                            {
                                int tmpint4 = (from listCompute in db_server.listCompute select listCompute.id).ToList().ElementAt(int.Parse(pole4));
                                tmp3 = (from listfolder in tmp3 where (listfolder.nameComputer == tmpint4) select listfolder);
                            }
                                result = tmp3.CopyToDataTable();
                                break;
                    }

                    result.TableName = "result_query";
                    return result;
                }
                //private void get_table_listimgs()
                //{
                //    laba5.DataSet1TableAdapters.listimgsTableAdapter listimgTAdapter = new DataSet1TableAdapters.listimgsTableAdapter();
                //    listimgTAdapter.Fill(db_server.listimgs);
                //}
                */
    }
    public class Result
    {
        public string Folder_name { get; set; }
        public string Location { get; set; }
        public string Computer_name { get; set; }
        public string IP_Local { get; set; }
    };
}
