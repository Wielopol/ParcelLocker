package com.parcel.Services;

import com.parcel.UserInterface.Console;
import com.parcel.Models.Package;
import com.parcel.Models.Parcel;
import com.parcel.Models.State;

import java.util.Locale;
import java.util.Scanner;

public class ParcelManager {
    static Parcel[] parcels = new Parcel[20];
    static Package[] packages = new Package[100];
    private static Console console = new Console();
    private static Validation validation = new Validation();
    private static Scanner s = new Scanner(System.in).useLocale(Locale.US);
    private static boolean exit = false;

    static Test test = new Test();


    public static void main(String[] args) {

        test.print();

        do {
            console.displayMenu();
            console.menuChooser();
        } while (!exit);

    }

    public void exit() {
        exit = true;
    }

    public String addParcel() {
        String id = console.getInformation("Enter Parcel ID");

        if (!validation.valId(id)) {
            return "Invalid Parcel ID";
        }

        String name = console.getInformation("Enter Parcel name");

        if (!validation.valName(name)) {
            return "Invalid Parcel name";
        }

        String address = console.getInformation("Enter Parcel address (street,city,postal code)");

        if (!validation.valAddress(address)){
            return "Invalid Parcel address";
        }

        for (int i = 0; i < parcels.length; i++) {
            if (parcels[i] == null){
                parcels[i] = new Parcel(id, name, address);
                return "Parcel " + name + " of ID: " + id + " added successfully.";
            }
        }
        return "Array of parcels is full";
    }

    public String removeParcel() {
        String id = console.getInformation("Enter Parcel ID");

        if (!validation.valId(id)) {
            return "Invalid Parcel ID";
        }

        int index = findParcelById(id);

        if (index == -1){
            return "Parcel of ID: " + id + " not found.";
        }

        parcels[index] = null;
        return "Parcel of ID: " + id + " removed.";
    }

    public String displayAllParcels(){
        String text = "";
        for (Parcel parcel : parcels) {
            if (parcel != null) {
                text += parcel + "\n";
            }
        }
        return text;
    }

    public String displayParcelsByCity(){
        String text = "";
        String city = console.getInformation("Enter Parcel city");
        boolean cityFound = false;

        if (!validation.valName(city)) {
            return "Invalid Parcel city";
        }

        for (Parcel parcel : parcels) {
            if (parcel != null && parcel.getAddress().getCity().equals(city)) {
                text += parcel + "\n";
                cityFound = true;
            }
        }

        if (!cityFound) {
            return "City " + city + " not found.";
        }
        return text;
    }

    public String displayPackagesByParcel(){
        String text = "";
        String ParcelId = console.getInformation("Enter Parcel ID");
        boolean parcelFound = false;

        if (!validation.valId(ParcelId)) {
            return "Invalid Parcel ID";
        }

        for (Parcel parcel : parcels) {
            if (parcel != null && parcel.getId().equals(ParcelId)) {
                parcelFound = true;
                for (Package p : parcel.getPackages()){
                    if (p != null) {
                        text += parcel + "\n";
                    }
                }
            }
        }

        if (!parcelFound) {
            return "Parcel of ID: " + ParcelId + " not found.";
        }
        return text;
    }

    public String updateParcel(){
        String id = console.getInformation("Enter Parcel ID");

        if (!validation.valId(id)) {
            return "Invalid Parcel ID";
        }

        int index = findParcelById(id);

        if (index == -1){
            return "Parcel of ID: " + id + " not found.";
        }

        int choice = console.getIntInformation("[1] - Update name\n[2] - Update address");

        if (choice == 1){
            String name = console.getInformation("Enter new Parcel name");

            if (!validation.valName(name)) {
                return "Invalid Parcel name";
            }

            parcels[index].setName(name);
            return "Parcel of ID: " + id + " name updated.";
        } else if (choice == 2){
            String address = console.getInformation("Enter new Parcel address (street,city,postal code)");

            if (!validation.valAddress(address)) {
                return "Invalid Parcel address";
            }

            parcels[index].setAddress(address);
            return "Parcel of ID: " + id + " address updated.";
        } else {
            return "Invalid action number";
        }
    }

