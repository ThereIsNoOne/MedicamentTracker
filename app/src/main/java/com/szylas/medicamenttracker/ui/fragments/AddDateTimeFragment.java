package com.szylas.medicamenttracker.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;

import com.google.android.material.button.MaterialButton;
import com.szylas.medicamenttracker.R;
import com.szylas.medicamenttracker.ui.abstr.DateTimeFragment;
import com.szylas.medicamenttracker.ui.viewmodels.TreatmentDataViewModel;


public class AddDateTimeFragment extends DateTimeFragment {

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        this.view = view;
        setViewModel(TreatmentDataViewModel.class);
        setRecyclerView();
        setApplicationTimePicker(R.id.application_time_field, R.id.application_time_picker);
        setFinishDatePicker(R.id.finish_date_field, R.id.finish_date_picker);
        setStartDatePicker(R.id.start_date_field, R.id.start_date_picker);
        setButton();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_date_time, container, false);
    }

    @Override
    protected void setButton() {
        MaterialButton accept = view.findViewById(R.id.add_single_med);
        accept.setOnClickListener(view_param -> Navigation.findNavController(view).navigate(
                R.id.action_dateTimeFragment_to_medsFragment));
    }


}