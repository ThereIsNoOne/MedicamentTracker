package com.szylas.medicamenttracker.ui.helpers;

import java.util.List;

public final class ParcelPacker {
    public static int[] packTimesToArray(List<Integer> times) {
        return times.stream().mapToInt(i -> i).toArray();
    }

    public static String[] packMedsToArray(List<String> meds) {
        String[] result = new String[meds.size()];
        for (int i = 0; i < meds.size(); i++) {
            result[i] = meds.get(i);
        }
        return result;
    }
}
