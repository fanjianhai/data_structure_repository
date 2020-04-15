import java.util.HashMap;
import java.util.Map;

/**
 * 基于数组实现LRU缓存
 * 1. 空间复杂度为O(n)
 * 2. 时间复杂度为O(n)
 * 3. 不支持null的缓存
 */
public class LRUBasedArray<T> {
    // 把1左移了3位  1 x 2^3
    private static final int DEFAULT_CAPACITY = (1 << 3);
    // 数组大小
    private int size;
    // 数组内容
    private T[] value;
    // 缓存集合
    private Map<T, Integer> holder;

    public LRUBasedArray() {
        this(DEFAULT_CAPACITY);
    }

    public LRUBasedArray(int capacity) {
        this.value = (T[]) new Object[capacity];
        this.size = 0;
        this.holder = new HashMap<T, Integer>(capacity);
    }

    /**
     * 模拟访问某个值
     * @param object
     */
    public void offer(T object) {
        if(object == null) {
            throw new IllegalArgumentException("该缓存容器不支持null!");
        }
        Integer index = holder.get(object);
        if(index == null) {
            if (isFull()) {
                removeAndCache(object);
            }else {
                cache(object, size);
            }
        } else {
            update(index);
        }
    }

    /**
     * 缓存满的情况，踢出最后一个元素后，再缓存到数组的头部
     * @param object
     */
    public void removeAndCache(T object) {
        T key = value[--size];
        holder.remove(key);
        cache(object, size);
    }

    /**
     * 若缓存中有指定的值，则更新位置
     * @param end
     */
    public void update(int end) {
        T target = value[end];
        rightShift(end);
        value[0] = target;
        holder.put(target, 0);
    }

    /**
     * 缓存数据到头部，但要先右移
     * @param object
     * @param end
     */
    public void cache(T object, int end) {
        rightShift(end);
        value[0] = object;
        holder.put(object, 0);
        size ++;
    }

    /**
     * end左边的数据统一右移一位
     * @param end
     */
    private void rightShift(int end) {
        for (int i = end -1; i >= 0; i--) {
            value[i + 1] = value[i];
            holder.put(value[i], i + 1);
        }
    }

    public boolean isContain(T object) {
        return holder.containsKey(object);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == value.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < size; i ++) {
            sb.append(value[i]).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//        testWithException();
//        testDefaultConstructor();
        testSpecifiedConstructor(4);
    }

    private static void testWithException() {
        LRUBasedArray<Integer>lru = new LRUBasedArray<>();
        lru.offer(null);
    }

    public static void testDefaultConstructor() {
        System.out.println("======无参测试========");
        LRUBasedArray<Integer> lru = new LRUBasedArray<Integer>();
        lru.offer(1);
        lru.offer(2);
        lru.offer(3);
        lru.offer(4);
        lru.offer(2);
        System.out.println(lru);
        lru.offer(6);
        lru.offer(7);
        lru.offer(8);
        lru.offer(9);
        System.out.println(lru);
    }

    public static void testSpecifiedConstructor(int capacity) {
        System.out.println("======有参测试========");
        LRUBasedArray<Integer> lru = new LRUBasedArray<Integer>(capacity);
        lru.offer(1);
        System.out.println(lru);
        lru.offer(2);
        System.out.println(lru);
        lru.offer(3);
        System.out.println(lru);
        lru.offer(4);
        System.out.println(lru);
        lru.offer(2);
        System.out.println(lru);
        lru.offer(4);
        System.out.println(lru);
        lru.offer(7);
        System.out.println(lru);
        lru.offer(1);
        System.out.println(lru);
        lru.offer(2);
        System.out.println(lru);
    }


}