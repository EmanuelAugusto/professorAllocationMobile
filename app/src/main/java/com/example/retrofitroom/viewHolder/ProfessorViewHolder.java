package com.example.retrofitroom.viewHolder;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitroom.adapter.DepartmentsAdapter;
import com.example.retrofitroom.adapter.ProfessorAdapter;
import com.example.retrofitroom.databinding.ProfessorListBinding;

public class ProfessorViewHolder extends RecyclerView.ViewHolder {
    private final ProfessorListBinding binding;

    public ProfessorViewHolder (@NonNull ProfessorListBinding view, ProfessorAdapter.IEvents events) {
        super(view.getRoot());

        binding = view;

        view.buttonDelete.setOnClickListener(View-> {
            events.deleteItem(getAdapterPosition());
        });

        view.buttonEdit.setOnClickListener(View-> {
            events.updateItem(getAdapterPosition());
        });

    }

    public TextView getTextView(){

        return binding.tvItem;
    }

    public ConstraintLayout getConstraintLayout(){

        return binding.cLayout;
    }
}
