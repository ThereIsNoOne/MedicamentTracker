package com.szylas.medicamenttracker.ui.abstr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.szylas.medicamenttracker.R;
import com.szylas.medicamenttracker.computation.TreatmentsManager;

public abstract class MedListAbstractAdapter extends RecyclerView.Adapter<MedListAbstractAdapter.ViewHolder> {

    protected final TreatmentsManager manager;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameView;
        private final TextView timeView;

        public ViewHolder(View view) {
            super(view);

            nameView = view.findViewById(R.id.medName);
            timeView = view.findViewById(R.id.medTime);
        }

        public void bind(String name, String time) {
            nameView.setText(name);
            timeView.setText(time);
        }
    }

    public MedListAbstractAdapter(TreatmentsManager manager) {
        this.manager = manager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.med_item, parent, false);

        return new ViewHolder(view);
    }

}

