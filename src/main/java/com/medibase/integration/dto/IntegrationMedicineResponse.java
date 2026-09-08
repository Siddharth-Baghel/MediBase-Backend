package com.medibase.integration.dto;

public class IntegrationMedicineResponse {

    private Long medicineId;

    private String medicineName;
    private String manufacturer;
    private String composition;
    private String strength;
    private String dosageForm;

    private Long pharmacyId;
    private String pharmacyName;
    private String pharmacyPhone;
    private String address;
    private String city;

    private Double latitude;
    private Double longitude;

    private Integer availableQuantity;


    public IntegrationMedicineResponse(
            Long medicineId,
            String medicineName,
            String manufacturer,
            String composition,
            String strength,
            String dosageForm,
            Long pharmacyId,
            String pharmacyName,
            String pharmacyPhone,
            String address,
            String city,
            Double latitude,
            Double longitude,
            Integer availableQuantity
    ) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.manufacturer = manufacturer;
        this.composition = composition;
        this.strength = strength;
        this.dosageForm = dosageForm;
        this.pharmacyId = pharmacyId;
        this.pharmacyName = pharmacyName;
        this.pharmacyPhone = pharmacyPhone;
        this.address = address;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.availableQuantity = availableQuantity;
    }


    public Long getMedicineId() {
        return medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getComposition() {
        return composition;
    }

    public String getStrength() {
        return strength;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public String getPharmacyPhone() {
        return pharmacyPhone;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }
}