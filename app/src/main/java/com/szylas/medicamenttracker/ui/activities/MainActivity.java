package com.szylas.medicamenttracker.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.szylas.medicamenttracker.R;
import com.szylas.medicamenttracker.computation.TreatmentsManager;
import com.szylas.medicamenttracker.databinding.ActivityMainBinding;
import com.szylas.medicamenttracker.ui.adapters.MedListAdapter;
import com.szylas.medicamenttracker.ui.helpers.Literals;
import com.szylas.medicamenttracker.ui.helpers.TreatmentParcel;

public class MainActivity extends AppCompatActivity {

    private MedListAdapter medListAdapter;
    private RecyclerView medRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.szylas.medicamenttracker.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);



        medRecyclerView = findViewById(R.id.med_recycler_view);
        setupMedListAdapter();
    }

    private void setupMedListAdapter() {
        medRecyclerView.setVisibility(View.GONE);
        Thread loadData = new Thread(this::run);
        loadData.start();
    }

    private void setupMedRecyclerView() {

        medRecyclerView.setAdapter(medListAdapter);
        medRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.add_meds) {
            startMedAddActivity();
            return true;
        } else if (id == R.id.manage_meds) {
            startManageMedsActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startManageMedsActivity() {
        Intent intent = new Intent(this, ManageMedsActivity.class);
        startActivity(intent);
    }

    private void startMedAddActivity() {
        Intent intent = new Intent(this, AddMedsActivity.class);
        startActivity(intent);
    }

    private void run() {
        TreatmentsManager treatmentsManager = new TreatmentsManager(this);
        TreatmentParcel parcel;

        Log.d("Treatment manager", "Initiallizing treatment manager");
        if ((parcel = getIntent().getParcelableExtra(Literals.TREATMENT_PARCEL, TreatmentParcel.class)) != null) {
            treatmentsManager.addNewTreatment(parcel.getTreatment());
        }

        medListAdapter = new MedListAdapter(treatmentsManager);
        runOnUiThread(() -> {
            medRecyclerView.setVisibility(View.VISIBLE);
            findViewById(R.id.loadingPanel).setVisibility(View.GONE);
            setupMedRecyclerView();
        });
        treatmentsManager.saveTreatments();
    }
}