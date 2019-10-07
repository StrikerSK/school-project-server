package com.javapid.entity;

public enum PersonType {

    ADULT("Dospelý"),
    SENIOR("Dôchodca"),
    JUNIOR("Junior"),
    PORTABLE("Prenosná"),
    STUDENT("Študent");

    private String value;

    private PersonType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
