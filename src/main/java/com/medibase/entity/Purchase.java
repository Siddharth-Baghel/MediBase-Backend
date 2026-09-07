package com.medibase.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invoice_number", nullable = false)
    private String invoiceNumber;

    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;

    private Double totalAmount;

    private Double discount;

    private Double taxAmount;

    private String notes;


    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;


    @ManyToOne
    @JoinColumn(name = "pharmacy_id", nullable = false)
    private Pharmacy pharmacy;


    public Purchase() {
    }


    public Long getId() {
        return id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Double getDiscount() {
        return discount;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public String getNotes() {
        return notes;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
}