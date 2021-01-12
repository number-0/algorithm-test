package com.shl.algorithm;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * @author songhengliang
 * @date 2021/1/12
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private int size;

    /**
     * 移除最近最少使用的数据，移除的是链表头部的数据
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Entry<K, V> eldest) {
        return size() > size;
    }

    private LRUCache(int size) {
        //accessOrder = true，true按访问顺序、false按插入顺序
        super(size, 0.75f, true);
        this.size = size;
    }

    public static <K, V> LRUCache<K, V> newInstance(int size) {
        return new LRUCache<>(size);
    }

    @Override
    public V put(K key, V value) {
        super.put(key, value);
        return value;
    }

    @Override
    public V get(Object key) {
        return super.get(key);
    }

    public static void main(String[] args) {
        LRUCache<Integer, Integer> lruCache = LRUCache.newInstance(3);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        lruCache.put(4, 4);

        System.out.println(lruCache);

        lruCache.get(2);
        System.out.println(lruCache);

        lruCache.put(5, 5);
        System.out.println(lruCache);
    }
}
