package com.medibase.controller;

import com.medibase.dto.request.CreateSupplierRequest;
import com.medibase.entity.Supplier;
import com.medibase.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {
    private final SupplierService supplierService;
    public SupplierController(SupplierService supplierService){this.supplierService=supplierService;}
    @PostMapping public Supplier createSupplier(@Valid @RequestBody CreateSupplierRequest request){return supplierService.createSupplier(request);}
    @GetMapping public List<Supplier> getCurrentSuppliers(){return supplierService.getCurrentSuppliers();}
    @GetMapping("/{supplierId}") public Supplier getSupplierById(@PathVariable Long supplierId){return supplierService.getSupplierById(supplierId);}
    @GetMapping("/organization/{organizationId}") public List<Supplier> getSuppliersByOrganization(@PathVariable Long organizationId){return supplierService.getSuppliersByOrganization(organizationId);}
}
