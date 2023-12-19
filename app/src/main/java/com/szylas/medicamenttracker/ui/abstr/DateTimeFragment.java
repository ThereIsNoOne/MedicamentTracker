package com.szylas.medicamenttracker.ui.abstr;

import static android.text.format.DateFormat.is24HourFormat;
import static com.szylas.medicamenttracker.ui.helpers.InputParser.parseDateToString;
import static com.szylas.medicamenttracker.ui.helpers.InputParser.parseTimeToString;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textview.MaterialTextView;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.szylas.medicamenttracker.R;
import com.szylas.medicamenttracker.ui.adapters.AddTimesAdapter;
import com.szylas.medicamenttracker.ui.helpers.Literals;
import com.szylas.medicamenttracker.ui.viewmodels.DateTimeViewModel;

import java.util.Objects;

public abstract class DateTimeFragment extends Fragment {
    protected View view;
    protected DateTimeViewModel viewModel;


    protected void setApplicationTimePicker(int timeFiledId, int timePickerId) {
        MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(is24HourFormat(view.getContext()) ? TimeFormat.CLOCK_24H : TimeFormat.CLOCK_12H)
                .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
                .build();

        MaterialTextView timeView = view.findViewById(timeFiledId);
        Button applicationTimeButton = view.findViewById(timePickerId);

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

    protected void setFinishDatePicker(int finishDateFiledId, int finishDatePickerId) {
        MaterialDatePicker.Builder<Long> datePickerBuilder = MaterialDatePicker.Builder.datePicker();

        // Finish date picker show dialog
        final MaterialDatePicker<Long> finishDatePicker = datePickerBuilder.build();

        MaterialTextView finishDateField = view.findViewById(finishDateFiledId);
        Button finishDatePickerButton = view.findViewById(finishDatePickerId);

        finishDatePickerButton.setOnClickListener(view -> finishDatePicker.show(
                getParentFragmentManager(), Literals.FINISH_PICKER
        ));
        finishDatePicker.addOnPositiveButtonClickListener(selection -> {
            finishDateField.setText(parseDateToString(selection));
            viewModel.setSelectedFinishDate(selection);
        });
    }

    protected void setStartDatePicker(int startDateFiledId, int startDatePickerId) {
        MaterialDatePicker.Builder<Long> datePickerBuilder = MaterialDatePicker.Builder.datePicker();

        // Start date picker show dialog
        final MaterialDatePicker<Long> startDatePicker = datePickerBuilder.build();

        MaterialTextView startDateField = view.findViewById(startDateFiledId);
        Button startDatePickerButton = view.findViewById(startDatePickerId);

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



    protected void setRecyclerView() {
        RecyclerView timeRecyclerView = view.findViewById(R.id.time_list);
        if (!(requireActivity() instanceof TimeAdapterable)) {
            throw new RuntimeException("Invalid activity detected!");
        }

        Class<? extends TimeAdapterable> type = (Class<? extends TimeAdapterable>) requireActivity().getClass();
        AddTimesAdapter adapter = (Objects.requireNonNull(type.cast(requireActivity())))
                .getAddTimesAdapter();

        if (adapter == null) {
            adapter = new AddTimesAdapter();
            Log.e("TimeListAdapter", "TimeListAdapter not found, replacing with empty!");
        }
        timeRecyclerView.setAdapter(adapter);
        timeRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    protected void setViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(DateTimeViewModel.class);
    }


    protected abstract void setButton();
}
