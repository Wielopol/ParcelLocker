package com.parcel;

import static com.parcel.ParcelManager.*;

public class Test {
    public Test() {
        parcels[0] = new Parcel("RLA01","LancutPld","ul. Lisa-Kuli 5,Lancut,37-100");
        parcels[1] = new Parcel("RZE01","RzeszowCtr","ul. Lisa-Kuli 9,Rzeszow,42-150");
        parcels[2] = new Parcel("RLA02","LancutPln","ul. Podzwierzyniec 45b,Lancut,37-100");
        parcels[4] = new Parcel("RZE02","RzeszowZach","ul. Iwonicka 1,Rzeszow,35-505");
        parcels[5] = new Parcel("KRA01","KrakowCzyzyny","ul. Centralna 41f,Krakow,31-586");
        parcels[6] = new Parcel("KRA02","KrakowBronowice","ul. Balicka 56,Krakow,30-486");
        parcels[8] = new Parcel("KRA03","KrakowPodgorze","ul. Wielicka 40,Krakow,35-346");

        packages[0] = new Package("koszula","30x40",0.2,"Krystian","lancerto",parcels[4],parcels[2]);
        packages[3] = new Package("dysk SSD","10x20x5",0.7,"Ala","xkom",parcels[5],parcels[6]);
        packages[6] = new Package("BUFOR","30x20",0.6,"Karol","Janek",parcels[0],parcels[8]);

    }

    public static void print(){
        System.out.println("koszula: " + packages[0].getId());
        System.out.println("dysk: " + packages[3].getId());
        System.out.println("BUFOR: " + packages[6].getId());
    }
}
