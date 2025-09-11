package assignement1;

import java.util.concurrent.locks.ReentrantLock;

/* You are allowed to 1. add modifiers to fields and method signatures and 2. add code at the marked places, including removing the following return */
public class LinkedQueue<T> {
    private Node<T> head;
    private Node<T> tail;
    private ReentrantLock lock = new ReentrantLock(true); // make the lock fair

    synchronized public T find(T t) {
        // lock.lock();
        // l try {
        Node<T> tmp = head;
        while (tmp != null) {
            if (t == tmp.content) {
                return t; /* return true??? */
            }
            tmp = tmp.next;
        }
        return null; /* didnt find :( */
        // } finally {
        // lock.unlock();
        // }
    }

    public void insert(T t) {
        lock.lock();
        try {
            Node<T> newNode = new Node<>(t);
            if (tail == null) {
                head = newNode;
            } else {
                tail.next = newNode;
            }
            tail = newNode;
        } finally {
            lock.unlock();
        }
    }

    synchronized public T delfront() {
        // lock.lock();
        // try {
        if (head != null) {
            if (head == tail) {
                tail = null;
            }
            Node<T> temp = head;
            head = head.next;
            return temp.content;
        }
        return null;
        // } finally {
        // lock.unlock();
        // }
    }
}
