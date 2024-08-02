import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
    Integer id;
    String name;
    String surname;
    Map<String, Integer> marks;

    Student(Integer id, String surname, String name, HashMap<String, Integer> marks) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.marks = marks;
    }

    public String toString() {
        return id + " – " + surname + " " + name;
    }
    public String getMarksAsString() {
        StringBuilder result = new StringBuilder();
        int i = 0;
        for (Map.Entry<String, Integer> mark : marks.entrySet()) {
            result.append(mark.getKey()).append(" – ").append(mark.getValue()).append('\n');
        }

        return result.toString();
    }
    boolean isExcellent() {
        for (HashMap.Entry<String, Integer> mark : marks.entrySet()) {
            if (mark.getValue() < 9) {
                return false;
            }
        }
        return true;
    }
}
