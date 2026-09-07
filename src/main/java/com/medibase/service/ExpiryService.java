package com.medibase.service;

import com.medibase.entity.InventoryBatch;
import com.medibase.repository.InventoryBatchRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExpiryService {
    private final InventoryBatchRepository batchRepository;
    private final CurrentUserService currentUserService;
    public ExpiryService(InventoryBatchRepository batchRepository, CurrentUserService currentUserService){this.batchRepository=batchRepository;this.currentUserService=currentUserService;}
    private boolean owned(InventoryBatch b){return b.getInventory().getPharmacy().getId().equals(currentUserService.getCurrentPharmacy().getId());}
    public List<InventoryBatch> getExpiredBatches(){LocalDate t=LocalDate.now();return batchRepository.findByExpiryDateBefore(t).stream().filter(b->b.getQuantity()>0&&owned(b)).toList();}
    public List<InventoryBatch> getNearExpiryBatches(int days){LocalDate t=LocalDate.now();return batchRepository.findByExpiryDateBetween(t,t.plusDays(days)).stream().filter(b->b.getQuantity()>0&&owned(b)).toList();}
}
