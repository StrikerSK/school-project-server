package com.javapid.entity;

public enum SellType {

    CARD("Čipová karta"),
    COUPON("Papierový kupón");

    private String value;

    private SellType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
