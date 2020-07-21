package com.abhijit.allinone.model;

public class FirebaseDBModel_Provider {

    String name;
    String occupassion;
    String contact_number;
    String address;
    boolean isonline;

    public FirebaseDBModel_Provider(String name, String contact_number, String address,String occupassion, boolean isonline) {
        this.name = name;
        this.contact_number = contact_number;
        this.address = address;
        this.occupassion=occupassion;
        this.isonline = isonline;
    }

    public String getOccupassion() {
        return occupassion;
    }

    public void setOccupassion(String occupassion) {
        this.occupassion = occupassion;
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
        return address;
    }

    public void setAddress(String address) {
        address = address;
    }

    public boolean isIsonline() {
        return isonline;
    }

    public void setIsonline(boolean isonline) {
        this.isonline = isonline;
    }


}
