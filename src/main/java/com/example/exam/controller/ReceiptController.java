package com.example.exam.controller;

import com.example.exam.model.ReceiptRequest;
import com.example.exam.model.ReceiptResponse;
import com.example.exam.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ReceiptController {

    @Autowired
    ReceiptService service;

    @PostMapping("/receipt")
    public ReceiptResponse postReceiptResponse(@RequestBody ReceiptRequest req){

        return  service.postReceiptResponse(req);
    }
}
