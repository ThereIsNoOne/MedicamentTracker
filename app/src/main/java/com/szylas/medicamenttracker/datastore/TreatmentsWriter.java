package com.szylas.medicamenttracker.datastore;

public final class TreatmentsWriter {

    private static final String PREAMBLE = "# Treatments are stored in this file in following format\n" +
            "# StartingDate[DD/MM/YYYY]:FinishingDate[DD/MM/YYYY]:Meds,:ApplicationTime[HH:mm],\n" +
            "# Line starting with '#' are ignored\n" +
            "# Meds should be formatted like this:\n" +
            "# ClassName:Name:Quantity:Dose{:Volume}\n" +
            "# Where {} are optional and depends on the type of the med.\n" +
            "\n" +
            "# WARNING: Do NOT change this file, it may (and probably will) cause app to stop working!";

    private TreatmentsWriter() {

    }

    public void Save() {

    }

}
