package study.ml.eattingsnake;
/*
   @ClassName: SnakeUtil
   @Author: Maola
   @Description:
   @Version: 1.0.0
*/

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SnakeUtil {

    public static Properties loadProperties(String path){
        InputStream resourceAsStream = SnakePanel.class.getClassLoader().getResourceAsStream(path);
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
