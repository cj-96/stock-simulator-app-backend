package org.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "stocks",
        uniqueConstraints = @UniqueConstraint(columnNames = "symbol"))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "symbol", nullable = false, unique = true, length = 10)
    @NotBlank(message = "Stock symbol is required")
    @Size(min = 1, max = 10, message = "Symbol must be between 1 and 10 characters")
    @Pattern(regexp = "^[A-Z]+$", message = "Symbol must contain only uppercase letters")
    private String symbol;

    @Column(name = "company_name", nullable = false, length = 100)
    @NotBlank(message = "Company name is required")
    @Size(min = 2, max = 100, message = "Company name must be between 2 and 100 characters")
    private String companyName;

    @Column(name = "current_price", nullable = false, precision = 12, scale = 2)
    @NotNull(message = "Current price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal currentPrice;

    @Column(name = "previous_close", precision = 12, scale = 2)
    @DecimalMin(value = "0.00", message = "Previous close must be non-negative")
    private BigDecimal previousClose;

    @Column(name = "volume")
    @Min(value = 0, message = "Volume must be non-negative")
    private Long volume;

    @Column(name = "active")
    private Boolean active = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
}