    public String addPackage() {
        String name = console.getInformation("Enter Package name");

        if (!validation.valName(name)) {
            return "Invalid Package name";
        }

        String size = console.getInformation("Enter Package size in cm ([width]x[height]x[length]) - length is optional");

        if (!validation.valSize(size)) {
            return "Invalid Package size";
        }

        double weight = console.getDoubleInformation("Enter Package weight");
        String recipient = console.getInformation("Enter Package recipient");

        if (!validation.valName(recipient)){
            return "Invalid Package recipient";
        }

        String sender = console.getInformation("Enter Package sender");

        if (!validation.valName(sender)){
            return "Invalid Package sender";
        }

        String recipientParcelId = console.getInformation("Enter recipient Parcel ID");

        if (!validation.valId(recipientParcelId)) {
            return "Invalid recipient Parcel ID";
        }

        int index1 = findParcelById(recipientParcelId);

        if (index1 == -1){
            return "Recipient Parcel of ID: " + recipientParcelId + " not found.";
        }

        String senderParcelId = console.getInformation("Enter sender Parcel ID");

        if (!validation.valId(senderParcelId)) {
            return "Invalid sender Parcel ID";
        }

        int index2 = findParcelById(senderParcelId);

        if (index2 == -1){
            return "Sender Parcel of ID: " + senderParcelId + " not found.";
        }

        for (int i = 0; i < packages.length; i++) {
            if (packages[i] == null){
                packages[i] = new Package(name,size,weight,recipient,sender,parcels[index1],parcels[index2]);
                return "Package " + name + " added successfully.\n" +
                        "Its ID is: " + packages[i].getId() + " - write it down to be able to update its details.";
            }
        }

        return "Array of packages is full";
    }

    public String addPackageToSenderParcel(Package p,int[] index) {
        for (int i = 0; i < p.getSenderParcel().getPackages().length; i++) {
            if (p.getSenderParcel().getPackages()[i] == null){
                p.getSenderParcel().getPackages()[i] = p;
                packages[index[0]] = null;
                p.setState(State.IN_SENDER_PARCEL);
                return "Package " + p.getName() + " added successfully to Sender Parcel.";
            }
        }
        return "Parcel is full";
    }

    public String addPackageToRecipientParcel(Package p,int[] index) {
        for (int i = 0; i < p.getRecipientParcel().getPackages().length; i++) {
            if (p.getRecipientParcel().getPackages()[i] == null){
                p.getRecipientParcel().getPackages()[i] = p;
                if (index[1] == -1){
                    packages[index[0]] = null;
                } else {
                    parcels[index[0]].getPackages()[index[1]] = null;
                }
                p.setState(State.IN_RECIPIENT_PARCEL);
                return "Package " + p.getName() + " added successfully to Recipient Parcel.";
            }
        }
        return "Parcel is full";
    }

    public String removePackageFromParcel(String id) {
        int[] index = findPackageById(id);

        if (index[0] == -1){
            return "Package of ID: " + id + " not found.";
        }

        for (int i = 0; i < packages.length; i++) {
            if (packages[i] == null){
                packages[i] = parcels[index[0]].getPackages()[index[1]];
                parcels[index[0]].getPackages()[index[1]] = null;
                return "Package of ID: " + id + " has left parcel.";
            }
        }

        return "Array of packages is full";
    }

    public String removePackage(String id) {
        int[] index = findPackageById(id);

        if (index[0] == -1){
            int[] indexArray = findPackageInArray(id);

            if (indexArray[0] == -1){
                return "Package of ID: " + id + " not found.";
            }

            packages[indexArray[0]]= null;
        } else {
            parcels[index[0]].getPackages()[index[1]] = null;
        }
        return "Package of ID: " + id + " removed.";
    }

    public String updatePackage(){
        String id = console.getInformation("Enter Package ID");

        if (!validation.valUUid(id)) {
            return "Invalid Package ID";
        }

        int[] index = findPackageById(id);
        Package p;

        if (index[0] == -1){
            int[] indexArray = findPackageInArray(id);

            if (indexArray[0] == -1){
                return "Package of ID: " + id + " not found.";
            }

            p = packages[indexArray[0]];
            index = indexArray;
        } else {
            p = parcels[index[0]].getPackages()[index[1]];
        }

        console.displayUpdateOptions();

        return console.updateChooser(p,index);
    }

