package org.example.log_utils;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Буфер для хранения логов на основе очереди
 */
public class LogBuffer<T> {
    private final Deque<T> deque;
    private final int capacity;

    public LogBuffer(int capacity) {
        this.capacity = capacity;
        this.deque = new ArrayDeque<>(capacity);
    }

    public void add(T element) {
        if (deque.size() == capacity) {
            deque.removeFirst();
        }
        deque.addLast(element);
    }

    public T get() {
        return deque.pollFirst();
    }

    public T peek() {
        return deque.peekFirst();
    }

    public boolean isEmpty() {
        return deque.isEmpty();
    }

    public boolean isFull() {
        return deque.size() == capacity;
    }

    public int size() {
        return deque.size();
    }

    public void clear() {
        deque.clear();
    }
}