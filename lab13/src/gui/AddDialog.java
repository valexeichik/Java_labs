package gui;

import student.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.StringTokenizer;

public class AddDialog extends JDialog {
    JTextField nameField;
    JTextField surnameField;
    JTextField idField;
    JTextArea marksArea;
    AddDialog(StrategyFrame owner, String title) {
        super(owner, title);

        setSize(330, 280);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel idLabel = new JLabel("Номер зачётки:");
        idLabel.setBounds(10, 10, 130, 30);
        add(idLabel);
        idField = new JTextField();
        idField.setBounds(10, 40, 130, 30);
        add(idField);

        JLabel nameLabel = new JLabel("Имя студента:");
        nameLabel.setBounds(10, 70, 130, 30);
        add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(10, 100, 130, 30);
        add(nameField);

        JLabel surnameLabel = new JLabel("Фамилия студента:");
        surnameLabel.setBounds(10, 130, 130, 30);
        add(surnameLabel);
        surnameField = new JTextField();
        surnameField.setBounds(10, 160, 130, 30);
        add(surnameField);

        JLabel marksLabel = new JLabel("<html>Оценки за сессию<br>(в формате предмет-оценка):");
        marksLabel.setBounds(170, 10, 130, 60);
        add(marksLabel);
        marksArea = new JTextArea();
        marksArea.setBounds(170, 70, 130, 60);
        marksArea.setLineWrap(true);
        add(marksArea);

        JButton addButton = new JButton("Добавить");
        addButton.setBounds(170, 160, 130, 30);
        add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (idField.getText().isEmpty() || nameField.getText().isEmpty()
                            || surnameField.getText().isEmpty() || marksArea.getText().isEmpty()) {
                        throw new IllegalArgumentException();
                    }

                    Integer id = Integer.parseInt(idField.getText());
                    String surname = surnameField.getText();
                    String name = nameField.getText();

                    HashMap<String, Integer> marks = new HashMap<>();
                    StringTokenizer area = new StringTokenizer(marksArea.getText(), " ;-\n", false);
                    while (area.hasMoreTokens()) {
                        String subject = area.nextToken();
                        Integer mark = Integer.parseInt(area.nextToken());
                        marks.put(subject, mark);
                    }

                    Student student = new Student(id, surname, name, marks);
                    owner.students.add(student);
                    owner.modelStudents.addElement(student.toString());

                    dispose();
                }
                catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(AddDialog.this, "Пожалуйста, проверьте поля!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton cleanButton = new JButton("Очистить");
        cleanButton.setBounds(10, 200, 130, 30);
        add(cleanButton);

        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                marksArea.setText("");
                idField.setText("");
                nameField.setText("");
                surnameField.setText("");
            }
        });

        JButton cancelButton = new JButton("Отмена");
        cancelButton.setBounds(170, 200, 130, 30);
        add(cancelButton);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}

