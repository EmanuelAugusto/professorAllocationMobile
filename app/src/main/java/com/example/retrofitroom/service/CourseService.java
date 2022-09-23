package com.example.retrofitroom.service;

import com.example.retrofitroom.models.Course;
import com.example.retrofitroom.models.CourseRequest;
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

public interface CourseService {
    @GET("/courses")
    Call<List<Course>> getAllCourses();

    @GET("/courses/{id}")
    Call<Course> getAllCourseById(@Path(value="id") Long id);

    @POST("/courses")
    Call<Course> createCourse(@Body() CourseRequest courseRequest);

    @PUT("/courses/{id}")
    Call<Course> updateCourse(@Path (value="id") Long id, @Body() CourseRequest courseRequest);

    @DELETE("/courses/{id}")
    Call<Void> deleteCourse(@Path (value="id") Long id);
}