    public String updateSenderParcel (Package p, int[] index){
        String newSenderId = console.getInformation("Enter new sender Parcel ID");

        if (!validation.valId(newSenderId)) {
            return "Invalid Package sender Parcel ID";
        }

        int indexNew = findParcelById(newSenderId);

        if (indexNew == -1){
            return "Parcel of ID: " + newSenderId + " not found.";
        }

        p.setSenderParcel(parcels[indexNew]);

        if (index[1] != -1){
            for (int i = 0; i < parcels[indexNew].getPackages().length; i++) {
                if (parcels[indexNew].getPackages()[i] == null){
                    parcels[indexNew].getPackages()[i] = p;
                    parcels[index[0]].getPackages()[index[1]] = null;
                    return "Package of ID: " + p.getId() + " sender Parcel ID updated.";
                }
            }
            return "Parcel of ID: " + newSenderId + " is full.";
        }

        return "Package of ID: " + p.getId() + " sender Parcel ID updated.";
    }

    public String updateRecipientParcel (Package p, int[] index){
        String newRecipientId = console.getInformation("Enter new recipient Parcel ID");

        if (!validation.valId(newRecipientId)) {
            return "Invalid Package recipient Parcel ID";
        }

        int indexNew = findParcelById(newRecipientId);

        if (indexNew == -1){
            return "Parcel of ID: " + newRecipientId + " not found.";
        }

        p.setRecipientParcel(parcels[indexNew]);

        return "Package of ID: " + p.getId() + " recipient Parcel ID updated.";
    }

    public String updatePackageState(Package p,int[] index) {
        String state = p.getState().getFull();
        console.displayStateOptions(state);
        int choice = s.nextInt();
        s.nextLine();

        if (choice == 1 && state.equals(State.PREPARED.getFull())) {
            return addPackageToSenderParcel(p,index);
        } else if (choice == 2 && state.equals(State.IN_SENDER_PARCEL.getFull())) {
            p.setState(State.TRAVELLING);
            return removePackageFromParcel(p.getId());
        } else if (choice == 3 && state.equals(State.TRAVELLING.getFull())) {
            return addPackageToRecipientParcel(p,index);
        } else if (choice == 4 && state.equals(State.IN_RECIPIENT_PARCEL.getFull())) {
            p.setState(State.DELIVERED);
            return removePackageFromParcel(p.getId());
        } else if (choice == 3 && state.equals(State.IN_SENDER_PARCEL.getFull())) {
            return addPackageToRecipientParcel(p,index);
        } else if (choice < 1 || choice > 4) {
            return "Invalid action number";
        } else if ((choice == 1 && state.equals(State.IN_SENDER_PARCEL.getFull())) ||
                (choice == 2 && state.equals(State.TRAVELLING.getFull())) ||
                (choice == 3 && state.equals(State.IN_RECIPIENT_PARCEL.getFull())) ||
                (choice == 4 && state.equals(State.DELIVERED.getFull()))) {
            return "No change of state";
        } else {
            return "Too big move of package";
        }
    }

    public int findParcelById(String id) {
        for (int i = 0; i < parcels.length; i++) {
            if (parcels[i] != null && parcels[i].getId().equals(id)){
                return i;
            }
        }
        return -1;
    }

    public int[] findPackageById(String id) {
        for (int i = 0; i < parcels.length; i++) {
            if (parcels[i] != null) {
                for (int j = 0; j < parcels[i].getPackages().length; j++){
                    if (parcels[i].getPackages()[j] != null && parcels[i].getPackages()[j].getId().equals(id)){
                        return new int[] {i,j};
                    }
                }
            }
        }
        return new int[] {-1,-1};
    }

    public int[] findPackageInArray(String id) {
        for (int i = 0; i < packages.length; i++) {
            if (packages[i] != null && packages[i].getId().equals(id)){
                return new int[] {i,-1};
            }
        }
        return new int[] {-1,-1};
    }

    public String displayPackagesBySender() {
        String text = "";
        String sender = console.getInformation("Enter Package sender");
        boolean senderFound = false;

        if (!validation.valName(sender)) {
            return "Invalid Package sender";
        }

        for (Parcel parcel : parcels) {
            if (parcel != null) {
                for (Package p : parcel.getPackages()){
                    if (p != null && p.getSender().equals(sender)) {
                        senderFound = true;
                        text += p + "\n";
                    }
                }
            }
        }

        for (Package p : packages){
            if (p != null && p.getSender().equals(sender)) {
                senderFound = true;
                text += p + "\n";
            }
        }

        if (!senderFound) {
            return "Sender " + sender + " not found.";
        }
        return text;
    }
}
