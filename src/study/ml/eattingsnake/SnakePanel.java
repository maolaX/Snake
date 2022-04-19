package study.ml.eattingsnake;
/*
   @ClassName: SnakePanel
   @Author: Maola
   @Description:
   @Version: 1.0.0
*/

import javafx.event.Event;
import javafx.event.EventHandler;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.*;

public class SnakePanel extends JPanel implements KeyListener, ActionListener {

    private final int STATE_FAIL = -1;
    private final int STATE_INIT = 0;
    private final int STATE_RUNNING = 1;
    private final int STATE_PAUSE = 2;
    private final int STATE_SUC = 3;


    private int state;
    private int newWidth;
    private int newHeight;
    private Snake snake;
    private SnakeMap snakeMap;
    private int frameRate;
    private Timer timer;
    private int blockPixel;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    SnakePanel(){
        //参数加载
        Properties properties = SnakeUtil.loadProperties("resource/application.properties");
        newWidth = Integer.parseInt((String) properties.get("snake.map.width"));
        newHeight = Integer.parseInt((String) properties.get("snake.map.height"));
        blockPixel = Integer.parseInt((String) properties.get("snake.block.pixels"));
        int flushTime = Integer.parseInt((String) properties.get("snake.flush.flushtime"));
        timer = new Timer(flushTime,this);
        this.addKeyListener(this);
        this.setFocusable(true);
        init();
    }

    private void init(){
        //初始化蛇
        snake = new Snake(new int[]{newWidth/2, newHeight/2});
        //初始化map
        snakeMap = new SnakeMap(newWidth - 1,newHeight - 1);
        snakeMap.setSnake(snake);
        state = STATE_INIT;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 清屏
        //画背景
        this.setBackground(Color.BLACK);
//        g.fillRect(0,0,newWidth * blockPixel, newHeight * blockPixel);
        // 画头部
        int headerHeight = 20;

        //画蛇
        g.setColor(Color.gray);
        g.fillRect(0,headerHeight + 5,newWidth * blockPixel,newHeight * blockPixel);
        snake.drawSnake(this,g,blockPixel,blockPixel);
        //画食物
        SnakeImg.food.paintIcon(this,g,snakeMap.getFoodPos()[0] * blockPixel,snakeMap.getFoodPos()[1] * blockPixel + blockPixel);
        //画状态提示
        g.setColor(Color.WHITE);
        g.setFont(new Font("楷体",Font.BOLD,30));
        String hint = null;
        switch (state){
            case STATE_INIT:
                 hint = "请按空格开始游戏";
                 break;
            case STATE_PAUSE:
                hint = "游戏已暂停，按空格继续";
                break;
            case STATE_FAIL:
                hint = "游戏失败，大侠请重新来过";
                break;
            case STATE_SUC:
                hint = "恭喜您通关";
        }
        if(hint != null){
            g.drawString(hint,newWidth/2 * blockPixel - hint.length() * 15,newHeight/2 * blockPixel) ;
        }
        //画头部
        g.setColor(Color.BLACK);
        g.fillRect(0,0,newWidth * blockPixel,blockPixel);
        g.setFont(new Font("黑体",Font.BOLD,headerHeight));
        g.setColor(Color.WHITE);
        g.drawString("时间：" +simpleDateFormat.format(new Date(System.currentTimeMillis())),0,headerHeight);
        g.drawString("分数：" + snake.getLength(),newWidth * blockPixel - 150,headerHeight);
    }


    //状态变换
    private int stateChange(){

        int i =  snakeMap.flush();
        System.out.println(Arrays.toString(snake.getHead()));
        return i;
    }

    public void start(){
        timer.start();
    }

    //实现按键
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        System.out.println("检测到按键：" + keyCode);
        //检查是否是空格
        if(keyCode == KeyEvent.VK_SPACE){
            if(state == STATE_INIT){
                state = STATE_RUNNING;
            }else if(state == STATE_RUNNING){
                state = STATE_PAUSE;
            }else if(state == STATE_PAUSE){
                state = STATE_RUNNING;
            }else{
                init();
            }
            return;
        }
        if(state == STATE_RUNNING){
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    snake.setDirection(Snake.SNAKE_DIRECTION_LEFT);
                    break;
                case KeyEvent.VK_UP:
                    snake.setDirection(Snake.SNAKE_DIRECTION_UP);
                    break;
                case KeyEvent.VK_RIGHT:
                    snake.setDirection(Snake.SNAKE_DIRECTION_RIGHT);
                    break;
                case KeyEvent.VK_DOWN:
                    snake.setDirection(Snake.SNAKE_DIRECTION_DOWN);
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

//    public void listen(CountDownEvent cde) {
//        //到了固定的事件间隔了；
//        if(true){ //进行游戏状态判断
//            stateChange();
//            repaint();
//        }
//        synchronized (cde.getSource()){
//            notifyAll();
//        }
//    }

    public int getBlockPixel(){
        return blockPixel;
    }
    public int getNewWidth(){
        return newWidth;
    }
    public int getNewHeight(){
        return newHeight;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //进行游戏状态判断

        if(state == STATE_RUNNING){ //进行游戏状态判断
            int i = stateChange();
            if(i == -1){
                state = STATE_FAIL;
            }
        }
        repaint();

        timer.start();
    }
}
