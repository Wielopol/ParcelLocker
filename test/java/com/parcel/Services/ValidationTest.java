package com.parcel.Services;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ValidationTest {

    @Test
    public void testValName() {

        Validation validation = new Validation();

        boolean result = validation.valName("Karol");

        assertThat(result).isNotNull()
                .isTrue();
    }

    @Test
    public void testValId() {

        Validation validation = new Validation();

        boolean result = validation.valId("Lanc0");

        assertThat(result).isNotNull()
                .isFalse();
    }

    @Test
    public void testValSize() {

        Validation validation = new Validation();

        boolean result = validation.valSize("40x20x10");

        assertThat(result).isNotNull()
                .isTrue();

        boolean result2 = validation.valSize("40x20");

        assertThat(result2).isNotNull()
                .isTrue();

        boolean result3 = validation.valSize("40x20x10x50");

        assertThat(result3).isNotNull()
                .isFalse();
    }

    @Test
    public void testValAddress() {

        Validation validation = new Validation();

        boolean result = validation.valAddress("ul. Mickiewicza 5,Lancut,37-100");

        assertThat(result).isNotNull()
                .isTrue();

        boolean result2 = validation.valAddress("aleja 29 Listopada 59g,Krakow,31423");

        assertThat(result2).isNotNull()
                .isTrue();

        boolean result3 = validation.valAddress("lancut lisa kuli 16, 41-321");

        assertThat(result3).isNotNull()
                .isFalse();
    }

    @Test
    public void testValUUid() {

        Validation validation = new Validation();

        boolean result = validation.valUUid("7bf05cf7-8eb6-4a1d-8423-e3fa3ac8d70d");

        assertThat(result).isNotNull()
                .isTrue();

        boolean result2 = validation.valUUid("7bf0cf7-8eb6-4a1d-8423-e3fa3ac8d70d");

        assertThat(result2).isNotNull()
                .isFalse();
    }
}