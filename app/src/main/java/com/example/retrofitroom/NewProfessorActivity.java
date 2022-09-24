package com.example.retrofitroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.example.retrofitroom.connection.ConnectionConfig;
import com.example.retrofitroom.databinding.ActivityNewProfessorBinding;
import com.example.retrofitroom.models.Course;
import com.example.retrofitroom.models.CourseRequest;
import com.example.retrofitroom.models.Departments;
import com.example.retrofitroom.models.ProfessorRequest;
import com.example.retrofitroom.models.Teacher;
import com.example.retrofitroom.service.DepartmentService;
import com.example.retrofitroom.service.ProfessorService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewProfessorActivity extends AppCompatActivity {

    ArrayList<String> departmentsList = new ArrayList<String>();

    List<Departments> departments;

    Long professorId;

    long departmentSelected = 0;

    ActivityNewProfessorBinding activityNewProfessorBinding;

    DepartmentService departmentService = ConnectionConfig.newInstance().departmentService();

    ProfessorService professorService = ConnectionConfig.newInstance().professorService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNewProfessorBinding = ActivityNewProfessorBinding.inflate(getLayoutInflater());
        setContentView(activityNewProfessorBinding.getRoot());

        this.getAllDepartments();

        Intent intent = getIntent();
        String professorIdString = intent.getStringExtra("professorId");

        if(professorIdString != null){
            this.professorId = Long.parseLong(professorIdString);
        }


        activityNewProfessorBinding.btEnviar.setOnClickListener(view->{
            this.createProfessor();
        });

        activityNewProfessorBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                departmentSelected = id;
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    protected void createProfessor() {

        ProfessorRequest professorRequest = new ProfessorRequest();
        professorRequest.setName(activityNewProfessorBinding.edName.getText().toString());
        professorRequest.setCpf(activityNewProfessorBinding.edCPF.getText().toString());
        professorRequest.setDepartmentId(departments.get(activityNewProfessorBinding.spinner.getSelectedItemPosition()).getId());

        activityNewProfessorBinding.progressBarNewProfessors.setVisibility(View.VISIBLE);

        activityNewProfessorBinding.spinner.setVisibility(View.GONE);
        activityNewProfessorBinding.btEnviar.setVisibility(View.GONE);
        activityNewProfessorBinding.edCPF.setVisibility(View.GONE);
        activityNewProfessorBinding.edName.setVisibility(View.GONE);
        activityNewProfessorBinding.titleProfessorsForm.setVisibility(View.GONE);

        professorService.createProfessor(professorRequest).enqueue(new Callback<Teacher>() {
            @Override
            public void onResponse(Call<Teacher> call, Response<Teacher> response) {
                Toast.makeText(getApplicationContext(), "Criado com sucesso", Toast.LENGTH_LONG).show();

                Intent professorActivity = new Intent(getApplicationContext(), ProfessorActivity.class);
                startActivity(professorActivity);
                finish();
            }

            @Override
            public void onFailure(Call<Teacher> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Falha na comunicaçao com o serviço", Toast.LENGTH_LONG).show();
            }
        });
    }

    protected  void getAllDepartments(){
        departmentService.getAllDepartment().enqueue(new Callback<List<Departments>>() {
            @Override
            public void onResponse(Call<List<Departments>> call, Response<List<Departments>> response) {
                departments = response.body();

                for (Departments dp: departments) {
                    departmentsList.add(dp.getName());
                }

                ArrayAdapter<String> arrayAdapterSpinner = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, departmentsList);
                arrayAdapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                activityNewProfessorBinding.spinner.setAdapter(arrayAdapterSpinner);

                if(professorId != null){
                    activityNewProfessorBinding.titleProfessorsForm.setText("Editar professor");

                    professorService.getAllProfessorById(professorId).enqueue(new Callback<Teacher>() {
                        @Override
                        public void onResponse(Call<Teacher> call, Response<Teacher> response) {

                            Teacher responseData = response.body();

                            activityNewProfessorBinding.edCPF.setText(responseData.getName());
                            activityNewProfessorBinding.edName.setText(responseData.getCpf());

                            System.out.println(departments);
                            int position = 0;
                            for (Departments obj : departments) {
                                if (obj.getId().equals(responseData.getdepartmentId())) {
                                    break;
                                }
                                position += 1;
                            }

                            activityNewProfessorBinding.spinner.setSelection(position);

                            activityNewProfessorBinding.progressBarNewProfessors.setVisibility(View.GONE);

                            activityNewProfessorBinding.spinner.setVisibility(View.VISIBLE);
                            activityNewProfessorBinding.btEnviar.setVisibility(View.VISIBLE);
                            activityNewProfessorBinding.edCPF.setVisibility(View.VISIBLE);
                            activityNewProfessorBinding.edName.setVisibility(View.VISIBLE);
                            activityNewProfessorBinding.titleProfessorsForm.setVisibility(View.VISIBLE);


                        }
                        @Override
                        public void onFailure(Call<Teacher> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Erro ao criar departamento", Toast.LENGTH_LONG).show();
                        }
                    });
                }else {
                    activityNewProfessorBinding.titleProfessorsForm.setText("Novo professor");

                    activityNewProfessorBinding.progressBarNewProfessors.setVisibility(View.GONE);

                    activityNewProfessorBinding.spinner.setVisibility(View.VISIBLE);
                    activityNewProfessorBinding.btEnviar.setVisibility(View.VISIBLE);
                    activityNewProfessorBinding.edCPF.setVisibility(View.VISIBLE);
                    activityNewProfessorBinding.edName.setVisibility(View.VISIBLE);
                    activityNewProfessorBinding.titleProfessorsForm.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailure(Call<List<Departments>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Falha na comunicaçao com o serviço", Toast.LENGTH_LONG).show();
            }
        });
    }
}