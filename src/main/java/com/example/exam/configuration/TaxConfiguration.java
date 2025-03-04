package com.example.exam.configuration;

import com.example.exam.model.StateTax;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class TaxConfiguration {

    @Bean(name = "CaStateTax")
    public StateTax CaStateTax(){

        StateTax stateTax = new StateTax();
        stateTax.setTaxRate(9.75);
        stateTax.setState("CA");
        ArrayList<String> list = new ArrayList<>();
        list.add("food");
        stateTax.setExemptList(list);

        return  stateTax;
    }

    @Bean(name = "NyStateTax")
    public StateTax NyStateTax(){

        StateTax stateTax = new StateTax();
        stateTax.setTaxRate(8.875);
        stateTax.setState("NY");
        ArrayList<String> list = new ArrayList<>();
        list.add("food");
        list.add("clothing");
        stateTax.setExemptList(list);

        return  stateTax;
    }

}
