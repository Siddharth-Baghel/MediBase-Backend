package com.medibase.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "inventory_batches",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"inventory_id", "batch_number"}
                )
        }
)
public class InventoryBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventory;


    @Column(name = "batch_number", nullable = false)
    private String batchNumber;


    @Column(nullable = false)
    private LocalDate expiryDate;


    @Column(nullable = false)
    private Integer quantity;


    private Double mrp;

    private Double purchasePrice;

    private Double sellingPrice;


    public InventoryBatch() {
    }


    public InventoryBatch(
            Inventory inventory,
            String batchNumber,
            LocalDate expiryDate,
            Integer quantity,
            Double mrp,
            Double purchasePrice,
            Double sellingPrice
    ) {
        this.inventory = inventory;
        this.batchNumber = batchNumber;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
        this.mrp = mrp;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
    }


    public Long getId() {
        return id;
    }

    public Inventory getInventory() {
        return inventory;
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

    public Double getMrp() {
        return mrp;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }


    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setMrp(Double mrp) {
        this.mrp = mrp;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
