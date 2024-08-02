import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class Frame extends JFrame {
    JList<Lamp> jLamps;
    DefaultListModel<Lamp> modelLamps;
    DefaultComboBoxModel<String> modelBox;
    JComboBox<String> box;
    JTextArea area;
    List<Lamp> lamps;
    JRadioButton ledButton;
    JRadioButton incButton;
    Frame(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        lamps = new ArrayList<>();

        JTextField field = new JTextField();
        modelBox = new DefaultComboBoxModel<>();
        box = new JComboBox<>(modelBox);
        box.setBounds(15, 405, 105, 30);
        add(box);

        JButton readButton = new JButton("Read from file");
        readButton.setBounds(60, 10, 130, 30);
        add(readButton);
        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                field.setText("");
                area.setText("");
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("./"));
                int result = chooser.showDialog(null, "Open file");
                if (result == JFileChooser.APPROVE_OPTION) {
                    readDataFromFile(chooser.getSelectedFile().getPath(), lamps);
                    modelLamps.removeAllElements();
                    for (Lamp lamp : lamps) modelLamps.addElement(lamp);
                    jLamps = new JList<>(modelLamps);
                }
                modelBox.removeAllElements();
                for (String s : Edison.getManufacturerList(lamps)) modelBox.addElement(s);
                box = new JComboBox<>(modelBox);
            }
        });

        ButtonGroup group = new ButtonGroup();
        ledButton = new JRadioButton("LED");
        ledButton.setBounds(270, 10, 70, 30);
        ledButton.setSelected(true);
        incButton = new JRadioButton("incandescent");
        incButton.setBounds(340, 10, 130, 30);
        group.add(ledButton);
        group.add(incButton);
        add(ledButton);
        add(incButton);

        modelLamps = new DefaultListModel<>();
        jLamps = new JList<>(modelLamps);
        JScrollPane scrollList = new JScrollPane(jLamps);
        scrollList.setBounds(15, 50, 220, 305);
        add(scrollList);

        area = new JTextArea();
        area.setEditable(false);
        area.setLineWrap(true);
        JScrollPane scrollArea = new JScrollPane();
        scrollArea.getViewport().add(area);
        scrollArea.setBounds(260, 50, 200, 200);
        add(scrollArea);

        JButton button1 = new JButton("<html>Show the list of lamps<br>in ascending order of cost");
        button1.setBounds(250, 260, 220, 40);
        add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                try {
                    if (lamps.isEmpty()) throw new IllegalArgumentException();
                    writeIntoArea(Edison.getLampsInAscendingOrderOfCost(lamps));
                }
                catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(Frame.this, "List is empty!", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton button2 = new JButton("<html>Show the list of lamps<br>by cost/power in descending order");
        button2.setBounds(250, 305, 220, 50);
        add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (lamps.isEmpty()) throw new IllegalArgumentException();
                    writeIntoArea(Edison.getLampsInDescendingOrderOfCostToPowerRatio(lamps));
                }
                catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(Frame.this, "List is empty!", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton button3 = new JButton("<html>Show the list of manufacturers<br>starting with \"C\"");
        button3.setBounds(250, 360, 220, 40);
        add(button3);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (lamps.isEmpty()) throw new IllegalArgumentException();
                    if (Edison.getManufacturersStartingWithCList(lamps).isEmpty())
                        area.setText("There is no manufacturers starting with C :(");
                    else writeIntoArea(Edison.getManufacturersStartingWithCList(lamps));
                }
                catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(Frame.this, "List is empty!", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        field.setBounds(130, 405, 105, 30);
        field.setBackground(Color.WHITE);
        field.setEditable(false);
        add(field);
        JButton button4 = new JButton("<html>Count average cost<br>of manufacturer");
        button4.setBounds(40, 360, 160, 40);
        add(button4);
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (lamps.isEmpty()) throw new IllegalArgumentException();
                    field.setText(String.format("%.2f", Edison.getAverageCost(lamps, box.getSelectedItem().toString())));
                }
                catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(Frame.this, "List is empty!", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    void writeIntoArea(ArrayList<String> manufactures) {
        area.setText("");
        for (String s: manufactures) {
            area.append(s + '\n');
        }
    }

    void writeIntoArea(List<Lamp> lamps) {
        area.setText("");
        for (Lamp lamp: lamps) {
            area.append(lamp.toString() + '\n');
        }
    }

    void readDataFromFile(String path, List<Lamp> lamps) {
        try {
            Scanner scanner = new Scanner(new File(path));
            scanner.useDelimiter(System.getProperty("line.separator"));
            while (scanner.hasNext()) {
                StringTokenizer currentLine = new StringTokenizer(scanner.next(), " ", false);

                Double power = Double.parseDouble(currentLine.nextToken());
                String manufacturer = currentLine.nextToken();
                Integer addField = Integer.parseInt(currentLine.nextToken());
                if (addField < 0 || power < 0) throw new IllegalArgumentException();

                if (ledButton.isSelected()) lamps.add(new LedLamp(power, manufacturer, addField));
                else lamps.add(new IncandescentLamp(power, manufacturer, addField));
            }
        }
        catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(Frame.this, "Please, check your file!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        catch (IllegalArgumentException | NoSuchElementException ex) {
            JOptionPane.showMessageDialog(Frame.this, "Please, check data in your file!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
