package com.kaminsky.marketpayment.service;

import com.kaminsky.entity.MarketOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class PaidProducerService {
    private final KafkaTemplate<String, MarketOrder> kafkaTemplate;

    @Autowired
    public PaidProducerService(KafkaTemplate<String, MarketOrder> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Async
    public void sendMessage(String topic, MarketOrder order)
    {
        kafkaTemplate.send(topic, order);
    }
}