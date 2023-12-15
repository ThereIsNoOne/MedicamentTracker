package com.szylas.medicamenttracker.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TreatmentDataViewModel extends DateTimeViewModel {
    private final MutableLiveData<String> selectedMed = new MutableLiveData<>();


    public void setSelectedMed(String item) {
        selectedMed.postValue(item);
    }

    public LiveData<String> getSelectedMed() {
        return selectedMed;
    }


}
