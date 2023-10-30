package com.szylas.medicamenttracker.models;

public abstract class Medicament {

    protected String name;
    protected int quantity;
    protected int dose;

    abstract int remaining();

    public int getDose() {
        return dose;
    }

    public String getName() {
        return name;
    }

    public void decreaseQuantity(int decreasingValue) {
        if (decreasingValue <= 0) {
            throw new IllegalArgumentException(
                    String.format("Decreasing value has to be more than 0, but is %d",
                            decreasingValue));
        }
        if (decreasingValue > quantity) {
            throw new IllegalArgumentException(
                    String.format("Decreasing value (%d) is more than actual quantity (%d).",
                            decreasingValue, quantity)
            );
        }
        quantity -= decreasingValue;
    }

    public void increaseQuantity(int increasingValue) {
        if (increasingValue <= 0) {
            throw new IllegalArgumentException(
                    String.format("Increasing value has to be more than 0, but is %d",
                            increasingValue));
        }
        quantity += increasingValue;
    }

    public void taken() {
        quantity -= dose;
    }
}
