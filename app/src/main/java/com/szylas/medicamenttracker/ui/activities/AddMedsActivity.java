package com.szylas.medicamenttracker.ui.activities;

import static com.szylas.medicamenttracker.ui.helpers.ParcelPacker.packMedsToArray;
import static com.szylas.medicamenttracker.ui.helpers.ParcelPacker.packTimesToArray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.szylas.medicamenttracker.R;
import com.szylas.medicamenttracker.ui.adapters.AddMedsAdapter;
import com.szylas.medicamenttracker.ui.adapters.AddTimesAdapter;
import com.szylas.medicamenttracker.ui.helpers.Literals;
import com.szylas.medicamenttracker.ui.helpers.TreatmentParcel;
import com.szylas.medicamenttracker.ui.viewmodels.TreatmentDataViewModel;

import java.util.Objects;

public class AddMedsActivity extends AppCompatActivity {

    private AddTimesAdapter addTimesAdapter;
    private AddMedsAdapter addMedsAdapter;

    long[] dates = new long[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meds);

        Toolbar toolbar = findViewById(R.id.toolbar_add_meds_activity);
        setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        addTimesAdapter = new AddTimesAdapter();
        addMedsAdapter = new AddMedsAdapter();
        setViewModel();
        setButtonsActions();

    }

    private void setViewModel() {
        TreatmentDataViewModel viewModel = new ViewModelProvider(this).get(TreatmentDataViewModel.class);
        viewModel.getSelectedTime().observe(this, item -> addTimesAdapter.addItem(item));
        viewModel.getSelectedStartDate().observe(this, item -> dates[0] = item);
        viewModel.getSelectedFinishDate().observe(this, item -> dates[1] = item);
        viewModel.getSelectedMed().observe(this, item -> addMedsAdapter.addItem(item));
    }

    private void setButtonsActions() {
        // Ok button
        Button okButton = findViewById(R.id.ok_add_med_button);
        okButton.setOnClickListener(this::onOkClick);

        // Cancel button
        Button cancelButton = findViewById(R.id.cancel_add_med_button);
        cancelButton.setOnClickListener(this::onCancelClick);
    }

    public void onCancelClick(View view) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mainIntent);
    }

    public void onOkClick(View view) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add date validation!
        mainIntent.putExtra(Literals.TREATMENT_PARCEL, new TreatmentParcel(
                dates[0],
                dates[1],
                packTimesToArray(addTimesAdapter.getDataList()),
                packMedsToArray(addMedsAdapter.getDataList())
        ));

        startActivity(mainIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public AddTimesAdapter getAddTimesAdapter() {
        return addTimesAdapter;
    }

    public AddMedsAdapter getAddMedsAdapter() {
        return addMedsAdapter;
    }
}