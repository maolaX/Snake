package study.ml.eattingsnake;
/*
   @ClassName: SnakeMap
   @Author: Maola
   @Description:
   @Version: 1.0.0
*/

import java.util.Arrays;
import java.util.Random;

public class SnakeMap {

    private Random random = new Random();
    private Snake snake;
    private int[] foodPos = new int[2];
    private boolean[][] map;
    private int width;
    private int height;

    SnakeMap(int width, int height){
        this.width = width;
        this.height = height;
        map = new boolean[width][height];
    }

    public void setSnake(Snake snake){
        this.snake = snake;
        setTrue(snake.getHead());
        flushFoodPos();
    }

    private void setTrue(int[] pos){
        map[pos[0]][pos[1]] = true;
    }
    private void setFalse(int[] pos){
        map[pos[0]][pos[1]] = false;
    }


    public int flush(){
        // 小蛇移动
        snake.move();
        //map去除尾巴
        setFalse(snake.getLastTail());
        //判断是否出界或者撞了自己
        if(failJuge(snake.getHead())){
            System.out.println("出界");
            return -1;
        }
        //map添加头部
        setTrue(snake.getHead());
        //判断是否吃到苹果
        System.out.println(Arrays.toString(snake.getHead()) + "------" + Arrays.toString(foodPos));
        if(Arrays.equals(snake.getHead(), foodPos)){
            //吃到了蛇边长
            snake.extendBody();
            setTrue(snake.getTail());
            //刷新苹果位置
            flushFoodPos();
        }
        //判断游戏是否成功
        if(snake.getLength() == width * height){
            return 1;
        }else {
            return 0;
        }
    }

    private boolean failJuge(int[] headPos){
        int count = 0;
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                count += map[i][j] ? 1 : 0;
            }
        }
        System.out.println(count);
        if(headPos[0] < 0 || headPos[0] >= width){
            return true;
        }
        if(headPos[1] < 0 || headPos[1] >= height){
            return true;
        }
        if(map[headPos[0]][headPos[1]]) return true;
        return false;
    }

    private void flushFoodPos(){
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        for(int j = y; j < height; j++){
            if(!map[x][j]) {
                foodPos[0] = x;
                foodPos[1] = j;
                return;
            }
        }
        for(int i = x + 1; i < width; i++){
            for(int j = 0; j < height; j++){
                if(!map[i][j]){
                    foodPos[0] = i;
                    foodPos[1] = j;
                    return;
                }
            }
        }
        for(int i = 0; i < x; i++){
            for(int j = 0; j < height; j ++){
                if(!map[i][j]){
                    foodPos[0] = i;
                    foodPos[1] = j;
                    return;
                }
            }
        }
        for(int j = 0; j < y; j ++){
            if(!map[x][j]){
                foodPos[0] = x;
                foodPos[1] = j;
                return;
            }
        }
    }

    public int[] getFoodPos() {
        return foodPos;
    }
}
