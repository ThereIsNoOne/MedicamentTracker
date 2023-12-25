package com.szylas.medicamenttracker.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.szylas.medicamenttracker.models.Treatment;

import java.util.concurrent.atomic.AtomicBoolean;

public class ManageMedsViewModel extends DateTimeViewModel {

    private final MutableLiveData<Integer> currentPosition = new MutableLiveData<>();
    private final MutableLiveData<AtomicBoolean> notification = new MutableLiveData<>(new AtomicBoolean(false));
    private final MutableLiveData<Treatment> newTreatment = new MutableLiveData<>();

    public LiveData<AtomicBoolean> getNotify() {
        return notification;
    }

    public void setNotify(boolean value) {
        notification.postValue(new AtomicBoolean(value));
    }

    public LiveData<Treatment> getNewTreatment() {
        return newTreatment;
    }

    public void setNewTreatment(Treatment treatment) {
        newTreatment.setValue(treatment);
    }

    public LiveData<Integer> getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Integer currentPosition) {
        this.currentPosition.setValue(currentPosition);
    }
}
