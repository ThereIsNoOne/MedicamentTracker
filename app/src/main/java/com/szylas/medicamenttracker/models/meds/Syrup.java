package com.szylas.medicamenttracker.models.meds;

public class Syrup extends Medicament {
    private int volume;

    public Syrup(String name, int dose, int quantity, int volumePerBottle) {
        this.name = name;
        this.dose = dose;
        this.quantity = quantity;
        this.volume = volumePerBottle * quantity;
    }

    @Override
    int remaining() {
        return volume / dose;
    }

    public void decreaseVolume(int decreasingVolume) {
        if (decreasingVolume <= 0) {
            throw new IllegalArgumentException(
                    String.format("Decreasing volume has to be more than 0, but is %d",
                            decreasingVolume));
        }
        if (decreasingVolume > volume) {
            throw new IllegalArgumentException(
                    String.format("Decreasing volume (%d) is more than actual volume (%d).",
                            decreasingVolume, volume)
            );
        }
        volume -= decreasingVolume;
    }

    public void increaseVolume(int increasingVolume) {
        if (increasingVolume <= 0) {
            throw new IllegalArgumentException(
                    String.format("Increasing volume has to be more than 0, but is %d.",
                            increasingVolume)
            );
        }
        volume += increasingVolume;
    }
}
