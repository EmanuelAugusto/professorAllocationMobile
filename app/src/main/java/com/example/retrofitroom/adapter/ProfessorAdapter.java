package com.example.retrofitroom.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitroom.NewCourseActivity;
import com.example.retrofitroom.NewProfessorActivity;
import com.example.retrofitroom.connection.ConnectionConfig;
import com.example.retrofitroom.databinding.ItemListBinding;
import com.example.retrofitroom.databinding.ProfessorListBinding;
import com.example.retrofitroom.models.Course;
import com.example.retrofitroom.models.Teacher;
import com.example.retrofitroom.service.CourseService;
import com.example.retrofitroom.service.ProfessorService;
import com.example.retrofitroom.viewHolder.CourseViewHolder;
import com.example.retrofitroom.viewHolder.ProfessorViewHolder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfessorAdapter extends RecyclerView.Adapter<ProfessorViewHolder> {

    private ProfessorAdapter.IEvents iEvents;

    private List<Teacher> localDataSet;

    private ProfessorListBinding itemListBinding;

    private Context context;

    ProfessorService professorService = ConnectionConfig.newInstance().professorService();

    public ProfessorAdapter(List<Teacher> dataSet, Context ctx) {

        localDataSet = dataSet;

        this.context = ctx;
    }

    @Override
    public ProfessorViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        itemListBinding = ProfessorListBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);

        iEvents = new ProfessorAdapter.IEvents() {
            @Override
            public void deleteItem(int position) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(viewGroup.getContext());

                alertDialogBuilder.setTitle("Apagar professor.");

                alertDialogBuilder.setMessage("Deseja realmente apagar o professor");

                alertDialogBuilder.setPositiveButton("Sim",(dialog, which) -> {
                    Long id = localDataSet.get(position).getId();

                    professorService.deleteProfessor(id).enqueue(new Callback<Void>() {
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

                String professorId = localDataSet.get(position).getId().toString();

                Intent newProfessorActivity = new Intent(context, NewProfessorActivity.class);
                newProfessorActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                newProfessorActivity.putExtra("professorId", professorId);
                context.startActivity(newProfessorActivity);
            }
        };

        return new ProfessorViewHolder(itemListBinding, iEvents);
    }

    @Override
    public void onBindViewHolder(ProfessorViewHolder viewHolder, final int position) {
        itemListBinding.tvItem.setText(localDataSet.get(position).getName());
        itemListBinding.tvCpf.setText(localDataSet.get(position).getCpf());
        itemListBinding.tvDepartment.setText(localDataSet.get(position).getdepartment().getName());
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
