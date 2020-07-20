package com.abhijit.allinone.model;

public class FirebaseDBModel_Provider {

    String name;
    String contact_number;
    String Address;
    boolean isonline;

    public FirebaseDBModel_Provider(String name, String contact_number, String address, boolean isonline) {
        this.name = name;
        this.contact_number = contact_number;
        Address = address;
        this.isonline = isonline;
    }


    public FirebaseDBModel_Provider(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public boolean isIsonline() {
        return isonline;
    }

    public void setIsonline(boolean isonline) {
        this.isonline = isonline;
    }


}
