package com.parcel.UserInterface;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleTest {
    private static final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;

    @Before
    public void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @After
    public static void restoreSystemInput() {
        System.setIn(systemIn);
    }

    @Test
    public void testGetInformation() {

        Console console = new Console();

        String result = console.getInformation("Podaj swoje imie");

        assertThat(result).isNotNull()
                .isEqualTo("Karol");
    }

    @Test
    public void testGetIntInformation() {
        final String text = "6\nKarol\n73.5\n";
        provideInput(text);

        Console console = new Console();

        int result = console.getIntInformation("Wybierz liczbe od 0 do 9");

        assertThat(result).isNotNull()
                .isBetween(0,9);
    }

    @Test
    public void testGetDoubleInformation() {

        Console console = new Console();

        double result = console.getDoubleInformation("Podaj swoja wage");

        assertThat(result).isEqualTo(73.5);
    }
}