package com.example.retrofitroom.viewHolder;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.retrofitroom.adapter.CourseAdapter;
import com.example.retrofitroom.databinding.ItemListBinding;

public class CourseViewHolder extends RecyclerView.ViewHolder {

    private final ItemListBinding binding;

    public CourseViewHolder (@NonNull ItemListBinding view, CourseAdapter.IEvents events) {
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
