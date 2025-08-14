package org.example.dto;


import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockTickDto {

    private Long id;

    @NotNull(message = "Stock ID is required")
    private Long stockId;

    @NotBlank(message = "Stock symbol is required")
    @Size(min = 1, max = 10, message = "Symbol must be between 1 and 10 characters")
    private String symbol;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Price must have at most 10 digits and 2 decimal places")
    private BigDecimal price;

    @Min(value = 1, message = "Volume must be at least 1")
    private Long volume;

    @NotNull(message = "Timestamp is required")
    private LocalDateTime timestamp;

}


