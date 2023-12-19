package com.szylas.medicamenttracker.ui.helpers;

import static com.szylas.medicamenttracker.computation.ObjectParser.parseMeds;
import static com.szylas.medicamenttracker.computation.ObjectParser.parseTimes;

import com.szylas.medicamenttracker.models.Treatment;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

public final class ParcelPacker {
    public static int[] packTimesToArray(List<Integer> times) {
        return times.stream().mapToInt(i -> i).toArray();
    }

    public static String[] packMedsToArray(List<String> meds) {
        String[] result = new String[meds.size()];
        for (int i = 0; i < meds.size(); i++) {
            result[i] = meds.get(i);
        }
        return result;
    }

    public static TreatmentParcel pack(Treatment treatment) {
        long startTimestamp = LocalDateTime.of(treatment.getStartDate(), LocalTime.of(0, 0))
                .atZone(ZoneId.systemDefault())
                .toEpochSecond() * 1000L;

        long endTimestamp = 0L;
        if (treatment.getFinishDate().isPresent()) {
            endTimestamp = LocalDateTime.of(treatment.getFinishDate().get(), LocalTime.of(0, 0))
                    .atZone(ZoneId.systemDefault())
                    .toEpochSecond() * 1000L;
        }

        int[] timeArray = treatment.getApplicationTime()
                .stream()
                .mapToInt(item -> item.getHour() * 60 + item.getMinute())
                .toArray();
        String[] medsString = parseMeds(treatment.getMedications()).split(",");

        return new TreatmentParcel(startTimestamp, endTimestamp, timeArray, medsString);
    }
}
