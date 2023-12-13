package com.szylas.medicamenttracker.ui.fragments;


import androidx.navigation.Navigation;

import com.google.android.material.button.MaterialButton;
import com.szylas.medicamenttracker.R;

public class ManageDateTimesFragment extends DateTimeFragment {

    @Override
    protected void setButton() {
        MaterialButton accept = view.findViewById(R.id.add_single_med);
        accept.setOnClickListener(view_param -> Navigation.findNavController(view).navigate(
                R.id.action_manageDateTimesFragment_to_manageMedicationsFragment));
    }
}