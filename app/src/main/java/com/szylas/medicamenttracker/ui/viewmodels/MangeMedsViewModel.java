package com.szylas.medicamenttracker.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MangeMedsViewModel extends ViewModel {
    private final MutableLiveData<Integer> currentPosition = new MutableLiveData<>();


    public LiveData<Integer> getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Integer currentPosition) {
        this.currentPosition.setValue(currentPosition);
    }
}
