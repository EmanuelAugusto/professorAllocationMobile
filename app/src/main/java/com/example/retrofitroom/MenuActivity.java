package com.example.retrofitroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import com.example.retrofitroom.adapter.DepartmentsAdapter;
import com.example.retrofitroom.adapter.MenuAdapter;
import com.example.retrofitroom.databinding.ActivityMenuBinding;
import com.example.retrofitroom.models.MenuItens;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    ActivityMenuBinding menuActivityBiding;

    private MenuAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menuActivityBiding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(menuActivityBiding.getRoot());

        ArrayList<MenuItens> menuOptions = new ArrayList();

        MenuItens menu1 = new MenuItens();
        menu1.setName("Departamentos");
        menu1.setOption("department");

        MenuItens menu2 = new MenuItens();
        menu2.setName("Professores");
        menu2.setOption("professors");

        MenuItens menu3 = new MenuItens();
        menu3.setName("Cursos");
        menu3.setOption("courses");

        MenuItens menu4 = new MenuItens();
        menu4.setName("Alocações");
        menu4.setOption("allocations");

        menuOptions.add(menu1);
        menuOptions.add(menu2);
        menuOptions.add(menu3);
        menuOptions.add(menu4);

        menuAdapter = new MenuAdapter(menuOptions, this);

        RecyclerView recyclerView = menuActivityBiding.recyclerView;
        recyclerView.setAdapter(menuAdapter);

    }
}