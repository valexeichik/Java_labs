import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PaintWindow extends JFrame {
    class PaintPanel extends JPanel {
        Graphics graphics;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
            for (PaintLine line: lineList) {
                line.draw(g);
            }
        }
    }

    BufferedImage image;
    PaintPanel panel;
    ArrayList<PaintLine> lineList;
    PaintLine paintLine;
    Color currentColor;
    JFileChooser chooser;
    PaintWindow(String title) {
        super(title);
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("./"));

        lineList = new ArrayList<>();
        currentColor = Color.RED;

        panel = new PaintPanel();
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(1200,800));
        add(new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);

        JPanel colorPanel = new JPanel();
        JButton redButton = new JButton();
        redButton.setBackground(Color.RED);
        JButton greenButton = new JButton();
        greenButton.setBackground(Color.GREEN);
        JButton blueButton = new JButton();
        blueButton.setBackground(Color.BLUE);
        colorPanel.add(redButton);
        colorPanel.add(greenButton);
        colorPanel.add(blueButton);
        add(colorPanel, BorderLayout.SOUTH);

        JPanel filePanel = new JPanel();
        JButton openButton = new JButton("Открыть");
        filePanel.add(openButton);
        JButton saveButton = new JButton("Сохранить");
        filePanel.add(saveButton);
        add(filePanel, BorderLayout.NORTH);

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                paintLine.addPoint(e.getX(),e.getY());
                repaint();
            }
        });

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                paintLine = new PaintLine(currentColor);
                paintLine.addPoint(e.getX(),e.getY());
                lineList.add(paintLine);
            }
        });

        redButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentColor = Color.RED;
            }
        });
        greenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentColor = Color.GREEN;
            }
        });
        blueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentColor = Color.BLUE;
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chooser.showSaveDialog(PaintWindow.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        BufferedImage im = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
                        panel.paint(im.getGraphics());
                        File file = chooser.getSelectedFile();
                        if (!file.getAbsolutePath().endsWith(".png")) {
                            file = new File(file.getAbsolutePath() + ".png");
                        }
                        ImageIO.write(im, "png", file);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(PaintWindow.this, ex, "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chooser.showDialog(null, "Oткрыть файл") == JFileChooser.APPROVE_OPTION) {
                    try {
                        File file = new File(chooser.getSelectedFile().toString());
                        lineList.clear();
                        image = ImageIO.read(file);

                        image.createGraphics();

                        panel.repaint();
                        panel.setSize(1200, 800);
                    }
                    catch (IOException | NullPointerException ex) {
                        JOptionPane.showMessageDialog(PaintWindow.this, ex, "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

    }
}
