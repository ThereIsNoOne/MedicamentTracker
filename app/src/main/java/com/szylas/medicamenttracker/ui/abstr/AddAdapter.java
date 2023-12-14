package com.szylas.medicamenttracker.ui.abstr;

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

public abstract class AddAdapter<T> extends RecyclerView.Adapter<AddAdapter.ViewHolder>{

    protected final List<T> dataList = new LinkedList<>();
    protected Consumer<Integer> atItemRemoved;

    public AddAdapter() {
        atItemRemoved = position -> {
            dataList.remove((int) position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, dataList.size());
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;
        private final MaterialButton removeButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_textview);
            removeButton = itemView.findViewById(R.id.remove_row_button);
        }

        public void bind(String text, Consumer<Integer> consumer) {
            textView.setText(text);
            removeButton.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position >= 0) {
                    consumer.accept(position);
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.text_button_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(dataList.get(position).toString(), atItemRemoved);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void addItem(T item) {
        dataList.add(item);
        notifyItemInserted(dataList.size()-1);
    }
}

