package com.medibase.service;

import com.medibase.dto.request.LoginRequest;
import com.medibase.dto.request.PharmacyRegistrationRequest;
import com.medibase.dto.request.RegisterRequest;
import com.medibase.dto.response.AuthResponse;
import com.medibase.entity.Organization;
import com.medibase.entity.Pharmacy;
import com.medibase.entity.Role;
import com.medibase.entity.User;
import com.medibase.repository.OrganizationRepository;
import com.medibase.repository.PharmacyRepository;
import com.medibase.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final PharmacyRepository pharmacyRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            OrganizationRepository organizationRepository,
            PharmacyRepository pharmacyRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.organizationRepository = organizationRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    // =========================
    // NORMAL USER REGISTRATION
    // =========================

    @Transactional
    public User register(RegisterRequest request) {

        String email = request.getEmail()
                .trim()
                .toLowerCase();

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already registered");
        }

        Organization organization = null;
        Pharmacy pharmacy = null;

        if (request.getOrganizationId() != null) {

            organization = organizationRepository
                    .findById(request.getOrganizationId())
                    .orElseThrow(() ->
                            new RuntimeException("Organization not found")
                    );
        }

        if (request.getPharmacyId() != null) {

            pharmacy = pharmacyRepository
                    .findById(request.getPharmacyId())
                    .orElseThrow(() ->
                            new RuntimeException("Pharmacy not found")
                    );
        }

        // Check pharmacy belongs to organization
        if (pharmacy != null && organization != null) {

            if (!pharmacy.getOrganization()
                    .getId()
                    .equals(organization.getId())) {

                throw new RuntimeException(
                        "Pharmacy does not belong to organization"
                );
            }
        }

        Role role = request.getRole() == null
                ? Role.PHARMACY_OWNER
                : request.getRole();

        User user = new User(
                request.getName(),
                email,
                passwordEncoder.encode(request.getPassword()),
                role,
                organization,
                pharmacy
        );

        return userRepository.save(user);
    }


    // =========================
    // PHARMACY REGISTRATION
    // =========================

    @Transactional
    public AuthResponse registerPharmacy(
            PharmacyRegistrationRequest request
    ) {

        String email = request.getEmail()
                .trim()
                .toLowerCase();

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already registered");
        }


        // Create Organization
        Organization organization = new Organization(
                request.getPharmacyName(),
                request.getOwnerName(),
                request.getGstNumber(),
                request.getDrugLicenseNumber(),
                request.getPhone(),
                email
        );

        organization = organizationRepository.save(organization);


        // Create Pharmacy
        Pharmacy pharmacy = new Pharmacy(
                request.getPharmacyName(),
                request.getPhone(),
                email,
                request.getAddress(),
                request.getCity(),
                null,
                null,
                organization
        );

        pharmacy = pharmacyRepository.save(pharmacy);


        // Create User
        User user = new User(
                request.getOwnerName(),
                email,
                passwordEncoder.encode(request.getPassword()),
                Role.PHARMACY_OWNER,
                organization,
                pharmacy
        );

        user = userRepository.save(user);


        return buildResponse(
                user,
                "Pharmacy registered successfully"
        );
    }


    // =========================
    // LOGIN
    // =========================

    public AuthResponse login(LoginRequest request) {

        String email = request.getEmail()
                .trim()
                .toLowerCase();

        String password = request.getPassword();

        System.out.println("========== LOGIN DEBUG ==========");
        System.out.println("EMAIL RECEIVED: " + email);


        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> {

                    System.out.println(
                            "USER NOT FOUND FOR EMAIL: " + email
                    );

                    return new RuntimeException(
                            "Invalid email or password"
                    );
                });


        System.out.println(
                "USER FOUND: " + user.getEmail()
        );

        System.out.println(
                "USER ID: " + user.getId()
        );

        System.out.println(
                "PASSWORD HASH EXISTS: "
                        + (user.getPassword() != null)
        );


        boolean passwordMatches = passwordEncoder.matches(
                password,
                user.getPassword()
        );


        System.out.println(
                "PASSWORD MATCHES: " + passwordMatches
        );

        System.out.println("=================================");


        if (!passwordMatches) {

            throw new RuntimeException(
                    "Invalid email or password"
            );
        }


        return buildResponse(
                user,
                "Login successful"
        );
    }


    // =========================
    // CURRENT USER
    // =========================

    public AuthResponse currentUser(String email) {

        String normalizedEmail = email
                .trim()
                .toLowerCase();

        User user = userRepository
                .findByEmail(normalizedEmail)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        return buildResponse(
                user,
                "Current user"
        );
    }


    // =========================
    // BUILD AUTH RESPONSE
    // =========================

    private AuthResponse buildResponse(
            User user,
            String message
    ) {

        Long organizationId = user.getOrganization() == null
                ? null
                : user.getOrganization().getId();

        Long pharmacyId = user.getPharmacy() == null
                ? null
                : user.getPharmacy().getId();

        String pharmacyName = user.getPharmacy() == null
                ? null
                : user.getPharmacy().getName();


        String token = jwtService.generateToken(
                user.getEmail()
        );


        return new AuthResponse(
                token,
                message,
                user.getId(),
                user.getName(),
                user.getRole().name(),
                organizationId,
                pharmacyId,
                pharmacyName
        );
    }
}