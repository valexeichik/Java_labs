public class Student {
    Integer id;
    String name;
    String surname;

    Student(Integer id, String surname, String name) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public String toString() {
        return id + " – " + surname + " " + name;
    }
}
