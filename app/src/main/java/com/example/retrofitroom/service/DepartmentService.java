package com.example.retrofitroom.service;

import com.example.retrofitroom.models.Departments;
import com.example.retrofitroom.models.DepartmentsRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DepartmentService {

    @GET("/departaments")
    Call<List<Departments>> getAllDepartment();

    @GET("/departaments/{id}")
    Call<Departments> getAllDepartmentById(@Path (value="id") Long id);

    @POST("/departaments")
    Call<Departments> createDepartment(@Body() DepartmentsRequest departments);

    @PUT("/departaments/{id}")
    Call<Departments> updateDepartment(@Path (value="id") Long id, @Body()DepartmentsRequest departments);

    @DELETE("/departaments/{id}")
    Call<Void> deleteDepartment(@Path (value="id") Long id);

}
