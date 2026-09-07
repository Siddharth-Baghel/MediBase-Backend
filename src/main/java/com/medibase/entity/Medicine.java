package com.medibase.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "medicines")
public class Medicine {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String name;
    private String manufacturer;
    private String composition;
    private String strength;
    private String dosageForm;
    private String category;
    private String barcode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    public Medicine() {}
    public Medicine(String name,String manufacturer,String composition,String strength,String dosageForm,String category,String barcode,Pharmacy pharmacy){
        this.name=name;this.manufacturer=manufacturer;this.composition=composition;this.strength=strength;this.dosageForm=dosageForm;this.category=category;this.barcode=barcode;this.pharmacy=pharmacy;
    }
    public Long getId(){return id;} public String getName(){return name;} public String getManufacturer(){return manufacturer;} public String getComposition(){return composition;}
    public String getStrength(){return strength;} public String getDosageForm(){return dosageForm;} public String getCategory(){return category;} public String getBarcode(){return barcode;} public Pharmacy getPharmacy(){return pharmacy;}
    public void setName(String v){name=v;} public void setManufacturer(String v){manufacturer=v;} public void setComposition(String v){composition=v;} public void setStrength(String v){strength=v;}
    public void setDosageForm(String v){dosageForm=v;} public void setCategory(String v){category=v;} public void setBarcode(String v){barcode=v;} public void setPharmacy(Pharmacy v){pharmacy=v;}
}
