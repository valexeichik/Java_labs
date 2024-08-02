import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame1 extends JFrame {
    JLabel statusLabel;
    JButton button;
    Frame1(String title) {
        super("Task 1");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new BorderLayout());

        statusLabel = new JLabel();
        add(statusLabel, BorderLayout.SOUTH);

        button = new JButton();
        button.setBounds(250, 250, 100, 30);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(button);
        add(panel, BorderLayout.CENTER);

        button.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                StringBuilder text = new StringBuilder(button.getText());
                if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    if (!text.isEmpty()) {
                        text.deleteCharAt(text.length() - 1);
                    }
                }
                else {
                    text.append(e.getKeyChar());
                }
                button.setText(text.toString());
                button.setSize(button.getPreferredSize());
            }
        });

        button.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                setStatus(e.getX() + button.getX(), e.getY() + button.getY());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int newX = e.getX() + button.getX();
                int newY = e.getY() + button.getY();
                int deltaWidth = 500 - button.getWidth();
                int deltaHeight = 500 - 30 - button.getHeight() - 10;
                if (0 <= newX && newX <= deltaWidth && 0 <= newY && newY <= deltaHeight) {
                    setStatus(newX, newY);
                    if (e.isControlDown()) {
                        button.setLocation(newX, newY);
                    }
                }
            }
        });

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button.setLocation(e.getX(), e.getY());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                statusLabel.setText("mouse is out of panel :(");
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                setStatus(e.getX(), e.getY());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                mouseMoved(e);
            }
        });
    }

    void setStatus(int x, int y) {
        statusLabel.setText("x=" + x + ", y=" + y);
    }
}
