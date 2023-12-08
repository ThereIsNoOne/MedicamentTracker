package com.szylas.medicamenttracker.ui.adapters;


import static com.szylas.medicamenttracker.ui.helpers.InputParser.parseTimeToString;

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

public class AddTimesAdapter extends AddAdapter<Integer> {
//    InputParser.parseTimeToString(
//    time / 60, time % 60
//            )

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
