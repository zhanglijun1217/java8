package optional;

import java.util.Optional;

/**
 * Java8中的optional使用
 * 这是一个可以为null的容器对象。如果值存在则isPresent方法返回true，调用get()方法会返回该对象
 *
 * @author 夸克
 * @create 2018/7/10 14:26
 */
public class OptionalTest {

    public static void main(String[] args) {

        /**
         * 1.of方法，为非null的值创建一个Optional of方法通过工厂方法创建Optional类，需要注意传入参数不能为null
         * 否则会抛出NullPointerException
         */
        Optional<String> name = Optional.of("abce");
        System.out.println(name.get());

//        Optional<String> nullString = Optional.of(null); // 这里会抛出空指针异常

        /**
         * 2.ofNullable方法，为指定的值创建一个Optional 如果指定的值为null，则返回一个空的Optional
         */
        Optional empty = Optional.ofNullable(null);
        System.out.println(Optional.empty().equals(empty));// 返回true


        /**
         * 3.isPresent方法 Optional对象中存在返回true 不存在返回false
         */
        boolean present = empty.isPresent();// false
        boolean present1 = name.isPresent();// true

        /**
         * 4.get方法 如果Optional有值则返回，否则抛出NoSuchElementException
         */
        name.get();
//        empty.get();// 会抛出noSuchElementException

        /**
         * 5.ifPresent方法 如果Optional实例有值则为其调用Consumer,否则不做处理
         * Consumer类包含一个抽象方法。该抽象方法对传入的值进行处理，没有返回值。
         */
        name.ifPresent((s) -> { // s为optional容器实例中的对象
            System.out.println(s);
        });

        /**
         * 6.orElse方法 如果有值将其返回，否则返回其他的指定值
         */
        System.out.println(name.orElse("this is no value")); // 打印容器中的字符串对象
        System.out.println(empty.orElse("this is no value"));// 打印this is no value

        /**
         * 7.
         */

    }

}
