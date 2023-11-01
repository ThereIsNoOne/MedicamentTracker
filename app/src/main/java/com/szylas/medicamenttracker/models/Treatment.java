package com.szylas.medicamenttracker.models;

import androidx.annotation.NonNull;

import com.szylas.medicamenttracker.exceptions.OutOfMedsException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Treatment implements Iterable<Medicament> {

    private ArrayList<Medicament> medications = new ArrayList<>();
    private ArrayList<LocalTime> applicationTime = new ArrayList<>();
    private final LocalDate startDate;

    // May be null to indicate that there is no finish date and treatment should be
    // continued till user cancels it.
    private LocalDate finishDate;

    public Treatment(Medicament medicament,
                     LocalDate startDate,
                     LocalDate finishDate,
                     ArrayList<LocalTime> applicationTime) {
        this.applicationTime = applicationTime;
        this.startDate = startDate;
        this.finishDate = finishDate;
        medications.add(medicament);
    }

    public Treatment(ArrayList<Medicament> medicament,
                     LocalDate startDate,
                     LocalDate finishDate,
                     ArrayList<LocalTime> applicationTime) {
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
}
