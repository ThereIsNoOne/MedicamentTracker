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

public class AddMedsAdapter extends AddAdapter<String> {

    public AddMedsAdapter() {
        super();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(dataList.get(position).split(":")[1], atItemRemoved);
    }

}