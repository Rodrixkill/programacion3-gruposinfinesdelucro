package com.programacion3.gruposinfinesdelucro.app.classes;

import android.support.design.internal.ParcelableSparseArray;

import java.io.Serializable;

/**
 * Created by Joaco99 on 05/05/2018.
 */

public class Exercise implements Serializable {
    private String description, imagen, name, musculo;
    private Enums.Exercisetype type;

    public Exercise() {
    }

    public Exercise(String description, String imagen, String musculo, String name, Enums.Exercisetype type) {
        this.name = name;
        this.musculo = musculo;
        this.description = description;
        this.imagen = imagen;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Nombre: " + name + "\nDescripción: " + description + "\nImagen: " + imagen;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImagen() {
        return imagen;
    }

    public String getMusculo() {
        return musculo;
    }

    public Enums.Exercisetype getType() {
        return type;
    }
}
