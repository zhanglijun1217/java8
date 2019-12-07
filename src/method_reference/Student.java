package method_reference;

/**
 * Created by zlj on 2019/12/7.
 */
public class Student {

    String name;
    Integer score;

    public Student(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public int compareByName(final Student s2) {
        return this.getName().compareToIgnoreCase(s2.getName());
    }

    public int compareByScore(final Student s2) {
        return this.getScore() - s2.getScore();
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
