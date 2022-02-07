package com.parcel.Models;

public enum State {
    PREPARED("Prepared"),
    IN_SENDER_PARCEL("In sender parcel"),
    TRAVELLING("Travelling"),
    IN_RECIPIENT_PARCEL("In recipient parcel"),
    DELIVERED("Delivered");

    private String full;

    State(String full) {
        this.full = full;
    }

    public String getFull() { return full; }
}
