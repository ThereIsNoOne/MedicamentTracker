package com.szylas.medicamenttracker.computation.helpers;

import com.szylas.medicamenttracker.models.MedTimePair;
import com.szylas.medicamenttracker.models.meds.Medicament;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Sorter {

    public static ArrayList<MedTimePair<LocalTime, Medicament>> sort(
            ArrayList<MedTimePair<LocalTime, Medicament>> array) {
        Comparator<MedTimePair<LocalTime, Medicament>> comparator = Comparator.comparing(MedTimePair::getKey);
        array.sort(comparator);
        return array;
    }
}
