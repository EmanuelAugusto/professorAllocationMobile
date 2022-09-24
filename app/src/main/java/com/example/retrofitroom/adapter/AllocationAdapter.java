package com.example.retrofitroom.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitroom.connection.ConnectionConfig;
import com.example.retrofitroom.databinding.AllocationsListBinding;
import com.example.retrofitroom.models.Allocation;
import com.example.retrofitroom.service.AllocationService;
import com.example.retrofitroom.shared.helpers;
import com.example.retrofitroom.viewHolder.AllocationViewHolder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllocationAdapter extends RecyclerView.Adapter<AllocationViewHolder> {

    List<Allocation> localDataSet;

    AllocationAdapter.IEvents iEvents;

    private AllocationsListBinding allocationsListBinding;

    Context context;

    AllocationService allocationService = ConnectionConfig.newInstance().allocationService();

    public AllocationAdapter(List<Allocation> dataSet, Context ctx){
        localDataSet = dataSet;

        this.context = ctx;
    }

    public AllocationViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){

        allocationsListBinding = AllocationsListBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);

        iEvents = new AllocationAdapter.IEvents(){
            @Override
            public void deleteItem(int position) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(viewGroup.getContext());

                alertDialogBuilder.setTitle("Apagar alocação.");

                alertDialogBuilder.setMessage("Deseja realmente apagar a alocação?");

                alertDialogBuilder.setPositiveButton("Sim",(dialog, which) -> {
                    Long id = localDataSet.get(position).getId();

                    allocationService.deleteAllocations(id).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(context.getApplicationContext(), "Deletado", Toast.LENGTH_SHORT).show();
                            localDataSet.remove(position);
                            notifyItemRemoved(position);
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(context.getApplicationContext(), "Erro ao deletar", Toast.LENGTH_SHORT).show();
                        }
                    });
                });

                alertDialogBuilder.setNegativeButton("Não",(dialog, which) -> {

                });

                AlertDialog  alertDialog = alertDialogBuilder.create();

                alertDialog.show();

            }

            @Override
            public void updateItem(int position) {

            }
        };

        return new AllocationViewHolder(allocationsListBinding, iEvents);
    }


    @Override
    public void onBindViewHolder(@NonNull AllocationViewHolder holder, int position) {
        String dayOfWeek = helpers.getDayOfWeek(localDataSet.get(position).getDay());

        allocationsListBinding.tvDepartment2.setText(dayOfWeek);

        String hour = localDataSet.get(position).getStart() + " ás " + localDataSet.get(position).getEnd();
        allocationsListBinding.tvItem.setText(hour);
        allocationsListBinding.tvCpf.setText(localDataSet.get(position).getTeacher().getName());
        allocationsListBinding.tvCourse.setText(localDataSet.get(position).getCourse().getName());
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
