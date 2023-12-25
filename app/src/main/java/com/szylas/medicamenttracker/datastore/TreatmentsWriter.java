package com.szylas.medicamenttracker.datastore;

import android.content.Context;
import android.util.Log;

import com.szylas.medicamenttracker.computation.ObjectParser;
import com.szylas.medicamenttracker.models.Treatment;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
        String message = prepareMessage(treatments);

        synchronized (String.class) {
            try (FileOutputStream outputStream = context.openFileOutput(PATH, Context.MODE_PRIVATE)) {
                outputStream.write(message.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Log.d("SAVING", String.format(message));
    }

    private static String prepareMessage(List<Treatment> treatments) {
        StringBuilder builder = new StringBuilder();
        for (Treatment treatment : treatments) {
            builder.append(FORMATTER.format(treatment.getStartDate()));
            builder.append(";");
            Optional<LocalDate> finishDate = treatment.getFinishDate();
            if (finishDate.isPresent()) {
                builder.append(FORMATTER.format(finishDate.get()));
            } else {
                builder.append("null");
            }
            builder.append(";");
            builder.append(ObjectParser.parseMeds(treatment));
            builder.append(";");
            builder.append(ObjectParser.parseTimes(treatment));
            builder.append("\n");
        }
        return builder.toString();
    }

    private TreatmentsWriter() {

    }

}
