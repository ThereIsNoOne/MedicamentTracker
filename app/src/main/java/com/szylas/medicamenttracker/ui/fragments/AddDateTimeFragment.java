package com.szylas.medicamenttracker.ui.fragments;

import static android.text.format.DateFormat.is24HourFormat;
import static com.szylas.medicamenttracker.ui.helpers.InputParser.parseDateToString;
import static com.szylas.medicamenttracker.ui.helpers.InputParser.parseTimeToString;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
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
import com.szylas.medicamenttracker.ui.adapters.AddTimesAdapter;
import com.szylas.medicamenttracker.ui.helpers.Literals;
import com.szylas.medicamenttracker.ui.viewmodels.AddMedsViewModel;


public class AddDateTimeFragment extends DateTimeFragment {

    @Override
    protected void setViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(AddMedsViewModel.class);
    }
    @Override
    protected void setButton() {
        MaterialButton accept = view.findViewById(R.id.add_single_med);
        accept.setOnClickListener(view1 -> Navigation.findNavController(view).navigate(
                R.id.action_dateTimeFragment_to_medsFragment));
    }


}