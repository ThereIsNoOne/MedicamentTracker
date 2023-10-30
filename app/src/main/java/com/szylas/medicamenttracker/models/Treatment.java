package com.szylas.medicamenttracker.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Treatment {

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

    public void confirmApplication(boolean isTaken) {
        if (!isTaken) {
            return;
        }
        for (Medicament medicament : medications) {
            medicament.taken();
        }
    }

    public void addMedicament(Medicament medicament) {
        medications.add(medicament);
    }

    public void removeMedicament(Medicament medicament) {
        medications.remove(medicament);
    }
}
