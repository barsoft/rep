using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using System.Windows.Navigation;
using AlcoStore.Web;

namespace AlcoStore.Views
{
    public partial class Tables : Page
    {
        public Tables()
        {
            InitializeComponent();
        }

        // Выполняется, когда пользователь переходит на эту страницу.
        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////
        // UPDATE /////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////
        private void bankDataForm_EditEnded(object sender, DataFormEditEndedEventArgs e)
        {
            if (e.EditAction == DataFormEditAction.Commit)
            {

                bankDomainDataSource.SubmitChanges();

            }
        }
        private void contractorDataForm_EditEnded(object sender, DataFormEditEndedEventArgs e)
        {
            if (e.EditAction == DataFormEditAction.Commit)
            {

                contractorDomainDataSource.SubmitChanges();

            }
        }
        private void productDataForm_EditEnded(object sender, DataFormEditEndedEventArgs e)
        {
            if (e.EditAction == DataFormEditAction.Commit)
            {

                productDomainDataSource.SubmitChanges();

            }
        }
        private void stewardDataForm_EditEnded(object sender, DataFormEditEndedEventArgs e)
        {
            if (e.EditAction == DataFormEditAction.Commit)
            {

                stewardDomainDataSource.SubmitChanges();

            }
        }
        private void transactionDataForm_EditEnded(object sender, DataFormEditEndedEventArgs e)
        {
            if (e.EditAction == DataFormEditAction.Commit)
            {

                transactionDomainDataSource.SubmitChanges();

            }
        }
        private void transaction_Warehouse_ProductDataForm_EditEnded(object sender, DataFormEditEndedEventArgs e)
        {
            if (e.EditAction == DataFormEditAction.Commit)
            {

                transaction_Warehouse_ProductDomainDataSource.SubmitChanges();

            }
        }
        private void warehouseDataForm_EditEnded(object sender, DataFormEditEndedEventArgs e)
        {
            if (e.EditAction == DataFormEditAction.Commit)
            {

                warehouseDomainDataSource.SubmitChanges();

            }
        }
        private void workersDataForm_EditEnded(object sender, DataFormEditEndedEventArgs e)
        {
            if (e.EditAction == DataFormEditAction.Commit)
            {

                warehouseDomainDataSource.SubmitChanges();

            }
        }
        private void bankDataForm_DeletingItem(object sender, System.ComponentModel.CancelEventArgs e)
        {

            var ctx = bankDomainDataSource.DomainContext as DomainService1;

            DataForm form = sender as DataForm;

            form.CommitEdit();

            Bank ev = form.CurrentItem as Bank;

            ctx.Banks.Remove(ev);

            ctx.SubmitChanges((op) =>
            {

                if (op.HasError)
                {

                    MessageBox.Show(op.Error.Message);

                }

            }, null);

        }
        ///////////////////////////////////////////////////////////////////////////////////////////////
        // DELETE /////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////
        private void contractorDataForm_DeletingItem(object sender, System.ComponentModel.CancelEventArgs e)
        {

            var ctx = contractorDomainDataSource.DomainContext as DomainService1;

            DataForm form = sender as DataForm;

            form.CommitEdit();

            Contractor ev = form.CurrentItem as Contractor;

            ctx.Contractors.Remove(ev);

            ctx.SubmitChanges((op) =>
            {

                if (op.HasError)
                {

                    MessageBox.Show(op.Error.Message);

                }

            }, null);

        }
       
        private void productDataForm_DeletingItem(object sender, System.ComponentModel.CancelEventArgs e)
        {

            var ctx = productDomainDataSource.DomainContext as DomainService1;

            DataForm form = sender as DataForm;

            form.CommitEdit();

            Product ev = form.CurrentItem as Product;

            ctx.Products.Remove(ev);

            ctx.SubmitChanges((op) =>
            {

                if (op.HasError)
                {

                    MessageBox.Show(op.Error.Message);

                }

            }, null);

        }
       
        private void stewardDataForm_DeletingItem(object sender, System.ComponentModel.CancelEventArgs e)
        {

            var ctx = stewardDomainDataSource.DomainContext as DomainService1;

            DataForm form = sender as DataForm;

            form.CommitEdit();

            Steward ev = form.CurrentItem as Steward;

            ctx.Stewards.Remove(ev);

            ctx.SubmitChanges((op) =>
            {

                if (op.HasError)
                {

                    MessageBox.Show(op.Error.Message);

                }

            }, null);

        }
        
        private void transactionDataForm_DeletingItem(object sender, System.ComponentModel.CancelEventArgs e)
        {

            var ctx = transactionDomainDataSource.DomainContext as DomainService1;

            DataForm form = sender as DataForm;

            form.CommitEdit();

            Transaction ev = form.CurrentItem as Transaction;

            ctx.Transactions.Remove(ev);

            ctx.SubmitChanges((op) =>
            {

                if (op.HasError)
                {

                    MessageBox.Show(op.Error.Message);

                }

            }, null);

        }
      
        private void transaction_Warehouse_ProductDataForm_DeletingItem(object sender, System.ComponentModel.CancelEventArgs e)
        {

            var ctx = transaction_Warehouse_ProductDomainDataSource.DomainContext as DomainService1;

            DataForm form = sender as DataForm;

            form.CommitEdit();

            Transaction_Warehouse_Product ev = form.CurrentItem as Transaction_Warehouse_Product;

            ctx.Transaction_Warehouse_Products.Remove(ev);

            ctx.SubmitChanges((op) =>
            {

                if (op.HasError)
                {

                    MessageBox.Show(op.Error.Message);

                }

            }, null);

        }
     
        private void warehouseDataForm_DeletingItem(object sender, System.ComponentModel.CancelEventArgs e)
        {

            var ctx = warehouseDomainDataSource.DomainContext as DomainService1;

            DataForm form = sender as DataForm;

            form.CommitEdit();

            Warehouse ev = form.CurrentItem as Warehouse;

            ctx.Warehouses.Remove(ev);

            ctx.SubmitChanges((op) =>
            {

                if (op.HasError)
                {

                    MessageBox.Show(op.Error.Message);

                }

            }, null);

        }
        
        private void workersDataForm_DeletingItem(object sender, System.ComponentModel.CancelEventArgs e)
        {

            var ctx = workersDomainDataSource.DomainContext as DomainService1;

            DataForm form = sender as DataForm;

            form.CommitEdit();

            Workers ev = form.CurrentItem as Workers;

            ctx.Workers.Remove(ev);

            ctx.SubmitChanges((op) =>
            {

                if (op.HasError)
                {

                    MessageBox.Show(op.Error.Message);

                }

            }, null);

        }

        private void Page_Loaded(object sender, RoutedEventArgs e)
        {

        }
    }
}
