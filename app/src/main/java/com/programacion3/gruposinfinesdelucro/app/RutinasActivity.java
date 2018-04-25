package com.programacion3.gruposinfinesdelucro.app;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class RutinasActivity extends NavigationActivity  {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String[] exercises,duration;
    int[] img_res;
    ArrayList<Ejercicio> arrayList= new ArrayList<Ejercicio>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_rutinas);
        super.onCreate(savedInstanceState);

        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        Ejercicio ejercicio = new Ejercicio(R.drawable.bicep1,"Curl","12 min");
        Ejercicio ejercicio1 = new Ejercicio(R.drawable.chest1,"press","12 reps");
        Ejercicio ejercicio2 = new Ejercicio(R.drawable.cardio1,"legs","12 min");

        arrayList.add(ejercicio);
        arrayList.add(ejercicio1);
        arrayList.add(ejercicio2);
        adapter= new RecyclerAdapter(arrayList, this);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        }


}
