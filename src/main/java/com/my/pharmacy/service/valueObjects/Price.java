package org.my.pharmacy.service.valueObjects;

public class Price {

    private final float value;


    public Price(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }


    public static Price create(float value) {
        if (value < 0) {
            throw new IllegalArgumentException("Price value cannot be negative");
        }

        var roundedValue = (float) Math.round(value * 100) / 100;
        return new Price(roundedValue);
    }
}