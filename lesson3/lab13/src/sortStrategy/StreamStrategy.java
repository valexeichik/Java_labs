package sortStrategy;

import student.Student;

import java.util.Comparator;
import java.util.List;

public class StreamStrategy implements SortStrategy {
    @Override
    public List<Student> execute(List<Student> students) {
        return students.stream()
                .filter(Student::isExcellent)
                .sorted(new Comparator<>() {
                    @Override
                    public int compare(Student st1, Student st2) {
                        if (st1.getSurname().equalsIgnoreCase(st2.getSurname()))
                            return st1.getName().toLowerCase().compareTo(st2.getName().toLowerCase());
                        else return st1.getSurname().toLowerCase().compareTo(st2.getSurname().toLowerCase());
                    }
                }).toList();
    }
}
