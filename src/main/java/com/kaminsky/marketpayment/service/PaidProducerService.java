package com.kaminsky.marketpayment.service;

import com.kaminsky.marketpayment.entity.PayedOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaidProducerService {
    private final KafkaTemplate<String, PayedOrder> kafkaTemplate;

    @Autowired
    public PaidProducerService(KafkaTemplate<String, PayedOrder> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, PayedOrder order)
    {
        kafkaTemplate.send(topic, order);
    }
}