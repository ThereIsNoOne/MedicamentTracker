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
import com.szylas.medicamenttracker.models.Treatment;
import com.szylas.medicamenttracker.models.meds.Medicament;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class MedListAdapter extends RecyclerView.Adapter<MedListAdapter.ViewHolder> {

    private TreatmentsManager treatmentsManager;
    private ArrayList<MedTimePair<LocalTime, Medicament>> todayMeds;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameView;

        public ViewHolder(View view) {
            super(view);

            nameView = (TextView) view.findViewById(R.id.medName);
        }

        public TextView getNameView() {
            return nameView;
        }
    }

    public MedListAdapter(TreatmentsManager treatmentsManager) {
        this.treatmentsManager = treatmentsManager;
        setUpTodayMeds(LocalDate.now());
    }

    public MedListAdapter(TreatmentsManager treatmentsManager, LocalDate today) {
        this.treatmentsManager = treatmentsManager;
        setUpTodayMeds(today);
    }

    private void setUpTodayMeds(LocalDate date) {
        todayMeds = treatmentsManager.treatmentsForDay(date);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.med_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
        holder.getNameView().setText(todayMeds.get(position).getValue().getName());
    }

    @Override
    public int getItemCount() {
        return todayMeds.size();
    }



}
