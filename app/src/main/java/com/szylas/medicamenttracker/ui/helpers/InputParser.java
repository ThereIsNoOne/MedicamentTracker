package com.szylas.medicamenttracker.ui.helpers;

import android.annotation.SuppressLint;
import android.util.Log;

import com.google.android.material.textfield.TextInputEditText;
import com.szylas.medicamenttracker.models.MedType;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

public final class InputParser {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String parseDateToString(long timestamp) {
        return Instant
                .ofEpochMilli(timestamp)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .format(formatter);
    }

    public static String parseDateToString(LocalDate date) {
        return date.format(formatter);
    }

    @SuppressLint("DefaultLocale")
    public static String parseTimeToString(int hour, int minute) {
        return String.format("%02d:%02d", hour, minute);
    }

    public static String parseInput(MedType type, Map<InputType, TextInputEditText> map, String medName) {
        String medTypeStr = type.name();
        String quantityStr;
        String doseStr;
        String volumeStr;
        try {
            quantityStr = String.valueOf(Objects.requireNonNull(map.get(InputType.QUANTITY)).getText());
        } catch (NullPointerException e) {
            quantityStr = "0";
        }
        try {
            doseStr = String.valueOf(Objects.requireNonNull(map.get(InputType.DOSE)).getText());
        } catch (NullPointerException e) {
            doseStr = "0";
        }
        if (type == MedType.SYRUP) {
            try {
                volumeStr = String.valueOf(Objects.requireNonNull(map.get(InputType.VOLUME)).getText());
            } catch (NullPointerException e) {
                volumeStr = "0";
            }
        } else {
            volumeStr = "";
        }
        String result = medTypeStr + ":" + medName + ":" + quantityStr + ":" + doseStr;
        if (type == MedType.SYRUP) {
            result += ":" + volumeStr;
        }
        Log.d("Result:", result);
        return result;
    }
}
