/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.oktyabr.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maks
 */
@Entity
@Table(name = "SEED_TYPES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeedTypes.findAll", query = "SELECT s FROM SeedTypes s"),
    @NamedQuery(name = "SeedTypes.findById", query = "SELECT s FROM SeedTypes s WHERE s.id = :id"),
    @NamedQuery(name = "SeedTypes.findByName", query = "SELECT s FROM SeedTypes s WHERE s.name = :name"),
    @NamedQuery(name = "SeedTypes.findByPackSizeGr", query = "SELECT s FROM SeedTypes s WHERE s.packSizeGr = :packSizeGr"),
    @NamedQuery(name = "SeedTypes.findByPricePerInstance", query = "SELECT s FROM SeedTypes s WHERE s.pricePerInstance = :pricePerInstance")})
public class SeedTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 16)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PACK_SIZE_GR")
    private short packSizeGr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRICE_PER_INSTANCE")
    private short pricePerInstance;

    public SeedTypes() {
    }

    public SeedTypes(Long id) {
        this.id = id;
    }

    public SeedTypes(Long id, short packSizeGr, short pricePerInstance) {
        this.id = id;
        this.packSizeGr = packSizeGr;
        this.pricePerInstance = pricePerInstance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getPackSizeGr() {
        return packSizeGr;
    }

    public void setPackSizeGr(short packSizeGr) {
        this.packSizeGr = packSizeGr;
    }

    public short getPricePerInstance() {
        return pricePerInstance;
    }

    public void setPricePerInstance(short pricePerInstance) {
        this.pricePerInstance = pricePerInstance;
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
        if (!(object instanceof SeedTypes)) {
            return false;
        }
        SeedTypes other = (SeedTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.barsoft.oktyabr.entities.SeedTypes[ id=" + id + " ]";
    }
    
}
