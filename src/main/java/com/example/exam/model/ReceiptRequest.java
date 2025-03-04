package com.example.exam.model;

import lombok.Data;

import java.util.List;

@Data
public class ReceiptRequest {

    private String state;
    private List<Product> products;


}
