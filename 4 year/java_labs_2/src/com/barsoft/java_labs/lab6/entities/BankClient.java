/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs.lab1.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Maks
 */
@Entity
@Table(name = "BANK_CLIENT")
@NamedQueries({
    @NamedQuery(name = "BankClient.findAll", query = "SELECT b FROM BankClient b")})
public class BankClient implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "AMOUNT")
    private Integer amount;
    @JoinColumn(name = "DEPOSIT_TYPE_ID", referencedColumnName = "ID")
    @ManyToOne
    private DepositType depositTypeId;
    @JoinColumn(name = "CURRENCY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Currency currencyId;

    public BankClient() {
    }

    public BankClient(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public DepositType getDepositType() {
        return depositTypeId;
    }

    public void setDepositType(DepositType depositTypeId) {
        this.depositTypeId = depositTypeId;
    }

    public Currency getCurrency() {
        return currencyId;
    }

    public void setCurrency(Currency currencyId) {
        this.currencyId = currencyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BankClient)) {
            return false;
        }
        BankClient other = (BankClient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "java_labs2.entities.BankClient[ id=" + id + " ]";
    }
    
}
