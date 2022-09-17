package com.example.retrofitroom.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.example.retrofitroom.NewDepartmentActivity;
import com.example.retrofitroom.connection.ConnectionConfig;
import com.example.retrofitroom.databinding.ItemListBinding;
import com.example.retrofitroom.models.Departments;
import com.example.retrofitroom.service.DepartmentService;
import com.example.retrofitroom.viewHolder.ItemViewHolder;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DepartmentsAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private IEvents iEvents;

    private List<Departments> localDataSet;

    private ItemListBinding itemListBinding;

    private Context context;

    DepartmentService departmentService = ConnectionConfig.newInstance().departmentService();

    public DepartmentsAdapter(List<Departments> dataSet, Context ctx) {

        localDataSet = dataSet;

        this.context = ctx;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        itemListBinding = ItemListBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);

        iEvents = new IEvents() {
            @Override
            public void deleteItem(int position) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(viewGroup.getContext());

                alertDialogBuilder.setTitle("Apagar departamento.");

                alertDialogBuilder.setMessage("Deseja realmente apagar o departamento");

                alertDialogBuilder.setPositiveButton("Sim",(dialog, which) -> {
                    Long id = localDataSet.get(position).getId();

                    departmentService.deleteDepartment(id).enqueue(new Callback<Void>() {
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

            public void updateItem(int position) {
                Toast.makeText(context.getApplicationContext(), "Editando", Toast.LENGTH_SHORT).show();

                String departmentId = localDataSet.get(position).getId().toString();

                Intent newDepartmentActivity = new Intent(context, NewDepartmentActivity.class);
                newDepartmentActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                newDepartmentActivity.putExtra("departmentId", departmentId);
                context.startActivity(newDepartmentActivity);
            }
        };

        return new ItemViewHolder(itemListBinding, iEvents);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder viewHolder, final int position) {
        itemListBinding.tvItem.setText(localDataSet.get(position).getName());
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
