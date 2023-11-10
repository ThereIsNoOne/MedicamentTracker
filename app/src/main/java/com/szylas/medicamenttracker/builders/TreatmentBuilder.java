package com.szylas.medicamenttracker.builders;

import com.szylas.medicamenttracker.models.Treatment;
import com.szylas.medicamenttracker.models.meds.Pill;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public final class TreatmentBuilder {
    private TreatmentBuilder() { }

    public static Treatment getTreatment(long startDateTimestamp,
                                  long endDateTimestamp,
                                  long[] applicationTimes,
                                  String[] medications) {

        // Temporarily
        return new Treatment(
                new Pill("Test" ,30, 1),
                LocalDate.of(2022, 11, 1),
                LocalDate.of(2024, 11, 1),
                new ArrayList<LocalTime>() {{
                    add(LocalTime.of(8, 30));
                    add(LocalTime.of(12, 30));
                }}
                );
    }
}
