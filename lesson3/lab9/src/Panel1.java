import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Panel1 extends JPanel {
    Vector<Student> leftData;
    Vector<Student> rightData;
    Panel1() {
        super();
        setLayout(new BorderLayout());

        leftData = readDataFromFile("students1.txt");
        rightData = readDataFromFile("students2.txt");

        DefaultListModel<Student> leftListModel = new DefaultListModel<>();
        for (Student st : leftData) leftListModel.addElement(st);
        JList<Student> leftList = new JList<>(leftListModel);
        DefaultListModel<Student> rightListModel = new DefaultListModel<>();
        for (Student st : rightData) rightListModel.addElement(st);
        JList<Student> rightList = new JList<>(rightListModel);
        rightList.setPreferredSize(new Dimension(200, 400));
        leftList.setPreferredSize(new Dimension(200, 400));

        JScrollPane leftScroll = new JScrollPane(leftList);
        JScrollPane rightScroll = new JScrollPane(rightList);
        add(leftScroll, BorderLayout.WEST);
        add(rightScroll, BorderLayout.EAST);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        JButton leftButton = new JButton("←");
        leftButton.setBackground(Color.WHITE);
        panel.add(leftButton, BorderLayout.SOUTH);
        JButton rightButton = new JButton("→");
        rightButton.setBackground(Color.WHITE);
        panel.add(rightButton, BorderLayout.NORTH);

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!leftList.isSelectionEmpty()) {
                    int[] indices = leftList.getSelectedIndices();
                    for (int i = indices.length - 1; i >= 0; i--) {
                        //rightData.add(leftData.elementAt(indices[i]));
                        rightListModel.addElement(leftListModel.elementAt(indices[i]));
                        //leftData.remove(indices[i]);
                        leftListModel.remove(indices[i]);
                    }
                }
            }
        });

        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!rightList.isSelectionEmpty()) {
                    int[] indices = rightList.getSelectedIndices();
                    for (int i = indices.length - 1; i >= 0; i--) {
                        leftData.add(rightData.elementAt(indices[i]));
                        leftListModel.addElement(rightListModel.elementAt(indices[i]));
                        rightData.remove(indices[i]);
                        rightListModel.remove(indices[i]);
                    }
                }
            }
        });
    }

    Vector<Student> readDataFromFile(String path) {
        Vector<Student> stud = new Vector<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            scanner.useDelimiter(System.getProperty("line.separator"));
            while (scanner.hasNext()) {
                StringTokenizer currentLine = new StringTokenizer(scanner.next(), " ", false);
                Integer id = Integer.parseInt(currentLine.nextToken());
                String surname = currentLine.nextToken();
                String name = currentLine.nextToken();
                Student student = new Student(id, surname, name);
                stud.add(student);
            }
        }
        catch (FileNotFoundException | IllegalArgumentException | NoSuchElementException ex) {
            System.out.println(ex.getMessage());
        }
        return stud;
    }
}

class Student {
    Integer id;
    String name;
    String surname;

    Student(Integer id, String surname, String name) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public String toString() {
        return id + " – " + surname + " " + name;
    }
}
