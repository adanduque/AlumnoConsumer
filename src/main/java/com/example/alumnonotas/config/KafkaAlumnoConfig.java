package com.example.alumnonotas.config;

import com.example.alumnonotas.event.Event;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.util.backoff.FixedBackOff;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaAlumnoConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    /**
     * Usado para recibir data de replyingTemplate
     * @return
     */
    @Bean
    public ConsumerFactory<String, Event<?>> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
    }



    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Event<?>>
    kafkaListenerContainerFactory()  {

        ConcurrentKafkaListenerContainerFactory<String, Event<?>> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}