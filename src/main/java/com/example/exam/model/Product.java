package com.example.exam.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

    private String name;
    private BigDecimal price;
    private int quantity;
    private String category;
}
