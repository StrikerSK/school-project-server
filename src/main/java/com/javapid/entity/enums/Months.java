package com.javapid.entity.enums;

public enum Months {

    JANUARY("Január"),
    FEBRUARY("Február"),
    MARCH("Marec"),
    APRIL("Apríl"),
    MAY("Máj"),
    JUNE("Jún"),
    JULY("Júl"),
    AUGUST("August"),
    SEPTEMBER("September"),
    OCTOBER("Október"),
    NOVEMBER("November"),
    DECEMBER("December");

    private String value;

    private Months(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
