import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Panel2 extends JPanel {
    String name;
    Color color;
    final static int N = 5;
    Panel2() {
        super();

        MouseListener listener = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    JButton bt = (JButton)e.getSource();
                    name = bt.getText();
                    bt.setText("Clicked!");
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    JButton bt = (JButton)e.getSource();
                    bt.setText(name);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                JButton bt = (JButton)e.getSource();
                color = bt.getBackground();
                bt.setBackground(Color.magenta);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton bt = (JButton)e.getSource();
                bt.setBackground(color);
            }
        };

        setLayout(new GridLayout(N, N));
        for (int i = 1; i <= N * N; i++) {
            JButton button = new JButton(i + "");
            button.setBackground(Color.WHITE);
            button.addMouseListener(listener);
            add(button);
        }
    }
}
