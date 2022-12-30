package com.example.alumnonotas.repository;

import com.example.alumnonotas.model.AlumnoModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlumnoRepository extends MongoRepository<AlumnoModel, String> {



}