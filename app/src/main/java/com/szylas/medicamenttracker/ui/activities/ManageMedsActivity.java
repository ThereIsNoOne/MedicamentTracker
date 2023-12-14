package com.szylas.medicamenttracker.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.szylas.medicamenttracker.R;
import com.szylas.medicamenttracker.computation.TreatmentsManager;

import java.util.Objects;

public class ManageMedsActivity extends AppCompatActivity {

    private TreatmentsManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_meds);

        Toolbar toolbar = findViewById(R.id.manage_meds_toolbar);
        setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        manager = new TreatmentsManager(this);
    }

    public TreatmentsManager getManager() {
        return manager;
    }
}