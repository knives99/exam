package com.example.exam.cache;

import java.util.LinkedHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class AccountCache<K,V>  extends LinkedHashMap<K,V> {


    //cache 上限值
    private final int maxCapacity = 50;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private final Lock lock = new ReentrantLock();

    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
        return size() > maxCapacity;
    }


    @Override
    public V get(Object key) {
        try {
            //資料處理時上鎖
            lock.lock();
            return super.get(key);
        } finally {
            //資料處理完時解鎖
            lock.unlock();
        }
    }




}
