package com.szylas.medicamenttracker.ui.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.szylas.medicamenttracker.R;
import com.szylas.medicamenttracker.ui.helpers.InputParser;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class AddTimesAdapter extends RecyclerView.Adapter<AddTimesAdapter.ViewHolder> {

    private final List<Integer> timeList;
    private final Consumer<Integer> atItemRemoved;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView timeView;
        private final MaterialButton removeButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            timeView = itemView.findViewById(R.id.time_row_text);
            removeButton = itemView.findViewById(R.id.remove_row_button);
        }

        public void bind(int time, Consumer<Integer> consumer) {
            timeView.setText(InputParser.parseTimeToString(
                    time / 60, time % 60
            ));
            removeButton.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position >= 0) {
                    consumer.accept(position);
                }
            });
        }
    }

    public AddTimesAdapter() {
        timeList = new LinkedList<>();
        atItemRemoved = position -> {
            timeList.remove(timeList.get(position));
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, timeList.size());
        };
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.time_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(timeList.get(position), atItemRemoved);
    }

    @Override
    public int getItemCount() {
        return timeList.size();
    }

    public List<Integer> getTimeList() {
        return timeList;
    }

    public void addTime(int time) {
        timeList.add(time);
        notifyItemInserted(timeList.size() - 1);
    }
}
