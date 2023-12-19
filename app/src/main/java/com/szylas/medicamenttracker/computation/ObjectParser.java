package com.szylas.medicamenttracker.computation;

import static com.szylas.medicamenttracker.sharedhelpers.MedicamentParser.parseSingleMedicament;

import androidx.annotation.NonNull;

import com.szylas.medicamenttracker.models.Treatment;
import com.szylas.medicamenttracker.models.meds.Medicament;
import com.szylas.medicamenttracker.models.meds.Syrup;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class ObjectParser {

    public static ArrayList<LocalTime> parseApplicationTime(String value) {
        String[] times = value.split(",");
        ArrayList<LocalTime> timesArray = new ArrayList<>();

        for (String time : times) {
            String[] timeVal = time.split(":");
            int hour = Integer.parseInt(timeVal[0]);
            int minute = Integer.parseInt(timeVal[1]);
            timesArray.add(LocalTime.of(hour, minute));
        }

        return timesArray;
    }

    public static ArrayList<Medicament> parseMedicamentList(String value) {
        String[] medications = value.split(",");
        ArrayList<Medicament> medicationsArray = new ArrayList<>();

        for (String medication : medications) {
            Optional<Medicament> med = parseSingleMedicament(medication);

            if (!med.isPresent()) {
                continue;
            }
            medicationsArray.add(
                    med.get()
            );
        }
        return medicationsArray;
    }

    public static String parseTimes(Treatment treatment) {
        return parseTimeToString(treatment.getApplicationTime());
    }

    public static String parseTimes(List<LocalTime> timeList) {
        return parseTimeToString(timeList);
    }

    @NonNull
    private static String parseTimeToString(List<LocalTime> timeList) {
        StringBuilder builder = new StringBuilder();
        for (LocalTime time : timeList) {
            builder.append(time.getHour());
            builder.append(":");
            builder.append(time.getMinute());
            builder.append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    public static String parseMeds(Treatment treatment) {
        return parseMedsToString(treatment.getMedications());
    }

    public static String parseMeds(List<Medicament> medicamentList) {
        return parseMedsToString(medicamentList);
    }

    @NonNull
    private static String parseMedsToString(List<Medicament> medicamentList) {
        StringBuilder builder = new StringBuilder();
        for (Medicament medicament : medicamentList) {
            String[] class_ = medicament.getClass().toString().split("\\.");
            builder.append(class_[class_.length - 1].toUpperCase());
            builder.append(":");
            builder.append(medicament.getName());
            builder.append(":");
            builder.append(medicament.getQuantity());
            builder.append(":");
            builder.append(medicament.getDose());
            if (medicament instanceof Syrup) {
                builder.append(":");
                builder.append(((Syrup) medicament).getVolume());
            }
            builder.append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}