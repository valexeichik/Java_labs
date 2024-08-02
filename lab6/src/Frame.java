import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Frame extends JFrame {
    List<Student> students;
    JList<String> listStudents;
    DefaultListModel<String> modelStudents;
    JTextArea marksArea;

    Frame(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 440);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        JTextField pathTextField = new JTextField();
        pathTextField.setBounds(70, 10, 230, 30);
        add(pathTextField);
        JButton fileButton = new JButton("Выбрать файл");
        fileButton.setBounds(300, 10, 130, 30);
        add(fileButton);

        fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int result = chooser.showDialog(null, "Oткрыть файл");
                if (result == JFileChooser.APPROVE_OPTION) {
                    pathTextField.setText(chooser.getSelectedFile().getName());
                }
            }
        });

        students = new ArrayList<>();
        modelStudents = new DefaultListModel<>();
        listStudents = new JList<>();
        listStudents.setModel(modelStudents);
        JScrollPane scrollPane = new JScrollPane(listStudents);
        scrollPane.setBounds(55, 90, 265, 300);
        add(scrollPane);



        JButton listButton = new JButton("Показать список студентов");
        listButton.setBounds(150, 50, 200, 30);
        add(listButton);

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelStudents = new DefaultListModel<>();
                students = new ArrayList<>();
                readDataFromFile(pathTextField.getText(), students);
                for (Student student : students) {
                    modelStudents.addElement(student.toString());
                }
                listStudents.setModel(modelStudents);
            }
        });

        marksArea = new JTextArea("Здесь отображаются отметки за сессию выбранного слева студента.");
        marksArea.setEditable(false);
        marksArea.setLineWrap(true);
        marksArea.setBounds(325, 90, 120, 100);
        add(marksArea);

        listStudents.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (listStudents.getSelectedIndex() == -1) {
                    marksArea.setText("Здесь отображаются отметки за сессию выбранного слева студента.");
                } else marksArea.setText(students.get(listStudents.getSelectedIndex()).getMarksAsString());
            }
        });

        JButton cleanButton = new JButton("Очистить");
        cleanButton.setBounds(325, 360, 120, 30);
        add(cleanButton);

        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                marksArea.setText("Здесь отображаются отметки за сессию выбранного слева студента.");
                pathTextField.setText("");
                modelStudents.clear();
                students = new ArrayList<>();
            }
        });

        JButton addButton = new JButton("Добавить");
        addButton.setBounds(325, 305, 120, 30);
        add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddDialog dialog = new AddDialog(Frame.this, "Добавить студента");
                dialog.setVisible(true);
            }
        });

        JButton excellentButton = new JButton("<html>Сформировать<br>список отличников");
        excellentButton.setBounds(325, 260, 120, 45);
        add(excellentButton);

        excellentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createExcellentStudentsFrame();
                }
                catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(Frame.this, "Пожалуйста, внесите список студентов!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    void createExcellentStudentsFrame() {
        if (students.isEmpty()) {
            throw new IllegalArgumentException();
        }

        ExcellentStudentFrame frame2 = new ExcellentStudentFrame("Список отличников");

        List<Student> exStudents = students.stream().filter(Student::isExcellent).toList();
        exStudents = exStudents.stream().sorted(new Comparator<>() {
            @Override
            public int compare(Student st1, Student st2) {
                if (st1.surname.equalsIgnoreCase(st2.surname))
                    return st1.name.toLowerCase().compareTo(st2.name.toLowerCase());
                else return st1.surname.toLowerCase().compareTo(st2.surname.toLowerCase());
            }
        }).toList();

        if (exStudents.isEmpty()) {
            frame2.area.setText("В этом семестре нет отличников :(");
        } else {
            for (Student student : exStudents) {
                frame2.area.append(student.toString() + '\n');
            }
        }
        frame2.setVisible(true);
    }
    void readDataFromFile(String path, List<Student> stud) {
        try {
            Scanner scanner = new Scanner(new File(path));
            scanner.useDelimiter(System.getProperty("line.separator"));
            while (scanner.hasNext()) {
                StringTokenizer currentLine = new StringTokenizer(scanner.next(), " ;-", false);

                Integer id = Integer.parseInt(currentLine.nextToken());
                String surname = currentLine.nextToken();
                String name = currentLine.nextToken();

                HashMap<String, Integer> marks = new HashMap<>();
                while (currentLine.hasMoreTokens()) {
                    String subject = currentLine.nextToken();
                    Integer mark = Integer.parseInt(currentLine.nextToken());
                    marks.put(subject, mark);
                }

                Student student = new Student(id, surname, name, marks);
                stud.add(student);
            }
        }
        catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(Frame.this, "Пожалуйста, проверьте файл!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
        }
        catch (IllegalArgumentException | NoSuchElementException ex) {
            JOptionPane.showMessageDialog(Frame.this, "Пожалуйста, проверьте данные в файле!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
