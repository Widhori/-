package com.atcaomin.linkedlist;

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