package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zlj on 2019/12/29.
 */
public class GroupByTest {

    public static void main(String[] args) {

        List<Student> students = Arrays.asList(new Student("zhangsan", 288),
                new Student("lisi", 342),
                new Student("wangwu", 232),
                new Student("zhangsan", 354));

        // 分组可以直接求得每个key对应的数量
        students.stream().collect(Collectors.groupingBy(Student::getName, Collectors.counting())).forEach(GroupByTest::printKv);

        // 也可以直接对value求平均值。
        students.stream().collect(Collectors.groupingBy(Student::getName, Collectors.averagingDouble(Student::getScore))).forEach(GroupByTest::printKv);

    }

    private static <K,V> void  printKv(K key, V value) {
        System.out.println("key is " + key +", value is " + value);
    }

    public static class Student {
        private String name;
        private int score;

        public Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }
}
