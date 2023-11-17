package com.szylas.medicamenttracker.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.szylas.medicamenttracker.R;
import com.szylas.medicamenttracker.ui.helpers.Literals;
import com.szylas.medicamenttracker.ui.helpers.TreatmentParcel;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

public class AddMedsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meds);

        Toolbar toolbar = findViewById(R.id.toolbar_add_meds_activity);
        setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setStartDatePickers();
        setButtonsActions();

    }

    private void setStartDatePickers() {
        MaterialDatePicker.Builder datePickerBuilder = MaterialDatePicker.Builder.datePicker();

        // Start date picker show dialog
        final MaterialDatePicker startDatePicker = datePickerBuilder.build();

        TextInputEditText startDateField = findViewById(R.id.start_date_field);
        Button startDatePickerButton = findViewById(R.id.start_date_picker);

        startDatePickerButton.setOnClickListener(view -> startDatePicker.show(
                getSupportFragmentManager(), Literals.START_PICKER));
        startDatePicker.addOnPositiveButtonClickListener(selection -> startDateField.setText(selection.toString()));
    }

    private void setButtonsActions() {
        // Ok button
        Button okButton = findViewById(R.id.ok_add_med_button);
        okButton.setOnClickListener(view -> {
            Intent mainIntent = new Intent(this, MainActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            mainIntent.putExtra(Literals.TREATMENT_PARCEL, new TreatmentParcel(
                    LocalDateTime.of(2022, 11, 1, 0, 0).atZone(ZoneId.systemDefault()).toEpochSecond(),
                    LocalDateTime.of(2024, 11, 1, 0 ,0).atZone(ZoneId.systemDefault()).toEpochSecond(),
                    new int[] {4*60+30},
                    new String[] {"PILL:IBum:30:1", "SYRUP:LevoPront:15:300:3", "INJECTION:Insulin:70:1"}
            ));

            startActivity(mainIntent);
        });

        // Cancel button
        Button cancelButton = findViewById(R.id.cancel_add_med_button);
        cancelButton.setOnClickListener(view -> {
            Intent mainIntent = new Intent(this, MainActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}