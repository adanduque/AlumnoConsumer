package com.example.alumnonotas.service;

import com.example.alumnonotas.event.AlumnoCreatedEvent;
import com.example.alumnonotas.event.Event;
import com.example.alumnonotas.model.AlumnoModel;
import com.example.alumnonotas.repository.AlumnoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AlumnoEventService {

    @Autowired
    private AlumnoRepository studentRepository;


    @KafkaListener(
            topics = "alumno_notas",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "grupo1")
    public void consumer(Event<?> event) {
        if (event.getClass().isAssignableFrom(AlumnoCreatedEvent.class)) {
            AlumnoCreatedEvent customerCreatedEvent = (AlumnoCreatedEvent) event;


            log.info("Received Alumnos created event .... with Id={}, data={}",
                    customerCreatedEvent.getId(),
                    customerCreatedEvent.getData().toString());
            AlumnoModel alumno= customerCreatedEvent.getData();
            Double promedio = Double.valueOf(alumno.getNotas().stream().reduce(0, Integer::sum)/4);
            alumno.setPromedio(promedio);
            studentRepository.save(alumno);
        }

    }
}
