package mvc;

import strategy.StreamCalcCardinalityStrategy;
import strategy.VisitorCalcCardinalityStrategy;

import javax.swing.*;

public class View extends JFrame {
    private JList<Integer> setJList;
    private JTextArea inputArea;
    private JButton addButton;
    private JButton saveButton;
    private JTextArea setArea;
    private JLabel streamLabel;
    private JLabel iteratorLabel;
    private JLabel minLabel;
    public View(String title) {
        super(title);
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel binaryLabel = new JLabel("Set in binary representation:");
        binaryLabel.setBounds(30, 10, 200, 15);
        add(binaryLabel);

        setJList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(setJList);
        scrollPane.setBounds(30, 30, 200, 420);
        add(scrollPane);

        JLabel inputLabel = new JLabel("Write elements to add:");
        inputLabel.setBounds(260, 10, 200, 15);
        add(inputLabel);

        inputArea = new JTextArea();
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        inputArea.setBounds(260, 30, 200, 60);
        add(inputArea);

        minLabel = new JLabel();
        minLabel.setBounds(260, 215, 80, 15);
        add(minLabel);

        JLabel cardinalityLabel = new JLabel("cardinality = ");
        cardinalityLabel.setBounds(260, 240, 80, 15);
        add(cardinalityLabel);

        iteratorLabel = new JLabel("0 (iterator)");
        iteratorLabel.setBounds(340,230,80,15);
        add(iteratorLabel);

        streamLabel = new JLabel("0 (stream)");
        streamLabel.setBounds(340,250,80,15);
        add(streamLabel);

        addButton = new JButton("Add elements from area");
        addButton.setBounds(260, 110, 200, 30);
        add(addButton);

        JLabel normalLabel = new JLabel("Set in normal representation:");
        normalLabel.setBounds(260, 300, 200, 15);
        add(normalLabel);

        setArea = new JTextArea("[]");
        setArea.setEditable(false);
        setArea.setLineWrap(true);
        setArea.setWrapStyleWord(true);
        setArea.setBounds(260, 320, 200, 60);
        add(setArea);

        saveButton = new JButton("Save into file");
        saveButton.setBounds(260, 150, 200, 30);
        add(saveButton);
    }

    public void update(Set set) {
        setJList.setModel(set.getBinaryListModel());
        setArea.setText(set.toString());
        minLabel.setText("min = " + set.getMin());
        set.setStrategy(new StreamCalcCardinalityStrategy());
        iteratorLabel.setText(set.cardinality() + " (stream)");
        set.setStrategy(new VisitorCalcCardinalityStrategy());
        streamLabel.setText(set.cardinality() + " (visitor)");
    }


    public JButton getAddButton() { return addButton; }

    public JLabel getMinLabel() { return minLabel; }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JTextArea getInputArea() {
        return inputArea;
    }
}