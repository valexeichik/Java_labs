package Observer;

import java.util.ArrayList;
import java.util.List;

public interface Subject {
    void notify(String keyName);
    void attach(Observer o);
}

class KeySubject implements Subject {
    List<Observer> observers;
    public KeySubject() {
        this.observers = new ArrayList<>();
    }
    @Override
    public void attach(Observer o) {
        observers.add(o);
    }
    @Override
    public void notify(String keyName) {
        for (Observer o : observers) o.update(keyName);
    }
}


