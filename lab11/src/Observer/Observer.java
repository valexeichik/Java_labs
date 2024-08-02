package Observer;

import javax.swing.*;

public interface Observer {
    void update(String keyName);
}

class LogObserver implements Observer {
    private DefaultListModel<String> model;
    public LogObserver(DefaultListModel<String> model) { this.model = model; }
    @Override
    public void update(String keyName) {
        model.add(0, keyName + " was pressed!");
    }
}

class CurrentKeyObserver implements Observer {
    private JLabel label;
    public CurrentKeyObserver(JLabel label) { this.label = label; }
    @Override
    public void update(String keyName) {
        label.setText(keyName);
    }
}
