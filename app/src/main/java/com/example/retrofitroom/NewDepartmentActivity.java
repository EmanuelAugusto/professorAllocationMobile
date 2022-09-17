package com.example.retrofitroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.retrofitroom.connection.ConnectionConfig;
import com.example.retrofitroom.databinding.ActivityMainBinding;
import com.example.retrofitroom.models.Departments;
import com.example.retrofitroom.models.DepartmentsRequest;
import com.example.retrofitroom.service.DepartmentService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.retrofitroom.databinding.ActivityNewDepartmentBinding;

public class NewDepartmentActivity extends AppCompatActivity {

    ActivityNewDepartmentBinding activityNewDepartmentBinding;

    private Long departmentId;

    DepartmentService departmentService = ConnectionConfig.newInstance().departmentService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNewDepartmentBinding = ActivityNewDepartmentBinding.inflate(getLayoutInflater());
        setContentView(activityNewDepartmentBinding.getRoot());

        Intent intent = getIntent();
        String departmentIdString = intent.getStringExtra("departmentId");

        if (departmentIdString != null) {

            this.departmentId = Long.parseLong(departmentIdString);

            activityNewDepartmentBinding.titleDepartmentForm.setText("Editar departamento");

            departmentService.getAllDepartmentById(this.departmentId).enqueue(new Callback<Departments>() {
                @Override
                public void onResponse(Call<Departments> call, Response<Departments> response) {

                    Departments responseData = response.body();

                    activityNewDepartmentBinding.edMensagem.setText(responseData.getName());

                    activityNewDepartmentBinding.titleDepartmentForm.setVisibility(View.VISIBLE);
                    activityNewDepartmentBinding.btEnviar.setVisibility(View.VISIBLE);
                    activityNewDepartmentBinding.edMensagem.setVisibility(View.VISIBLE);
                    activityNewDepartmentBinding.progressBarNewDepartments.setVisibility(View.GONE);

                }
                @Override
                public void onFailure(Call<Departments> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Erro ao criar departamento", Toast.LENGTH_LONG).show();
                }
            });
        }else{
            activityNewDepartmentBinding.titleDepartmentForm.setText("Novo departamento");

            activityNewDepartmentBinding.titleDepartmentForm.setVisibility(View.VISIBLE);
            activityNewDepartmentBinding.btEnviar.setVisibility(View.VISIBLE);
            activityNewDepartmentBinding.edMensagem.setVisibility(View.VISIBLE);
            activityNewDepartmentBinding.progressBarNewDepartments.setVisibility(View.GONE);
        }

        activityNewDepartmentBinding.btEnviar.setOnClickListener(view -> {
            String nameDepartament = this.activityNewDepartmentBinding.edMensagem.getText().toString();

            if (nameDepartament.isEmpty()) {
                Toast.makeText(this, "Preencha o nome do departamento", Toast.LENGTH_SHORT).show();
                return;
            }

            if (this.departmentId != null) {
                this.updateDepartment(this.activityNewDepartmentBinding.edMensagem.getText().toString());
            } else {
                this.createDepartment(this.activityNewDepartmentBinding.edMensagem.getText().toString());
            }
        });
    }

    protected void createDepartment(String name) {

        DepartmentsRequest departmentsRequest = new DepartmentsRequest();
        departmentsRequest.setName(name);

        activityNewDepartmentBinding.titleDepartmentForm.setVisibility(View.GONE);
        activityNewDepartmentBinding.btEnviar.setVisibility(View.GONE);
        activityNewDepartmentBinding.edMensagem.setVisibility(View.GONE);
        activityNewDepartmentBinding.progressBarNewDepartments.setVisibility(View.VISIBLE);

        departmentService.createDepartment(departmentsRequest).enqueue(new Callback<Departments>() {
            @Override
            public void onResponse(Call<Departments> call, Response<Departments> response) {
                Toast.makeText(getApplicationContext(), "Criado com sucesso", Toast.LENGTH_LONG).show();

                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);
                finish();
            }

            @Override
            public void onFailure(Call<Departments> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Falha na comunicaçao com o serviço", Toast.LENGTH_LONG).show();
            }
        });
    }

    protected void updateDepartment(String name) {

        activityNewDepartmentBinding.titleDepartmentForm.setVisibility(View.GONE);
        activityNewDepartmentBinding.btEnviar.setVisibility(View.GONE);
        activityNewDepartmentBinding.edMensagem.setVisibility(View.GONE);
        activityNewDepartmentBinding.progressBarNewDepartments.setVisibility(View.VISIBLE);

        DepartmentsRequest departmentsRequest = new DepartmentsRequest();
        departmentsRequest.setName(name);

        departmentService.updateDepartment(this.departmentId, departmentsRequest).enqueue(new Callback<Departments>() {
            @Override
            public void onResponse(Call<Departments> call, Response<Departments> response) {
                Toast.makeText(getApplicationContext(), "Atualizado com sucesso", Toast.LENGTH_LONG).show();

                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);
                finish();
            }

            @Override
            public void onFailure(Call<Departments> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Falha na comunicaçao com o serviço", Toast.LENGTH_LONG).show();
            }
        });
    }
}