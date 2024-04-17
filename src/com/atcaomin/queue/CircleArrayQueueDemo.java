package com.atcaomin.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //测试模拟环形队列是否正确
        CircleArray circleArray = new CircleArray(4);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s:显示队列");
            System.out.println("a:添加数据到队列");
            System.out.println("g:从队列取出数据");
            System.out.println("h:查看队列头部数据");
            System.out.println("e:退出程序");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    circleArray.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数:");
                    int value = scanner.nextInt();
                    circleArray.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = circleArray.getQueue();
                        System.out.println(res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = circleArray.headQueue();
                        System.out.println(res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

class CircleArray {
    private int maxSize;//队列最大容量
    private int rear;//队列最后一个元素的后一个位置
    private int front;//指向队列的第一个元素
    private int[] array;//用于存放数据，模拟队列

    //创建构造方法
    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        array = new int[maxSize];
        rear = 0;
        front = 0;
    }
    //判断队列是否为满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //添加数据——入队列
    public void addQueue(int n) {
        if(isFull()) {
            System.out.println("队列已满，无法添加");
        }else {
            array[rear] = n;
            rear = (rear + 1) % maxSize;
        }
    }

    //获取数据——出队列
    public int getQueue() {
        if(isEmpty()) {
            throw new RuntimeException("队列为空，无法获取数据");
        }else {
            int value = array[front];
            front = (front + 1) % maxSize;
            return value;
        }
    }

    //显示队列
    public void showQueue() {
        if(isEmpty()) {
            System.out.println("队列为空，无法显示");
        }else {
            for (int i = front; i < front + size(); i++) {
                System.out.printf("array[%d] = %d\n",i % maxSize,array[i % maxSize]);
            }
        }
    }

    //获取队列元素个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    //显示对列的头数据是多少，注意这不是去除数据
    public int headQueue() {
        if(isEmpty()) {
            throw new RuntimeException("队列为空");
        }else {
            return array[front];
        }
    }

}
