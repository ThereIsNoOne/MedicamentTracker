package com.szylas.medicamenttracker.ui.helpers;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.szylas.medicamenttracker.models.MedType;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

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

    public static String parseInput(MedType type, Map<InputType, TextInputEditText> map, String medName) {
        String medTypeStr = type.name();
        String quantityStr;
        String doseStr;
        String volumeStr;
        try {
            quantityStr = String.valueOf(map.get(InputType.QUANTITY).getText());
        } catch (NullPointerException e) {
            quantityStr = "0";
        }
        try {
            doseStr = String.valueOf(map.get(InputType.DOSE).getText());
        } catch (NullPointerException e) {
            doseStr = "0";
        }
        if (type == MedType.SYRUP) {
            try {
                volumeStr = String.valueOf(map.get(InputType.VOLUME).getText());
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
