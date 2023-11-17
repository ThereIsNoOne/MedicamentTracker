package com.szylas.medicamenttracker.builders;

import static com.szylas.medicamenttracker.sharedhelpers.MedicamentParser.parseSingleMedicament;

import com.szylas.medicamenttracker.models.Treatment;
import com.szylas.medicamenttracker.models.meds.Medicament;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Optional;

public final class TreatmentBuilder {
    private TreatmentBuilder() { }

    public static Treatment getTreatment(long startDateTimestamp,
                                  long endDateTimestamp,
                                  int[] applicationTimes,
                                  String[] medications) {

        LocalDate startDate = Instant.ofEpochMilli(startDateTimestamp).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = null;
        if (endDateTimestamp != 0) {
            endDate = Instant.ofEpochMilli(endDateTimestamp).atZone(ZoneId.systemDefault()).toLocalDate();
        }

        ArrayList<LocalTime> applicationTimesList = parseLongArray(applicationTimes);
        ArrayList<Medicament> medsList = parseMedsArray(medications);
        // Temporarily
        return new Treatment(
                medsList,
                startDate,
                endDate,
                applicationTimesList
                );
    }

    private static ArrayList<Medicament> parseMedsArray(String[] medications) {
        ArrayList<Medicament> result = new ArrayList<>();
        for (String medicationString : medications) {
            Optional<Medicament> med = parseSingleMedicament(medicationString);

            if (!med.isPresent()) {
                continue;
            }
            result.add(
                    med.get()
            );
        }
        return result;
    }

    private static ArrayList<LocalTime> parseLongArray(int[] applicationTimes) {
        return new ArrayList<LocalTime>(){{
            for (int time : applicationTimes) {
                add(LocalTime.of(time/60, time%60));
            }
        }};
    }
}
