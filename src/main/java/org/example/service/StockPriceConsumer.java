package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.dto.StockTickDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockPriceConsumer {

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "stock-prices", groupId = "stock-group")
    public void consume(String message) {
        try {
            StockTickDto stock = objectMapper.readValue(message, StockTickDto.class);
            System.out.println("Consumed: " +
                    stock.getSymbol() +
                    " | Price: " + stock.getPrice() +
                    " | Volume: " + stock.getVolume() +
                    " | Time: " + stock.getTimestamp());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

