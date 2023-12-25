package com.szylas.medicamenttracker.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.szylas.medicamenttracker.R;
import com.szylas.medicamenttracker.models.MedType;
import com.szylas.medicamenttracker.models.Treatment;
import com.szylas.medicamenttracker.models.meds.Injection;
import com.szylas.medicamenttracker.models.meds.Medicament;
import com.szylas.medicamenttracker.models.meds.Pill;
import com.szylas.medicamenttracker.models.meds.Syrup;
import com.szylas.medicamenttracker.ui.helpers.InputType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageMedsAdapter extends RecyclerView.Adapter<ManageMedsAdapter.ViewHolder> {

    private final Treatment treatment;
    private final ArrayList<Medicament> medications;

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

    public ManageMedsAdapter(Treatment treatment) {
        this.treatment = treatment;
        this.medications = treatment.getMedications();
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
        Medicament medicament = medications.get(position);

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

        holder.bind(name, medType.name(), medType, values.stream().mapToInt(i -> i).toArray());
    }

    @Override
    public int getItemCount() {
        return medications.size();
    }
}
