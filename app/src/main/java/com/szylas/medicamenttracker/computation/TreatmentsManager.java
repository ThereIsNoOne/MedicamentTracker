package com.szylas.medicamenttracker.computation;

import static com.szylas.medicamenttracker.computation.helpers.Sorter.sort;

import android.content.res.AssetManager;

import com.szylas.medicamenttracker.datastore.TreatmentsReader;
import com.szylas.medicamenttracker.models.MedTimePair;
import com.szylas.medicamenttracker.models.Treatment;
import com.szylas.medicamenttracker.models.meds.Medicament;
import com.szylas.medicamenttracker.models.meds.Pill;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TreatmentsManager {
    /**
     * Stores treatments, as a map where treatment is value and starting date is the key.
     */
    private HashMap<LocalDate, Treatment> treatmentsMap = new HashMap<>();

    public TreatmentsManager(AssetManager assets) {
        for (Treatment treatment : TreatmentsReader.loadTreatments(assets)) {
            treatmentsMap.put(treatment.getStartDate(), treatment);
        }
    }

    public ArrayList<MedTimePair<LocalTime, Medicament>> treatmentsForDay(LocalDate date) {
        ArrayList<MedTimePair<LocalTime, Medicament>> todayMeds = new ArrayList<>();

        for (Map.Entry<LocalDate, Treatment> entry : treatmentsMap.entrySet()) {
            if (entry.getKey().isAfter(date)) {
                continue;
            }
            if (entry.getValue().getFinishDate().isPresent() &&
                    entry.getValue().getFinishDate().get().isBefore(date)) {
                continue;
            }
            todayMeds.addAll(entry.getValue().getMedTimePairs());
        }
        return sort(todayMeds);
    }

    public void testPut() {
        treatmentsMap.put(LocalDate.now(), new Treatment(
                new Pill("Test", 30, 1),
                LocalDate.of(2022, 11, 1),
                LocalDate.of(2024, 11, 1),
                new ArrayList<LocalTime>() {{
                    LocalTime.of(8, 30);
                    LocalTime.of(19, 30);
                }}
        ));
    }
}
