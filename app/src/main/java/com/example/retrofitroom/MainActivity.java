package com.example.retrofitroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.retrofitroom.adapter.DepartmentsAdapter;
import com.example.retrofitroom.connection.ConnectionConfig;
import com.example.retrofitroom.databinding.ActivityMainBinding;
import com.example.retrofitroom.models.Departments;
import com.example.retrofitroom.models.DepartmentsRequest;
import com.example.retrofitroom.service.DepartmentService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private DepartmentsAdapter departmentsAdapter;

    ActivityMainBinding activityMainBinding;

    DepartmentService departmentService = ConnectionConfig.newInstance().departmentService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        activityMainBinding.floatingActionButton.setOnClickListener(view -> {
            Intent newDepartmentActivity = new Intent(getApplicationContext(), NewDepartmentActivity.class);
            newDepartmentActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(newDepartmentActivity);
            finish();
        });

        this.getAllDepartments();
    }

    protected  void getAllDepartments(){
        departmentService.getAllDepartment().enqueue(new Callback<List<Departments>>() {
            @Override
            public void onResponse(Call<List<Departments>> call, Response<List<Departments>> response) {
                List<Departments> reponseList = response.body();

                activityMainBinding.listadapter.setVisibility(View.VISIBLE);
                activityMainBinding.progressBarDepartments.setVisibility(View.GONE);
                departmentsAdapter = new DepartmentsAdapter(reponseList, getApplicationContext());

                RecyclerView recyclerView = activityMainBinding.listadapter;
                recyclerView.setAdapter(departmentsAdapter);
            }

            @Override
            public void onFailure(Call<List<Departments>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Falha na comunicaçao com o serviço", Toast.LENGTH_LONG).show();
            }
        });
    }

}