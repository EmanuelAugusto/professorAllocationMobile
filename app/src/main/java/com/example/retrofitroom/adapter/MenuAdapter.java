package com.example.retrofitroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitroom.databinding.MenuListBinding;
import com.example.retrofitroom.models.MenuItens;
import com.example.retrofitroom.viewHolder.MenuViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder> {

    private ArrayList<MenuItens> localDataSet;

    private MenuListBinding itemListBinding;

    private Context context;

    public MenuAdapter(ArrayList<MenuItens> dataSet, Context ctx) {
        this.localDataSet = dataSet;
        this.context = ctx;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        itemListBinding = MenuListBinding.inflate(LayoutInflater.from(viewGroup.getContext()));

        return new MenuViewHolder(itemListBinding, localDataSet, context);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder viewHolder, final int position) {
        itemListBinding.tvName.setText(localDataSet.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
