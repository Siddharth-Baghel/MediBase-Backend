package com.medibase.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CreateSupplierRequest {
    @NotBlank(message="Supplier name is required") private String name;
    private String contactPerson; private String phone; private String email; private String address; private String city; private String gstNumber;
    public String getName(){return name;} public String getContactPerson(){return contactPerson;} public String getPhone(){return phone;} public String getEmail(){return email;} public String getAddress(){return address;} public String getCity(){return city;} public String getGstNumber(){return gstNumber;}
    public void setName(String v){name=v;} public void setContactPerson(String v){contactPerson=v;} public void setPhone(String v){phone=v;} public void setEmail(String v){email=v;} public void setAddress(String v){address=v;} public void setCity(String v){city=v;} public void setGstNumber(String v){gstNumber=v;}
}
