package com.szylas.medicamenttracker.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.szylas.medicamenttracker.R;
import com.szylas.medicamenttracker.computation.TreatmentsManager;
import com.szylas.medicamenttracker.ui.abstr.TimeAdapterable;
import com.szylas.medicamenttracker.ui.adapters.AddMedsAdapter;
import com.szylas.medicamenttracker.ui.adapters.AddTimesAdapter;
import com.szylas.medicamenttracker.ui.adapters.ManageTreatmentAdapter;
import com.szylas.medicamenttracker.ui.viewmodels.MangeMedsViewModel;
import com.szylas.medicamenttracker.ui.viewmodels.TreatmentDataViewModel;

import java.util.Objects;

public class ManageMedsActivity extends AppCompatActivity implements TimeAdapterable {

    private TreatmentsManager manager;
    private AddTimesAdapter adapter;

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
        adapter = new AddTimesAdapter();
    }

    private void setUpViewModel() {
        MangeMedsViewModel viewModel = new ViewModelProvider(this).get(MangeMedsViewModel.class);
        viewModel.getCurrentPosition().observe(this, item -> Log.d("MANAGE_MEDS_CHOSEN", item.toString()));
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
    public AddTimesAdapter getAddTimesAdapter() {
        return adapter;
    }
}