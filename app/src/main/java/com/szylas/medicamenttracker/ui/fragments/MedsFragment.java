package com.szylas.medicamenttracker.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.szylas.medicamenttracker.R;
import com.szylas.medicamenttracker.models.MedType;
import com.szylas.medicamenttracker.ui.activities.AddMedsActivity;
import com.szylas.medicamenttracker.ui.adapters.AddMedsAdapter;
import com.szylas.medicamenttracker.ui.adapters.AddTimesAdapter;
import com.szylas.medicamenttracker.ui.helpers.InputType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MedsFragment extends Fragment {

    private View view;
    private Map<InputType, TextInputEditText> inputs = new HashMap<>();

    public MedsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meds, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle bundle) {
        this.view = view;
        setRecyclerView();
        findInputs();
        setBackButton();
        setComboBox();
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = view.findViewById(R.id.add_meds_recycler_view);
        AddMedsAdapter adapter = ((AddMedsActivity) MedsFragment.this.getActivity())
                .getAddMedsAdapter();
        if (adapter == null) {
            adapter = new AddMedsAdapter();
            Log.e("TimeListAdapter", "TimeListAdapter not found, replacing with empty!");
        }
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    private void findInputs() {
        inputs.put(InputType.DOSE, view.findViewById(R.id.dose_input));
        inputs.put(InputType.VOLUME, view.findViewById(R.id.volume_input));
        inputs.put(InputType.QUANTITY, view.findViewById(R.id.quantity_input));
    }

    private void setComboBox() {
        AutoCompleteTextView inputLayout = view.findViewById(R.id.med_type_combo_box_item);
        ArrayAdapter<MedType> adapter = new ArrayAdapter<>(requireContext(), R.layout.med_type_item, MedType.values());
        inputLayout.setAdapter(adapter);
        inputLayout.setOnItemClickListener((adapter_, view_, i, l) -> enableInputs((MedType) adapter_.getItemAtPosition(i)));

    }

    private void enableInputs(MedType itemAtPosition) {
        Objects.requireNonNull(inputs.get(InputType.DOSE)).setEnabled(true);
        Objects.requireNonNull(inputs.get(InputType.QUANTITY)).setEnabled(true);
        Objects.requireNonNull(inputs.get(InputType.VOLUME)).setEnabled(itemAtPosition == MedType.SYRUP);
    }


    private void setBackButton() {
        MaterialButton button = view.findViewById(R.id.manage_dates_button);
        button.setOnClickListener(view -> Navigation.findNavController(view).navigate(
                R.id.action_medsFragment_to_dateTimeFragment));
    }

}