package com.atcaomin.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {

    public static void main(String[] args) {
        //进行测试
        //创建几个节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建一个链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //按顺序
        /*singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
*/
        //按编号
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.showList();
        System.out.println();
        //singleLinkedList.reverse();
        //singleLinkedList.reversePrint();
        //singleLinkedList.showList();
        singleLinkedList.print(singleLinkedList.getHead().next);


        //测试修改节点的代码
        /*HeroNode newHeroNode = new HeroNode(2,"小卢","玉麒麟");
        singleLinkedList.update(newHeroNode);
        singleLinkedList.showList();*/

        //测试删除的代码
        /*singleLinkedList.delete(3);
        singleLinkedList.showList();*/

        //测试单链表的有效节点个数
        //System.out.println(singleLinkedList.getLength());
    }

}

//定义链表

class SingleLinkedList {
    //定义一个头节点，不含具体信息
    HeroNode head = new HeroNode(0,null,null);

    //添加节点到链表
    //这种添加方式不考虑编号顺序，直接加载链表尾部
    public void add(HeroNode heroNode) {
        HeroNode cur = head;
        while (true) {
            //判断cur是否是最后一个节点
            if(cur.next == null) {
                cur.next = heroNode;
                break;
            }
            cur = cur.next;
        }
    }

    //获取头节点
    public HeroNode getHead() {
        return head;
    }

    //添加节点到链表
    //这种添加方式考虑编号顺序
    public void addByOrder(HeroNode heroNode) {
        HeroNode cur = head;
        //设置一个flag，初始为false，确定当前编号在链表中是否已经存在（需求就是链表中存在序号相同的就不需要添加）
        boolean flag = false;
        while (true) {
            //需要插入的位置在链表末尾
            if(cur.next == null) {//
                break;
            }else if(cur.next.id > heroNode.id) {
                //需要在cur后面插入节点
                break;
            } else if (cur.id == heroNode.id) {
                //当前链表已经有这个节点，不需要再添加
                flag = true;
            }
            cur = cur.next;
        }
        if(flag) {
            System.out.printf("编号%d已经存在",heroNode.id);
            return;
        }
        //添加节点
        heroNode.next = cur.next;
        cur.next = heroNode;
    }
    //修改链表的信息，即根据id来修改
    public void update(HeroNode newHeroNode) {
        HeroNode cur = head;
        boolean flag = false;
        while (true) {
            //先判断是否为空
            if(isEmpty()) {
                System.out.println("链表为空");
            }
            //没有找到对应的编号id
            if(cur == null) {
                break;
            }
            if(cur.id == newHeroNode.id) {
                flag = true;
                break;
            }
            cur = cur.next;
        }
        //根据flag判断是否找到要修改的节点
        if(flag) {
            cur.name = newHeroNode.name;
            cur.nickName = newHeroNode.nickName;
        }else {
            System.out.printf("没有找到编号为%d的节点，无法修改",newHeroNode.id);
        }
    }

    //删除节点
    public void delete(int id) {
        HeroNode cur = head;
        boolean flag = false;
        while (true) {
            if (cur.next == null) {//已经到链表最后了
                break;
            }
            if(cur.next.id == id) {//找到待删除的前一个节点cur
                flag = true;
                break;
            }
            cur = cur.next;
        }
        if(flag) {
            cur.next = cur.next.next;
        }else {
            System.out.printf("要删除的节点%d不存在",id);
        }
    }

    //显示链表
    public void showList() {
        HeroNode cur = head.next;
        while (true) {
            if(cur == null) {
                break;
            }
            System.out.println(cur);
            cur = cur.next;
        }
    }

    //获取单链表的节点个数（如果是带头节点的链表，不统计头节点）
    public int getLength() {
        if(head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null){
            length++;
            cur = cur.next;
        }
        return length;
    }

    //翻转链表
    public void reverse() {
        if(head.next == null || head.next.next == null) {//链表为空或者只有一个节点
            return;
        }
        HeroNode reverseHead = new HeroNode(0,null,null);
        HeroNode cur = head.next;
        HeroNode next = null;
        while(cur != null) {
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        head.next = reverseHead.next;
    }

    //判断链表是否为空
    public boolean isEmpty() {
        return head.next == null;
    }

    //逆序打印链表
    //使用栈
    public void reversePrint() {
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (cur != null) {
            stack.push(cur);
            cur =cur.next;
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
    //使用递归
    public void print(HeroNode cur) {
        if(cur == null) {
            return;
        }
        print(cur.next);
        System.out.println(cur);
    }
}

 //定义节点
class HeroNode {
    public int id;
    public String name;
    public String nickName;
    HeroNode next = null;

     public HeroNode(int id, String name, String nickName) {
         this.id = id;
         this.name = name;
         this.nickName = nickName;
     }

     @Override
     public String toString() {
         return "HeroNode{" +
                 "id=" + id +
                 ", name='" + name + '\'' +
                 ", nickName='" + nickName + '\'' +
                 '}';
     }
 }