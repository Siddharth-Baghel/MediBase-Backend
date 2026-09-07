package com.medibase.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "stock_adjustments")
public class StockAdjustment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_batch_id", nullable = false)
    private InventoryBatch inventoryBatch;

    @Column(nullable = false)
    private Integer quantityChange;

    @Column(nullable = false)
    private String reason;

    private String notes;

    @Column(nullable = false)
    private LocalDateTime adjustedAt;

    public StockAdjustment() {
    }

    public StockAdjustment(
            Inventory inventory,
            InventoryBatch inventoryBatch,
            Integer quantityChange,
            String reason,
            String notes
    ) {
        this.inventory = inventory;
        this.inventoryBatch = inventoryBatch;
        this.quantityChange = quantityChange;
        this.reason = reason;
        this.notes = notes;
        this.adjustedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public InventoryBatch getInventoryBatch() {
        return inventoryBatch;
    }

    public Integer getQuantityChange() {
        return quantityChange;
    }

    public String getReason() {
        return reason;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDateTime getAdjustedAt() {
        return adjustedAt;
    }
}