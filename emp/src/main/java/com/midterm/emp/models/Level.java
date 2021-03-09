package com.midterm.emp.models;

import java.math.BigDecimal;

import javax.money.Monetary;
import javax.money.MonetaryAmount;

public enum Level {
    C1,
    C2,
    C3;

    public MonetaryAmount getSalary(Level level){
        switch (level) {
            case C1:
                MonetaryAmount amount =
                    Monetary.getDefaultAmountFactory()
                        .setNumber(new BigDecimal(200))
                        .setCurrency("USD")
                        .create(); 
                    
                return amount;
            case C2:
                MonetaryAmount amount2 =
                    Monetary.getDefaultAmountFactory()
                        .setNumber(new BigDecimal(500))
                        .setCurrency("USD")
                        .create(); 
                return amount2;
            case C3:
                MonetaryAmount amount3 =
                    Monetary.getDefaultAmountFactory()
                        .setNumber(new BigDecimal(1000))
                        .setCurrency("USD")
                        .create(); 
                return amount3;
            }
            
        return null;
    }
}
