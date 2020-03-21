package game;

import javax.imageio.ImageIO;


import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

/**
 * 地面
 */
class Ground {

    // 图片
    Image image;
    // 位置
    int x, y;
    // 宽高
    int width, height;

    // 初始化地面
    public Ground() throws Exception {
        //image = ImageIO.read(getClass().getResource("/resources/ground.png"));
        image = Toolkit.getDefaultToolkit().getImage("/src/resources/ground.png");
        width = image.getWidth(null);
        height = image.getHeight(null);
        x = 0;
        y = 500;
    }

    // 向左移动一步
    public void step() {
        x--;
        if (x == -109) {
            x = 0;
        }
    }

}
