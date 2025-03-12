package com.example.exam.model;

import com.example.exam.cache.AccountCache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Account {

    private String key;
    private String cachedData;
    private static LinkedHashMap<String,Account> cacheMap = new AccountCache<>() ;


    public static Account getInstance(String key) {
        Account account = cacheMap.get(key);
        if(account == null) {
            account = new Account(key);
            account.init();
            cacheMap.put(key, account);
        }
        return account;
    }

    // for測試
    @Deprecated
    public static LinkedHashMap<String,Account> getCacheMap(){
        return cacheMap;
    }

    //修改成private  禁止外部存取
    private Account(String key) {
        this.key = key;
    }

    private void init() {
    // 從資料庫載入資料存入 cachedData,假設載入一個帳號大約需要10秒(不必實作載入的動作)
    }
}
