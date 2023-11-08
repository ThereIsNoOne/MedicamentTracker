package com.szylas.medicamenttracker.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.szylas.medicamenttracker.R;
import com.szylas.medicamenttracker.models.Treatment;

public class MedListAdapter extends RecyclerView.Adapter<MedListAdapter.ViewHolder> {

    private Treatment medicamentList;

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

    public MedListAdapter(Treatment medsList) {
        medicamentList = medsList;
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
        holder.getNameView().setText(medicamentList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return medicamentList.size();
    }



}
