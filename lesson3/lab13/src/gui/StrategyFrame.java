package gui;

import org.xml.sax.SAXException;
import sortStrategy.BaseStrategy;
import sortStrategy.SortStrategy;
import sortStrategy.StreamStrategy;
import student.Student;
import xml.DOMParser;
import xmlStrategy.DOMStrategy;
import xmlStrategy.SAXStrategy;
import xmlStrategy.XMLStrategy;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class StrategyFrame extends JFrame {
    List<Student> students;
    JList<String> listStudents;
    DefaultListModel<String> modelStudents;
    JTextArea marksArea;
    SortStrategy sortStrategy;
    XMLStrategy xmlStrategy;
    JRadioButton streamButton = new JRadioButton("stream API");
    JRadioButton baseButton = new JRadioButton("в лоб");
    public StrategyFrame(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 440);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setFocusable(true);

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        students = new ArrayList<>();
        modelStudents = new DefaultListModel<>();
        listStudents = new JList<>();
        listStudents.setModel(modelStudents);
        JScrollPane scrollPane = new JScrollPane(listStudents);
        scrollPane.setBounds(55, 90, 265, 260);
        add(scrollPane);

        ButtonGroup readGroup = new ButtonGroup();
        JRadioButton domButton = new JRadioButton("DOM");
        JRadioButton saxButton = new JRadioButton("SAX");
        readGroup.add(domButton); readGroup.add(saxButton);
        domButton.setBounds(170, 50, 100, 30);
        saxButton.setBounds(270, 50, 100, 30);
        domButton.setSelected(true);
        add(domButton);
        add(saxButton);

        JButton listButton = new JButton("Прочитать список студентов");
        listButton.setBounds(120, 20, 265, 30);
        add(listButton);

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelStudents = new DefaultListModel<>();
                students = new ArrayList<>();
                xmlStrategy = saxButton.isSelected() ? new SAXStrategy() : new DOMStrategy();
                try {
                    int result = chooser.showDialog(null, "Oткрыть файл");
                    if (result == JFileChooser.APPROVE_OPTION) {
                        students = xmlStrategy.parse(chooser.getSelectedFile().getAbsolutePath());
                    }

                    for (Student student : students) {
                        modelStudents.addElement(student.toString());
                    }
                    listStudents.setModel(modelStudents);
                } catch (IOException | ParserConfigurationException | SAXException ex) {
                    JOptionPane.showMessageDialog(StrategyFrame.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        marksArea = new JTextArea("Здесь отображаются отметки за сессию выбранного слева студента.");
        marksArea.setEditable(false);
        marksArea.setLineWrap(true);
        marksArea.setWrapStyleWord(true);
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
                modelStudents.clear();
                students = new ArrayList<>();
            }
        });

        JButton saveButton = new JButton("Сохранить в .xml");
        saveButton.setBounds(100, 360, 170, 30);
        add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (chooser.showSaveDialog(StrategyFrame.this) == JFileChooser.APPROVE_OPTION) {
                        File file = chooser.getSelectedFile();
                        String path = file.getAbsolutePath();
                        if (path.isEmpty()) path = "students";
                        if (!path.endsWith(".xml")) path += ".xml";
                        DOMParser.saveStudents(students, path);
                    }
                }
                catch(ParserConfigurationException | TransformerException ex){
                    JOptionPane.showMessageDialog(StrategyFrame.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton addButton = new JButton("Добавить");
        addButton.setBounds(325, 305, 120, 30);
        add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddDialog dialog = new AddDialog(StrategyFrame.this, "Добавить студента");
                dialog.setVisible(true);
            }
        });

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(baseButton);
        buttonGroup.add(streamButton);
        streamButton.setSelected(true);
        streamButton.setBounds(325, 205, 120, 15);
        add(streamButton);
        baseButton.setBounds(325, 230, 120, 15);
        add(baseButton);

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
                    JOptionPane.showMessageDialog(StrategyFrame.this, "Пожалуйста, внесите список студентов!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    void createExcellentStudentsFrame() {
        if (students.isEmpty()) {
            throw new IllegalArgumentException();
        }

        ExcellentStudentFrame frame2 = new ExcellentStudentFrame("Список отличников");
        if (streamButton.isSelected()) sortStrategy = new StreamStrategy();
        else sortStrategy = new BaseStrategy();

        List<Student> exStudents = sortStrategy.execute(students);

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
            JOptionPane.showMessageDialog(StrategyFrame.this, "Пожалуйста, проверьте файл!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
        }
        catch (IllegalArgumentException | NoSuchElementException ex) {
            JOptionPane.showMessageDialog(StrategyFrame.this, "Пожалуйста, проверьте данные в файле!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
        }
    }
}