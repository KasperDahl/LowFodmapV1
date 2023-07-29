package com.lowfodmapv1.enums;

public enum Unit {
    GRAM("g"),
    STYK("stk"),
    DAASE("ds"),
    SPISESKE("spsk"),
    TESKE("tsk"),
    LITER("l"),
    DECILITER("dl"),
    BUNDT("bdt"),
    KILOGRAM("kg"),
    MILLILITER("ml"),
    PAKKE("pk");

    private String value;

    Unit(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
