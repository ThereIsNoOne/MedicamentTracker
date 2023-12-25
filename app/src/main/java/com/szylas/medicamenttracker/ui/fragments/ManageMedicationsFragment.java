package com.szylas.medicamenttracker.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.szylas.medicamenttracker.R;
import com.szylas.medicamenttracker.models.Treatment;
import com.szylas.medicamenttracker.ui.adapters.ManageMedsAdapter;
import com.szylas.medicamenttracker.ui.helpers.Literals;
import com.szylas.medicamenttracker.ui.helpers.TreatmentParcel;
import com.szylas.medicamenttracker.ui.viewmodels.ManageMedsViewModel;

public class ManageMedicationsFragment extends Fragment {

    private View view;
    private Treatment treatment;
    private ManageMedsViewModel viewModel;

    public ManageMedicationsFragment() {
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
        return inflater.inflate(R.layout.fragment_manage_medications, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle bundle) {
        this.view = view;
        viewModel = new ViewModelProvider(requireActivity()).get(ManageMedsViewModel.class);
        treatment = getArguments()
                .getParcelable(Literals.TREATMENT_PARCEL, TreatmentParcel.class)
                .getTreatment();


        setRecyclerView();
        setButton();
    }

    private void setButton() {
        MaterialButton okButton = view.findViewById(R.id.manage_meds_ok_button);

        okButton.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(
                    R.id.action_manageMedicationsFragment_to_manageMedsListFragment
            );
        });
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = view.findViewById(R.id.manage_meds_recycler_view);
        ManageMedsAdapter adapter = new ManageMedsAdapter(treatment);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }
}