package com.example.retrofitroom;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.example.retrofitroom.connection.ConnectionConfig;
import com.example.retrofitroom.databinding.ActivityNewAllocationBinding;
import com.example.retrofitroom.models.Allocation;
import com.example.retrofitroom.models.AllocationRequest;
import com.example.retrofitroom.models.Course;
import com.example.retrofitroom.models.Teacher;
import com.example.retrofitroom.service.AllocationService;
import com.example.retrofitroom.service.CourseService;
import com.example.retrofitroom.service.ProfessorService;
import com.example.retrofitroom.shared.helpers;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewAllocationActivity extends AppCompatActivity {

    ActivityNewAllocationBinding activityNewAllocationBinding;

    ArrayList<String> daysOfWeekList = helpers.getDaysOfWeek();

    CourseService courseService = ConnectionConfig.newInstance().courseService();

    ProfessorService professorService = ConnectionConfig.newInstance().professorService();

    AllocationService allocationService = ConnectionConfig.newInstance().allocationService();

    List<Course> courses;

    List<Teacher> professors;

    long courseSpinnerIndex;

    long daySpinnerIndex;

    long professorSpinnerIndex;

    ArrayList<String> courseListString = new ArrayList<String>();

    ArrayList<String> professorListString = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNewAllocationBinding = ActivityNewAllocationBinding.inflate(getLayoutInflater());
        setContentView(activityNewAllocationBinding.getRoot());

        ArrayAdapter<String> arrayAdapterSpinner = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, daysOfWeekList);
        arrayAdapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityNewAllocationBinding.spinner3.setAdapter(arrayAdapterSpinner);

        getAllCourses();

        activityNewAllocationBinding.btEnviar.setOnClickListener(View->{
            createAllocation();
        });

        activityNewAllocationBinding.spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                daySpinnerIndex = id;
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        activityNewAllocationBinding.spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                courseSpinnerIndex = id;
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        activityNewAllocationBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                professorSpinnerIndex = id;
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

    }

    protected  void getAllCourses(){
        courseService.getAllCourses().enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                courses = response.body();

                for (Course dp: courses) {
                    courseListString.add(dp.getName());
                }

                ArrayAdapter<String> arrayAdapterSpinner = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, courseListString);
                arrayAdapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                activityNewAllocationBinding.spinner2.setAdapter(arrayAdapterSpinner);

                getAllProfessors();

            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Falha na comunicaçao com o serviço", Toast.LENGTH_LONG).show();
            }
        });
    }

    protected  void getAllProfessors(){
        professorService.getAllProfessors().enqueue(new Callback<List<Teacher>>() {
            @Override
            public void onResponse(Call<List<Teacher>> call, Response<List<Teacher>> response) {
                professors = response.body();

                for (Teacher dp: professors) {
                    professorListString.add(dp.getName());
                }

                ArrayAdapter<String> arrayAdapterSpinner = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, professorListString);
                arrayAdapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                activityNewAllocationBinding.spinner.setAdapter(arrayAdapterSpinner);

                endLoadingStateView();
            }

            @Override
            public void onFailure(Call<List<Teacher>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Falha na comunicaçao com o serviço", Toast.LENGTH_LONG).show();
            }
        });
    }

    protected void initLoadingStateView(){
        activityNewAllocationBinding.progressBarNewProfessors.setVisibility(View.VISIBLE);

        activityNewAllocationBinding.spinner.setVisibility(View.GONE);
        activityNewAllocationBinding.btEnviar.setVisibility(View.GONE);
        activityNewAllocationBinding.datePicker1.setVisibility(View.GONE);
        activityNewAllocationBinding.textView2.setVisibility(View.GONE);
        activityNewAllocationBinding.textView5.setVisibility(View.GONE);
        activityNewAllocationBinding.textView6.setVisibility(View.GONE);
        activityNewAllocationBinding.datePicker2.setVisibility(View.GONE);
        activityNewAllocationBinding.spinner2.setVisibility(View.GONE);
        activityNewAllocationBinding.spinner3.setVisibility(View.GONE);
        activityNewAllocationBinding.titleProfessorsForm.setVisibility(View.GONE);
    }

    protected void endLoadingStateView(){
        activityNewAllocationBinding.progressBarNewProfessors.setVisibility(View.GONE);

        activityNewAllocationBinding.spinner.setVisibility(View.VISIBLE);
        activityNewAllocationBinding.datePicker2.setVisibility(View.VISIBLE);
        activityNewAllocationBinding.datePicker1.setVisibility(View.VISIBLE);
        activityNewAllocationBinding.textView2.setVisibility(View.VISIBLE);
        activityNewAllocationBinding.textView5.setVisibility(View.VISIBLE);
        activityNewAllocationBinding.textView6.setVisibility(View.VISIBLE);
        activityNewAllocationBinding.spinner2.setVisibility(View.VISIBLE);
        activityNewAllocationBinding.spinner3.setVisibility(View.VISIBLE);
        activityNewAllocationBinding.titleProfessorsForm.setVisibility(View.VISIBLE);
        activityNewAllocationBinding.btEnviar.setVisibility(View.VISIBLE);
    }


    protected AllocationRequest getFormState(){
        AllocationRequest allocationRequest = new AllocationRequest();
        allocationRequest.setDay(helpers.getDayOfWeekByIndex((int)daySpinnerIndex));
        allocationRequest.setCourseId(courses.get((int)courseSpinnerIndex).getId());
        allocationRequest.setTeacherId(professors.get((int)professorSpinnerIndex).getId());

        int hourStart = activityNewAllocationBinding.datePicker1.getHour();

        int minuteStart = activityNewAllocationBinding.datePicker1.getMinute();

        String hourStartString = hourStart <= 9 ? "0"+hourStart : ""+hourStart;

        String hourStoptString = minuteStart <= 9 ? "0"+minuteStart : ""+minuteStart;

        allocationRequest.setStart(hourStartString + ":" + hourStoptString + "+0000");

        int hourEnd = activityNewAllocationBinding.datePicker2.getHour();

        int minuteEnd = activityNewAllocationBinding.datePicker2.getMinute();

        String hourEndString = hourEnd <= 9 ? "0"+hourEnd : ""+hourEnd;

        String hourEndtString = minuteEnd <= 9 ? "0"+minuteEnd : ""+minuteEnd;

        allocationRequest.setEnd(hourEndString + ":" + hourEndtString + "+0000");

        return allocationRequest;
    }


    protected void createAllocation(){
        initLoadingStateView();

        allocationService.createAllocations(getFormState()).enqueue(new Callback<Allocation>() {
            @Override
            public void onResponse(Call<Allocation> call, Response<Allocation> response) {
                Toast.makeText(getApplicationContext(), "Criado com sucesso", Toast.LENGTH_LONG).show();

                Intent allocationActivity = new Intent(getApplicationContext(), AllocationActivity.class);
                startActivity(allocationActivity);
                finish();
            }

            @Override
            public void onFailure(Call<Allocation> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Falha na comunicaçao com o serviço", Toast.LENGTH_LONG).show();
            }
        });



    }


}