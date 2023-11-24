package com.szylas.medicamenttracker.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.szylas.medicamenttracker.R;

public class MedsFragment extends Fragment {

    private View view;

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
        setButton();
    }

    private void setButton() {
        MaterialButton button = view.findViewById(R.id.manage_dates_button);
        button.setOnClickListener(view -> Navigation.findNavController(view).navigate(
                R.id.action_medsFragment_to_dateTimeFragment));
    }
}