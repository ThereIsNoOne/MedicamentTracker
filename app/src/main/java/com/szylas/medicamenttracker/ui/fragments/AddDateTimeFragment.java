package com.szylas.medicamenttracker.ui.fragments;

import androidx.navigation.Navigation;

import com.google.android.material.button.MaterialButton;
import com.szylas.medicamenttracker.R;
import com.szylas.medicamenttracker.ui.abstr.DateTimeFragment;


public class AddDateTimeFragment extends DateTimeFragment {

    @Override
    protected void setButton() {
        MaterialButton accept = view.findViewById(R.id.add_single_med);
        accept.setOnClickListener(view_param -> Navigation.findNavController(view).navigate(
                R.id.action_dateTimeFragment_to_medsFragment));
    }


}