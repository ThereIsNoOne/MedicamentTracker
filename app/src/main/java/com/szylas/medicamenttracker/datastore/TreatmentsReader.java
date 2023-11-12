package com.szylas.medicamenttracker.datastore;

import static com.szylas.medicamenttracker.sharedhelpers.MedicamentParser.determineType;
import static com.szylas.medicamenttracker.sharedhelpers.MedicamentParser.parseSingleMedicament;

import android.content.res.AssetManager;

import com.szylas.medicamenttracker.builders.MedicamentFactory;
import com.szylas.medicamenttracker.models.MedType;
import com.szylas.medicamenttracker.models.meds.Medicament;
import com.szylas.medicamenttracker.models.Treatment;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public final class TreatmentsReader {

    private static final String PATH = "TreatmentStore.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private TreatmentsReader() {

    }

    public static ArrayList<Treatment> loadTreatments(AssetManager assets) {

        InputStream stream;
        try {
            stream = assets.open(PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Treatment> treatments = new ArrayList<>();

        Scanner scanner;
        scanner = new Scanner(stream);

        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.startsWith("#")) {
                continue;
            }

            String[] values = line.split(";");
            if (values.length != 4) {
                continue;
            }

            Treatment treatment = getTreatment(values);
            treatments.add(treatment);
        }
        return treatments;
    }

    private static Treatment getTreatment(String[] values) {
        LocalDate startDate = LocalDate.parse(values[0], FORMATTER);

        LocalDate finishDate = null;
        if (!values[1].equals("null")) {
            finishDate = LocalDate.parse(values[1], FORMATTER);
        }

        ArrayList<Medicament> medicament = parseMedicamentList(values[2]);
        ArrayList<LocalTime> applicationTime = parseApplicationTime(values[3]);

        return new Treatment(medicament, startDate, finishDate, applicationTime);
    }

    private static ArrayList<LocalTime> parseApplicationTime(String value) {
        String[] times = value.split(",");
        ArrayList<LocalTime> timesArray = new ArrayList<>();

        for (String time : times) {
            timesArray.add(LocalTime.parse(time));
        }

        return timesArray;
    }

    private static ArrayList<Medicament> parseMedicamentList(String value) {
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

}
