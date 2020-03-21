package game;

import javax.imageio.ImageIO;


import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

/**
 * С��
 */
class Bird {

    // ͼƬ
    Image image;
    // λ��
    int x, y;
    // ���
    int width, height;
    // ��С��������ײ��⣩
    int size;

    // �������ٶ�
    double g;
    // λ�Ƶļ��ʱ��
    double t;
    // ��������ٶ�
    double v0;
    // ��ǰ�����ٶ�
    double speed;
    // ����ʱ��t֮���λ��
    double s;
    // С�����ǣ����ȣ�
    double alpha;

    // һ��ͼƬ����¼С��Ķ���֡
    BufferedImage[] images;
    // ����֡������±�
    int index;

    // ��ʼ��С��
    public Bird() throws Exception {
        // ��ʼ����������
        //image = ImageIO.read(getClass().getResource("/resources/0.png"));
    	image = ImageIO.read(new FileInputStream("D://eclipse//bird//bin//game//0.png"));
    	//image = Toolkit.getDefaultToolkit().getImage("/src/resources/0.png");
        
        width = image.getWidth(null);
        height = image.getHeight(null);
        x = 132;
        y = 280;
        size = 40;

        // ��ʼ��λ�Ʋ���
        g = 4;
        v0 = 20;
        t = 0.25;
        speed = v0;
        s = 0;
        alpha = 0;

        // ��ʼ������֡����
        images = new BufferedImage[8];
        for (int i = 0; i < 8; i++) {
            //images[i] = ImageIO.read(getClass().getResource("/resources/" + i + ".png"));
            //image = Toolkit.getDefaultToolkit().getImage("/src/resources/ground.png");
        	images[i] = ImageIO.read(new FileInputStream("D://eclipse//bird//bin//game//" + i + ".png"));
        	
        }
        index = 0;
    }

    // ���ж������仯һ֡��
    public void fly() {
        index++;
        image = images[(index / 12) % 8];
    }

    // �ƶ�һ��
    public void step() {
        double v0 = speed;
        // ���������˶�λ��
        s = v0 * t + g * t * t / 2;
        // �����������λ��
        y = y - (int) s;
        // �����´��ƶ��ٶ�
        double v = v0 - g * t;
        speed = v;
        // ������ǣ������к�����
        alpha = Math.atan(s / 8);
    }

    // ���Ϸ���
    public void flappy() {
        // �����ٶ�
        speed = v0;
    }

    // ���С���Ƿ���ײ������
    public boolean hit(Ground ground) {
        boolean hit = y + size / 2 > ground.y;
        if (hit) {
            y = ground.y - size / 2;
            alpha = -3.14159265358979323 / 2;
        }
        return hit;
    }

    // ���С���Ƿ�ײ������
    public boolean hit(Column column) {
        // �ȼ���Ƿ������ӵķ�Χ��
        if (x > column.x - column.width / 2 - size / 2
                && x < column.x + column.width / 2 + size / 2) {
            // �ټ���Ƿ������ӵķ�϶��
            if (y > column.y - column.gap / 2 + size / 2
                    && y < column.y + column.gap / 2 - size / 2) {
                return false;
            }
            return true;
        }
        return false;
    }
}