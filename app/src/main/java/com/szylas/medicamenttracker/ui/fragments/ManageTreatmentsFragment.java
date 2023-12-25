package com.szylas.medicamenttracker.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.szylas.medicamenttracker.R;
import com.szylas.medicamenttracker.computation.TreatmentsManager;
import com.szylas.medicamenttracker.ui.activities.ManageMedsActivity;
import com.szylas.medicamenttracker.ui.adapters.ManageTreatmentAdapter;
import com.szylas.medicamenttracker.ui.helpers.Literals;
import com.szylas.medicamenttracker.ui.helpers.ParcelPacker;
import com.szylas.medicamenttracker.ui.viewmodels.ManageMedsViewModel;

public class ManageTreatmentsFragment extends Fragment {

    private ManageMedsViewModel viewModel;
    private View view;

    public ManageTreatmentsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_manage_treatments, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle bundle) {
        this.view = view;
        viewModel = new ViewModelProvider(requireActivity()).get(ManageMedsViewModel.class);
        setupRecyclerView();

        setupButton(view);
    }

    private void setupButton(@NonNull View view) {
        MaterialButton okButton = view.findViewById(R.id.manage_treatments_ok_button);
        okButton.setOnClickListener(((ManageMedsActivity) requireActivity())::okButton);
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = view.findViewById(R.id.manage_treatments_recycler_view);

        TreatmentsManager manager = ((ManageMedsActivity) requireActivity()).getManager();
        Log.d("AdapterCreation", "Creating adapter for treatments management.");
        // Informs which treatment is being modified
        // Packs information about treatment
        ManageTreatmentAdapter manageTreatmentAdapter = new ManageTreatmentAdapter(manager, position -> {
            // Informs which treatment is being modified
            viewModel.setCurrentPosition(position);

            // Packs information about treatment
            Bundle treatmentBundle = new Bundle();
            treatmentBundle.putParcelable(Literals.TREATMENT_PARCEL, ParcelPacker.pack(manager.get(position)));

            Navigation.findNavController(view).navigate(
                    R.id.action_manageMedsListFragment_to_manageDateTimesFragment, treatmentBundle);
        });
        recyclerView.setAdapter(manageTreatmentAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

    }
}