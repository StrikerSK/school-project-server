package com.javapid.entity.enums;

public enum Validity {

    MONTHLY("Mesačná"),
    THREE_MONTHS("3 Mesačná"),
    FIVE_MONTHS("5 Mesačná"),
    YEARLY("Ročná");

    private String value;

    private Validity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
