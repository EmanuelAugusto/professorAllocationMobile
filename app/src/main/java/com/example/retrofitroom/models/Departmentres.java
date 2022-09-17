package com.example.retrofitroom.models;

import java.util.ArrayList;

public class Departmentres {

        public int id;
        public String name;
        public String cpf;
        public int departmentId;
        public Departments department;
        public ArrayList<Allocation> allocations;

        public int getId() {
                return id;
        }

        public void setId(int id) {
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

        public int getDepartmentId() {
                return departmentId;
        }

        public void setDepartmentId(int departmentId) {
                this.departmentId = departmentId;
        }

        public Departments getDepartment() {
                return department;
        }

        public void setDepartment(Departments department) {
                this.department = department;
        }

        public ArrayList<Allocation> getAllocations() {
                return allocations;
        }

        public void setAllocations(ArrayList<Allocation> allocations) {
                this.allocations = allocations;
        }
}
