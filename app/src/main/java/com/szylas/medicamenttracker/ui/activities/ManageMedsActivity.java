package com.szylas.medicamenttracker.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.szylas.medicamenttracker.R;
import com.szylas.medicamenttracker.computation.TreatmentsManager;
import com.szylas.medicamenttracker.ui.abstr.TimeAdapterable;
import com.szylas.medicamenttracker.ui.adapters.TimesAdapter;
import com.szylas.medicamenttracker.ui.viewmodels.ManageMedsViewModel;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ManageMedsActivity extends AppCompatActivity implements TimeAdapterable {

    private TreatmentsManager manager;
    private TimesAdapter adapter;

    private int currentModification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_meds);

        Toolbar toolbar = findViewById(R.id.manage_meds_toolbar);
        setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setUpViewModel();


        manager = new TreatmentsManager(this);
        adapter = new TimesAdapter();
    }

    public void okButton(View view) {
        new Thread(() -> manager.saveTreatments()).start();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void setUpViewModel() {
        ManageMedsViewModel viewModel = new ViewModelProvider(this).get(ManageMedsViewModel.class);
        viewModel.getCurrentPosition().observe(this, item -> currentModification = item);
        viewModel.getSelectedTime().observe(this, item -> adapter.addItem(item));
        viewModel.getNewTreatment().observe(this, item -> {
            manager.replaceTreatment(currentModification, item);
            viewModel.setNotify(true);
        });
    }

    public TreatmentsManager getManager() {
        return manager;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public TimesAdapter getAddTimesAdapter() {
        return adapter;
    }

    public List<LocalTime> getApplicationTimes() {
        return adapter.getDataList()
                .stream()
                .map(item -> LocalTime.of(item / 60, item % 60))
                .collect(Collectors.toList());
    }
}