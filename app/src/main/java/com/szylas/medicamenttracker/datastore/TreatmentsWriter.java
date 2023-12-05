package com.szylas.medicamenttracker.datastore;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.szylas.medicamenttracker.models.Treatment;
import com.szylas.medicamenttracker.models.meds.Medicament;
import com.szylas.medicamenttracker.models.meds.Syrup;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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

    public static void save(List<Treatment> treatments, Context context) {
        try (FileOutputStream outputStream = context.openFileOutput(PATH, Context.MODE_PRIVATE)) {
            String message = prepareMessage(treatments);
            outputStream.write(message.getBytes());
            Log.d("SAVING", String.format(message));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String prepareMessage(List<Treatment> treatments) {
        StringBuilder builder = new StringBuilder();
        for (Treatment treatment : treatments) {
            builder.append(FORMATTER.format(treatment.getStartDate()));
            builder.append(";");
            Optional<LocalDate> finishDate = treatment.getFinishDate();
            if (finishDate.isPresent()) {
                builder.append(FORMATTER.format(treatment.getFinishDate().get()));
            } else {
                builder.append("null");
            }
            builder.append(";");
            builder.append(parseMeds(treatment));
            builder.append(";");
            builder.append(parseTimes(treatment));
            builder.append("\n");
        }
        return builder.toString();
    }

    private static String parseTimes(Treatment treatment) {
        StringBuilder builder = new StringBuilder();
        for (LocalTime time: treatment.getApplicationTime()) {
            builder.append(time.getHour());
            builder.append(":");
            builder.append(time.getMinute());
            builder.append(",");
        }
        builder.deleteCharAt(builder.length()-1);
        return builder.toString();
    }

    private static String parseMeds(Treatment treatment) {
        StringBuilder builder = new StringBuilder();
        for (Medicament medicament: treatment.getMedications()) {
            String[] class_ = medicament.getClass().toString().split("\\.");
            builder.append(class_[class_.length-1].toUpperCase());
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
        builder.deleteCharAt(builder.length()-1);
        return builder.toString();
    }

    private TreatmentsWriter() {

    }

}
