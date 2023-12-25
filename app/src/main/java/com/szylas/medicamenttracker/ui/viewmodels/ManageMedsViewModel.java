package com.szylas.medicamenttracker.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.szylas.medicamenttracker.models.Treatment;

public class ManageMedsViewModel extends DateTimeViewModel {

    private final MutableLiveData<Integer> currentPosition = new MutableLiveData<>();

    private final MutableLiveData<Treatment> newTreatment = new MutableLiveData<>();

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
