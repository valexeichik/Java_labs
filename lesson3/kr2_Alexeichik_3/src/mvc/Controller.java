package mvc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Controller {
    private Set model;
    private View view;
    private JFileChooser chooser;

    public Controller(Set model, View view) {
        this.model = model;
        this.view = view;
        this.view.setVisible(true);
        this.view.getMinLabel().setText("min = " + this.model.getMin());
    }

    public void initController() {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("./"));

        view.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readDataFromArea();
                view.update(model);
            }
        });

        view.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                if (chooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
                        File file = chooser.getSelectedFile();
                        String fileName = file.getAbsolutePath();
                        if (!fileName.endsWith(".txt")) {
                            file = new File(fileName + ".txt");
                        }
                        model.save(fileName);
                    }
                }
                catch (IOException ex){
                    JOptionPane.showMessageDialog(view, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                }
                catch (IndexOutOfBoundsException ex){
                    JOptionPane.showMessageDialog(view, "Set is empty!", "Error!", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    void readDataFromArea() {
        try {
            StringTokenizer tokenizer = new StringTokenizer(view.getInputArea().getText(), " ;,", false);
            while (tokenizer.hasMoreTokens()) {
                Integer element = Integer.parseInt(tokenizer.nextToken());
                model.add(element);
            }
            view.getInputArea().setText("");
        }
        catch (IllegalArgumentException | NoSuchElementException ex) {
            JOptionPane.showMessageDialog(view, "Check input!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
}