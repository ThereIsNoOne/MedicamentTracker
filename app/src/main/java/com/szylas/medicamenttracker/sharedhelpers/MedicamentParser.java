package com.szylas.medicamenttracker.sharedhelpers;

import com.szylas.medicamenttracker.builders.MedicamentFactory;
import com.szylas.medicamenttracker.models.MedType;
import com.szylas.medicamenttracker.models.meds.Medicament;

import java.util.Optional;

public final class MedicamentParser {
    private MedicamentParser() {}

    public static MedType determineType(String medicationValue) {
        switch (medicationValue) {
            case "PILL":
                return MedType.PILL;
            case "SYRUP":
                return MedType.SYRUP;
            case "INJECTION":
                return MedType.INJECTION;
            default:
                throw new IllegalStateException("Unknown MedType: " + medicationValue);
        }
    }

    public static Optional<Medicament> parseSingleMedicament(String medicationValue) {
        String[] medicationValues = medicationValue.split(":");

        if (medicationValues.length < 4) {
            return Optional.empty();        }

        MedType type = determineType(medicationValues[0]);
        String name = medicationValues[1];
        int quantity = Integer.parseInt(medicationValues[2]);
        int dose = Integer.parseInt(medicationValues[3]);
        int volume = medicationValues.length == 5 ? Integer.parseInt(medicationValues[4]) : 0;

        return Optional.of(MedicamentFactory.getMedicament(type, name, quantity, dose, volume));
    }
}
