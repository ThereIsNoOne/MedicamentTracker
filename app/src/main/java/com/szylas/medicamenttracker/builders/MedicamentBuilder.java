package com.szylas.medicamenttracker.builders;

import com.szylas.medicamenttracker.models.meds.Injection;
import com.szylas.medicamenttracker.models.MedType;
import com.szylas.medicamenttracker.models.meds.Medicament;
import com.szylas.medicamenttracker.models.meds.Pill;
import com.szylas.medicamenttracker.models.meds.Syrup;

public class MedicamentBuilder {
    public static Medicament getMedicament(MedType type,
                                           String name,
                                           int quantity,
                                           int dose,
                                           int volume) {
        switch (type) {
            case PILL:
                return new Pill(name, quantity, dose);
            case INJECTION:
                return new Injection(name, quantity, dose);
            case SYRUP:
                return new Syrup(name, dose, quantity, volume);
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
