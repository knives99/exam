package com.example.exam.service;

import com.example.exam.model.Product;
import com.example.exam.model.ReceiptRequest;
import com.example.exam.model.ReceiptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ReceiptService {


    @Autowired
    TaxStateService taxStateService;

    public ReceiptResponse postReceiptResponse(ReceiptRequest req){


       List<Product> products = req.getProducts();

        BigDecimal subtotal = BigDecimal.ZERO;
        BigDecimal salesTax = BigDecimal.ZERO;

        for (int i =0; i < products.size(); i++){
            BigDecimal price = products.get(i).getPrice();
            int quantity = products.get(i).getQuantity();
            String category = products.get(i).getCategory();

            BigDecimal itemTotal = price.multiply(BigDecimal.valueOf(quantity));
            subtotal = subtotal.add(itemTotal);

           String state = req.getState();
           BigDecimal itemtax = taxStateService.setSate(state).getTax(price,quantity,category);
           salesTax = salesTax.add(itemtax);

        }

        BigDecimal total = subtotal.add(salesTax);

        ReceiptResponse response = new ReceiptResponse();
        response.setProducts(products);
        response.setTotal(total);
        response.setSubtotal(subtotal);
        response.setSalesTax(salesTax);

        return  response;
    }
}
