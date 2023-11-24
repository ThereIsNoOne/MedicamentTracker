package com.szylas.medicamenttracker.ui.fragments;

import static android.text.format.DateFormat.is24HourFormat;
import static com.szylas.medicamenttracker.ui.helpers.InputParser.parseDateToString;
import static com.szylas.medicamenttracker.ui.helpers.InputParser.parseTimeToString;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textview.MaterialTextView;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.szylas.medicamenttracker.R;
import com.szylas.medicamenttracker.ui.activities.AddMedsActivity;
import com.szylas.medicamenttracker.ui.adapters.TimeListAdapter;
import com.szylas.medicamenttracker.ui.helpers.Literals;
import com.szylas.medicamenttracker.ui.viewmodels.AddMedsViewModel;


public class DateTimeFragment extends Fragment {

    private View view;
    private AddMedsViewModel viewModel;


    private void setApplicationTimePicker() {
        MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(is24HourFormat(view.getContext()) ? TimeFormat.CLOCK_24H : TimeFormat.CLOCK_12H)
                .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
                .build();

        MaterialTextView timeView = view.findViewById(R.id.application_time_field);
        Button applicationTimeButton = view.findViewById(R.id.application_time_picker);

        applicationTimeButton.setOnClickListener(view -> timePicker.show(
                getParentFragmentManager(), Literals.TIME_PICKER
        ));

        timePicker.addOnPositiveButtonClickListener(result -> {
            viewModel.setSelectTime(
                    timePicker.getHour() * 60 + timePicker.getMinute()
            );
            timeView.setText(parseTimeToString(timePicker.getHour(), timePicker.getMinute()));
            timePicker.setHour(0);
            timePicker.setMinute(0);
        });
    }

    private void setFinishDatePicker() {
        MaterialDatePicker.Builder<Long> datePickerBuilder = MaterialDatePicker.Builder.datePicker();

        // Finish date picker show dialog
        final MaterialDatePicker<Long> finishDatePicker = datePickerBuilder.build();

        MaterialTextView finishDateField = view.findViewById(R.id.finish_date_field);
        Button finishDatePickerButton = view.findViewById(R.id.finish_date_picker);

        finishDatePickerButton.setOnClickListener(view -> finishDatePicker.show(
                getParentFragmentManager(), Literals.FINISH_PICKER
        ));
        finishDatePicker.addOnPositiveButtonClickListener(selection -> {
            finishDateField.setText(parseDateToString(selection));
            viewModel.setSelectedFinishDate(selection);
        });
    }

    private void setStartDatePicker() {
        MaterialDatePicker.Builder<Long> datePickerBuilder = MaterialDatePicker.Builder.datePicker();

        // Start date picker show dialog
        final MaterialDatePicker<Long> startDatePicker = datePickerBuilder.build();

        MaterialTextView startDateField = view.findViewById(R.id.start_date_field);
        Button startDatePickerButton = view.findViewById(R.id.start_date_picker);

        startDatePickerButton.setOnClickListener(view -> startDatePicker.show(
                getParentFragmentManager(), Literals.START_PICKER
        ));
        startDatePicker.addOnPositiveButtonClickListener(selection -> {
            startDateField.setText(parseDateToString(selection));
            viewModel.setSelectedStartDate(selection);
        });
    }

    public DateTimeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_date_time, container, false);
    }

    private void setRecyclerView() {
        RecyclerView timeRecyclerView = view.findViewById(R.id.time_list);
        TimeListAdapter adapter = ((AddMedsActivity) DateTimeFragment.this.getActivity())
                .getTimeListAdapter();
        if (adapter == null) {
            adapter = new TimeListAdapter();
            Log.e("TimeListAdapter", "TimeListAdapter not found, replacing with empty!");
        }
        timeRecyclerView.setAdapter(adapter);
        timeRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.view = view;
        viewModel = new ViewModelProvider(requireActivity()).get(AddMedsViewModel.class);
        setRecyclerView();
        setApplicationTimePicker();
        setFinishDatePicker();
        setStartDatePicker();
        setButton();
    }

    private void setButton() {
        MaterialButton accept = view.findViewById(R.id.add_single_med);
        accept.setOnClickListener(view1 -> Navigation.findNavController(view).navigate(
                R.id.action_dateTimeFragment_to_medsFragment));
    }


}