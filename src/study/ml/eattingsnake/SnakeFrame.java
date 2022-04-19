package study.ml.eattingsnake;
/*
   @ClassName: SnakeFrame
   @Author: Maola
   @Description:
   @Version: 1.0.0
*/

import javax.swing.*;
import java.util.Properties;

public class SnakeFrame extends JFrame {

    SnakeFrame(){
        super();
        init();
    }

    public void init(){
        Properties properties = SnakeUtil.loadProperties("resource/application.properties");
        SnakePanel snakePanel = new SnakePanel();
        int width = snakePanel.getNewWidth();
        int height = snakePanel.getNewHeight();
        int bolckSize = snakePanel.getBlockPixel();
        this.setSize(width * bolckSize - bolckSize/2, height * bolckSize + bolckSize * 3 / 2);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(snakePanel);
        this.setVisible(true);
        snakePanel.start();
    }
}
