package com.example.retrofitroom.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitroom.NewCourseActivity;
import com.example.retrofitroom.NewDepartmentActivity;
import com.example.retrofitroom.connection.ConnectionConfig;
import com.example.retrofitroom.databinding.ItemListBinding;
import com.example.retrofitroom.models.Course;
import com.example.retrofitroom.models.Departments;
import com.example.retrofitroom.service.CourseService;
import com.example.retrofitroom.service.DepartmentService;
import com.example.retrofitroom.viewHolder.CourseViewHolder;
import com.example.retrofitroom.viewHolder.ItemViewHolder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseAdapter extends RecyclerView.Adapter<CourseViewHolder> {

    private CourseAdapter.IEvents iEvents;

    private List<Course> localDataSet;

    private ItemListBinding itemListBinding;

    private Context context;

    CourseService courseService = ConnectionConfig.newInstance().courseService();

    public CourseAdapter(List<Course> dataSet, Context ctx) {

        localDataSet = dataSet;

        this.context = ctx;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        itemListBinding = ItemListBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);

        iEvents = new CourseAdapter.IEvents() {
            @Override
            public void deleteItem(int position) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(viewGroup.getContext());

                alertDialogBuilder.setTitle("Apagar curso.");

                alertDialogBuilder.setMessage("Deseja realmente apagar o curso?");

                alertDialogBuilder.setPositiveButton("Sim",(dialog, which) -> {
                    Long id = localDataSet.get(position).getId();

                    courseService.deleteCourse(id).enqueue(new Callback<Void>() {
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

                String courseId = localDataSet.get(position).getId().toString();

                Intent newCourseActivity = new Intent(context, NewCourseActivity.class);
                newCourseActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                newCourseActivity.putExtra("courseId", courseId);
                context.startActivity(newCourseActivity);
            }
        };

        return new CourseViewHolder(itemListBinding, iEvents);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder viewHolder, final int position) {
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
