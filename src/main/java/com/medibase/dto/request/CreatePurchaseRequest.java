package com.medibase.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class CreatePurchaseRequest {
    @NotNull(message="Supplier ID is required") private Long supplierId;
    @NotBlank(message="Invoice number is required") private String invoiceNumber;
    @NotNull(message="Purchase date is required") private LocalDate purchaseDate;
    private Double discount; private Double taxAmount; private String notes;
    @Valid @NotEmpty(message="At least one purchase item is required") private List<CreatePurchaseItemRequest> items;
    public Long getSupplierId(){return supplierId;} public void setSupplierId(Long v){supplierId=v;}
    public String getInvoiceNumber(){return invoiceNumber;} public void setInvoiceNumber(String v){invoiceNumber=v;}
    public LocalDate getPurchaseDate(){return purchaseDate;} public void setPurchaseDate(LocalDate v){purchaseDate=v;}
    public Double getDiscount(){return discount;} public void setDiscount(Double v){discount=v;}
    public Double getTaxAmount(){return taxAmount;} public void setTaxAmount(Double v){taxAmount=v;}
    public String getNotes(){return notes;} public void setNotes(String v){notes=v;}
    public List<CreatePurchaseItemRequest> getItems(){return items;} public void setItems(List<CreatePurchaseItemRequest> v){items=v;}
}
