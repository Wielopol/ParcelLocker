package com.parcel.UserInterface;

import com.parcel.Models.Package;
import com.parcel.Services.ParcelManager;
import com.parcel.Services.Validation;

import java.util.Locale;
import java.util.Scanner;

public class Console {
    private static Validation validation = new Validation();
    static Scanner s = new Scanner(System.in).useLocale(Locale.US);

    public void displayMenu(){
        System.out.println();
        System.out.println("Parcel Manager Menu:");
        System.out.println("[1] - Add parcel locker");
        System.out.println("[2] - Remove parcel locker");
        System.out.println("[3] - Display all parcel lockers");
        System.out.println("[4] - Display parcel locker by city name");
        System.out.println("[5] - Update parcel locker");
        System.out.println("[6] - Add package");
        System.out.println("[7] - Remove package");
        System.out.println("[8] - Display packages by parcel locker");
        System.out.println("[9] - Update package details");
        System.out.println("[0] - Exit");
    }

    public void displayUpdateOptions(){
        System.out.println();
        System.out.println("Package update options:");
        System.out.println("[1] - Package name");
        System.out.println("[2] - Package size");
        System.out.println("[3] - Package weight");
        System.out.println("[4] - Package recipient");
        System.out.println("[5] - Package sender");
        System.out.println("[6] - Package sender parcel");
        System.out.println("[7] - Package recipient parcel");
        System.out.println("[8] - Package state");
    }

    public void displayStateOptions(String state){
        System.out.println();
        System.out.println("Current Package state is: " + state + ". Choose new state");
        System.out.println("[1] - In sender parcel");
        System.out.println("[2] - Travelling");
        System.out.println("[3] - In recipient parcel");
        System.out.println("[4] - Delivered");
    }

    public String getInformation(String message){
        System.out.println(message);
        return s.nextLine();
    }

    public int getIntInformation(String message){
        System.out.println(message);
        int i = s.nextInt();
        s.nextLine();
        return i;
    }

    public double getDoubleInformation(String message){
        System.out.println(message);
        double d = s.nextDouble();
        s.nextLine();
        return d;
    }

    public void menuChooser() {
        int choice = s.nextInt();
        s.nextLine();

        switch (choice) {
            case 1 -> {
                System.out.println(ParcelManager.addParcel());
            }
            case 2 -> {
                System.out.println(ParcelManager.removeParcel());
            }
            case 3 -> {
                ParcelManager.displayAllParcels();
            }
            case 4 -> {
                ParcelManager.displayParcelsByCity();
            }
            case 5 -> {
                System.out.println(ParcelManager.updateParcel());
            }
            case 6 -> {
                System.out.println(ParcelManager.addPackage());
            }
            case 7 -> {
                String id = getInformation("Enter Package ID");

                if (!validation.valUUid(id)) {
                    System.out.println("Invalid Package ID");
                    break;
                }

                System.out.println(ParcelManager.removePackage(id));
            }
            case 8 -> {
                ParcelManager.displayPackagesByParcel();
            }
            case 9 -> {
                System.out.println(ParcelManager.updatePackage());
            }
            case 0 -> {
                ParcelManager.exit();
            }
            default -> {
                System.out.println("Invalid action number");
            }
        }
    }

    public String updateChooser(Package p, int[] index) {
        int choice = s.nextInt();
        s.nextLine();

        switch (choice) {
            case 1 -> {
                String name = getInformation("Enter new Package name");

                if (!validation.valName(name)) {
                    return "Invalid Package name";
                }

                p.setName(name);
                return "Package of ID: " + p.getId() + " name updated.";
            }
            case 2 -> {
                String size = getInformation("Enter new Package size in cm ([width]x[height]x[length]) - length is optional");

                if (!validation.valSize(size)) {
                    return "Invalid Package size";
                }

                p.setSize(size);
                return "Package of ID: " + p.getId() + " size updated.";
            }
            case 3 -> {
                double weight = getDoubleInformation("Enter new Package weight");

                p.setWeight(weight);
                return "Package of ID: " + p.getId() + " weight updated.";
            }
            case 4 -> {
                String recipient = getInformation("Enter new Package recipient");

                if (!validation.valName(recipient)) {
                    return "Invalid Package recipient";
                }

                p.setRecipient(recipient);
                return "Package of ID: " + p.getId() + " recipient updated.";
            }
            case 5 -> {
                String sender = getInformation("Enter new Package sender");

                if (!validation.valName(sender)) {
                    return "Invalid Package sender";
                }

                p.setSender(sender);
                return "Package of ID: " + p.getId() + " sender updated.";
            }
            case 6 -> {
                return ParcelManager.updateSenderParcel(p,index);
            }
            case 7 -> {
                return ParcelManager.updateRecipientParcel(p,index);
            }
            case 8 -> {
                return ParcelManager.updatePackageState(p,index);
            }
            default -> {
                return "Invalid action number";
            }
        }
    }
}
