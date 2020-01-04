package stream;

import java.util.*;
import java.util.stream.Collectors;

/**
 * groupBy和分区的demoTest
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

        System.out.println("-------------------------------");
        // 分组之后再分组
        students.stream().collect(Collectors.groupingBy(Student::getName, Collectors.groupingBy(Student::getScore))).forEach(GroupByTest::printKv);

        // 分区之后再分区
        students.stream().collect(Collectors.partitioningBy(e -> e.getScore() > 300, Collectors.partitioningBy(e -> e.getScore() > 340))).forEach(GroupByTest::printKv);


        System.out.println("-------------------------------");
        // collectingAndThen方法  先根据名字分组 然后找到分数最小的学生，因为minBy返回的是option，而这里student都是存在的，应该直接调用get方法得到学生对象，
        // 这样返回值就是一个 Map<Stirng, stundet>对象，而不是一个Map<String,Optional<Student>>对象。
        students.stream().collect
                (
                        Collectors.groupingBy
                                (Student::getName,
                                        Collectors.collectingAndThen(
                                                Collectors.minBy(Comparator.comparingInt(Student::getScore)),
                                                Optional::get
                                        )
                                )
                )
                .forEach(GroupByTest::printKv);

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

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", score=" + score +
                    '}';
        }
    }
}
