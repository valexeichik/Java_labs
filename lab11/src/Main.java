import Observer.ObserverFrame;
import Strategy.StrategyFrame;

public class Main {
    public static void main(String[] args) {
        //ObserverFrame frame = new ObserverFrame("Observer");
        StrategyFrame frame = new StrategyFrame("Strategy");
        frame.setVisible(true);
    }
}