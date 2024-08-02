import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class Frame2 extends JFrame {
    final int FRAME_WIDTH = 500;
    final int FRAME_HEIGHT = 500;
    Frame2(String title) {
        super(title);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        JLabel label = new JLabel("Нравится ли Вам писать на Java?");
        label.setBounds(150, 170,250, 30);
        add(label);

        JButton yesButton = new JButton("Да :)");
        yesButton.setBounds(125, 200, 100, 30);
        add(yesButton);
        JButton noButton = new JButton("Нет :(");
        noButton.setBounds(275, 200, 100, 30);
        add(noButton);

        noButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Random rand = new Random();
                int newX = rand.nextInt(FRAME_WIDTH - noButton.getWidth() + 1);
                int newY = rand.nextInt(FRAME_HEIGHT - noButton.getHeight() - 30 + 1);
                noButton.setLocation(newX, newY);
            }
        });

        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Frame2.this, "Ждём Вас на МСС!", "Ура!", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
