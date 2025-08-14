package org.example.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_ticks",
        indexes = {
                @Index(name = "idx_stock_timestamp", columnList = "stock_id, timestamp"),
                @Index(name = "idx_timestamp", columnList = "timestamp")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockTickEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stock_id", nullable = false)
    @NotNull(message = "Stock ID is required")
    private Long stockId;

    @Column(name = "symbol", nullable = false, length = 10)
    @NotBlank(message = "Stock symbol is required")
    @Size(min = 1, max = 10, message = "Symbol must be between 1 and 10 characters")
    private String symbol;

    @Column(name = "price", nullable = false, precision = 12, scale = 2)
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal price;

    @Column(name = "volume")
    @Min(value = 1, message = "Volume must be at least 1")
    private Long volume;

    @CreationTimestamp
    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

}
