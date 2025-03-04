package com.example.exam.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ReceiptResponse {

    private List<Product> products;
    private BigDecimal subtotal;
    private BigDecimal salesTax;
    private BigDecimal total;

}
