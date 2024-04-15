package com.atcaomin.queue;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        //测试一下队列
        //创建一个队列
        ArrayQueue arrayQueue = new ArrayQueue(3);
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
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数:");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.println(res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
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

//使用数组模拟实现队列-编写一个ArrayQueue类
class ArrayQueue {
    private int maxSize;//队列最大容量
    private int rear;//队尾
    private int front;//对头
    private int[] array;//用于存放数据，模拟队列

    //创建构造方法
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        array = new int[maxSize];
        rear = -1;
        front = -1;
    }

    //判断队列是否为满
    public boolean isFull() {
        return rear == maxSize;
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
            rear++;
            array[rear] = n;
        }
    }

    //获取数据——出队列
    public int getQueue() {
       if(isEmpty()) {
           throw new RuntimeException("队列为空，无法获取数据");
       }else {
           front++;
           return array[front];
       }
    }

    //显示队列
    public void showQueue() {
        if(isEmpty()) {
            System.out.println("队列为空，无法显示");
        }else {
            for (int i = front + 1; i <= rear; i++) {
                System.out.printf("array[%d] = %d\n",i,array[i]);
            }
        }
    }

    //显示对列的头数据是多少，注意这不是去除数据
    public int headQueue() {
        if(isEmpty()) {
            throw new RuntimeException("队列为空");
        }else {
            return array[front + 1];
        }
    }
}
