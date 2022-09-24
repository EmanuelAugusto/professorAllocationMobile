package com.example.retrofitroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.retrofitroom.adapter.AllocationAdapter;
import com.example.retrofitroom.connection.ConnectionConfig;
import com.example.retrofitroom.databinding.ActivityAllocationBinding;
import com.example.retrofitroom.models.Allocation;
import com.example.retrofitroom.service.AllocationService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllocationActivity extends AppCompatActivity {

    ActivityAllocationBinding activityAllocationBinding;

    private AllocationAdapter allocationAdapter;

    AllocationService allocationService = ConnectionConfig.newInstance().allocationService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAllocationBinding = ActivityAllocationBinding.inflate(getLayoutInflater());
        setContentView(activityAllocationBinding.getRoot());

        this.getAllocations();
    }

    protected void getAllocations(){
        allocationService.getAllAllocations().enqueue(new Callback<List<Allocation>>() {
            @Override
            public void onResponse(Call<List<Allocation>> call, Response<List<Allocation>> response) {
                if(response.isSuccessful()){
                    List<Allocation> reponseList = response.body();

                    activityAllocationBinding.progressBarProfessors.setVisibility(View.GONE);
                    activityAllocationBinding.listadapter.setVisibility(View.VISIBLE);

                    allocationAdapter = new AllocationAdapter(reponseList, getApplicationContext());

                    activityAllocationBinding.listadapter.setAdapter(allocationAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Allocation>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Falha na comunicaçao com o serviço", Toast.LENGTH_LONG).show();
            }
        });
    }
}