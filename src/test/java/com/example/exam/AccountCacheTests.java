package com.example.exam;


import com.example.exam.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AccountCacheTests {


    @Test
    void AccountCacheLimit50() {
        for (int i = 0; i <=50 ; i++){
            Account  account = Account.getInstance(String.valueOf(i));
        }
        LinkedHashMap<String, Account> cacheMap = Account.getCacheMap();
        String key = cacheMap.keySet().iterator().next();
        assertEquals(key,String.valueOf(1));

    }
}
