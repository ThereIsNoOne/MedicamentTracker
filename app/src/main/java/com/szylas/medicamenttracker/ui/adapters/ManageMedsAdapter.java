package com.szylas.medicamenttracker.ui.adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.szylas.medicamenttracker.R;
import com.szylas.medicamenttracker.models.MedType;
import com.szylas.medicamenttracker.models.meds.Injection;
import com.szylas.medicamenttracker.models.meds.Medicament;
import com.szylas.medicamenttracker.models.meds.Pill;
import com.szylas.medicamenttracker.models.meds.Syrup;
import com.szylas.medicamenttracker.ui.helpers.InputType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ManageMedsAdapter extends RecyclerView.Adapter<ManageMedsAdapter.ViewHolder> {

    private final List<Medicament> medicamentList;
    private static final Map<Integer, List<Integer>> values = new TreeMap<>();

    private static class ValuesWatcher implements TextWatcher {

        private final int position;
        private final InputType inputType;

        public ValuesWatcher(int position, InputType inputType) {
            this.position = position;
            this.inputType = inputType;
        }

        @Override
        public void beforeTextChanged(CharSequence text, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence text, int start, int before, int count) {
            if (text.length() == 0) {
                return;
            }
//            Log.d("AlterValues", String.format("Values length: %d, Values keys: %s", values.size(), values.keySet()));
//            Log.d("AlterValues", String.format("position: %d\nvalue: %s", position, text));
//            Log.d("AlterValues", String.format("Values@position: %s@%d", values.get(position), position));

            if (values.get(position) == null) {
                return;
            }

            switch (inputType) {
                case QUANTITY:
                    values.get(position).add(0, Integer.valueOf(text.toString()));
                    break;
                case DOSE:
                    values.get(position).add(1, Integer.valueOf(text.toString()));
                    break;
                case VOLUME:
                    values.get(position).add(2, Integer.valueOf(text.toString()));
                    break;
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView medNameView;
        private final TextView medTypeView;

        private final Map<InputType, TextInputEditText> inputs = new HashMap<>();

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            medNameView = itemView.findViewById(R.id.med_name_text_view);
            medTypeView = itemView.findViewById(R.id.med_type_text_view);

            inputs.put(InputType.DOSE, itemView.findViewById(R.id.dose_manage_input));
            inputs.put(InputType.QUANTITY, itemView.findViewById(R.id.quantity_manage_input));
            inputs.put(InputType.VOLUME, itemView.findViewById(R.id.volume_manage_input));

        }

        public void bind(String name, String type, MedType medType, int[] values) {
            for (InputType key : inputs.keySet()) {
                inputs.get(key).addTextChangedListener(new ValuesWatcher(getAdapterPosition(), key));
            }

            medNameView.setText(name);
            medTypeView.setText(type);

            inputs.get(InputType.QUANTITY).setText(String.valueOf(values[0]));
            inputs.get(InputType.DOSE).setText(String.valueOf(values[1]));

            if (medType == MedType.SYRUP) {
                inputs.get(InputType.VOLUME).setText(String.valueOf(values[2]));
            } else {
                inputs.get(InputType.VOLUME).setEnabled(false);
            }
        }
    }

    public ManageMedsAdapter(List<Medicament> medicamentList) {
        this.medicamentList = medicamentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.manage_medications_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Medicament medicament = medicamentList.get(position);

        String name = medicament.getName();
        MedType medType;
        List<Integer> values = new ArrayList<>();
        values.add(medicament.getQuantity());
        values.add(medicament.getDose());

        if (medicament instanceof Syrup) {
            medType = MedType.SYRUP;
            values.add(((Syrup) medicament).getVolume());
        } else if (medicament instanceof Pill) {
            medType = MedType.PILL;
        } else if (medicament instanceof Injection) {
            medType = MedType.INJECTION;
        } else {
            throw new RuntimeException("Unknown medicament");
        }

        // Remember values for each position
        ManageMedsAdapter.values.put(position, values);

        holder.bind(name, medType.name(), medType, values.stream().mapToInt(i -> i).toArray());
    }

    @Override
    public int getItemCount() {
        return medicamentList.size();
    }

    public List<Medicament> treatment() {
        return medicamentList;
    }

    public List<Medicament> getNewMedsValues() {
        for (int i = 0; i < medicamentList.size(); i++) {
            medicamentList.get(i).setValues(values.get(i));
        }
        return medicamentList;
    }
}
