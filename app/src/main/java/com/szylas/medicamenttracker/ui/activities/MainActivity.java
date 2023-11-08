package com.szylas.medicamenttracker.ui.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.szylas.medicamenttracker.R;
import com.szylas.medicamenttracker.databinding.ActivityMainBinding;
import com.szylas.medicamenttracker.datastore.TreatmentsReader;
import com.szylas.medicamenttracker.models.meds.Pill;
import com.szylas.medicamenttracker.models.Treatment;
import com.szylas.medicamenttracker.ui.adapters.MedListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;

    private ArrayList<Treatment> treatmentList;
    private int currentTreatment = 0;
    private MedListAdapter medListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        treatmentList = TreatmentsReader.loadTreatments(getAssets());
        medListAdapter = new MedListAdapter(treatmentList.get(currentTreatment));

        setupMedRecyclerView();
    }

    private void setupMedRecyclerView() {
        RecyclerView medRecyclerView = findViewById(R.id.med_recycler_view);

        medRecyclerView.setAdapter(medListAdapter);
        medRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        System.out.println("Menu");
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
            treatmentList.get(currentTreatment).addMedicament(new Pill("Test pill", 1, 1));
            medListAdapter.notifyItemInserted(treatmentList.get(currentTreatment).size() - 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}