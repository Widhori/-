package com.atcaomin.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //创建几个人物
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        //添加节点
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        //显示链表
//        doubleLinkedList.showList();
//        System.out.println();

//        HeroNode2 newHeroNode = new HeroNode2(4,"公孙胜","入云龙");
//
//        doubleLinkedList.update(newHeroNode);
//        doubleLinkedList.showList();
//        System.out.println();

        doubleLinkedList.delete(2);
        doubleLinkedList.showList();
        System.out.println();

        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.showList();
        System.out.println();
    }
}

class DoubleLinkedList {
    //定义一个头节点，不含具体信息
    HeroNode2 head = new HeroNode2(0,null,null);

    //添加节点到链表
    //这种添加方式不考虑编号顺序，直接加在链表尾部
    public void add(HeroNode2 heroNode) {
        HeroNode2 cur = head;
        while (true) {
            //判断cur是否是最后一个节点
            if(cur.next == null) {
                cur.next = heroNode;
                heroNode.pre =cur;
                break;
            }
            cur = cur.next;
        }
    }

    //添加节点到链表
    //这种添加方式考虑编号顺序
    public void addByOrder(HeroNode2 heroNode) {
        HeroNode2 cur = head;
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
        heroNode.pre = cur;
    }

    //显示链表
    public void showList() {
        HeroNode2 cur = head.next;
        while (true) {
            if(cur == null) {
                break;
            }
            System.out.println(cur);
            cur = cur.next;
        }
    }

    //修改链表
    public void update(HeroNode2 newHeroNode) {
        HeroNode2 cur = head;
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
        HeroNode2 cur = head;
        boolean flag = false;
        while (true) {
            if (cur == null) {//已经到链表最后了
                break;
            }
            if(cur.id == id) {//找到待删除的前一个节点cur
                flag = true;
                break;
            }
            cur = cur.next;
        }
        if(flag) {
            cur.pre.next = cur.next;
            if(cur.next != null) {
                cur.next.pre = cur.pre;
            }
        }else {
            System.out.printf("要删除的节点%d不存在",id);
        }
    }

    //判断链表是否为空
    public boolean isEmpty() {
        return head.next == null;
    }
}

class HeroNode2 {
    public int id;
    public String name;
    public String nickName;
    HeroNode2 pre = null;
    HeroNode2 next = null;

    public HeroNode2(int id, String name, String nickName) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}