package game;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestFrame {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Frame frame = new Frame();
		frame.setBounds(100,600, 1000,1000);
		frame.setTitle("Hello Java GUI");
		frame.setBackground(Color.pink);
		frame.addWindowListener(new WindowAdapter() {
            public void windowClosing( WindowEvent e ) {
                System.exit(0);
            }
        });
        frame.setVisible(true);
		

	}

}
