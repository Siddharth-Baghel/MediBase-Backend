package com.medibase.service;

import com.medibase.dto.request.CreateInventoryRequest;
import com.medibase.entity.Inventory;
import com.medibase.entity.Medicine;
import com.medibase.entity.Pharmacy;
import com.medibase.repository.InventoryRepository;
import com.medibase.repository.MedicineRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final MedicineRepository medicineRepository;
    private final CurrentUserService currentUserService;
    public InventoryService(InventoryRepository inventoryRepository, MedicineRepository medicineRepository, CurrentUserService currentUserService){this.inventoryRepository=inventoryRepository;this.medicineRepository=medicineRepository;this.currentUserService=currentUserService;}

    public Inventory createInventory(CreateInventoryRequest request){
        Pharmacy pharmacy=currentUserService.getCurrentPharmacy();
        Medicine medicine=medicineRepository.findByIdAndPharmacyId(request.getMedicineId(),pharmacy.getId()).orElseThrow(()->new RuntimeException("Medicine not found in your pharmacy"));
        if(inventoryRepository.findByPharmacyIdAndMedicineId(pharmacy.getId(),medicine.getId()).isPresent()) throw new RuntimeException("Inventory already exists for this medicine");
        return inventoryRepository.save(new Inventory(pharmacy,medicine,0));
    }
    public List<Inventory> getCurrentInventory(){return inventoryRepository.findByPharmacyId(currentUserService.getCurrentPharmacy().getId());}
    public List<Inventory> getInventoryByPharmacy(Long pharmacyId){
        Long current=currentUserService.getCurrentPharmacy().getId();
        if(!current.equals(pharmacyId)) throw new RuntimeException("Access denied to this pharmacy inventory");
        return inventoryRepository.findByPharmacyId(current);
    }
    public Inventory getInventoryById(Long inventoryId){
        Inventory inventory=inventoryRepository.findById(inventoryId).orElseThrow(()->new RuntimeException("Inventory not found"));
        if(!inventory.getPharmacy().getId().equals(currentUserService.getCurrentPharmacy().getId())) throw new RuntimeException("Access denied");
        return inventory;
    }
}
