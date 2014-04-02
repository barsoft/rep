
namespace AlcoStore.Web
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel;
    using System.ComponentModel.DataAnnotations;
    using System.Data;
    using System.Linq;
    using System.ServiceModel.DomainServices.EntityFramework;
    using System.ServiceModel.DomainServices.Hosting;
    using System.ServiceModel.DomainServices.Server;


    // Реализует логику приложения с использованием контекста AlcoStoreDBEntities.
    // TODO: добавьте свою прикладную логику в эти или другие методы.
    // TODO: включите проверку подлинности (Windows/ASP.NET Forms) и раскомментируйте следующие строки, чтобы запретить анонимный доступ
    // Кроме того, рассмотрите возможность добавления ролей для соответствующего ограничения доступа.
    // [RequiresAuthentication]
    [EnableClientAccess()]
    public class DomainService1 : LinqToEntitiesDomainService<AlcoStoreDBEntities>
    {

        // TODO:
        // рассмотрите возможность сокращения результатов метода запроса.  Если необходим дополнительный ввод,
        // то в этот метод можно добавить параметры или создать дополнительные методы выполнения запроса с другими именами.
        // Для поддержки разбиения на страницы добавьте упорядочение в запрос "Bank".
        public IQueryable<Bank> GetBank()
        {
            return this.ObjectContext.Bank;
        }

        public void InsertBank(Bank bank)
        {
            if ((bank.EntityState != EntityState.Detached))
            {
                this.ObjectContext.ObjectStateManager.ChangeObjectState(bank, EntityState.Added);
            }
            else
            {
                this.ObjectContext.Bank.AddObject(bank);
            }
        }

        public void UpdateBank(Bank currentBank)
        {
            this.ObjectContext.Bank.AttachAsModified(currentBank, this.ChangeSet.GetOriginal(currentBank));
        }

        public void DeleteBank(Bank bank)
        {
            if ((bank.EntityState != EntityState.Detached))
            {
                this.ObjectContext.ObjectStateManager.ChangeObjectState(bank, EntityState.Deleted);
            }
            else
            {
                this.ObjectContext.Bank.Attach(bank);
                this.ObjectContext.Bank.DeleteObject(bank);
            }
        }

        // TODO:
        // рассмотрите возможность сокращения результатов метода запроса.  Если необходим дополнительный ввод,
        // то в этот метод можно добавить параметры или создать дополнительные методы выполнения запроса с другими именами.
        // Для поддержки разбиения на страницы добавьте упорядочение в запрос "Contractor".
        public IQueryable<Contractor> GetContractor()
        {
            return this.ObjectContext.Contractor;
        }

        public void InsertContractor(Contractor contractor)
        {
            if ((contractor.EntityState != EntityState.Detached))
            {
                this.ObjectContext.ObjectStateManager.ChangeObjectState(contractor, EntityState.Added);
            }
            else
            {
                this.ObjectContext.Contractor.AddObject(contractor);
            }
        }

        public void UpdateContractor(Contractor currentContractor)
        {
            this.ObjectContext.Contractor.AttachAsModified(currentContractor, this.ChangeSet.GetOriginal(currentContractor));
        }

        public void DeleteContractor(Contractor contractor)
        {
            if ((contractor.EntityState != EntityState.Detached))
            {
                this.ObjectContext.ObjectStateManager.ChangeObjectState(contractor, EntityState.Deleted);
            }
            else
            {
                this.ObjectContext.Contractor.Attach(contractor);
                this.ObjectContext.Contractor.DeleteObject(contractor);
            }
        }

        // TODO:
        // рассмотрите возможность сокращения результатов метода запроса.  Если необходим дополнительный ввод,
        // то в этот метод можно добавить параметры или создать дополнительные методы выполнения запроса с другими именами.
        // Для поддержки разбиения на страницы добавьте упорядочение в запрос "Product".
        public IQueryable<Product> GetProduct()
        {
            return this.ObjectContext.Product;
        }

        public void InsertProduct(Product product)
        {
            if ((product.EntityState != EntityState.Detached))
            {
                this.ObjectContext.ObjectStateManager.ChangeObjectState(product, EntityState.Added);
            }
            else
            {
                this.ObjectContext.Product.AddObject(product);
            }
        }

        public void UpdateProduct(Product currentProduct)
        {
            this.ObjectContext.Product.AttachAsModified(currentProduct, this.ChangeSet.GetOriginal(currentProduct));
        }

        public void DeleteProduct(Product product)
        {
            if ((product.EntityState != EntityState.Detached))
            {
                this.ObjectContext.ObjectStateManager.ChangeObjectState(product, EntityState.Deleted);
            }
            else
            {
                this.ObjectContext.Product.Attach(product);
                this.ObjectContext.Product.DeleteObject(product);
            }
        }

        // TODO:
        // рассмотрите возможность сокращения результатов метода запроса.  Если необходим дополнительный ввод,
        // то в этот метод можно добавить параметры или создать дополнительные методы выполнения запроса с другими именами.
        // Для поддержки разбиения на страницы добавьте упорядочение в запрос "Steward".
        public IQueryable<Steward> GetSteward()
        {
            return this.ObjectContext.Steward;
        }

        public void InsertSteward(Steward steward)
        {
            if ((steward.EntityState != EntityState.Detached))
            {
                this.ObjectContext.ObjectStateManager.ChangeObjectState(steward, EntityState.Added);
            }
            else
            {
                this.ObjectContext.Steward.AddObject(steward);
            }
        }

        public void UpdateSteward(Steward currentSteward)
        {
            this.ObjectContext.Steward.AttachAsModified(currentSteward, this.ChangeSet.GetOriginal(currentSteward));
        }

        public void DeleteSteward(Steward steward)
        {
            if ((steward.EntityState != EntityState.Detached))
            {
                this.ObjectContext.ObjectStateManager.ChangeObjectState(steward, EntityState.Deleted);
            }
            else
            {
                this.ObjectContext.Steward.Attach(steward);
                this.ObjectContext.Steward.DeleteObject(steward);
            }
        }

        // TODO:
        // рассмотрите возможность сокращения результатов метода запроса.  Если необходим дополнительный ввод,
        // то в этот метод можно добавить параметры или создать дополнительные методы выполнения запроса с другими именами.
        // Для поддержки разбиения на страницы добавьте упорядочение в запрос "Transaction".
        public IQueryable<Transaction> GetTransaction()
        {
            return this.ObjectContext.Transaction;
        }

        public void InsertTransaction(Transaction transaction)
        {
            if ((transaction.EntityState != EntityState.Detached))
            {
                this.ObjectContext.ObjectStateManager.ChangeObjectState(transaction, EntityState.Added);
            }
            else
            {
                this.ObjectContext.Transaction.AddObject(transaction);
            }
        }

        public void UpdateTransaction(Transaction currentTransaction)
        {
            this.ObjectContext.Transaction.AttachAsModified(currentTransaction, this.ChangeSet.GetOriginal(currentTransaction));
        }

        public void DeleteTransaction(Transaction transaction)
        {
            if ((transaction.EntityState != EntityState.Detached))
            {
                this.ObjectContext.ObjectStateManager.ChangeObjectState(transaction, EntityState.Deleted);
            }
            else
            {
                this.ObjectContext.Transaction.Attach(transaction);
                this.ObjectContext.Transaction.DeleteObject(transaction);
            }
        }

        // TODO:
        // рассмотрите возможность сокращения результатов метода запроса.  Если необходим дополнительный ввод,
        // то в этот метод можно добавить параметры или создать дополнительные методы выполнения запроса с другими именами.
        // Для поддержки разбиения на страницы добавьте упорядочение в запрос "Transaction_Warehouse_Product".
        public IQueryable<Transaction_Warehouse_Product> GetTransaction_Warehouse_Product()
        {
            return this.ObjectContext.Transaction_Warehouse_Product;
        }

        public void InsertTransaction_Warehouse_Product(Transaction_Warehouse_Product transaction_Warehouse_Product)
        {
            if ((transaction_Warehouse_Product.EntityState != EntityState.Detached))
            {
                this.ObjectContext.ObjectStateManager.ChangeObjectState(transaction_Warehouse_Product, EntityState.Added);
            }
            else
            {
                this.ObjectContext.Transaction_Warehouse_Product.AddObject(transaction_Warehouse_Product);
            }
        }

        public void UpdateTransaction_Warehouse_Product(Transaction_Warehouse_Product currentTransaction_Warehouse_Product)
        {
            this.ObjectContext.Transaction_Warehouse_Product.AttachAsModified(currentTransaction_Warehouse_Product, this.ChangeSet.GetOriginal(currentTransaction_Warehouse_Product));
        }

        public void DeleteTransaction_Warehouse_Product(Transaction_Warehouse_Product transaction_Warehouse_Product)
        {
            if ((transaction_Warehouse_Product.EntityState != EntityState.Detached))
            {
                this.ObjectContext.ObjectStateManager.ChangeObjectState(transaction_Warehouse_Product, EntityState.Deleted);
            }
            else
            {
                this.ObjectContext.Transaction_Warehouse_Product.Attach(transaction_Warehouse_Product);
                this.ObjectContext.Transaction_Warehouse_Product.DeleteObject(transaction_Warehouse_Product);
            }
        }

        // TODO:
        // рассмотрите возможность сокращения результатов метода запроса.  Если необходим дополнительный ввод,
        // то в этот метод можно добавить параметры или создать дополнительные методы выполнения запроса с другими именами.
        // Для поддержки разбиения на страницы добавьте упорядочение в запрос "Warehouse".
        public IQueryable<Warehouse> GetWarehouse()
        {
            return this.ObjectContext.Warehouse;
        }

        public void InsertWarehouse(Warehouse warehouse)
        {
            if ((warehouse.EntityState != EntityState.Detached))
            {
                this.ObjectContext.ObjectStateManager.ChangeObjectState(warehouse, EntityState.Added);
            }
            else
            {
                this.ObjectContext.Warehouse.AddObject(warehouse);
            }
        }

        public void UpdateWarehouse(Warehouse currentWarehouse)
        {
            this.ObjectContext.Warehouse.AttachAsModified(currentWarehouse, this.ChangeSet.GetOriginal(currentWarehouse));
        }

        public void DeleteWarehouse(Warehouse warehouse)
        {
            if ((warehouse.EntityState != EntityState.Detached))
            {
                this.ObjectContext.ObjectStateManager.ChangeObjectState(warehouse, EntityState.Deleted);
            }
            else
            {
                this.ObjectContext.Warehouse.Attach(warehouse);
                this.ObjectContext.Warehouse.DeleteObject(warehouse);
            }
        }

        // TODO:
        // рассмотрите возможность сокращения результатов метода запроса.  Если необходим дополнительный ввод,
        // то в этот метод можно добавить параметры или создать дополнительные методы выполнения запроса с другими именами.
        // Для поддержки разбиения на страницы добавьте упорядочение в запрос "Workers".
        public IQueryable<Workers> GetWorkers()
        {
            return this.ObjectContext.Workers;
        }

        public void InsertWorkers(Workers workers)
        {
            if ((workers.EntityState != EntityState.Detached))
            {
                this.ObjectContext.ObjectStateManager.ChangeObjectState(workers, EntityState.Added);
            }
            else
            {
                this.ObjectContext.Workers.AddObject(workers);
            }
        }

        public void UpdateWorkers(Workers currentWorkers)
        {
            this.ObjectContext.Workers.AttachAsModified(currentWorkers, this.ChangeSet.GetOriginal(currentWorkers));
        }

        public void DeleteWorkers(Workers workers)
        {
            if ((workers.EntityState != EntityState.Detached))
            {
                this.ObjectContext.ObjectStateManager.ChangeObjectState(workers, EntityState.Deleted);
            }
            else
            {
                this.ObjectContext.Workers.Attach(workers);
                this.ObjectContext.Workers.DeleteObject(workers);
            }
        }
    }
}


