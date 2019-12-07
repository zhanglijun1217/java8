package method_reference;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by zlj on 2019/10/6.
 * 方法引用的demo
 */
@SuppressWarnings("ALL")
public class TestMethodReference {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("11","22","33");

//        list.forEach(l -> System.out.println(l));

        list.forEach(System.out::println);

        /**
         * 方法引用的四种分类
         * （1）静态方法引用 class::static method
         * （2) 实例方法引用 对象::实例方法
         * （3）类实例方法引用  类::实例方法（比较特殊）
         * （4）构造方法引用 类::new
         */


        // 演示类实例方法
        类实例方法();
        类实例方法2();

    }

    private static void 类实例方法() {
        Student s1 = new Student("张三", 21);
        Student s2 = new Student("李四", 22);
        Student s3 = new Student("王五", 23);


        List<Student> students = Arrays.asList(s1, s2, s3);
        students.sort(Student::compareByName); // 在实例方法内部compareByName处打了断点，会被调用两次（list中只有三个元素），
        // List.sort方法接收参数是一个Comparator，而这个实例方法是返回int的。
        // 这个说明 类::实例方法 这种方法引用是肯定要有一个对象去调用这个实例方法的。这个对象就是传入lambda表达式的list中的第一个Student对象 而后一个student对象会作为compareByName的参数传入


        students.forEach(System.out::println);
    }

    private static void 类实例方法2() {
        // 通过  类::实例方法 这种方法引用 来简化代码

        List<String> strings = Arrays.asList("shanghai", "guangzhou", "shenzhne", "changsha");

        Collections.sort(strings, (c1, c2) -> c1.compareToIgnoreCase(c2)); // 其实这也是 类::实例方法 这种方法引用最直接的一种解释
        strings.forEach(System.out::println);


        // 这里compareToIgnoreCase是String的static方法 可以直接换成  类::实例方法  这种方法引用
        Collections.sort(strings, String::compareToIgnoreCase);
        strings.forEach(System.out::println);

    }
}
