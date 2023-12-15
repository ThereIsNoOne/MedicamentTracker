package com.szylas.medicamenttracker.models;

import androidx.annotation.NonNull;

import com.szylas.medicamenttracker.exceptions.OutOfMedsException;
import com.szylas.medicamenttracker.models.meds.Medicament;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Treatment implements Iterable<Medicament> {

    public ArrayList<Medicament> getMedications() {
        return medications;
    }

    public ArrayList<LocalTime> getApplicationTime() {
        return applicationTime;
    }

    private ArrayList<Medicament> medications = new ArrayList<>();
    private ArrayList<LocalTime> applicationTime;
    private final LocalDate startDate;

    // May be null to indicate that there is no finish date and treatment should be
    // continued till user cancels it.
    private LocalDate finishDate;

    public Treatment(Medicament medicament,
                     LocalDate startDate,
                     LocalDate finishDate,
                     ArrayList<LocalTime> applicationTime) {
        if (applicationTime.size() == 0) {
            throw new IllegalStateException("Application time should not be empty");
        }
        if (startDate == null) {
            throw new IllegalStateException("Start date should not be null");
        }
        this.applicationTime = applicationTime;
        this.startDate = startDate;
        this.finishDate = finishDate;
        medications.add(medicament);
    }

    public Treatment(ArrayList<Medicament> medicament,
                     LocalDate startDate,
                     LocalDate finishDate,
                     ArrayList<LocalTime> applicationTime) {
        if (applicationTime.size() == 0) {
            throw new IllegalStateException("Application time should not be empty");
        }
        if (startDate == null) {
            throw new IllegalStateException("Start date should not be null");
        }
        this.applicationTime = applicationTime;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.medications = medicament;
    }

    public Optional<List<Medicament>> confirmApplication(boolean isTaken) {
        if (!isTaken) {
            return Optional.empty();
        }
        List<Medicament> notEnough = new ArrayList<>();

        for (Medicament medicament : medications) {
            try {
                medicament.taken();
            } catch (OutOfMedsException exception) {
                notEnough.add(medicament);
            }
        }
        return notEnough.size() > 0 ? Optional.of(notEnough) : Optional.empty();
    }

    public void addMedicament(Medicament medicament) {
        medications.add(medicament);
    }

    public void removeMedicament(Medicament medicament) {
        medications.remove(medicament);
    }

    @NonNull
    @Override
    public Iterator<Medicament> iterator() {
        return medications.iterator();
    }

    public Medicament get(int index) {
        return medications.get(index);
    }

    public int size() {
        return medications.size();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Optional<LocalDate> getFinishDate() {
        return Optional.ofNullable(finishDate);
    }

    public ArrayList<MedTimePair<LocalTime, Medicament>> getMedTimePairs() {
        return new ArrayList<MedTimePair<LocalTime, Medicament>>() {{
            for (LocalTime time: applicationTime) {
                for (Medicament medicament: medications) {
                    add(new MedTimePair<>(time, medicament));
                }
            }
        }};
    }

    public String[] representation() {
        StringBuilder medBuilder = new StringBuilder();
        switch (medications.size()) {
            case 0:
                medBuilder.append("No drugs on list!");
                break;
            case 1:
                medBuilder.append(medications.get(0));
                break;
            case 2:
                medBuilder.append(medications.get(0)).append(", ").append(medications.get(1));
                break;
            default:
                medBuilder
                        .append(medications.get(0))
                        .append(", ")
                        .append(medications.get(1))
                        .append(", ...");
        }

        StringBuilder timeBuilder = new StringBuilder();
        timeBuilder.append(startDate);
        if (finishDate != null) {
            timeBuilder.append(" -> ").append(finishDate);
        }

        return new String[] {medBuilder.toString(), timeBuilder.toString()};
    }
}
