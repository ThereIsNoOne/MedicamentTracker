package com.szylas.medicamenttracker.ui.helpers;

import android.annotation.SuppressLint;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public final class InputParser {
    public static String parseDateToString(long timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return Instant
                .ofEpochMilli(timestamp)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .format(formatter);
    }

    @SuppressLint("DefaultLocale")
    public static String parseTimeToString(int hour, int minute) {
        return String.format("%02d:%02d", hour, minute);
    }
}
