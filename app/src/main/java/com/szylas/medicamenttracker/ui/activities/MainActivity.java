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
import com.szylas.medicamenttracker.ui.helpers.MedListAdapter;
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

        if (treatmentsManager == null) {
            Log.d("MainActivityCreation", "Setting new treatment manager");
            treatmentsManager = new TreatmentsManager(getAssets());
            unpackIntent();
        }
        medListAdapter = new MedListAdapter(treatmentsManager);

        setupMedRecyclerView();
    }

    private void unpackIntent() {
        TreatmentParcel parcel = getIntent().getParcelableExtra(Literals.TREATMENT_PARCEL, TreatmentParcel.class);
        if (parcel == null) {
            return;
        }
        Log.d("PARCEL_INFO", String.valueOf(parcel.getTreatment().getMedTimePairs().size()));
        treatmentsManager.addNewTreatment(parcel.getTreatment());
    }

    private void setupMedRecyclerView() {
        RecyclerView medRecyclerView = findViewById(R.id.med_recycler_view);

        medRecyclerView.setAdapter(medListAdapter);
        medRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
}