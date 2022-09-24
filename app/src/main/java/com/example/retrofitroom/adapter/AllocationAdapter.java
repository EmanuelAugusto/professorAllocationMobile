package com.example.retrofitroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.retrofitroom.databinding.AllocationsListBinding;
import com.example.retrofitroom.models.Allocation;
import com.example.retrofitroom.viewHolder.AllocationViewHolder;

import java.util.List;

public class AllocationAdapter extends RecyclerView.Adapter<AllocationViewHolder> {

    List<Allocation> localDataSet;

    private AllocationsListBinding allocationsListBinding;

    Context context;

    public AllocationAdapter(List<Allocation> dataSet, Context ctx){
        localDataSet = dataSet;

        this.context = ctx;
    }

    public AllocationViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){

        allocationsListBinding = AllocationsListBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);

        return new AllocationViewHolder(allocationsListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AllocationViewHolder holder, int position) {
        allocationsListBinding.tvDepartment2.setText(localDataSet.get(position).getDay().toString());
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public interface IEvents {
        void deleteItem(int position);

        void updateItem(int position);
    }
}
