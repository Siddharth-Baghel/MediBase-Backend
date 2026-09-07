package com.medibase.service;

import com.medibase.dto.request.CreateMedicineRequest;
import com.medibase.entity.Medicine;
import com.medibase.entity.Pharmacy;
import com.medibase.repository.MedicineRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicineService {
    private final MedicineRepository medicineRepository;
    private final CurrentUserService currentUserService;
    public MedicineService(MedicineRepository medicineRepository, CurrentUserService currentUserService){this.medicineRepository=medicineRepository;this.currentUserService=currentUserService;}

    public Medicine createMedicine(CreateMedicineRequest request){
        Pharmacy pharmacy=currentUserService.getCurrentPharmacy();
        if(request.getBarcode()!=null && !request.getBarcode().isBlank() && medicineRepository.existsByBarcodeAndPharmacyId(request.getBarcode(), pharmacy.getId()))
            throw new RuntimeException("Medicine with this barcode already exists in your pharmacy");
        return medicineRepository.save(new Medicine(request.getName(),request.getManufacturer(),request.getComposition(),request.getStrength(),request.getDosageForm(),request.getCategory(),request.getBarcode(),pharmacy));
    }
    public List<Medicine> getAllMedicines(){return medicineRepository.findByPharmacyId(currentUserService.getCurrentPharmacy().getId());}
    public Medicine getMedicineById(Long id){Long p=currentUserService.getCurrentPharmacy().getId();return medicineRepository.findByIdAndPharmacyId(id,p).orElseThrow(()->new RuntimeException("Medicine not found"));}
    public List<Medicine> searchMedicine(String name){return medicineRepository.findByPharmacyIdAndNameContainingIgnoreCase(currentUserService.getCurrentPharmacy().getId(),name);}
}
