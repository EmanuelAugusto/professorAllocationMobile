package com.example.retrofitroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.retrofitroom.adapter.CourseAdapter;
import com.example.retrofitroom.connection.ConnectionConfig;
import com.example.retrofitroom.databinding.ActivityCourseBinding;
import com.example.retrofitroom.models.Course;
import com.example.retrofitroom.service.CourseService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseActivity extends AppCompatActivity {

    private CourseAdapter courseAdapter;

    ActivityCourseBinding activityCourseBinding;

    CourseService courseService = ConnectionConfig.newInstance().courseService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCourseBinding = ActivityCourseBinding.inflate(getLayoutInflater());
        setContentView(activityCourseBinding.getRoot());

        activityCourseBinding.floatingActionButton.setOnClickListener(view -> {
            Intent newCourseActivity = new Intent(getApplicationContext(), NewCourseActivity.class);
            newCourseActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(newCourseActivity);
            finish();
        });


        this.getAllCourses();
    }

    protected  void getAllCourses(){
        courseService.getAllCourses().enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                List<Course> reponseList = response.body();

                activityCourseBinding.listadapter.setVisibility(View.VISIBLE);
                activityCourseBinding.progressBarDepartments.setVisibility(View.GONE);
                courseAdapter = new CourseAdapter(reponseList, getApplicationContext());

                RecyclerView recyclerView = activityCourseBinding.listadapter;
                recyclerView.setAdapter(courseAdapter);
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Falha na comunicaçao com o serviço", Toast.LENGTH_LONG).show();
            }
        });
    }
}