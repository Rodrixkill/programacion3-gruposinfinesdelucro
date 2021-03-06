package com.programacion3.gruposinfinesdelucro.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.programacion3.gruposinfinesdelucro.app.R;
import com.programacion3.gruposinfinesdelucro.app.classes.Enums;
import com.programacion3.gruposinfinesdelucro.app.classes.Exercise;
import com.programacion3.gruposinfinesdelucro.app.classes.Routine;
import com.programacion3.gruposinfinesdelucro.app.classes.ScheduledExercise;

import java.io.Serializable;

public class SetRepsSetsActivity extends NavigationActivity {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private Button done;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private EditText reps, sets;
    private Bundle datos;
    private Exercise exercise;
    private RadioGroup radioGroup;
    private Routine routine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_setrepset);
        super.onCreate(savedInstanceState);
        final String[] str = new String[1];
        str[0] = "SUNDAY";
        done = (Button) findViewById(R.id.done);
        reps = (EditText) findViewById(R.id.set_repeticiones);
        sets = (EditText) findViewById(R.id.set_sets);
        radioGroup = findViewById(R.id.dias);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.lunes) {
                    str[0] = "MONDAY";
                } else if (i == R.id.martes) {
                    str[0] = "TUESDAY";
                } else if (i == R.id.miercoles) {
                    str[0] = "WEDNESDAY";
                } else if (i == R.id.jueves) {
                    str[0] = "THURSDAY";
                } else if (i == R.id.viernes) {
                    str[0] = "FRIDAY";
                } else if (i == R.id.sabado) {
                    str[0] = "SATURDAY";
                } else {
                    str[0] = "SUNDAY";
                }

            }
        });
        datos = getIntent().getExtras();
        exercise = (Exercise) datos.getSerializable("Ejercicio");
        routine = (Routine) datos.getSerializable("rutina");

        final DatabaseReference usersRef = database.getReference("users");


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int repsIngresado;
                int setsIngresado;
                if (!isNumeric(sets.getText().toString())) {
                    setsIngresado = 4;
                } else {
                    setsIngresado = Integer.parseInt(sets.getText().toString());
                }
                if (!isNumeric(reps.getText().toString())) {
                    repsIngresado = 15;
                } else {
                    repsIngresado = Integer.parseInt(reps.getText().toString());
                }
                ScheduledExercise exe1 = new ScheduledExercise(exercise, repsIngresado, setsIngresado);
                if (str[0].equals("MONDAY")) {
                    routine.addExercise(Enums.Day.MONDAY, exe1);
                } else if (str[0].equals("TUESDAY")) {
                    routine.addExercise(Enums.Day.TUESDAY, exe1);
                } else if (str[0].equals("WEDNESDAY")) {
                    routine.addExercise(Enums.Day.WEDNESDAY, exe1);
                } else if (str[0].equals("THURSDAY")) {
                    routine.addExercise(Enums.Day.THURSDAY, exe1);
                } else if (str[0].equals("FRIDAY")) {
                    routine.addExercise(Enums.Day.FRIDAY, exe1);
                } else if (str[0].equals("SATURDAY")) {
                    routine.addExercise(Enums.Day.SATURDAY, exe1);
                } else {
                    routine.addExercise(Enums.Day.SUNDAY, exe1);
                }
                usersRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("routines").child(routine.getName()).setValue(routine);
                Toast.makeText(SetRepsSetsActivity.this, "EXERCISE ADDED SUCCESFULLY", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }


}
