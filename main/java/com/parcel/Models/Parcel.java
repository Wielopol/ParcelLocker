package com.parcel.Models;

public class Parcel {
    private String id;
    private String name;
    private Address address; //format: street,city,postal code
    private Package[] packages = new Package[50];

    public Parcel(String id, String name, String address) {
        this.id = id;
        this.name = name;
        setAddress(address);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Package[] getPackages() {
        return packages;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        String[] addressParts = address.split(",");
        this.address = new Address(addressParts[0],addressParts[1],addressParts[2]);
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
