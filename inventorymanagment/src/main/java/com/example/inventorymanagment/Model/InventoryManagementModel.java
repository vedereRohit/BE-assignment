package com.example.inventorymanagment.Model;

import javax.persistence.*;

@Entity
@Table(name = "inventory")
public class InventoryManagementModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "batch")
    private String batch;

    @Column(name = "stock")
    private Float stock;

    @Column(name = "deal")
    private Float deal;

    @Column(name = "free")
    private Float free;

    @Column(name = "mrp")
    private Float mrp;

    @Column(name = "rate")
    private Float rate;

    @Column(name = "exp")
    private String exp;

    @Column(name = "company")
    private String company;

    @Column(name = "supplier")
    private String supplier;

    private String value;

    public InventoryManagementModel(Long id, String code, String name, String batch, Float stock, Float deal, Float free, Float mrp, Float rate, String exp, String company, String supplier) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.batch = batch;
        this.stock = stock;
        this.deal = deal;
        this.free = free;
        this.mrp = mrp;
        this.rate = rate;
        this.exp = exp;
        this.company = company;
        this.supplier = supplier;
    }

    public InventoryManagementModel(String code, String name, String batch, Float stock, Float deal, Float free, Float mrp, Float rate, String exp, String company, String supplier) {
        this.code = code;
        this.name = name;
        this.batch = batch;
        this.stock = stock;
        this.deal = deal;
        this.free = free;
        this.mrp = mrp;
        this.rate = rate;
        this.exp = exp;
        this.company = company;
        this.supplier = supplier;
    }

    public InventoryManagementModel() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Float getStock() {
        return stock;
    }

    public void setStock(Float stock) {
        this.stock = stock;
    }

    public Float getDeal() {
        return deal;
    }

    public void setDeal(Float deal) {
        this.deal = deal;
    }

    public Float getFree() {
        return free;
    }

    public void setFree(Float free) {
        this.free = free;
    }

    public Float getMrp() {
        return mrp;
    }

    public void setMrp(Float mrp) {
        this.mrp = mrp;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
