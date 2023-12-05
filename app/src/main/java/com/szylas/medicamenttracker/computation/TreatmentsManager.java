package com.szylas.medicamenttracker.computation;

import static com.szylas.medicamenttracker.computation.helpers.Sorter.sort;

import android.content.Context;

import com.szylas.medicamenttracker.datastore.TreatmentsReader;
import com.szylas.medicamenttracker.datastore.TreatmentsWriter;
import com.szylas.medicamenttracker.models.MedTimePair;
import com.szylas.medicamenttracker.models.Treatment;
import com.szylas.medicamenttracker.models.meds.Medicament;
import com.szylas.medicamenttracker.models.meds.Pill;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TreatmentsManager {
    private final ArrayList<Treatment> treatmentsList = new ArrayList<>();
    private final Context context;

    public TreatmentsManager(Context context) {
        this.context = context;
        treatmentsList.addAll(TreatmentsReader.load(context));
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

    public void saveTreatments() {
        TreatmentsWriter.save(treatmentsList, context);
    }

}
