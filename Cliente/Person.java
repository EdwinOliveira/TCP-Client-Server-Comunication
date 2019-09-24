import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private int year;

    public Person(String name, int year) {
        this.name = name;
        this.year = year;
    }
    public String getName() {
        return name;
    }
}
