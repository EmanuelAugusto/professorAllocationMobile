package com.example.retrofitroom.connection;

import com.example.retrofitroom.service.CourseService;
import com.example.retrofitroom.service.DepartmentService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnectionConfig {
    private Retrofit  retrofit = new Retrofit.Builder().baseUrl("https://backallocation.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build();

    public static ConnectionConfig newInstance() { return new ConnectionConfig(); }

    public Retrofit getRetrofit() { return  retrofit; }

    public DepartmentService departmentService(){
        return retrofit.create(DepartmentService.class);
    }

    public CourseService courseService(){
        return retrofit.create(CourseService.class);
    }
}
