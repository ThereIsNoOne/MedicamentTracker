package com.szylas.medicamenttracker.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.szylas.medicamenttracker.R;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class AddMedsAdapter extends RecyclerView.Adapter<AddMedsAdapter.ViewHolder>{

    private final List<String> medsList;
    private final Consumer<Integer> atItemRemoved;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;
        private final MaterialButton removeButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.add_med_row_text);
            removeButton = itemView.findViewById(R.id.remove_med_row_button);
        }

        public void bind(String med, Consumer<Integer> consumer) {
            textView.setText(med);
            removeButton.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position >= 0) {
                    consumer.accept(position);
                }
            });
        }
    }

    public AddMedsAdapter() {
        medsList = new LinkedList<>();
        atItemRemoved = position -> {
            medsList.remove((int) position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, medsList.size());
        };
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.add_med_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(medsList.get(position).split(":")[1], atItemRemoved);
    }

    @Override
    public int getItemCount() {
        return medsList.size();
    }

    public List<String> getMedsList() {
        return medsList;
    }

    public void addMed(String med) {
        medsList.add(med);
        notifyItemInserted(medsList.size()-1);
    }
}
