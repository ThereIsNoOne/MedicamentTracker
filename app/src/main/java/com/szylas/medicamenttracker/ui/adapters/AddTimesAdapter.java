package com.szylas.medicamenttracker.ui.adapters;


import static com.szylas.medicamenttracker.ui.helpers.InputParser.parseTimeToString;

import androidx.annotation.NonNull;

public class AddTimesAdapter extends AddAdapter<Integer> {

    public AddTimesAdapter() {
        super();
        atItemRemoved = position -> {
            dataList.remove(dataList.get(position));
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, dataList.size());
        };
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int time = dataList.get(position);
        holder.bind(parseTimeToString(time/60, time%60), atItemRemoved);
    }
}
