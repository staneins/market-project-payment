package com.kaminsky.marketpayment.service;

import com.kaminsky.entity.MarketOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class PaidConsumerService {
    private final PaidProducerService paidProducerService;

    private final Logger logger = LoggerFactory.getLogger(PaidConsumerService.class);

    public PaidConsumerService(PaidProducerService paidProducerService) {
        this.paidProducerService = paidProducerService;
    }

    @Async
    @RetryableTopic(backoff = @Backoff(delay = 3000))
    @KafkaListener(topics = "new_orders", groupId = "payed_group")
    public void listen(MarketOrder order) {
        try {
            logger.info("Новый заказ: {}", order);
            order.setPaid(true);
            paidProducerService.sendMessage("payed_orders", order);
        } catch (Exception e) {
            logger.error("Ошибка при обработке заказа: {}", order, e);
            throw e;
        }
    }
}
