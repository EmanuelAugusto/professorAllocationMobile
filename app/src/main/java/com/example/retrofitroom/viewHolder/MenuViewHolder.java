package com.example.retrofitroom.viewHolder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitroom.AllocationActivity;
import com.example.retrofitroom.CourseActivity;
import com.example.retrofitroom.MainActivity;
import com.example.retrofitroom.ProfessorActivity;
import com.example.retrofitroom.databinding.MenuListBinding;
import com.example.retrofitroom.models.MenuItens;

import java.util.ArrayList;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private final MenuListBinding binding;

    private ArrayList<MenuItens> localDataSet;
    private Context context;

    public MenuViewHolder (@NonNull MenuListBinding view, ArrayList<MenuItens> dataSet, Context ctx) {
        super(view.getRoot());

        binding = view;

        this.localDataSet = dataSet;
        this.context = ctx;
        view.getRoot().setOnClickListener(this);
    }

    public TextView getTextView(){

        return binding.tvName;
    }

    public ConstraintLayout getConstraintLayout(){

        return binding.cLayout;
    }

    @Override
    public void onClick(View v){

        String option = localDataSet.get(getAdapterPosition()).getOption();

        if(option.equals("department")){
            Intent mainActivity = new Intent(context, MainActivity.class);
            context.startActivity(mainActivity);
        }
        if(option.equals("courses")){
            Intent courseActivity = new Intent(context, CourseActivity.class);
            context.startActivity(courseActivity);
        }

        if(option.equals("professors")){
            Intent professorActivity = new Intent(context, ProfessorActivity.class);
            context.startActivity(professorActivity);
        }

        if(option.equals("allocations")){
            Intent allocationActivity = new Intent(context, AllocationActivity.class);
            context.startActivity(allocationActivity);
        }



    }

}
