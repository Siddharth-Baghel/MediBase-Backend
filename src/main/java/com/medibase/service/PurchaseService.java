package com.medibase.service;

import com.medibase.dto.request.CreatePurchaseItemRequest;
import com.medibase.dto.request.CreatePurchaseRequest;
import com.medibase.entity.*;
import com.medibase.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final PurchaseItemRepository purchaseItemRepository;
    private final SupplierRepository supplierRepository;
    private final MedicineRepository medicineRepository;
    private final InventoryRepository inventoryRepository;
    private final InventoryBatchRepository inventoryBatchRepository;
    private final CurrentUserService currentUserService;

    public PurchaseService(PurchaseRepository purchaseRepository, PurchaseItemRepository purchaseItemRepository, SupplierRepository supplierRepository,
                           MedicineRepository medicineRepository, InventoryRepository inventoryRepository, InventoryBatchRepository inventoryBatchRepository,
                           CurrentUserService currentUserService){
        this.purchaseRepository=purchaseRepository;this.purchaseItemRepository=purchaseItemRepository;this.supplierRepository=supplierRepository;
        this.medicineRepository=medicineRepository;this.inventoryRepository=inventoryRepository;this.inventoryBatchRepository=inventoryBatchRepository;this.currentUserService=currentUserService;
    }

    @Transactional
    public Purchase createPurchase(CreatePurchaseRequest request){
        Pharmacy pharmacy=currentUserService.getCurrentPharmacy();
        Supplier supplier=supplierRepository.findById(request.getSupplierId()).orElseThrow(()->new RuntimeException("Supplier not found"));
        if(!supplier.getOrganization().getId().equals(currentUserService.getCurrentOrganization().getId())) throw new RuntimeException("Supplier does not belong to your organization");

        Purchase purchase=new Purchase(); purchase.setSupplier(supplier);purchase.setPharmacy(pharmacy);purchase.setInvoiceNumber(request.getInvoiceNumber());purchase.setPurchaseDate(request.getPurchaseDate());
        purchase.setDiscount(request.getDiscount()==null?0.0:request.getDiscount());purchase.setTaxAmount(request.getTaxAmount()==null?0.0:request.getTaxAmount());purchase.setNotes(request.getNotes());
        purchase=purchaseRepository.save(purchase);
        double total=0;
        for(CreatePurchaseItemRequest item:request.getItems()){
            Medicine medicine=medicineRepository.findByIdAndPharmacyId(item.getMedicineId(),pharmacy.getId()).orElseThrow(()->new RuntimeException("Medicine not found in your pharmacy"));
            Inventory inventory=inventoryRepository.findByPharmacyIdAndMedicineId(pharmacy.getId(),medicine.getId()).orElseGet(()->inventoryRepository.save(new Inventory(pharmacy,medicine,0)));
            InventoryBatch batch=inventoryBatchRepository.findByInventoryIdAndBatchNumber(inventory.getId(),item.getBatchNumber()).orElseGet(()->inventoryBatchRepository.save(new InventoryBatch(inventory,item.getBatchNumber(),item.getExpiryDate(),0,item.getMrp(),item.getPurchasePrice(),item.getSellingPrice())));
            batch.setQuantity(batch.getQuantity()+item.getQuantity());batch.setMrp(item.getMrp());batch.setPurchasePrice(item.getPurchasePrice());batch.setSellingPrice(item.getSellingPrice());inventoryBatchRepository.save(batch);
            inventory.setTotalQuantity(inventory.getTotalQuantity()+item.getQuantity());inventoryRepository.save(inventory);
            PurchaseItem pi=new PurchaseItem();pi.setPurchase(purchase);pi.setMedicine(medicine);pi.setBatchNumber(item.getBatchNumber());pi.setExpiryDate(item.getExpiryDate());pi.setQuantity(item.getQuantity());pi.setPurchasePrice(item.getPurchasePrice());pi.setMrp(item.getMrp());pi.setSellingPrice(item.getSellingPrice());purchaseItemRepository.save(pi);
            total+=item.getPurchasePrice()*item.getQuantity();
        }
        purchase.setTotalAmount(total-purchase.getDiscount()+purchase.getTaxAmount());
        return purchaseRepository.save(purchase);
    }
}
