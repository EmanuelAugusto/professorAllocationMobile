package com.example.retrofitroom.service;

import com.example.retrofitroom.models.Allocation;
import com.example.retrofitroom.models.AllocationRequest;
import com.example.retrofitroom.models.CourseRequest;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AllocationService {
    @GET("/allocations")
    Call<List<Allocation>> getAllAllocations();

    @GET("/allocations/{id}")
    Call<Allocation> getAllAllocationsById(@Path(value="id") Long id);

    @POST("/allocations")
    Call<Allocation> createAllocations(@Body() AllocationRequest allocationRequest);

    @PUT("/allocations/{id}")
    Call<Allocation> updateAllocations(@Path (value="id") Long id, @Body() AllocationRequest allocationRequest);

    @DELETE("/allocations/{id}")
    Call<Void> deleteAllocations(@Path (value="id") Long id);
}
