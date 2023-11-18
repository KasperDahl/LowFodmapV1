package com.lowfodmapv1.enums;

public enum MealType {
    HOVEDRET("Hovedret"),
    FORRET("Forret"),
    DESSERT("Dessert"),
    SALAT("Salat"),
    SNACK("Snack"),
    DRESSING("Dressing"),
    BAGNING("Bagning"),
    MORGENMAD("Morgenmad");

    private String value;

    MealType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
