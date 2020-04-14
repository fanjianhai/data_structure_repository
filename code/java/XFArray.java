package com.xiaofan.test;

/**
 * @author xiaofan
 * 1.数组的插入、删除、按照下标随机访问操作
 * 2.数组中的数据时int类型的
 */
public class XFArray {
    // 定义整形数据data保存类型
    public int data[];
    // 定义数组中元素实际个数
    private int size;

    // 构造方法定义数组大小
    public XFArray(int capacity) {
        this.data = new int[capacity];
        this.size = 0;
    }

    // 根据索引找到指定的元素并返回
    public int find(int index) {
        if(index < 0 || index >= size) return -1;
        return data[index];
    }

    // 插入元素，头部插入，尾部插入
    public boolean insert(int index, int value) {
        // 数组空间已满
        if(size == data.length) {
            System.out.println("没有可以插入的位置");
            return false;
        }
        // 位置不合法
        if(index < 0 || index > size) {
            System.out.println("位置不合法");
            return false;
        }
        // 位置合法
        for(int i = size; i > index; --i) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        ++size;
        return true;
    }

    // 根据索引删除数组中的元素
    public boolean delete(int index) {
        if(index < 0 || index >= size) return false;
        for(int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        --size;
        return true;
    }

    // 打印数组
    public void printAll() {
        for (int i = 0; i < size; ++i) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        XFArray array = new XFArray(5);
        array.printAll();
        array.insert(0, 3);
        array.insert(0, 4);
        array.insert(1, 5);
        array.insert(3, 9);
        array.insert(3, 10);
        int res = array.find(5);
        System.out.println(res);
        array.printAll();
    }
}
