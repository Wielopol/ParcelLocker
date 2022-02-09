package com.parcel.Services;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ParcelManagerTest {
    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;

    @Before
    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @After
    public void restoreSystemInput() {
        System.setIn(systemIn);
    }

    @Test
    public void testAddParcel() {
        String result;

        ParcelManager parcelManager = new ParcelManager();

        result = parcelManager.addParcel();
        assertThat(result).isEqualTo("Invalid Parcel ID");

        result = parcelManager.addParcel();
        assertThat(result).isEqualTo("Invalid Parcel name");

        result = parcelManager.addParcel();
        assertThat(result).isEqualTo("Invalid Parcel address");

        result = parcelManager.addParcel();
        assertThat(result).isEqualTo("Parcel LancutWsch of ID: RLA08 added successfully.");
        assertThat(ParcelManager.parcels[3].toString()).isEqualTo("Parcel{id='RLA08', name='LancutWsch', address='ul. Graniczna 10, 37-100 Lancut'}");
    }

    @Test
    public void testRemoveParcel() {
        String result;

        ParcelManager parcelManager = new ParcelManager();

        result = parcelManager.removeParcel();
        assertThat(result).isEqualTo("Invalid Parcel ID");

        result = parcelManager.removeParcel();
        assertThat(result).isEqualTo("Parcel of ID: KRA05 not found.");

        result = parcelManager.removeParcel();
        assertThat(result).isEqualTo("Parcel of ID: KRA02 removed.");
        assertThat(ParcelManager.parcels[6]).isNull();
    }

    @Test
    public void testDisplayAllParcels() {

        ParcelManager parcelManager = new ParcelManager();

        String result = parcelManager.displayAllParcels();

        assertThat(result).isEqualTo("Parcel{id='RLA01', name='LancutPld', address='ul. Lisa-Kuli 5, 37-100 Lancut'}\n" +
                "Parcel{id='RZE01', name='RzeszowCtr', address='ul. Lisa-Kuli 9, 42-150 Rzeszow'}\n" +
                "Parcel{id='RLA02', name='LancutPln', address='ul. Podzwierzyniec 45b, 37-100 Lancut'}\n" +
                "Parcel{id='RLA08', name='LancutWsch', address='ul. Graniczna 10, 37-100 Lancut'}\n" +
                "Parcel{id='RZE02', name='RzeszowZach', address='ul. Iwonicka 1, 35-505 Rzeszow'}\n" +
                "Parcel{id='KRA01', name='KrakowCzyzyny', address='ul. Centralna 41f, 31-586 Krakow'}\n" +
                "Parcel{id='KRA03', name='KrakowPodgorze', address='ul. Wielicka 40, 35-346 Krakow'}\n");
    }

    @Test
    public void testDisplayParcelsByCity() {
        String result;

        ParcelManager parcelManager = new ParcelManager();

        result = parcelManager.displayParcelsByCity();
        assertThat(result).isEqualTo("Invalid Parcel city");

        result = parcelManager.displayParcelsByCity();
        assertThat(result).isEqualTo("City Lublin not found.");

        result = parcelManager.displayParcelsByCity();
        assertThat(result).isEqualTo("Parcel{id='RLA01', name='LancutPld', address='ul. Lisa-Kuli 5, 37-100 Lancut'}\n" +
                "Parcel{id='RLA02', name='LancutPln', address='ul. Podzwierzyniec 45b, 37-100 Lancut'}\n");
    }

    @Test
    public void testDisplayParcelsBySender() {
        String result;

        ParcelManager parcelManager = new ParcelManager();

        result = parcelManager.displayPackagesBySender();
        assertThat(result).isEqualTo("Invalid Package sender");

        result = parcelManager.displayPackagesBySender();
        assertThat(result).isEqualTo("Sender neonet not found.");

        result = parcelManager.displayPackagesBySender();
        assertThat(result).contains("name='dysk SSD', size=Size{width=10.0, height=20.0, length=5.0}, weight=0.7, " +
                "recipient='Ala', sender='xkom', recipientParcel=KRA01, senderParcel=KRA02, state=Prepared}")
                        .contains("name='klawiatura', size=Size{width=90.0, height=30.0, length=5.0}, weight=0.5, " +
                                "recipient='Ola', sender='xkom', recipientParcel=RZE01, senderParcel=KRA02, state=Prepared}");
    }

    @Test
    public void testUpdateParcel() {


    }

    @Test
    public void testAddPackage() {


    }

    @Test
    public void testAddPackageToSenderParcel() {


    }

    @Test
    public void testAddPackageToRecipientParcel() {


    }

    @Test
    public void testRemovePackageFromParcel() {


    }

    @Test
    public void testRemovePackage() {
        String text = " \nLublin\nLancut\n" +
                "RLA008\nKRA05\nKRA02\n" +
                "RLA008\nRLA08\n \nRLA08\nLancutWsch\nul. Graniczna 10 Lancut 37-100\nRLA08\nLancutWsch\nul. Graniczna 10,Lancut,37-100\n" +
                " \nneonet\nxkom";

        provideInput(text);


    }

    @Test
    public void testUpdatePackage() {


    }

    @Test
    public void testUpdateSenderParcel() {


    }

    @Test
    public void testUpdateRecipientParcel() {


    }

    @Test
    public void testUpdatePackageState() {


    }

    @Test
    public void testFindParcelById() {


    }

    @Test
    public void testFindPackageById() {


    }

    @Test
    public void testFindPackageInArray() {


    }
}