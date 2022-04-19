package study.ml.eattingsnake;
/*
   @ClassName: SnakeImg
   @Author: Maola
   @Description: 加载Snake身体图像
   @Version: 1.0.0
*/

import javax.swing.*;
import java.net.URL;

public class SnakeImg {

    private static URL bodyURL = SnakeImg.class.getClassLoader().getResource("img/body.png");
    private static URL foodURL = SnakeImg.class.getClassLoader().getResource("img/food.png");
    private static URL headUpURL = SnakeImg.class.getClassLoader().getResource("img/head_up.png");
    private static URL headDownURL = SnakeImg.class.getClassLoader().getResource("img/head_down.png");

    private static URL headLeftURL = SnakeImg.class.getClassLoader().getResource("img/head_left.png");
    private static URL headRightURL = SnakeImg.class.getClassLoader().getResource("img/head_right.png");


   public static ImageIcon body = new ImageIcon(bodyURL);
   public static ImageIcon food = new ImageIcon(foodURL);
   public static ImageIcon headUp = new ImageIcon(headUpURL);
   public static ImageIcon headDown = new ImageIcon(headDownURL);
   public static ImageIcon headLeft = new ImageIcon(headLeftURL);
   public static ImageIcon headRight = new ImageIcon(headRightURL);
}
