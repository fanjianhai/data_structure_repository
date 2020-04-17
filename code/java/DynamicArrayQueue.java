package com.xiaofan.test;
/**
 * 动态列表
 */
public class DynamicArrayQueue {
    // 数组： items
    private String[] items;
    // 数组大小：capacity
    private int capacity = 0;
    // 对头下标
    private int head = 0;
    // 队尾下标
    private int tail = 0;

    public DynamicArrayQueue(int capacity) {
        items = new String[capacity];
        this.capacity = capacity;
    }
    // 入队
    public boolean enqueue(String item) {
        // tail == n 表示队列末尾没有空间了
        if (tail == capacity) {
            // tail ==n && head==0，表示整个队列都占满了
            if(head == 0) return false;
            // 数据搬移
            for(int i = head; i < tail; i++) {
                items[i-head] = items[i];
            }
            // 搬移玩之后重新更新head和tail
            tail -= head;
            head = 0;
        }
        items [tail] = item;
        tail ++;
        return true;

    }
    // 出队
    public String dequeue() {
        if (head == tail) return null;
        String ret = items[head];
        head ++;
        return ret;
    }

    public void printAll() {
        for (int i = head; i < tail; ++i) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DynamicArrayQueue queue = new DynamicArrayQueue(10);

        int i = 0;
        while (i < 10) {
            queue.enqueue(String.valueOf(i));
            i++;
        }
        queue.printAll();

        String ret = queue.dequeue();
        System.out.println(ret);
        queue.enqueue(String.valueOf(100));
        queue.printAll();
    }
}

