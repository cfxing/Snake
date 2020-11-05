package com.xnj.itheima;

import java.util.Random;

/**
 * 节点类：每一条蛇都是由若干节点构成，每一个节点都有确定的横纵坐标
 *
 * @author chen xuanyi
 * @Date 2020/4/23 17:25
 */
public class Node {
    private int x;
    private int y;

    public Node() {
    }

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    //随机生成位置
    public void random(){
        //创建 random对象
        Random random = new Random();
        //随机横坐标
        this.x = random.nextInt(40);
        //随机生成纵坐标
        this.y = random.nextInt(40);
    }

}
