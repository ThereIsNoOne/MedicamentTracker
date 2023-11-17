package com.szylas.medicamenttracker.ui.helpers;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.szylas.medicamenttracker.builders.TreatmentBuilder;
import com.szylas.medicamenttracker.models.Treatment;

public class TreatmentParcel implements Parcelable {
    // As EPOCH timestamp in milliseconds
    private final long startDateTimestamp;
    private final long endDateTimestamp;
    // In minutes from day start (0:00)
    private final int[] applicationTimes;
    private final String[] medications;


    protected TreatmentParcel(Parcel in) {
        startDateTimestamp = in.readLong();
        endDateTimestamp = in.readLong();
        applicationTimes = in.createIntArray();
        medications = in.createStringArray();
    }

    public static final Creator<TreatmentParcel> CREATOR = new Creator<TreatmentParcel>() {
        @Override
        public TreatmentParcel createFromParcel(Parcel in) {
            return new TreatmentParcel(in);
        }

        @Override
        public TreatmentParcel[] newArray(int size) {
            return new TreatmentParcel[size];
        }
    };

    public TreatmentParcel(long startDateTimestamp, long endDateTimestamp, int[] applicationTimes, String[] medications) {
        this.startDateTimestamp = startDateTimestamp;
        this.endDateTimestamp = endDateTimestamp;
        this.applicationTimes = applicationTimes;
        this.medications = medications;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeLong(startDateTimestamp);
        parcel.writeLong(endDateTimestamp);
        parcel.writeIntArray(applicationTimes);
        parcel.writeStringArray(medications);
    }

    public Treatment getTreatment() {
        return TreatmentBuilder.getTreatment(startDateTimestamp, endDateTimestamp, applicationTimes, medications);
    }
}
