package com.szylas.medicamenttracker.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.szylas.medicamenttracker.R;

import org.w3c.dom.Text;

public class ManageMedsAdapter extends RecyclerView.Adapter<ManageMedsAdapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView medNamesView;
        private final TextView datesView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            medNamesView = itemView.findViewById(R.id.medName);
            datesView = itemView.findViewById(R.id.medTime);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.med_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
