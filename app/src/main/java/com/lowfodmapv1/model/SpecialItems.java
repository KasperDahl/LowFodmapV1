package com.lowfodmapv1.model;

public class SpecialItems {
    private String name;
    private String category;
    private String fodmap;
    private String description;

    public SpecialItems() {
    }

    public SpecialItems(String name, String category, String fodmap, String description) {
        this.name = name;
        this.category = category;
        this.fodmap = fodmap;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getFodmap() {
        return fodmap;
    }

    public String getDescription() {
        return description;
    }
}