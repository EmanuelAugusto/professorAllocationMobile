package com.example.retrofitroom.service;

import com.example.retrofitroom.models.Course;
import com.example.retrofitroom.models.CourseRequest;
import com.example.retrofitroom.models.ProfessorRequest;
import com.example.retrofitroom.models.Teacher;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProfessorService {
    @GET("/teachers")
    Call<List<Teacher>> getAllProfessors();

    @GET("/teachers/{id}")
    Call<Teacher> getAllProfessorById(@Path(value="id") Long id);

    @POST("/teachers")
    Call<Teacher> createProfessor(@Body() ProfessorRequest professorRequest);

    @PUT("/teachers/{id}")
    Call<Teacher> updateProfessor(@Path (value="id") Long id, @Body() ProfessorRequest professorRequest);

    @DELETE("/teachers/{id}")
    Call<Void> deleteProfessor(@Path (value="id") Long id);
}
