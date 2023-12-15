package com.szylas.medicamenttracker.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.szylas.medicamenttracker.R;
import com.szylas.medicamenttracker.computation.TreatmentsManager;
import com.szylas.medicamenttracker.models.MedTimePair;
import com.szylas.medicamenttracker.models.meds.Medicament;
import com.szylas.medicamenttracker.ui.abstr.MedListAbstractAdapter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class MedListAdapter extends MedListAbstractAdapter {

    private ArrayList<MedTimePair<LocalTime, Medicament>> todayMeds;

    public MedListAdapter(TreatmentsManager treatmentsManager) {
        super(treatmentsManager);
        setUpTodayMeds(LocalDate.now());
    }

    public MedListAdapter(TreatmentsManager treatmentsManager, LocalDate today) {
        super(treatmentsManager);
        setUpTodayMeds(today);
    }

    private void setUpTodayMeds(LocalDate date) {
        todayMeds = manager.treatmentsForDay(date);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.bind(todayMeds.get(position).getValue().getName(),
                todayMeds.get(position).getKey().toString());
    }

    @Override
    public int getItemCount() {
        return todayMeds.size();
    }
}
