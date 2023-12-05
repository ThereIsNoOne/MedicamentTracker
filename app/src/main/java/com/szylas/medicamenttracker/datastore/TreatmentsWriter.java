package com.szylas.medicamenttracker.datastore;

import android.content.res.AssetManager;
import android.util.Log;

import com.szylas.medicamenttracker.models.Treatment;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.List;

public final class TreatmentsWriter {

    private static final String PATH = "TreatmentStore.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final String PREAMBLE = "# Treatments are stored in this file in following format\n" +
            "# StartingDate[DD/MM/YYYY]:FinishingDate[DD/MM/YYYY]:Meds,:ApplicationTime[HH:mm],\n" +
            "# Line starting with '#' are ignored\n" +
            "# Meds should be formatted like this:\n" +
            "# ClassName:Name:Quantity:Dose{:Volume}\n" +
            "# Where {} are optional and depends on the type of the med.\n" +
            "\n" +
            "# WARNING: Do NOT change this file, it may (and probably will) cause app to stop working!";

    public static void save(List<Treatment> treatments, AssetManager assets) {
        Log.d("SAVING", String.format("Saving list of length %d", treatments.size()));
    }

    private TreatmentsWriter() {

    }

}
