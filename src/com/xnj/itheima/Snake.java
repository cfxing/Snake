package com.xnj.itheima;

import java.util.LinkedList;

/**
 * LinkedList 集合存储 Node 节点
 *
 * @author chen xuanyi
 * @Date 2020/4/23 17:30
 */
public class Snake {
    //蛇的身体
    private LinkedList<Node> body;
    //蛇的默认运动方向
    Direction direction = Direction.LEFT;

    //判断蛇是否活着
    private boolean isLiving = true;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public LinkedList<Node> getBody() {
        return body;
    }

    public void setBody(LinkedList<Node> body) {
        this.body = body;
    }

    public Snake() {
        //蛇身初始化
        initSnake();

    }

    //初始化蛇
    private void initSnake() {
        body = new LinkedList<>();
        Node node = new Node();
        node.random();
        body.add(node);
        body.add(new Node(node.getX()+1,node.getY()));
        body.add(new Node(node.getX()+2,node.getY()));
        body.add(new Node(node.getX()+3,node.getY()));
        body.add(new Node(node.getX()+4,node.getY()));
        body.add(new Node(node.getX()+5,node.getY()));


    }

    //沿着蛇头方向移动
    //蛇头向前一个位置
    //蛇尾删除一个位置
    public void move(){

//        if (isLiving){

            //获取蛇头
            Node head = body.getFirst();
            switch (direction){
                case UP:
                    //在蛇头的上边添加一个节点
                    body.addFirst(new Node(head.getX(), head.getY() - 1));
                    break;
                case DOWN:
                    body.addFirst(new Node(head.getX(), head.getY() + 1));
                    break;
                case LEFT:
                    body.addFirst(new Node(head.getX() - 1, head.getY()));
                    break;
                case RIGHT:
                    body.addFirst(new Node(head.getX() + 1, head.getY()));
                    break;
            }
            //删除最后的节点
            body.removeLast();

            //判断蛇是否撞墙
            if (head.getX() <= 0){
                direction = Direction.RIGHT;
            }else if (head.getY() <= 0){
                direction = Direction.UP;
            }else if (head.getX() >= 40){
                direction = Direction.DOWN;
            }else if (head.getY() >= 40)
            {
                direction = Direction.LEFT;
            }
            //判断蛇是否撞到自己
            for (int i = 1; i < body.size(); i++) {
                Node node = body.get(i);
                if (head.getX() == node.getX() && head.getY() == node.getY()){
                    isLiving = false;
                    System.out.println("你的蛇死掉了");
                }
            }

//        }

    }

    //吃食物，沿着蛇头方向添加节点
    public void eat(Node food) {
        //获取蛇头
        Node head = body.getFirst();
        switch (direction){
            case UP:
                //在蛇头的上边添加一个节点
                body.addFirst(new Node(head.getX(), head.getY() - 1));
                break;
            case DOWN:
                body.addFirst(new Node(head.getX(), head.getY() + 1));
                break;
            case LEFT:
                body.addFirst(new Node(head.getX() - 1, head.getY()));
                break;
            case RIGHT:
                body.addFirst(new Node(head.getX() + 1, head.getY()));
                break;
        }
    }
}
