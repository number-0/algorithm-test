package com.shl.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import lombok.Getter;
import lombok.Setter;

/**
 * LRU：LinkedList双向链表 + HashMap
 * @author songhengliang
 * @date 2021/1/12
 */
public class LRUCache3 <K, V> {
    @Setter
    @Getter
    private static class Node<K, V> {
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    private int capacity;
    private HashMap<K, Node<K, V>> map = new HashMap<>();
    private LinkedList<Node<K, V>> list = new LinkedList<>();

    private LRUCache3(int capacity) {
        this.capacity = capacity;
    }

    public static LRUCache3 newInstance(int capacity) {
        return new LRUCache3(capacity);
    }

    public V get(K key) {
        //不存在
        if (!map.containsKey(key)) {
            return null;
        }

        //存在

        Node<K, V> node = map.get(key);
        //链表：将数据放到链表的尾部
        put(key, node.getValue());
        return node.getValue();
    }

    public void put(K key, V value) {
        Node<K, V> node = new Node<>(key, value);

        //已存在
        if (map.containsKey(key)) {
            //链表：将原数据删除，将新数据放到链表的尾部
            Node<K, V> oldNode = map.get(key);
            list.remove(oldNode);
            list.addLast(node);

            //hashMap：替换hashMap中的值
            map.put(key, node);

            return ;
        }


        //数据不存在

        //缓存已满
        if (map.size() == capacity) {
            //链表：删除链表头部的数据
            Node<K, V> headNode = list.removeFirst();

            //hashMap：删除hashMap中的数据
            map.remove(headNode.getKey());
        }
        //链表：将数据添加到链表尾部
        list.addLast(node);
        //hashMap：添加数据到hashMap
        map.put(key, node);
    }

    @Override
    public String toString() {

        StringBuilder text = new StringBuilder();
        for (Node<K, V> node : list) {
            text.append(node.toString()).append(", ");
        }
        return text.toString();
    }

    public static void main(String[] args) {
        LRUCache3<Integer, Integer> lruCache = newInstance(3);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        lruCache.put(4, 4);
        System.out.println(lruCache);

        lruCache.get(1);
        System.out.println(lruCache);

        lruCache.get(2);
        System.out.println(lruCache);

        lruCache.put(5, 5);
        System.out.println(lruCache);
    }
}
