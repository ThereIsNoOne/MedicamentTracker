package com.szylas.medicamenttracker.models.meds;

public class Injection extends Medicament {

    public Injection(String name, int quantity, int dose) {
        this.name = name;
        this.quantity = quantity;
        this.dose = dose;
    }

    @Override
    int remaining() {
        return quantity / dose;
    }
}
