package com.example.retrofitroom.viewHolder;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitroom.databinding.AllocationsListBinding;

public class AllocationViewHolder extends RecyclerView.ViewHolder {

    private final AllocationsListBinding allocationsListBinding;

    public AllocationViewHolder(@NonNull AllocationsListBinding view){
        super(view.getRoot());

        allocationsListBinding = view;
    }

    public TextView getTextView(){

        return allocationsListBinding.tvItem;
    }

    public ConstraintLayout getConstraintLayout(){

        return allocationsListBinding.cLayout;
    }
}
