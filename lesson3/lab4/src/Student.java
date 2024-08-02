import java.util.Collections;
import java.util.Comparator;

public class Student implements Comparable<Student> {
    private Integer id;
    private String name;
    private String surname;

    Student() {
        id = 0;
        name = null;
        surname = null;
    }
    Student(String name, String surname, int id) {
        this.id = id;
        this.surname = surname;
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }
    public Integer getId() {
        return id;
    }

    public int compareTo(Student st2) {
        Comparator<Student> personComparator = Comparator.comparing(Student::getId).thenComparing(Student::getSurname).thenComparing(Student::getName);
        return personComparator.compare(this, st2);
    }

    public String toString() {
        return name + " " + surname + " - " + id;
    }

}
