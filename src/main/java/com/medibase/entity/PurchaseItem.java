package com.medibase.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "purchase_items")
public class PurchaseItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Purchase Invoice
    @ManyToOne
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase;

    // Medicine
    @ManyToOne
    @JoinColumn(name = "medicine_id", nullable = false)
    private Medicine medicine;

    // Batch Information
    @Column(name = "batch_number", nullable = false)
    private String batchNumber;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    // Quantity
    @Column(nullable = false)
    private Integer quantity;

    // Pricing
    @Column(name = "purchase_price")
    private Double purchasePrice;

    private Double mrp;

    @Column(name = "selling_price")
    private Double sellingPrice;


    public PurchaseItem() {
    }


    public Long getId() {
        return id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public Double getMrp() {
        return mrp;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setMrp(Double mrp) {
        this.mrp = mrp;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}