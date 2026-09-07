package com.medibase.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "inventory",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"pharmacy_id", "medicine_id"}
                )
        }
)
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id", nullable = false)
    private Pharmacy pharmacy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine_id", nullable = false)
    private Medicine medicine;

    @Column(nullable = false)
    private Integer totalQuantity = 0;

    @Column(nullable = false)
    private Integer minimumStockLevel = 10;

    public Inventory() {
    }


    public Inventory(
            Pharmacy pharmacy,
            Medicine medicine,
            Integer totalQuantity
    ) {
        this.pharmacy = pharmacy;
        this.medicine = medicine;
        this.totalQuantity = totalQuantity;
        this.minimumStockLevel = 10;
    }


    public Long getId() {
        return id;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }


    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Integer getMinimumStockLevel() {
        return minimumStockLevel;
    }

    public void setMinimumStockLevel(Integer minimumStockLevel) {
        this.minimumStockLevel = minimumStockLevel;
    }
}
