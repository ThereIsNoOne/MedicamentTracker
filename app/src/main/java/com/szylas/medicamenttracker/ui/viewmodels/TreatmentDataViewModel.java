package com.szylas.medicamenttracker.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TreatmentDataViewModel extends ViewModel {
    private final MutableLiveData<Integer> selectedApplicationTime = new MutableLiveData<>();
    private final MutableLiveData<Long> selectedStartDate = new MutableLiveData<>();
    private final MutableLiveData<Long> selectedFinishDate = new MutableLiveData<>();
    private final MutableLiveData<String> selectedMed = new MutableLiveData<>();


    public void setSelectedMed(String item) {
        selectedMed.postValue(item);
    }

    public LiveData<String> getSelectedMed() {
        return selectedMed;
    }

    public void setSelectTime(Integer time) {
        selectedApplicationTime.setValue(time);
    }

    public LiveData<Integer> getSelectedTime() {
        return selectedApplicationTime;
    }

    public void setSelectedStartDate(long startDate) {
        selectedStartDate.setValue(startDate);
    }

    public LiveData<Long> getSelectedStartDate() {
        return selectedStartDate;
    }

    public void setSelectedFinishDate(long finishDate) {
        selectedFinishDate.setValue(finishDate);
    }

    public LiveData<Long> getSelectedFinishDate() {
        return selectedFinishDate;
    }
}
