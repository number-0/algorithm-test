package com.shl.algorithm;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 * LRU：自定义双向链表 + HashMap
 * @author songhengliang
 * @date 2021/1/12
 */
@Setter
@Getter
public class LRUCache2<K, V> {
    @Setter
    @Getter
    private static class Node<K, V> {
        private K key;
        private V value;

        private Node pre;
        private Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    @Setter
    @Getter
    private static class DoubleLinkedList<K, V> {
        private Node<K, V> head;
        private Node<K, V> tail;

        public void addLast(Node<K, V> node) {
            //首次添加
            if (Objects.isNull(head)) {
                this.head = node;
                this.tail = node;
                return;
            }


            //非首次添加

            //处理之前的尾节点
            this.tail.next = node;

            //处理当前节点
            node.pre = tail;

            //处理尾节点
            this.tail = node;
        }

        public Node<K, V> remove(Node<K, V> node) {
            Node<K, V> preNode = node.pre;
            Node<K, V> nextNode = node.next;

            //断开与前一个节点的连接、断开与后一个节点的连接


            //是否是头节点
            if (Objects.equals(node, head)) {
                this.head = node.next;
                if (Objects.nonNull(nextNode)) {
                    nextNode.pre = null;
                }
            } else if (Objects.equals(node, tail)) {
                //是否是尾节点
                preNode.next = null;
                this.tail = preNode;
            } else {
                //中间节点
                preNode.next = nextNode;
                nextNode.pre = preNode;
            }

            return node;
        }

        public Node<K, V> removeFirst() {
            return remove(this.head);
        }
    }

    private int capacity;
    private HashMap<K, Node<K, V>> map = new HashMap<>();
    private DoubleLinkedList<K, V> list = new DoubleLinkedList<>();

    private LRUCache2(int capacity) {
        this.capacity = capacity;
    }

   public static LRUCache2 newInstance(int capacity) {
        return new LRUCache2(capacity);
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
        Node node = null;
        if (Objects.isNull(node = this.list.head)) {
            return "";
        }

        StringBuilder text = new StringBuilder();
        while (true) {
            text.append(node.toString()).append(", ");
            node = node.next;
            if (Objects.isNull(node)) {
                break;
            }
        }
        return text.toString();
    }

    public static void main(String[] args) {
        LRUCache2<Integer, Integer> lruCache = LRUCache2.newInstance(3);
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




//---------------- 实现思路 ------------------------------------------------
//    public V get(K key) {
//        /*
//        if (key不存在) {
//            return null;
//        }
//
//        //存在
//        链表：将数据放到链表的尾部
//        return value;
//         */
//
//        return null;
//    }
//
//    public void put(K key, V value) {
//
//        /*
//        if(key已存在) {
//            链表：将原数据删除，将新数据放到链表的尾部
//            hashMap：替换hashMap中的值
//
//            return ;
//        }
//
//
//        //数据不存在
//        if (cache已满) {
//            链表：删除链表头部的数据
//            hashMap：删除hashMap中的数据
//        }
//        链表：将数据添加到链表尾部
//        hashMap：添加数据到hashMap
//         */
//
//    }