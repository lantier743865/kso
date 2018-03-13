package android.com.bugmonitor.queue;


import java.util.Arrays;

/**
 * Created by wuxiaolong on 2018/3/13.
 */

public class SequenceQueue<T>  {
    private int DEFAULT_SIZE = 30;
    // 保存数组的长度
    private int capacity;
    // 定义一个数组用于保存顺序队列的元素
    private Object[] elementData;
    // 保存顺序队列中元素的当前个数
    private int front = 0;
    private int rear = 0;

    // 以默认数组长度创建空顺序队列
    public SequenceQueue() {

        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];

    }

    // 以一个初始化元素来创建顺序队列
    public SequenceQueue(T element) {

        this();
        elementData[0] = element;
        rear++;

    }

    /**
     * 以指定长度的数组来创建顺序线性表
     *
     * @param element  指定顺序队列中第一个元素
     * @param initSize 指定顺序队列底层数组的长度
     */
    public SequenceQueue(T element, int initSize) {

        this.capacity = initSize;
        elementData = new Object[capacity];
        elementData[0] = element;
        rear++;
    }

    /**
     * 获取顺序队列的大小
     *
     * @return 顺序队列的大小值
     */
    public int length() {

        return rear - front;

    }

    /**
     * 插入队列
     *
     * @param element 入队列的元素
     */
    public void add(T element) {

        if (rear > capacity - 1) {
            throw new IndexOutOfBoundsException("队列已满异常");
        }
        elementData[rear++] = element;

    }

    /**
     * 移除队列
     *
     * @return 出队列的元素
     */
    public T remove() {

        if (empty()) {
            throw new IndexOutOfBoundsException("空队列异常");
        }

        // 保留队列的rear端的元素的值
        T oldValue = (T) elementData[front];
        // 释放队列顶元素
        elementData[front++] = null;
        return oldValue;

    }

    // 返回队列顶元素，但不删除队列顶元素
    public T element() {

        if (empty()) {
            throw new IndexOutOfBoundsException("空队列异常");
        }
        return (T) elementData[front];

    }

    // 判断顺序队列是否为空
    public boolean empty() {

        return rear == front;

    }

    // 清空顺序队列
    public void clear() {

        // 将底层数组所有元素赋值为null
        Arrays.fill(elementData, null);
        front = 0;
        rear = 0;

    }

    public String toString() {

        if (empty()) {

            return "[]";

        } else {

            StringBuilder sb = new StringBuilder("[");
            for (int i = front; i < rear; i++) {
                sb.append(elementData[i].toString() + ", ");
            }
            int len = sb.length();
            return sb.delete(len - 2, len).append("]").toString();
        }

    }

    public String flushString() {
        if (empty()) {

            return "";

        } else {

            StringBuilder sb = new StringBuilder();
            for (int i = front; i < rear; i++) {
                sb.append(elementData[i].toString() + "\r\n ");
            }

            return sb.toString();
        }
    }
}
