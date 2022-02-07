package com.parcel.Models;

import com.parcel.Services.Validation;

import java.util.UUID;

public class Package {
    private String id;
    private String name;
    private Size size;
    private double weight;
    private String recipient;
    private String sender;
    private Parcel recipientParcel;
    private Parcel senderParcel;
    private State state;

    private static Validation validation = new Validation();

    public Package(String name, String size, double weight, String recipient, String sender, Parcel recipientParcel, Parcel senderParcel) {
        String id = String.valueOf(UUID.randomUUID());

        if (validation.valUUid(id)) {
            this.id = id;
        } else {
            System.out.println("Bad UUID generated");
        }

        this.name = name;
        setSize(size);
        this.weight = weight;
        this.recipient = recipient;
        this.sender = sender;
        this.recipientParcel = recipientParcel;
        this.senderParcel = senderParcel;
        this.state = State.PREPARED;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Size getSize() {
        return size;
    }

    public double getWeight() {
        return weight;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSender() {
        return sender;
    }

    public Parcel getRecipientParcel() {
        return recipientParcel;
    }

    public Parcel getSenderParcel() {
        return senderParcel;
    }

    public State getState() {
        return state;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(String size) {
        String[] sizeParts = size.split("x");
        double[] sizeDoubles = new double[sizeParts.length];

        for (int i = 0; i < sizeParts.length; i++){
            sizeDoubles[i] = Double.parseDouble(sizeParts[i]);
        }
        if (sizeParts.length == 2){
            this.size = new Size(sizeDoubles[0],sizeDoubles[1]);
        } else {
            this.size = new Size(sizeDoubles[0],sizeDoubles[1],sizeDoubles[2]);
        }
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setRecipientParcel(Parcel recipientParcel) {
        this.recipientParcel = recipientParcel;
    }

    public void setSenderParcel(Parcel senderParcel) {
        this.senderParcel = senderParcel;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Package{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", weight=" + weight +
                ", recipient='" + recipient + '\'' +
                ", sender='" + sender + '\'' +
                ", recipientParcel=" + recipientParcel.getId() +
                ", senderParcel=" + senderParcel.getId() +
                ", state=" + state.getFull() +
                '}';
    }
}
