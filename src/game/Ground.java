package game;

import javax.imageio.ImageIO;


import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

/**
 * ����
 */
class Ground {

    // ͼƬ
    Image image;
    // λ��
    int x, y;
    // ���
    int width, height;

    // ��ʼ������
    public Ground() throws Exception {
        //image = ImageIO.read(getClass().getResource("/resources/ground.png"));
        image = Toolkit.getDefaultToolkit().getImage("/src/resources/ground.png");
        width = image.getWidth(null);
        height = image.getHeight(null);
        x = 0;
        y = 500;
    }

    // �����ƶ�һ��
    public void step() {
        x--;
        if (x == -109) {
            x = 0;
        }
    }

}
