package com.javapid.objects;

public enum TypeOfSell {

    KUPON("Papierový kupón"),
    KARTA("Čipová karta");

    private final String label;

    private TypeOfSell(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
