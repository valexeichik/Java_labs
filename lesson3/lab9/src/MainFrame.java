import javax.swing.*;

public class MainFrame extends JFrame {
    MainFrame(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(600, 600);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Task 1", new Panel1());
        tabbedPane.addTab("Task 2", new Panel2());
        tabbedPane.addTab("Task 3", new Panel3());
        add(tabbedPane);
    }
}
