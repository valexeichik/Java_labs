package Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ObserverFrame extends JFrame {
    DefaultListModel<String> model;
    JLabel label;
    public ObserverFrame(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(915, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setFocusable(true);

        label = new JLabel();
        model = new DefaultListModel<>();

        LogObserver logObserver = new LogObserver(model);
        CurrentKeyObserver currentKeyObserver = new CurrentKeyObserver(label);
        KeySubject keySubject = new KeySubject();
        keySubject.attach(currentKeyObserver);
        keySubject.attach(logObserver);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keySubject.notify(KeyEvent.getKeyText(e.getKeyCode()));
            }
        });

        label.setFont(new Font("Montserrat",Font.BOLD,80));
        label.setBounds(0, 0, 600, 465);
        add(label);

        JList<String> list = new JList<>(model);
        JScrollPane scroll = new JScrollPane(list);
        scroll.setBounds(600, 0, 300, 465);
        add(scroll);
    }
}
