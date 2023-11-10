package com.szylas.medicamenttracker.computation;

import static com.szylas.medicamenttracker.computation.helpers.Sorter.sort;

import android.content.res.AssetManager;

import com.szylas.medicamenttracker.datastore.TreatmentsReader;
import com.szylas.medicamenttracker.models.MedTimePair;
import com.szylas.medicamenttracker.models.Treatment;
import com.szylas.medicamenttracker.models.meds.Medicament;
import com.szylas.medicamenttracker.models.meds.Pill;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TreatmentsManager {
    /**
     * Stores treatments, as a map where treatment is value and starting date is the key.
     */
    private ArrayList<Treatment> treatmentsList = new ArrayList<>();

    public TreatmentsManager(AssetManager assets) {
        treatmentsList.addAll(TreatmentsReader.loadTreatments(assets));
    }

    public ArrayList<MedTimePair<LocalTime, Medicament>> treatmentsForDay(LocalDate date) {
        ArrayList<MedTimePair<LocalTime, Medicament>> todayMeds = new ArrayList<>();

        for (Treatment treatment : treatmentsList) {
            if (treatment.getStartDate().isAfter(date)) {
                continue;
            }
            if (treatment.getFinishDate().isPresent() &&
                    treatment.getFinishDate().get().isBefore(date)) {
                continue;
            }
            todayMeds.addAll(treatment.getMedTimePairs());
        }
        return sort(todayMeds);
    }

    public void addNewTreatment(Treatment treatment) {
        treatmentsList.add(treatment);
    }

    public void testPut() {
        treatmentsList.add(new Treatment(
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
