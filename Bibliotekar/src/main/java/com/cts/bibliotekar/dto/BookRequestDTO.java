package com.cts.bibliotekar.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BookRequestDTO {

    @NotBlank(message = "Title cannot be empty.")
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters.")
    private String title;

    @NotNull(message = "Price is required.")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0.")
    private BigDecimal price;

    @NotNull(message = "Publish date is required.")
    @PastOrPresent(message = "Publish date cannot be in the future.")
    private LocalDate publishDate;

    // Getters and setters


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
}
