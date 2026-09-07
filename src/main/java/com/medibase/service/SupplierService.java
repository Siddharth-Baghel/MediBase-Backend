package com.medibase.service;

import com.medibase.dto.request.CreateSupplierRequest;
import com.medibase.entity.Organization;
import com.medibase.entity.Supplier;
import com.medibase.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final CurrentUserService currentUserService;
    public SupplierService(SupplierRepository supplierRepository, CurrentUserService currentUserService){this.supplierRepository=supplierRepository;this.currentUserService=currentUserService;}
    public Supplier createSupplier(CreateSupplierRequest request){
        Organization organization=currentUserService.getCurrentOrganization();
        Supplier s=new Supplier(); s.setName(request.getName()); s.setContactPerson(request.getContactPerson()); s.setPhone(request.getPhone()); s.setEmail(request.getEmail()); s.setAddress(request.getAddress()); s.setCity(request.getCity()); s.setGstNumber(request.getGstNumber()); s.setOrganization(organization);
        return supplierRepository.save(s);
    }
    public List<Supplier> getCurrentSuppliers(){return supplierRepository.findByOrganizationId(currentUserService.getCurrentOrganization().getId());}
    public List<Supplier> getSuppliersByOrganization(Long organizationId){Long current=currentUserService.getCurrentOrganization().getId(); if(!current.equals(organizationId)) throw new RuntimeException("Access denied"); return supplierRepository.findByOrganizationId(current);}
    public Supplier getSupplierById(Long id){Supplier s=supplierRepository.findById(id).orElseThrow(()->new RuntimeException("Supplier not found"));if(!s.getOrganization().getId().equals(currentUserService.getCurrentOrganization().getId()))throw new RuntimeException("Access denied");return s;}
}
