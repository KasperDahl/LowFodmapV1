package com.lowfodmapv1.enums;

public enum Unit {
    GRAM("gram"),
    STYK("styk"),
    DAASE("dåse"),
    SPISESKE("spiseske"),
    TESKE("teske"),
    LITER("liter"),
    DECILITER("deciliter"),
    BUNDT("bundt"),
    KILOGRAM("kilogram"),
    MILLILITER("milliliter"),
    PAKKE("pakke");

    private String value;

    Unit(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
