package com.szylas.medicamenttracker.ui.adapters;

import androidx.annotation.NonNull;

import com.szylas.medicamenttracker.ui.abstr.AddAdapter;

public class AddMedsAdapter extends AddAdapter<String> {

    public AddMedsAdapter() {
        super();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(dataList.get(position).split(":")[1], atItemRemoved);
    }

}