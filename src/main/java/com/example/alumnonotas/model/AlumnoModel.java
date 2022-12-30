package com.example.alumnonotas.model;

import lombok.Data;

import java.util.List;

@Data
public class AlumnoModel {


    private String dni;

    private String nombre;

    private  Double promedio;

    private List<Integer> notas;
}
