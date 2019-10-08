package com.javapid.entity.enums;

public enum PersonType {

    ADULT("Dospelý"),
    SENIOR("Dôchodcovia"),
    JUNIOR("Juniori"),
    PORTABLE("Prenosná"),
    STUDENT("Študenti");

    private String value;

    private PersonType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
