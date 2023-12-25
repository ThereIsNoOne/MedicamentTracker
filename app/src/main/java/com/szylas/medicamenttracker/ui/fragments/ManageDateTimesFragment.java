package com.szylas.medicamenttracker.ui.fragments;


import static com.szylas.medicamenttracker.ui.helpers.InputParser.parseDateToString;
import static com.szylas.medicamenttracker.ui.helpers.ParcelPacker.pack;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;

import com.google.android.material.button.MaterialButton;
import com.szylas.medicamenttracker.R;
import com.szylas.medicamenttracker.models.Treatment;
import com.szylas.medicamenttracker.ui.abstr.DateTimeFragment;
import com.szylas.medicamenttracker.ui.helpers.Literals;
import com.szylas.medicamenttracker.ui.helpers.TreatmentParcel;
import com.szylas.medicamenttracker.ui.viewmodels.ManageMedsViewModel;

public class ManageDateTimesFragment extends DateTimeFragment {

    private Treatment treatment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_manage_date_times, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        this.view = view;
        setViewModel(ManageMedsViewModel.class);
        setRecyclerView();
        setApplicationTimePicker(R.id.application_time_field, R.id.application_time_picker);
        setFinishDatePicker(R.id.finish_date_field, R.id.finish_date_picker);
        setStartDatePicker(R.id.start_date_field, R.id.start_date_picker);

        Thread thread = new Thread(this::run);
        thread.start();
    }

    private void run() {
        getTreatment();
        fillAdapter();
    }

    private void fillAdapter() {
        treatment.getApplicationTime().stream()
                .mapToInt(item -> item.getHour() * 60 + item.getMinute())
                .forEach(item -> adapter.addItem(item));
    }

    private void setDates() {
        if (treatment == null) return;

        ((TextView) view.findViewById(R.id.start_date_field))
                .setText(parseDateToString(treatment.getStartDate()));

        if (treatment.getFinishDate().isPresent()) {
            ((TextView) view.findViewById(R.id.finish_date_field))
                    .setText(parseDateToString(treatment.getFinishDate().get()));
        }
    }

    private void getTreatment() {

        if (getArguments() == null) {
            // TODO: Add null handling
            Log.d("FRAGMENT_ARGS", "Args are null");
            return;
        }
        TreatmentParcel treatmentParcel = getArguments().getParcelable(Literals.TREATMENT_PARCEL, TreatmentParcel.class);

        if (treatmentParcel == null) {
            return;
        }

        treatment = treatmentParcel.getTreatment();
        requireActivity().runOnUiThread(this::setDates);
        requireActivity().runOnUiThread(this::setButton);
    }

    @Override
    protected void setButton() {

        Bundle bundle = new Bundle();
        bundle.putParcelable(Literals.TREATMENT_PARCEL, pack(treatment));

        MaterialButton accept = view.findViewById(R.id.add_single_med);
        accept.setOnClickListener(view_param -> Navigation.findNavController(view).navigate(
                R.id.action_manageDateTimesFragment_to_manageMedicationsFragment, bundle));
    }
}