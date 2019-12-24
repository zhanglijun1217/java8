package stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by zlj on 2019/12/24.
 */
public class Stream_api {

    public static void main(String[] args) {

        // collect原生的api一个熟悉 转换成list
        List<String> collect = Stream.of("1212", "4dasda", "43243")
                .collect(ArrayList::new, (list, string) -> list.add(string), (list1, list2) -> list1.addAll(list2));


        printf(collect);

        // 这里用更精简的方式去写出来
        ArrayList<Object> collect1 = Stream.of("1212", "4dasda", "43243")
                .collect(ArrayList::new, ArrayList::add, ArrayList::add);
        printf(collect1);



        // collector.joining
        System.out.println(Arrays.asList("32321213","dasda","海棠朵朵").stream().collect(Collectors.joining()).toString());

        // stream.peek方法


        // stream  generate和iterate方法
        Stream<String> generate = Stream.generate(UUID.randomUUID()::toString);
        generate.findFirst().ifPresent(System.out::println);


        Stream.iterate(1, a -> a+2).limit(10).forEach(System.out::println); // 如果不加limit方法  则会生成一个无限的stream对象。这里就是+2的等差数列


        // IntStream的summaryStatistics方法
        IntSummaryStatistics intSummaryStatistics = IntStream.of(1, 23423, 5234, 231, 21).summaryStatistics();
        System.out.println(intSummaryStatistics.getMax());
        System.out.println(intSummaryStatistics.getAverage());
        System.out.println(intSummaryStatistics.getSum());
    }

    static void printf(List list) {
        list.forEach(System.out::println);

    }
}
