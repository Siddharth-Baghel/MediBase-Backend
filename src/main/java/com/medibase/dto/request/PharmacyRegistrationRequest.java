package com.medibase.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class PharmacyRegistrationRequest {
    @NotBlank private String ownerName;
    @NotBlank @Email private String email;
    @NotBlank private String password;
    @NotBlank private String pharmacyName;
    @NotBlank private String phone;
    @NotBlank private String address;
    private String city;
    private String gstNumber;
    private String drugLicenseNumber;

    public String getOwnerName(){return ownerName;} public void setOwnerName(String v){ownerName=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getPassword(){return password;} public void setPassword(String v){password=v;}
    public String getPharmacyName(){return pharmacyName;} public void setPharmacyName(String v){pharmacyName=v;}
    public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
    public String getAddress(){return address;} public void setAddress(String v){address=v;}
    public String getCity(){return city;} public void setCity(String v){city=v;}
    public String getGstNumber(){return gstNumber;} public void setGstNumber(String v){gstNumber=v;}
    public String getDrugLicenseNumber(){return drugLicenseNumber;} public void setDrugLicenseNumber(String v){drugLicenseNumber=v;}
}
