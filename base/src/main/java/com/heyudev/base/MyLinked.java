package com.heyudev.base;

import java.util.LinkedList;

/**
 * @author heyudev
 * @date 2021/06/09
 */
public class MyLinked {
    public static void main(String[] args) {
        Node node1 = new Node(null, 1);
        Node node2 = new Node(null, 2);
        Node node3 = new Node(null, 3);
        Node node4 = new Node(null, 4);
        Node node5 = new Node(null, 5);
        Node node6 = new Node(null, 6);
        Node node7 = new Node(null, 7);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        Node node = node1;
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }


        Node tail = null;
        Node current = node1;
        while (current != null) {
            Node next = current.next;
            current.next = tail;
            tail = current;
            current = next;
        }
        Node n1 = node7;
        while (n1 != null) {
            System.out.println(n1.value);
            n1 = n1.next;
        }
    }

    static class Node {
        Node next;
        int value;

        public Node(Node next, int value) {
            this.next = next;
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
