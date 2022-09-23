package com.example.retrofitroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.retrofitroom.connection.ConnectionConfig;
import com.example.retrofitroom.databinding.ActivityNewCourseBinding;
import com.example.retrofitroom.models.Course;
import com.example.retrofitroom.models.CourseRequest;
import com.example.retrofitroom.models.Departments;
import com.example.retrofitroom.models.DepartmentsRequest;
import com.example.retrofitroom.service.CourseService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewCourseActivity extends AppCompatActivity {

    ActivityNewCourseBinding activityNewCourseBinding;

    private Long courseId;

    CourseService courseService = ConnectionConfig.newInstance().courseService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNewCourseBinding = activityNewCourseBinding.inflate(getLayoutInflater());
        setContentView(activityNewCourseBinding.getRoot());

        Intent intent = getIntent();
        String courseIdString = intent.getStringExtra("courseId");

        if (courseIdString != null) {

            this.courseId = Long.parseLong(courseIdString);

            activityNewCourseBinding.titleDepartmentForm.setText("Editar curso");

            courseService.getAllCourseById(this.courseId).enqueue(new Callback<Course>() {
                @Override
                public void onResponse(Call<Course> call, Response<Course> response) {

                    Course responseData = response.body();

                    activityNewCourseBinding.edMensagem.setText(responseData.getName());

                    activityNewCourseBinding.titleDepartmentForm.setVisibility(View.VISIBLE);
                    activityNewCourseBinding.btEnviar.setVisibility(View.VISIBLE);
                    activityNewCourseBinding.edMensagem.setVisibility(View.VISIBLE);
                    activityNewCourseBinding.progressBarNewDepartments.setVisibility(View.GONE);

                }
                @Override
                public void onFailure(Call<Course> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Erro ao criar departamento", Toast.LENGTH_LONG).show();
                }
            });
        }else{
            activityNewCourseBinding.titleDepartmentForm.setText("Novo curso");

            activityNewCourseBinding.titleDepartmentForm.setVisibility(View.VISIBLE);
            activityNewCourseBinding.btEnviar.setVisibility(View.VISIBLE);
            activityNewCourseBinding.edMensagem.setVisibility(View.VISIBLE);
            activityNewCourseBinding.progressBarNewDepartments.setVisibility(View.GONE);
        }

        activityNewCourseBinding.btEnviar.setOnClickListener(view -> {
            String nameDepartament = this.activityNewCourseBinding.edMensagem.getText().toString();

            if (nameDepartament.isEmpty()) {
                Toast.makeText(this, "Preencha o nome do departamento", Toast.LENGTH_SHORT).show();
                return;
            }

            if (this.courseId != null) {
                this.updateCourse(this.activityNewCourseBinding.edMensagem.getText().toString());
            } else {
                this.createCourse(this.activityNewCourseBinding.edMensagem.getText().toString());
            }
        });
    }

    protected void createCourse(String name) {

        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setName(name);

        activityNewCourseBinding.titleDepartmentForm.setVisibility(View.GONE);
        activityNewCourseBinding.btEnviar.setVisibility(View.GONE);
        activityNewCourseBinding.edMensagem.setVisibility(View.GONE);
        activityNewCourseBinding.progressBarNewDepartments.setVisibility(View.VISIBLE);

        courseService.createCourse(courseRequest).enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                Toast.makeText(getApplicationContext(), "Criado com sucesso", Toast.LENGTH_LONG).show();

                Intent courseActivity = new Intent(getApplicationContext(), CourseActivity.class);
                startActivity(courseActivity);
                finish();
            }

            @Override
            public void onFailure(Call<Course> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Falha na comunicaçao com o serviço", Toast.LENGTH_LONG).show();
            }
        });
    }

    protected void updateCourse(String name) {

        activityNewCourseBinding.titleDepartmentForm.setVisibility(View.GONE);
        activityNewCourseBinding.btEnviar.setVisibility(View.GONE);
        activityNewCourseBinding.edMensagem.setVisibility(View.GONE);
        activityNewCourseBinding.progressBarNewDepartments.setVisibility(View.VISIBLE);

        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setName(name);

        courseService.updateCourse(this.courseId, courseRequest).enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                Toast.makeText(getApplicationContext(), "Atualizado com sucesso", Toast.LENGTH_LONG).show();

                Intent courseActivity = new Intent(getApplicationContext(), CourseActivity.class);
                startActivity(courseActivity);
                finish();
            }

            @Override
            public void onFailure(Call<Course> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Falha na comunicaçao com o serviço", Toast.LENGTH_LONG).show();
            }
        });
    }
}