package Strategy;

import javax.swing.*;

public class ExcellentStudentFrame extends JFrame {
    JTextArea area;
    ExcellentStudentFrame(String title) {
        super(title);
        setSize(200, 200);
        setResizable(false);
        setLocationRelativeTo(null);

        area = new JTextArea();
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(10, 10, 180, 180);
        add(scroll);
    }
}
