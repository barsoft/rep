
namespace AlcoStore.Web
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel;
    using System.ComponentModel.DataAnnotations;
    using System.Data.Objects.DataClasses;
    using System.Linq;
    using System.ServiceModel.DomainServices.Hosting;
    using System.ServiceModel.DomainServices.Server;


    // Атрибут MetadataTypeAttribute идентифицирует BankMetadata как класс,
    // который содержит дополнительные метаданные для класса Bank.
    [MetadataTypeAttribute(typeof(Bank.BankMetadata))]
    public partial class Bank
    {

        // Этот класс позволяет добавлять настраиваемые атрибуты к свойствам
        // класса Bank.
        //
        // Например, далее свойство Xyz помечено как
        // обязательное и указан формат допустимых значений:
        //    [Required]
        //    [RegularExpression("[A-Z][A-Za-z0-9]*")]
        //    [StringLength(32)]
        //    public string Xyz { get; set; }
        internal sealed class BankMetadata
        {

            // Классы метаданных не предназначены для создания экземпляров.
            private BankMetadata()
            {
            }

            public string Bank_name { get; set; }

            public EntityCollection<Contractor> Contractor { get; set; }

            public string MFO { get; set; }
        }
    }

    // Атрибут MetadataTypeAttribute идентифицирует ContractorMetadata как класс,
    // который содержит дополнительные метаданные для класса Contractor.
    [MetadataTypeAttribute(typeof(Contractor.ContractorMetadata))]
    public partial class Contractor
    {

        // Этот класс позволяет добавлять настраиваемые атрибуты к свойствам
        // класса Contractor.
        //
        // Например, далее свойство Xyz помечено как
        // обязательное и указан формат допустимых значений:
        //    [Required]
        //    [RegularExpression("[A-Z][A-Za-z0-9]*")]
        //    [StringLength(32)]
        //    public string Xyz { get; set; }
        internal sealed class ContractorMetadata
        {

            // Классы метаданных не предназначены для создания экземпляров.
            private ContractorMetadata()
            {
            }

            public Bank Bank { get; set; }

            public string Contractor_code { get; set; }

            public string Contractor_code2 { get; set; }

            public string Current_account { get; set; }

            public string MFO { get; set; }

            public string Name { get; set; }

            public string Payer_code { get; set; }

            public EntityCollection<Transaction> Transaction { get; set; }
        }
    }

    // Атрибут MetadataTypeAttribute идентифицирует ProductMetadata как класс,
    // который содержит дополнительные метаданные для класса Product.
    [MetadataTypeAttribute(typeof(Product.ProductMetadata))]
    public partial class Product
    {

        // Этот класс позволяет добавлять настраиваемые атрибуты к свойствам
        // класса Product.
        //
        // Например, далее свойство Xyz помечено как
        // обязательное и указан формат допустимых значений:
        //    [Required]
        //    [RegularExpression("[A-Z][A-Za-z0-9]*")]
        //    [StringLength(32)]
        //    public string Xyz { get; set; }
        internal sealed class ProductMetadata
        {

            // Классы метаданных не предназначены для создания экземпляров.
            private ProductMetadata()
            {
            }

            public string Name { get; set; }

            public double Price { get; set; }

            public string Product_code { get; set; }

            public EntityCollection<Transaction_Warehouse_Product> Transaction_Warehouse_Product { get; set; }

            public EntityCollection<Warehouse> Warehouse { get; set; }
        }
    }

    // Атрибут MetadataTypeAttribute идентифицирует StewardMetadata как класс,
    // который содержит дополнительные метаданные для класса Steward.
    [MetadataTypeAttribute(typeof(Steward.StewardMetadata))]
    public partial class Steward
    {

        // Этот класс позволяет добавлять настраиваемые атрибуты к свойствам
        // класса Steward.
        //
        // Например, далее свойство Xyz помечено как
        // обязательное и указан формат допустимых значений:
        //    [Required]
        //    [RegularExpression("[A-Z][A-Za-z0-9]*")]
        //    [StringLength(32)]
        //    public string Xyz { get; set; }
        internal sealed class StewardMetadata
        {

            // Классы метаданных не предназначены для создания экземпляров.
            private StewardMetadata()
            {
            }

            public string Steward_code { get; set; }

            public EntityCollection<Transaction> Transaction { get; set; }

            public string Worker_code { get; set; }

            public Workers Workers { get; set; }
        }
    }

    // Атрибут MetadataTypeAttribute идентифицирует TransactionMetadata как класс,
    // который содержит дополнительные метаданные для класса Transaction.
    [MetadataTypeAttribute(typeof(Transaction.TransactionMetadata))]
    public partial class Transaction
    {

        // Этот класс позволяет добавлять настраиваемые атрибуты к свойствам
        // класса Transaction.
        //
        // Например, далее свойство Xyz помечено как
        // обязательное и указан формат допустимых значений:
        //    [Required]
        //    [RegularExpression("[A-Z][A-Za-z0-9]*")]
        //    [StringLength(32)]
        //    public string Xyz { get; set; }
        internal sealed class TransactionMetadata
        {

            // Классы метаданных не предназначены для создания экземпляров.
            private TransactionMetadata()
            {
            }

            public Contractor Contractor { get; set; }

            public string Contractor_code { get; set; }

            public DateTime Date { get; set; }

            public int Number { get; set; }

            public string Product_code { get; set; }

            public Steward Steward { get; set; }

            public string Steward_code { get; set; }

            public string Transaction_code { get; set; }

            public string Transaction_number { get; set; }

            public Transaction_Warehouse_Product Transaction_Warehouse_Product { get; set; }

            public string Type_of_transaction { get; set; }

            public string Worker_code { get; set; }

            public Workers Workers { get; set; }
        }
    }

    // Атрибут MetadataTypeAttribute идентифицирует Transaction_Warehouse_ProductMetadata как класс,
    // который содержит дополнительные метаданные для класса Transaction_Warehouse_Product.
    [MetadataTypeAttribute(typeof(Transaction_Warehouse_Product.Transaction_Warehouse_ProductMetadata))]
    public partial class Transaction_Warehouse_Product
    {

        // Этот класс позволяет добавлять настраиваемые атрибуты к свойствам
        // класса Transaction_Warehouse_Product.
        //
        // Например, далее свойство Xyz помечено как
        // обязательное и указан формат допустимых значений:
        //    [Required]
        //    [RegularExpression("[A-Z][A-Za-z0-9]*")]
        //    [StringLength(32)]
        //    public string Xyz { get; set; }
        internal sealed class Transaction_Warehouse_ProductMetadata
        {

            // Классы метаданных не предназначены для создания экземпляров.
            private Transaction_Warehouse_ProductMetadata()
            {
            }

            public Product Product { get; set; }

            public string Product_code { get; set; }

            public int Quantity_in_warehouse { get; set; }

            public Transaction Transaction { get; set; }

            public string Transaction_code { get; set; }
        }
    }

    // Атрибут MetadataTypeAttribute идентифицирует WarehouseMetadata как класс,
    // который содержит дополнительные метаданные для класса Warehouse.
    [MetadataTypeAttribute(typeof(Warehouse.WarehouseMetadata))]
    public partial class Warehouse
    {

        // Этот класс позволяет добавлять настраиваемые атрибуты к свойствам
        // класса Warehouse.
        //
        // Например, далее свойство Xyz помечено как
        // обязательное и указан формат допустимых значений:
        //    [Required]
        //    [RegularExpression("[A-Z][A-Za-z0-9]*")]
        //    [StringLength(32)]
        //    public string Xyz { get; set; }
        internal sealed class WarehouseMetadata
        {

            // Классы метаданных не предназначены для создания экземпляров.
            private WarehouseMetadata()
            {
            }

            public Product Product { get; set; }

            public string Product_code { get; set; }

            public int Quantity_in_warehouse { get; set; }

            public string Warehouse_number { get; set; }
        }
    }

    // Атрибут MetadataTypeAttribute идентифицирует WorkersMetadata как класс,
    // который содержит дополнительные метаданные для класса Workers.
    [MetadataTypeAttribute(typeof(Workers.WorkersMetadata))]
    public partial class Workers
    {

        // Этот класс позволяет добавлять настраиваемые атрибуты к свойствам
        // класса Workers.
        //
        // Например, далее свойство Xyz помечено как
        // обязательное и указан формат допустимых значений:
        //    [Required]
        //    [RegularExpression("[A-Z][A-Za-z0-9]*")]
        //    [StringLength(32)]
        //    public string Xyz { get; set; }
        internal sealed class WorkersMetadata
        {

            // Классы метаданных не предназначены для создания экземпляров.
            private WorkersMetadata()
            {
            }

            public string Middle_name { get; set; }

            public string Name { get; set; }

            public EntityCollection<Steward> Steward { get; set; }

            public string Surname { get; set; }

            public EntityCollection<Transaction> Transaction { get; set; }

            public string Worker_code { get; set; }
        }
    }
}
