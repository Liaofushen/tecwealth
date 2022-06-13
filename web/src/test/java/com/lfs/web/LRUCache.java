package com.lfs.web;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    int cap;
    Map<String, Node> map;
    Node head;
    Node tail;

    LRUCache(int cap) {
        this.cap = cap;
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    void add(Node node) {
        if (map.containsKey(node.key)) {
            Node node1 = map.get(node.key);
        } else {

            node.next = head.next;
            head.next = node;
        }

    }

    void peek() {

    }

    void removeNode(Node node) {
        Node pre = node.pre;
        pre.next = node.next;
        node.next.pre = pre;
    }

    static class Node {

        String key;
        int val;
        Node pre;
        Node next;
    }
}
