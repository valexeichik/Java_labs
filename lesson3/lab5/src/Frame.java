import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Frame  extends JFrame {

    public static void main(String[] args) {
        Frame frame = new Frame("Series");
    }
    Series series;

    Frame(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel firstElLabel = new JLabel("First element:");
        firstElLabel.setBounds(80,10,110,30);
        JTextField firstElTextField = new JTextField();
        firstElTextField.setBounds(80,40,110,30);

        JLabel deltaLabel = new JLabel("Delta: ");
        deltaLabel.setBounds(190,10,110,30);
        JTextField deltaTextField = new JTextField();
        deltaTextField.setBounds(190,40,110,30);

        JLabel numberLabel = new JLabel("Num of elements:");
        numberLabel.setBounds(300,10,110,30);
        JTextField countElTextField = new JTextField();
        countElTextField.setBounds(300,40,110,30);

        ButtonGroup group = new ButtonGroup();
        JRadioButton linearButton = new JRadioButton("linear");
        linearButton.setSelected(true);
        JRadioButton expButton = new JRadioButton("exponential");
        group.add(linearButton);
        group.add(expButton);
        linearButton.setBounds(80, 120, 100, 30);
        expButton.setBounds(80, 90, 100, 30);

        JLabel fileLabel = new JLabel("File to print result:");
        fileLabel.setBounds(80, 170, 130, 30);
        JTextField fileTextField = new JTextField();
        fileTextField.setBounds(80,200,110,30);

        JTextArea outTextArea = new JTextArea("elements", 10, 20);
        outTextArea.setEditable(false);
        outTextArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(outTextArea);
        scrollPane.setBounds(190, 90, 220, 60);
        //outTextArea.setBounds(190, 90, 220, 60);

        JButton fileButton = new JButton("write");
        fileButton.setBounds(190, 200, 80, 30);
        JButton calcButton = new JButton("calculate");
        calcButton.setBounds(300, 170, 110, 30);
        JButton cleanButton = new JButton("clean");
        cleanButton.setBounds(300, 200, 110, 30);

        setVisible(true);

        add(fileLabel);
        add(deltaLabel);
        add(numberLabel);
        add(firstElLabel);
        add(countElTextField);
        add(firstElTextField);
        add(deltaTextField);
        add(linearButton);
        add(expButton);
        add(calcButton);
        add(cleanButton);
        add(fileButton);
        add(fileTextField);
        add(scrollPane);

        calcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    double firstEl = Double.parseDouble(firstElTextField.getText());
                    int n = Integer.parseInt(countElTextField.getText());
                    double del = Double.parseDouble(deltaTextField.getText());
                    if (linearButton.isSelected()) {
                        series = new Linear(firstEl, n, del);
                    } else {
                        series = new Exponential(firstEl, n, del);
                    }
                    outTextArea.setText(series.toString());
                }
                catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(Frame.this,  "Please, check fields!","Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    series.printInFile(fileTextField.getText());
                }
                catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(Frame.this,  "Please, create series!", "Error!", JOptionPane.ERROR_MESSAGE);
                }
                catch (IOException ex) {
                    JOptionPane.showMessageDialog(Frame.this,  "Please, check your file!", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                series = null;
                outTextArea.setText("elements");
                deltaTextField.setText("");
                firstElTextField.setText("");
                countElTextField.setText("");
                fileTextField.setText("");
            }
        });
    }
}
