package com.szylas.medicamenttracker.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.szylas.medicamenttracker.R;
import com.szylas.medicamenttracker.computation.TreatmentsManager;
import com.szylas.medicamenttracker.databinding.ActivityMainBinding;
import com.szylas.medicamenttracker.ui.helpers.Literals;
import com.szylas.medicamenttracker.ui.adapters.MedListAdapter;
import com.szylas.medicamenttracker.ui.helpers.TreatmentParcel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private MedListAdapter medListAdapter;
    private TreatmentsManager treatmentsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);



        setupMedListAdapter();
        setupMedRecyclerView();
    }

    private void setupMedListAdapter() {
        Thread loadData = new Thread(this::run);

    }

    private void setupMedRecyclerView() {
        RecyclerView medRecyclerView = findViewById(R.id.med_recycler_view);

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.add_meds) {
            startMedAddActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startMedAddActivity() {
        Intent intent = new Intent(MainActivity.this, AddMedsActivity.class);
        startActivity(intent);
    }

    private void run() {
        treatmentsManager = new TreatmentsManager(getAssets());
        TreatmentParcel parcel;
        if ((parcel = getIntent().getParcelableExtra(Literals.TREATMENT_PARCEL, TreatmentParcel.class)) != null) {
            treatmentsManager.addNewTreatment(parcel.getTreatment());
        }
        medListAdapter = new MedListAdapter(treatmentsManager);
    }
}