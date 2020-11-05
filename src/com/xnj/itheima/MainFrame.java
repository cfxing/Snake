package com.xnj.itheima;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.TimerTask;

/**
 * @author chen xuanyi
 * @Date 2020/4/23 16:51
 */
public class MainFrame extends JFrame {
    private Snake snake;
    JPanel jPanel;//游戏棋盘
    private Timer timer;//定时器,在指定的时间调用蛇移动的方法
    private Node food;//食物


    public MainFrame() throws HeadlessException {
        //初始化窗体参数
        iniFrame();
        //初始化游戏棋盘
        initGamePanel();
        //初始化蛇
        initSnake();
        //初始化定时器
        initTimer();
        //设置键盘监听，让蛇移动
        setKeyListener();
        //初始化食物
        initFood();
    }

    private void initFood() {
        food = new Node();
        food.random();


    }

    private void setKeyListener() {
        addKeyListener(new KeyAdapter() {
            //当键盘按下时，会执行此方法
            @Override
            public void keyPressed(KeyEvent e) {
                //键盘中每一个键都有一个编号
                switch (e.getKeyCode()){

                    case KeyEvent.VK_UP://上键
                        if (snake.getDirection() != Direction.DOWN){
                            snake.setDirection(Direction.UP);
                        }
                        break;
                    case KeyEvent.VK_DOWN://下键
                        if (snake.getDirection() != Direction.UP){
                            snake.setDirection(Direction.DOWN);
                        }
                        break;
                    case KeyEvent.VK_LEFT://左键
                        if (snake.getDirection() != Direction.RIGHT){
                            snake.setDirection(Direction.LEFT);
                        }
                        break;
                    case KeyEvent.VK_RIGHT://右键
                        if (snake.getDirection() != Direction.LEFT){
                            snake.setDirection(Direction.RIGHT);
                        }
                        break;
                    case KeyEvent.VK_SPACE://空格
                        break;

                }
            }
        });

    }

    private void initTimer() {
        timer = new Timer();
        //初始化定时任务
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                snake.move();
                //判断蛇头是否和食物重合
                Node head = snake.getBody().getFirst();
                if (head.getX() == food.getX() && head.getY() == food.getY()){
                    snake.eat(food);
                    food.random();
                }
                //重绘游戏棋盘
                jPanel.repaint();
            }
        };
        //每100ms 执行一次定时器
        if (snake.getBody().size() < 10){
            timer.scheduleAtFixedRate(timerTask,10, 200);
        }else if (snake.getBody().size() >10 && snake.getBody().size() <15){
            timer.scheduleAtFixedRate(timerTask, 10,100);
        }else {
            timer.scheduleAtFixedRate(timerTask, 10,70);
        }
    }

    private void initSnake() {
        snake = new Snake();
    }

    private void initGamePanel() {
        jPanel = new JPanel(){
            //绘制游戏棋盘的内容
            @Override
            public void paint(Graphics g) {
                //清空棋盘
                g.clearRect(0,0,600,600);

                //Graphics g :可以看作是画笔，提供了很多方法，可以绘制基本图行（直线，矩形）
                //绘制40条横线
                for (int i = 0; i <= 40; i++) {
                    g.drawLine(0,i*15,600,i*15);
                }

                //绘制40条竖线
                for (int i = 0; i <= 40; i++) {
                    g.drawLine(i*15, 0, i*15, 600 );
                }

                //绘制开始提示语
//                g.setFont(new Font("arial", Font.BOLD, 40));
//                g.drawString("Press Space to start", 100,300);

                //绘制蛇
                LinkedList<Node> body = snake.getBody();
                for (Node node : body){
                    g.fillRect(node.getX()*15, node.getY()*15,15,15);//填充颜色
                }

                //绘制食物
                g.fillRect(food.getX()*15, food.getY()*15, 15, 15);
            }
        };

        //把棋盘添加到窗体
        add(jPanel);
    }

    private void iniFrame() {
        //设置窗体宽高
        setSize(610, 640);
        //设置窗体位置
        setLocation(400,80);//x: 左上点举例屏幕左边的举例； y: 左上点距离右边的距离
        //设置关闭按钮的作用(退出程序)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗体大小不可变
        setResizable(false);
    }

    public static void main(String[] args) {
        //创建窗体对象
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }

}
