import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Frame extends JFrame {
    JList<Student> rightContent = new JList<>();
    JTextArea area = new JTextArea();
    Frame(String title) {
        super(title);
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        area.setEditable(false);
        Deque<Student> leftData = readDataFromFile("students1.txt");
        writeIntoArea(leftData);
        area.setBounds(30, 10, 200, 480);
        add(area);

        Deque<Student> rightData = readDataFromFile("students2.txt");
        DefaultListModel<Student> rightModel = rightData.getListModel();
        rightContent.setModel(rightModel);
        rightContent.setBounds(260, 10, 200, 250);
        add(rightContent);

        JButton pushFront = new JButton("Добавить в начало");
        pushFront.setBounds(260, 280, 200, 30);
        add(pushFront);
        pushFront.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!rightContent.isSelectionEmpty()) {
                    int[] indices = rightContent.getSelectedIndices();
                    Deque<Student> change = new Deque<>();
                    for (int i = indices.length - 1; i >= 0; i--) {
                        change.pushFront(rightModel.get(indices[i]));
                        rightModel.remove(indices[i]);
                    }
                    rightContent.setModel(rightModel);
                    leftData.pushFrontAll(change);
                    writeIntoArea(leftData);
                }
            }
        });

        JButton pushBack = new JButton("Добавить в конец");
        pushBack.setBounds(260, 330, 200, 30);
        add(pushBack);
        pushBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!rightContent.isSelectionEmpty()) {
                    int[] indices = rightContent.getSelectedIndices();
                    Deque<Student> change = new Deque<>();
                    for (int i = indices.length - 1; i >= 0; i--) {
                        change.pushFront(rightModel.get(indices[i]));
                        rightModel.remove(indices[i]);
                    }
                    rightContent.setModel(rightModel);
                    leftData.pushBackAll(change);
                    writeIntoArea(leftData);
                }
            }
        });

        JButton popBack = new JButton("Удалить последний");
        popBack.setBounds(260, 430, 200, 30);
        add(popBack);
        popBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    leftData.popBack();
                    writeIntoArea(leftData);
                }
                catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(Frame.this, "Больше нет данных!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton popFront = new JButton("Удалить первый");
        popFront.setBounds(260, 380, 200, 30);
        add(popFront);
        popFront.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    leftData.popFront();
                    writeIntoArea(leftData);
                }
                catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(Frame.this, "Больше нет данных!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    void writeIntoArea(Deque<Student> students) {
        area.setText(students.toString());
    }
    Deque<Student> readDataFromFile(String path) {
        Deque<Student> stud = new Deque<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            scanner.useDelimiter(System.getProperty("line.separator"));
            while (scanner.hasNext()) {
                StringTokenizer currentLine = new StringTokenizer(scanner.next(), " ", false);
                Integer id = Integer.parseInt(currentLine.nextToken());
                String surname = currentLine.nextToken();
                String name = currentLine.nextToken();
                Student student = new Student(id, surname, name);
                stud.pushBack(student);
            }
        }
        catch (FileNotFoundException | IllegalArgumentException | NoSuchElementException ex) {
            System.out.println(ex.getMessage());
        }
        return stud;
    }
}
