package com.szylas.medicamenttracker.ui.helpers;

import java.util.Arrays;
import java.util.List;

public final class ParcelPacker {
    public static int[] packTimesToArray(List<Integer> times) {
        return times.stream().mapToInt(i -> i).toArray();
    }
}
