package com.parcel;

import java.util.Locale;
import java.util.Scanner;

public class ParcelManager {
    private static Parcel[] parcels = new Parcel[20];
    private static Package[] packages = new Package[100];
    private static Console console = new Console();
    private static Validation validation = new Validation();
    private static Scanner s = new Scanner(System.in).useLocale(Locale.US);
    private static boolean exit = false;

    public ParcelManager() {
        parcels[0] = new Parcel("RLA01","LancutPld","ul. Lisa-Kuli 5,Lancut,37-100");
        parcels[1] = new Parcel("RZE01","RzeszowCtr","ul. Lisa-Kuli 9,Rzeszow,42-150");
        parcels[2] = new Parcel("RLA02","LancutPln","ul. Podzwierzyniec 45b,Lancut,37-100");
        parcels[4] = new Parcel("RZE02","RzeszowZach","ul. Iwonicka 1,Rzeszow,35-505");
        parcels[5] = new Parcel("KRA01","KrakowCzyzyny","ul. Centralna 41f,Krakow,31-586");
        parcels[6] = new Parcel("KRA02","KrakowBronowice","ul. Balicka 56,Krakow,30-486");
        parcels[8] = new Parcel("KRA03","KrakowPodgorze","ul. Wielicka 40,Krakow,35-346");

        packages[0] = new Package("koszula","30x40",0.2,"Krystian","lancerto",parcels[4],parcels[2]);
        System.out.println("koszula: " + packages[0].getId());
        packages[3] = new Package("dysk SSD","10x20x5",0.7,"Ala","xkom",parcels[5],parcels[6]);
        System.out.println("dysk: " + packages[3].getId());
        packages[6] = new Package("BUFOR","30x20",0.6,"Karol","Janek",parcels[0],parcels[8]);
        System.out.println("BUFOR: " + packages[6].getId());
    }

    static ParcelManager parcelManager = new ParcelManager();


    public static void main(String[] args) {

        do {
            console.displayMenu();
            console.menuChooser();
        } while (!exit);

    }

    public static void exit() {
        exit = true;
    }

    public static String addParcel() {
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

    public static String removeParcel() {
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

    public static void displayAllParcels(){
        for (Parcel parcel : parcels) {
            if (parcel != null) {
                System.out.println(parcel);
            }
        }
    }

    public static void displayParcelsByCity(){
        String city = console.getInformation("Enter Parcel city");
        boolean cityFound = false;

        if (!validation.valName(city)) {
            System.out.println("Invalid Parcel city");
            return;
        }

        for (Parcel parcel : parcels) {
            if (parcel != null && parcel.getAddress().getCity().equals(city)) {
                System.out.println(parcel);
                cityFound = true;
            }
        }

        if (!cityFound) {
            System.out.println("City " + city + " not found.");
        }
    }

    public static void displayPackagesByParcel(){
        String ParcelId = console.getInformation("Enter Parcel ID");
        boolean cityFound = false;

        if (!validation.valId(ParcelId)) {
            System.out.println("Invalid Parcel ID");
            return;
        }

        for (Parcel parcel : parcels) {
            if (parcel != null && parcel.getId().equals(ParcelId)) {
                for (Package p : parcel.getPackages()){
                    if (p != null) {
                        System.out.println(p);
                    }
                }
            }
        }

        if (!cityFound) {
            System.out.println("Parcel of ID: " + ParcelId + " not found.");
        }
    }

    public static String updateParcel(){
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

    public static String addPackage() {
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

    public static String addPackageToSenderParcel(Package p,int[] index) {
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

    public static String addPackageToRecipientParcel(Package p,int[] index) {
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

    public static String removePackageFromParcel(String id) {
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

    public static String removePackage(String id) {
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

    public static String updatePackage(){
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

    public static String updateSenderParcel (Package p, int[] index){
        String newSenderId = console.getInformation("Enter new sender Parcel ID");

        if (!validation.valId(newSenderId)) {
            return "Invalid Package sender Parcel ID";
        }

        int indexNew = ParcelManager.findParcelById(newSenderId);

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

    public static String updateRecipientParcel (Package p, int[] index){
        String newRecipientId = console.getInformation("Enter new recipient Parcel ID");

        if (!validation.valId(newRecipientId)) {
            return "Invalid Package recipient Parcel ID";
        }

        int indexNew = ParcelManager.findParcelById(newRecipientId);

        if (indexNew == -1){
            return "Parcel of ID: " + newRecipientId + " not found.";
        }

        p.setRecipientParcel(parcels[indexNew]);

        return "Package of ID: " + p.getId() + " recipient Parcel ID updated.";
    }

    public static String updatePackageState(Package p,int[] index) {
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

    public static int findParcelById(String id) {
        for (int i = 0; i < parcels.length; i++) {
            if (parcels[i] != null && parcels[i].getId().equals(id)){
                return i;
            }
        }
        return -1;
    }

    public static int[] findPackageById(String id) {
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

    public static int[] findPackageInArray(String id) {
        for (int i = 0; i < packages.length; i++) {
            if (packages[i] != null && packages[i].getId().equals(id)){
                return new int[] {i,-1};
            }
        }
        return new int[] {-1,-1};
    }
}
