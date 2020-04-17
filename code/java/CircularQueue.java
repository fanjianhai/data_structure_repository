package com.xiaofan.test;

/**
 * 循环队列
 */
public class CircularQueue {
    // 数组： items
    private String[] items;
    // 数组大小：capacity
    private int capacity = 0;
    // 队头下标
    private int head = 0;
    // 队尾下标
    private int tail = 0;

    public CircularQueue(int capacity) {
        items = new String[capacity];
        this.capacity = capacity;
    }
    // 入队
    public boolean enqueue(String item) {
        if ((tail + 1) % capacity == head) return false;
        items[tail] = item;
        tail = (tail + 1) % capacity;
        return true;

    }
    // 出队
    public String dequeue() {
        if (head == tail) return null;
        String ret = items[head];
        head = (head + 1) % capacity;
        return ret;
    }

    public void printAll() {
        if (capacity == 0) return;
        for (int i = head; i % capacity != tail;i = (i + 1) % capacity) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(10);
        for (int i = 0;i < 10;i ++) {
            queue.enqueue(String.valueOf(i));
        }
        queue.printAll();

        String ret = queue.dequeue();
        System.out.println(ret);
        queue.enqueue(String.valueOf(100));
        queue.printAll();
    }
}
