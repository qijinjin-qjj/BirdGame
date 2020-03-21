package game;

import javax.imageio.ImageIO;


import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.Random;

/**
 * ����
 */
class Column {

    // ͼƬ
    Image image;
    // λ��
    int x, y;
    // ���
    int width, height;
    // ����֮��ķ�϶
    int gap;
    // ����֮��ľ���
    int distance;
    // ���������
    Random random = new Random();

    /**
     * ��ʼ����N������
     */
    public Column(int n) throws Exception {
        //image = ImageIO.read(getClass().getResource("/resources/column.png"));
        //image = Toolkit.getDefaultToolkit().getImage("/src/resources/column.png");
        image = ImageIO.read(new FileInputStream("D://eclipse//bird//bin//game//column.png"));
        width = image.getWidth(null);
        height = image.getHeight(null);
        gap = 144;
        distance = 245;
        x = 550 + (n - 1) * distance;
        y = random.nextInt(218) + 132;
    }

    // �����ƶ�һ��
    public void step() {
        x--;
        if (x == -width / 2) {
            x = distance * 2 - width / 2;
            y = random.nextInt(218) + 132;
        }
    }
}