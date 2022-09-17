package com.example.retrofitroom.models;

import com.example.retrofitroom.models.Departments;

import java.util.ArrayList;

public class Teacher {

    private Long id;

    private String name;

    private String cpf;

    private Long departmentId;

    private Departments department;

    public ArrayList<Allocation> allocations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long getdepartmentId() {
        return departmentId;
    }

    public void setdepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Departments getdepartment() {
        return department;
    }

    public void setdepartment(Departments department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", departmentId=" + departmentId +
                ", department=" + department +
                '}';
    }
}
