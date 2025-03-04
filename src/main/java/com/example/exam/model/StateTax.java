package com.example.exam.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class StateTax {

    String state;
    ArrayList<String> exemptList;
    Double taxRate;



}
