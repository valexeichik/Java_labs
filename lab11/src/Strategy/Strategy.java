package Strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface Strategy {
    List<Student> execute(List<Student> students);
}

class StreamStrategy implements Strategy {
    StreamStrategy() {};
    @Override
    public List<Student> execute(List<Student> students) {
        return students.stream()
                .filter(Student::isExcellent)
                .sorted(new Comparator<>() {
            @Override
            public int compare(Student st1, Student st2) {
                if (st1.surname.equalsIgnoreCase(st2.surname))
                    return st1.name.toLowerCase().compareTo(st2.name.toLowerCase());
                else return st1.surname.toLowerCase().compareTo(st2.surname.toLowerCase());
            }
        }).toList();
    }
}

class BaseStrategy implements Strategy {
    BaseStrategy() {}
    @Override
    public List<Student> execute(List<Student> students) {
        List<Student> exStudents = new ArrayList<>();
        for (Student st: students) {
            if (st.isExcellent()) exStudents.add(st);
        }
        exStudents.sort(new Comparator<>() {
            @Override
            public int compare(Student st1, Student st2) {
                if (st1.surname.equalsIgnoreCase(st2.surname))
                    return st1.name.toLowerCase().compareTo(st2.name.toLowerCase());
                else return st1.surname.toLowerCase().compareTo(st2.surname.toLowerCase());
            }
        });
        return exStudents;
    }
}


