package com.example.retrofitroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.retrofitroom.adapter.CourseAdapter;
import com.example.retrofitroom.adapter.ProfessorAdapter;
import com.example.retrofitroom.connection.ConnectionConfig;
import com.example.retrofitroom.databinding.ActivityCourseBinding;
import com.example.retrofitroom.databinding.ActivityProfessorBinding;
import com.example.retrofitroom.models.Course;
import com.example.retrofitroom.models.Teacher;
import com.example.retrofitroom.service.CourseService;
import com.example.retrofitroom.service.ProfessorService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfessorActivity extends AppCompatActivity {

    ActivityProfessorBinding activityProfessorBinding;

    private ProfessorAdapter professorAdapter;

    ProfessorService professorService = ConnectionConfig.newInstance().professorService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProfessorBinding = ActivityProfessorBinding.inflate(getLayoutInflater());
        setContentView(activityProfessorBinding.getRoot());

        activityProfessorBinding.floatingActionButton.setOnClickListener(view->{
            Intent newProfessorActivity = new Intent(getApplicationContext(), NewProfessorActivity.class);
            newProfessorActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(newProfessorActivity);
            finish();
        });

        this.getAllProfessors();
    }

    protected  void getAllProfessors(){
        professorService.getAllProfessors().enqueue(new Callback<List<Teacher>>() {
            @Override
            public void onResponse(Call<List<Teacher>> call, Response<List<Teacher>> response) {
                List<Teacher> reponseList = response.body();

                activityProfessorBinding.listadapter.setVisibility(View.VISIBLE);
                activityProfessorBinding.progressBarProfessors.setVisibility(View.GONE);

                professorAdapter = new ProfessorAdapter(reponseList, getApplicationContext());

                RecyclerView recyclerView = activityProfessorBinding.listadapter;
                recyclerView.setAdapter(professorAdapter);
            }

            @Override
            public void onFailure(Call<List<Teacher>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Falha na comunicaçao com o serviço", Toast.LENGTH_LONG).show();
            }
        });
    }
}