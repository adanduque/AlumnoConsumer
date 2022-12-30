package com.example.alumnonotas.event;

import com.example.alumnonotas.model.AlumnoModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlumnoCreatedEvent  extends Event<AlumnoModel>{
}
