package com.szylas.medicamenttracker.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.szylas.medicamenttracker.R;
import com.szylas.medicamenttracker.computation.TreatmentsManager;
import com.szylas.medicamenttracker.ui.abstr.MedListAbstractAdapter;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class ManageTreatmentAdapter extends MedListAbstractAdapter {

    private final Consumer<Integer> consumer;

    public ManageTreatmentAdapter(TreatmentsManager manager, Consumer<Integer> consumer) {
        super(manager);
        this.consumer = consumer;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.med_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String[] message = manager.get(position).representation();
        holder.bind(message[0], message[1]);
        holder.itemView.setOnClickListener(view -> consumer.accept(position));
    }

    @Override
    public int getItemCount() {
        return manager.size();
    }
}
