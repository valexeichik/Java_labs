package mvc;

import iterator.Deque;
import iterator.MyIterator;
import visitor.CountAverageVisitor;
import visitor.MyInteger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.view.setVisible(true);
    }

    public void initController() {
        view.getCountAverage().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (model.getModel().isEmpty()) throw new IndexOutOfBoundsException();
                    CountAverageVisitor visitor = new CountAverageVisitor();
                    MyIterator<MyInteger> it = model.getModel().createIterator();
                    while (it.hasNext()) {
                        it.currentElement().accept(visitor);
                        it.next();
                    }
                    view.getAverageField().setText(String.format("%.2f", visitor.countAverage()));
                }
                catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(view, "Дек пуст!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        view.getPushFront().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pushFrontAll(readDataFromArea());
            }
        });

        view.getPushBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pushBackAll(readDataFromArea());
            }
        });

        view.getPopBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (model.getModel().isEmpty()) throw new IndexOutOfBoundsException();
                    popBack();
                }
                catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(view, "Дек пуст!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        view.getPopFront().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (model.getModel().isEmpty()) throw new IndexOutOfBoundsException();
                    popFront();
                }
                catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(view, "Дек пуст!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void popBack(){
        model.getModel().popBack();
        view.update(model);
    }


    public void popFront(){
        model.getModel().popFront();
        view.update(model);
    }

    public void pushFrontAll(Deque<MyInteger> d){
        model.getModel().pushFrontAll(d);
        view.update(model);
    }

    public void pushBackAll(Deque<MyInteger> d){
        model.getModel().pushBackAll(d);
        view.update(model);
    }

    Deque<MyInteger> readDataFromArea() {
        Deque<MyInteger> input = new Deque<>();
        try {
            StringTokenizer tokenizer = new StringTokenizer(view.getAreaInput().getText(), " ;,", false);
            while (tokenizer.hasMoreTokens()) {
                MyInteger i = new MyInteger(Integer.parseInt(tokenizer.nextToken()));
                input.pushBack(i);
            }
            view.getAreaInput().setText("");
        }
        catch (IllegalArgumentException | NoSuchElementException ex) {
            JOptionPane.showMessageDialog(view, "Проверьте ввод!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
        }
        return input;
    }
}