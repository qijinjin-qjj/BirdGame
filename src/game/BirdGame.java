package game;

import javax.imageio.ImageIO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

/**
 * ��Ϸ����
 */
public class BirdGame extends JPanel {

    // ����ͼƬ
	BufferedImage background;

    // ��ʼͼƬ
	BufferedImage startImage;
    // ����ͼƬ
	BufferedImage gameOverImage;

    // ����
    Ground ground;
    // ����
    Column column1, column2;
    // С��
    Bird bird;

    // ��Ϸ����
    int score;

    // ��Ϸ״̬
    int state;
    // ״̬����
    public static final int START = 0; //��aʼ
    public static final int RUNNING = 1; //����
    public static final int GAME_OVER = 2; //����

    /**
     * ��ʼ����Ϸ
     */
    public BirdGame() throws Exception {
        // ��ʼ������ͼƬ
    	background = ImageIO.read(new FileInputStream("D://eclipse//bird//bin//game//bg.png"));
    	// ��ʼ����ʼ������ͼƬ
    	startImage = ImageIO.read(new FileInputStream("D://eclipse//bird//bin//game//start.png"));
    	gameOverImage = ImageIO.read(new FileInputStream("D://eclipse//bird//bin//game//gameover.png"));
    	
    	//String imgPath = "D://eclipse//bird//bin//game//bg.png";
    	//background = Toolkit.getDefaultToolkit().getImage("/src/resources/bg.png");
        //background = ImageIO.read(getClass().getResource("/src/game/resources/bg.png"));
        // ��ʼ����ʼ������ͼƬ
    	//startImage = Toolkit.getDefaultToolkit().getImage("/src/resources/start.png");
    	//gameOverImage = Toolkit.getDefaultToolkit().getImage("/src/resources/gameover.png");
        //startImage = ImageIO.read(getClass().getResource("/resources/start.png"));
        //gameOverImage = ImageIO.read(getClass().getResource("/resources/gameover.png"));

        // ��ʼ�����桢���ӡ�С��
        ground = new Ground();
        column1 = new Column(1);
        column2 = new Column(2);
        bird = new Bird();

        // ��ʼ������
        score = 0;

        // ��ʼ��״̬
        state = START;
    }

    /**
     * ���ƽ���
     */
    public void paint(Graphics g) {
        // ���Ʊ���
        g.drawImage(background, 0, 0, null);

        // ���Ƶ���
        g.drawImage(ground.image, ground.x, ground.y, null);

        // ��������
        g.drawImage(column1.image, column1.x - column1.width / 2, column1.y
                - column1.height / 2, null);
        g.drawImage(column2.image, column2.x - column2.width / 2, column2.y
                - column2.height / 2, null);

        // ����С����ת����ϵ��
        Graphics2D g2 = (Graphics2D) g;
        g2.rotate(-bird.alpha, bird.x, bird.y);
        g.drawImage(bird.image, bird.x - bird.width / 2, bird.y - bird.height / 2, null);
        g2.rotate(bird.alpha, bird.x, bird.y);

        // ���Ʒ���
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 40);
        g.setFont(f);
        g.drawString("" + score, 40, 60);
        g.setColor(Color.WHITE);
        g.drawString("" + score, 40 - 3, 60 - 3);

        // ���ƿ�ʼ���������//GAME_OVER
        switch (state) {
            case START:
                g.drawImage(startImage, 0, 0, null);
                break;
            case GAME_OVER:
                g.drawImage(gameOverImage, 0, 0, null);
                break;
        }
    }

    // ��ʼ��Ϸ
    public void action() throws Exception {
        // ��������
        MouseListener l = new MouseAdapter() {
            // ��갴���¼�
            public void mousePressed(MouseEvent e) {
                try {
                    switch (state) {
                        case START:
                            // �ڿ�ʼ״̬�����������תΪ����״̬��
                            state = RUNNING;
                            break;
                        case RUNNING:
                            // ������״̬�����������С�����Ϸ��С�
                            bird.flappy();
                            break;
                        case GAME_OVER:
                            // �ڽ���״̬������������������ݣ��ٴ�תΪ��ʼ̬��
                            column1 = new Column(1);
                            column2 = new Column(2);
                            bird = new Bird();
                            score = 0;
                            state = START;
                            break;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };

        // ����������ӵ���ǰ�������
        addMouseListener(l);

        // ���ϵ��ƶ����ػ�
        while (true) {
            switch (state) {
                case START:
                    // С���������ж���
                    bird.fly();
                    // ���������ƶ�һ��
                    ground.step();
                    break;
                case RUNNING:
                    // ���������ƶ�һ��
                    ground.step();
                    // ���������ƶ�һ��
                    column1.step();
                    column2.step();
                    // С���������ж���
                    bird.fly();
                    // С�������ƶ�һ��
                    bird.step();
                    // �������
                    if (bird.x == column1.x || bird.x == column2.x) {
                        score++;
                    }
                    // ����Ƿ�����ײ
                    if (bird.hit(ground) || bird.hit(column1) || bird.hit(column2)) {
                        state = GAME_OVER;
                    }
                    break;
            }
            // ���»��ƽ���
            repaint();
            // ���� 1000/60 ����
            Thread.sleep(1000 / 60);
        }
    }

    /**
     * ��������
     */
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();
        BirdGame game = new BirdGame();
        frame.add(game);
        frame.setSize(440, 670);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        game.action();
    }

}