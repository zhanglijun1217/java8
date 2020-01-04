package stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.*;


/**
 * Created by zlj on 2020/1/4.
 * Collector接口  和 工具类Collectors 的一个test
 */
public class CollectorInterfaceTest {

    public static void main(String[] args) {

        List<GroupByTest.Student> students = Arrays.asList(new GroupByTest.Student("zhangsan", 288),
                new GroupByTest.Student("lisi", 342),
                new GroupByTest.Student("wangwu", 232),
                new GroupByTest.Student("zhangsan", 354));


        // 找出分数的最小值
        System.out.print("分数最小值");
        Optional<GroupByTest.Student> minBy = students.stream().collect(minBy(Comparator.comparingInt(GroupByTest.Student::getScore)));// 这里minby的结果是一个Optional，因为最小值可能不存在
        minBy.ifPresent(System.out::println);


        // 求平均值 这种是要传入Collectors的方法中
        System.out.println("平均值：" + students.stream().collect(averagingInt(GroupByTest.Student::getScore)));

        // 总和值
        System.out.println("总和值：" + students.stream().collect(summingInt(GroupByTest.Student::getScore)));

        // 摘要信息
        System.out.println("摘要信息：" + students.stream().collect(summarizingInt(GroupByTest.Student::getScore)));


        // 将名字连起来 Collectors.joining方法
        System.out.println("名字拼接："+ students.stream().map(GroupByTest.Student::getName).collect(joining()));
        // joining方法的重载：加入分隔符和前缀、后缀这样的方法来实现多种功能 （其实内部也是通过StringBuilder和StringJoiner来实现的）
        System.out.println("名字拼接（分隔符）：" + students.stream().map(GroupByTest.Student::getName).collect(joining(",")));
        // 注意前后缀 和 source中的元素拼接时是没有加入分隔符的
        System.out.println("名字拼接（分隔符 + 前后缀）：" + students.stream().map(GroupByTest.Student::getName).collect(joining(",", "begin", "end")));

    }
}
