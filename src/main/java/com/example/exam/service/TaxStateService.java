package com.example.exam.service;

import com.example.exam.model.StateTax;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Component
public class TaxStateService {

    @Autowired
    @Qualifier("CaStateTax")
    StateTax caStateTax;

    @Autowired
    @Qualifier("NyStateTax")
    StateTax nyStateTax;

   StateTax stateTax = null;

   public TaxStateService setSate(String state){

       if (state.equals("CA")){
           stateTax = caStateTax;
       }else {
           stateTax = nyStateTax;
       }
       return this;
   }

   public BigDecimal getTax(BigDecimal price,int quantity,String category ){

       Optional<StateTax> StateTaxOpt = Optional.of(stateTax);
       //TODO:defaultStateTax
       ArrayList<String> exemptList = StateTaxOpt.orElse(nyStateTax).getExemptList();

       if(category != null && exemptList.contains(category)){
           return BigDecimal.ZERO;
       }

       BigDecimal taxAmount =  price.multiply(BigDecimal.valueOf(stateTax.getTaxRate())).multiply(BigDecimal.valueOf(quantity))
               .divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);

       return roundUpToNearestFiveCents(taxAmount);
   }

    private BigDecimal roundUpToNearestFiveCents(BigDecimal amount) {
        return amount
                .divide(BigDecimal.valueOf(0.05), 0, RoundingMode.CEILING)
                .multiply(BigDecimal.valueOf(0.05));
    }



}
