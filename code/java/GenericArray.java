public class GenericArray<T> {
    private T[] data;
    private int size;

    public GenericArray(int capacity) {
        this.data = (T[]) new Object[capacity];
        this.size = 0;
    }

    public GenericArray() {
        this(10);
    }
    // 数组容量
    public int getCapacity() {
        return this.data.length;
    }
    // 当前元素个数
    public int count() {
        return this.size;
    }
    // 判断数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }
    // 修改index位置的元素
    public void set(int index, T e) {
        checkIndex(index);
        data[index] = e;
    }
    // 获取对应index位置的元素
    public T get(int index) {
        checkIndex(index);
        return data[index];
    }
    // 查看数组是否包含元素
    public boolean contains(T e) {
        for (int i = 0;i < size; i ++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }
    // 获取对应元素的下标，未找到， 返回-1
    public int find(T e) {
        for ( int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }
    // 在index位置， 插入元素e，时间复杂度为O（m+n）
    public void add(int index, T e) {
        checkIndex(index);
        // 如果当前元素个数等于数组容量
        if(size == data.length) {
            resize(2 * data.length);
        }
        for(int i = size - 1; i >= index; i --) {
            data[i+1] = data[i];
        }

        data[index] = e;
        size ++;
    }
    // 向数组头插入元素
    public void addFirst(T e) {
        add(0, e);
    }
    // 向数组头插入元素
    public void addLast(T e) {
        add(size, e);
    }
    // 删除index位置的元素，并返回
    public T remove(int index) {
        checkIndexForRemove(index);

        T ret = data[index];
        for (int i = index + 1; i < size; i ++) {
            data[i - 1] = data[i];
        }
        size --;
        data[size] = null;
        // 缩容
        if(size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return ret;

    }
    // 删除第一个元素
    public  T removeFirst() {
        return remove(0);
    }
    // 从数组中删除指定元素
    public void removeElement(T e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    // 删除末尾元素
    public T removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array size = %d, capacity = %d \n", size, data.length));
        builder.append('[');
        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if (i != size - 1) {
                builder.append(", ");
            }
        }
        builder.append(']');
        return builder.toString();
    }

    // 扩容方法，时间复杂度O(n)
    private void resize(int capacity) {
        T[] newData = (T[]) new Object[capacity];

        for (int i = 0;i < size;i ++) {
            newData[i] = data[i];
        }
        data = newData;

    }

    private void checkIndex(int index) {
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed! Require index >=0 and index <= size.");
        }
    }

    private void checkIndexForRemove(int index) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed! Require index >=0 and index < size.");
        }
    }

    public static void main(String[] args) {
        GenericArray<String> arr = new GenericArray<String>();
        arr.add(0, "100");
        arr.add(0, "200");
        arr.add(0, "300");
        arr.add(0, "400");
        arr.addFirst("e");
        System.out.println(arr);
    }

}
