package study.ml.eattingsnake;
/*
   @ClassName: Snake
   @Author: Maola
   @Description:
   @Version: 1.0.0
*/

import java.awt.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

public class Snake {


        //提供的一些常量
    public static final int[] SNAKE_DIRECTION_UP = new int[]{0,-1};
    public static final int[] SNAKE_DIRECTION_DOWN = new int[]{0,1};
    public static final int[] SNAKE_DIRECTION_LEFT = new int[]{-1,0};
    public static final int[] SNAKE_DIRECTION_RIGHT = new int[]{1,0};

    LinkedList<int[]> body;
    private int[] direction = SNAKE_DIRECTION_RIGHT;
    private int[] tail;

    Snake(int[] headPos){
        this.body = new LinkedList<>();
        body.add(headPos);
    }

    public void setDirection(int[] direction) {
        if(this.direction[0] + direction[0] == 0
        && this.direction[1] + direction[1] == 0){
            return;
        }
        this.direction = direction;
    }

    //定义蛇的移动
    public void move(){
        tail = body.getLast();
        int[] tmp = body.getFirst().clone();

        tmp[0] += direction[0];
        tmp[1] += direction[1];
        body.addFirst(tmp);
        body.removeLast();
    }
    //定义蛇延长身体
    public void extendBody(){
        body.addLast(tail);
    }

    public int[] getHead(){
        return body.getFirst();
    }
    public int[] getTail(){
        return body.getLast();
    }
    public int[] getLastTail(){return tail;}

    public int getLength(){
        return body.size();
    }

    public List<int[]> getBody(){
        return body;
    }

    //画蛇
    public void drawSnake(Component c, Graphics g, int blockSize, int headerHeight){
        //画蛇身体
        boolean isHead = true;
        for(int[] tmp : body){
            if(isHead){
                isHead = false;
            }else{
                SnakeImg.body.paintIcon(c,g,tmp[0] * blockSize, tmp[1] * blockSize + headerHeight);
            }
        }
        //画蛇头
        if(direction.equals(SNAKE_DIRECTION_RIGHT)){
            SnakeImg.headRight.paintIcon(c,g,getHead()[0] * blockSize,getHead()[1] * blockSize + headerHeight);
        }else if(direction.equals(SNAKE_DIRECTION_LEFT)){
            SnakeImg.headLeft.paintIcon(c,g,getHead()[0] * blockSize,getHead()[1] * blockSize + headerHeight);
        }else if(direction.equals((SNAKE_DIRECTION_UP))){
            SnakeImg.headUp.paintIcon(c,g,getHead()[0] * blockSize,getHead()[1] * blockSize + headerHeight);
        }else {
            SnakeImg.headDown.paintIcon(c,g,getHead()[0] * blockSize,getHead()[1] * blockSize + headerHeight);
        }
    }


}
