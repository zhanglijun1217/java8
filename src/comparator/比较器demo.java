package comparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zlj on 2020/3/15.
 */
public class 比较器demo {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("hello", "world", "normal", "fuck");

        // 普通的按照字典顺序去排序
//        Collections.sort(list);

        // Collections.sort提供了传入比较器的方式去排序 （这里实现根据字符串长度降序排序）
//        list.sort(Comparator.comparingInt(String::length).reversed());

        // 这里尝试使用lambda方式去实现根据字符串长度降序排序
//        list.sort(Comparator.comparingInt(item -> item.length()).reversed()); // 注意如果调用了reversed方法，那么item.length会报编译错误。但是不调用reversed是可以编译通过的
        // 这里是因为 失去了lambda的类型推断 具体见博客：https://blog.csdn.net/u013096088/article/details/69367260


        // 使用多重比较 并且应用String类提供的不区分大小写的字段续比较器
//        list.sort(Comparator.comparingInt(String::length).reversed().thenComparing(String.CASE_INSENSITIVE_ORDER.reversed()));


        // 使用多重比较，使用thenComparing / comparing 重载的方法。（参数是keyExtractor和keyComparator）
//        list.sort(Comparator.comparingInt(String::length).thenComparing(Comparator.comparing(String::toUpperCase, Comparator.reverseOrder())));
        // 上边那行代码的简化方式 因为thenComparing也提供了keyExtractor和keyComparator作为参数的方法
        list.sort(Comparator.comparingInt(String::length).thenComparing(String::toLowerCase, Comparator.reverseOrder()));

        System.out.println(list);


        /**
         * 再次演示一个事情：thenComparing只有第一重比较器的结果是0时，才会应用
         */
        List<String> strings = Arrays.asList("def", "abc", "hel", "world");
        strings.sort(Comparator.comparingInt(String::length).reversed() //（1）
                .thenComparing(String::compareToIgnoreCase) // （2）
                .thenComparing(Comparator.reverseOrder()) // （3）这个比较器不会被应用 因为比较器（2）已经把结果比较出来了，并且没有相等的结果，这里不会再应用（3）比较器
        );

        System.out.println(strings); // 输出[world, abc, def, hel]

    }
}
