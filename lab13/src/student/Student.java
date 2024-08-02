package student;

import java.util.HashMap;
import java.util.Map;

public class Student {
    private Integer id;
    private String name;
    private String surname;
    private Map<String, Integer> marks;

    public Student() {};

    public Student(Integer id, String surname, String name, HashMap<String, Integer> marks) {
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
        for (Map.Entry<String, Integer> mark : marks.entrySet()) {
            result.append(mark.getKey()).append(" – ").append(mark.getValue()).append('\n');
        }

        return result.toString();
    }
    public boolean isExcellent() {
        for (HashMap.Entry<String, Integer> mark : marks.entrySet()) {
            if (mark.getValue() < 9) {
                return false;
            }
        }
        return true;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMarks(Map<String, Integer> marks) {
        this.marks = marks;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public Map<String, Integer> getMarks() { return marks; }
    public String getSurname() { return surname; }
}
