package com.szylas.medicamenttracker.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;

import com.google.android.material.button.MaterialButton;
import com.szylas.medicamenttracker.R;
import com.szylas.medicamenttracker.ui.abstr.DateTimeFragment;

public class ManageDateTimesFragment extends DateTimeFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_manage_date_times, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        this.view = view;
        setViewModel();
        setRecyclerView();
        setApplicationTimePicker(R.id.application_time_field, R.id.application_time_picker);
        setFinishDatePicker(R.id.finish_date_field, R.id.finish_date_picker);
        setStartDatePicker(R.id.start_date_field, R.id.start_date_picker);
        setButton();

        setDates();
    }

    private void setDates() {
        ((TextView) view.findViewById(R.id.application_time_field)).setText("");
        ((TextView) view.findViewById(R.id.finish_date_field)).setText("");
        ((TextView) view.findViewById(R.id.start_date_field)).setText("");
    }

    @Override
    protected void setButton() {
        MaterialButton accept = view.findViewById(R.id.add_single_med);
        accept.setOnClickListener(view_param -> Navigation.findNavController(view).navigate(
                R.id.action_manageDateTimesFragment_to_manageMedicationsFragment));
    }
}