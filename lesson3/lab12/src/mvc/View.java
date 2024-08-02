package mvc;
import iterator.Deque;
import visitor.MyInteger;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    JButton countAverage;
    JTextField averageField;
    JList<MyInteger> listInteger;
    JTextArea areaInput;
    JButton pushBack;
    JButton pushFront;
    JButton popBack;
    JButton popFront;

    public View(String title) {
        super(title);
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        listInteger = new JList<>();
        listInteger.setBounds(30, 10, 200, 440);
        add(listInteger);

        areaInput = new JTextArea();
        areaInput.setLineWrap(true);
        areaInput.setWrapStyleWord(true);
        areaInput.setBounds(260, 10, 200, 60);
        add(areaInput);

        JLabel label = new JLabel("Среднее: ");
        label.setBounds(260, 220, 80, 15);
        add(label);

        averageField = new JTextField();
        averageField.setBounds(350, 220, 110, 20);
        averageField.setBackground(Color.white);
        averageField.setEditable(false);
        add(averageField);

        countAverage = new JButton("Посчитать среднее");
        countAverage.setBounds(260, 180, 200, 30);
        add(countAverage);

        pushFront = new JButton("Добавить в начало");
        pushFront.setBounds(260, 280, 200, 30);
        add(pushFront);

        pushBack = new JButton("Добавить в конец");
        pushBack.setBounds(260, 320, 200, 30);
        add(pushBack);

        popBack = new JButton("Удалить последний");
        popBack.setBounds(260, 360, 200, 30);
        add(popBack);

        popFront = new JButton("Удалить первый");
        popFront.setBounds(260, 400, 200, 30);
        add(popFront);
    }

    public JButton getPopFront() {
        return popFront;
    }

    public JButton getPopBack() {
        return popBack;
    }

    public JButton getPushFront() {
        return pushFront;
    }

    public JButton getPushBack() {
        return pushBack;
    }

    public JList<MyInteger> getListInteger() { return listInteger; }

    public JTextField getAverageField() {
        return averageField;
    }

    public JButton getCountAverage() {
        return countAverage;
    }

    public JTextArea getAreaInput() {
        return areaInput;
    }

    public void update(Model model) {
        listInteger.setModel(model.getModel().getListModel());
    }
}