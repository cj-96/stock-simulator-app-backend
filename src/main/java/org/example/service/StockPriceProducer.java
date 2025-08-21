package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.dto.StockTickDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class StockPriceProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private final List<StockTickDto> stocks = Arrays.asList(
            new StockTickDto(1L, 1L, "AAPL", BigDecimal.valueOf(170), 100L, LocalDateTime.now()),
            new StockTickDto(2L, 2L, "GOOGL", BigDecimal.valueOf(135), 200L, LocalDateTime.now()),
            new StockTickDto(3L, 3L, "AMZN", BigDecimal.valueOf(135), 150L, LocalDateTime.now())
    );

    private final Random random = new Random();

    @Scheduled(fixedRate = 1000) // every second
    public void generateStockPrices() {
        for (StockTickDto stock : stocks) {
            double changePercent = (random.nextDouble() - 0.5) * 2; // -1% to +1%
            BigDecimal newPrice = stock.getPrice()
                    .multiply(BigDecimal.valueOf(1 + changePercent / 100))
                    .setScale(2, BigDecimal.ROUND_HALF_UP);
            stock.setPrice(newPrice);
            stock.setTimestamp(LocalDateTime.now());

            try {
                String json = objectMapper.writeValueAsString(stock);
                kafkaTemplate.send("stock-prices", stock.getSymbol(), json);
                System.out.println("Produced: " + json);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

