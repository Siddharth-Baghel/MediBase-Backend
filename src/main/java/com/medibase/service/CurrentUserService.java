package com.medibase.service;

import com.medibase.entity.Organization;
import com.medibase.entity.Pharmacy;
import com.medibase.entity.User;
import com.medibase.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CurrentUserService {
    private final UserRepository userRepository;
    public CurrentUserService(UserRepository userRepository) { this.userRepository = userRepository; }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getName() == null || auth.getName().equals("anonymousUser")) {
            throw new RuntimeException("Authentication required");
        }
        return userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("Current user not found"));
    }

    @Transactional(readOnly = true)
    public Pharmacy getCurrentPharmacy() {
        Pharmacy pharmacy = getCurrentUser().getPharmacy();
        if (pharmacy == null) throw new RuntimeException("No pharmacy is linked to this user");
        return pharmacy;
    }

    @Transactional(readOnly = true)
    public Organization getCurrentOrganization() {
        Organization organization = getCurrentUser().getOrganization();
        if (organization == null) throw new RuntimeException("No organization is linked to this user");
        return organization;
    }
}
