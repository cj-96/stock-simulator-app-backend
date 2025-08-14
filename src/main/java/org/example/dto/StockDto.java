package org.example.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {

    private Long id;

    @NotBlank(message = "Stock symbol is required")
    @Size(min = 1, max = 10, message = "Symbol must be between 1 and 10 characters")
    @Pattern(regexp = "^[A-Z]+$", message = "Symbol must contain only uppercase letters")
    private String symbol;

    @NotBlank(message = "Company name is required")
    @Size(min = 2, max = 100, message = "Company name must be between 2 and 100 characters")
    private String companyName;

    @NotNull(message = "Current price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Price must have at most 10 digits and 2 decimal places")
    private BigDecimal currentPrice;

    @DecimalMin(value = "0.00", message = "Previous close must be non-negative")
    @Digits(integer = 10, fraction = 2, message = "Previous close must have at most 10 digits and 2 decimal places")
    private BigDecimal previousClose;

    @Min(value = 0, message = "Volume must be non-negative")
    private Long volume;

    private LocalDateTime lastUpdated;

    private Boolean active;
}
