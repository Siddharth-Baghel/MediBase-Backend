package com.medibase.dto.request;

import jakarta.validation.constraints.NotNull;

public class CreateInventoryRequest {
    @NotNull(message = "Medicine ID is required")
    private Long medicineId;
    public Long getMedicineId(){return medicineId;}
    public void setMedicineId(Long medicineId){this.medicineId=medicineId;}
}
