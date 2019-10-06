package optional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        Optional<String> empty = Optional.ofNullable(null);
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
         * 7.orElseGet方法 与OrElse方法相似，不同在于默认值的处理。OrElse方法将传入的字符串作为默认值
         * orElseGet方法则可以接受Supplier接口的实现来生成默认值 可以接受一个lambda的来作为默认值
         */

        System.out.println(empty.orElseGet(() -> "default value"));// 传入lambda表达式初始化默认值

        /**
         * 8.orElseThrow 如果有值则将其返回，否则抛出supplier接口创建的异常
         */
        try {
            empty.orElseThrow(() -> new OperationException());
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }

        /**
         * 9.map方法 如果有值，则对其执行mapping函数得到返回值。如果返回值不为null，则创建包含mapping返回值的
         * Optional作为map的返回值，否则返回空的Optional
         */
        Optional<String> chars = empty.map((s) -> s.toUpperCase());
        System.out.println(chars.orElse("this is no value"));

        /**
         * 10.flatMap方法，与map方法类似。不同点在于 flatMap方法中的mapping方法返回值必须是Optional类型的
         * 而map方法中mapping方法返回值可以是任意类型
         */
        Optional<String> flatMap = name.flatMap((s) -> Optional.of(s.toUpperCase()));
        System.out.println(flatMap.orElse("this is no value"));

        /**
         * 11.filter方法 如果有值并且满足断言条件返回包含该值得Optional，否则返回空Optional
         */
        Optional<String> filter1 = Optional.of("string");
        Optional<String> optionalS = filter1.filter(e -> e.length() > 3);
        System.out.println(optionalS.orElseGet(() -> "this is no value"));

        Optional<String> optionalS1 = filter1.filter(e -> e.length() > 6);
        System.out.println(optionalS1.orElseGet(() -> "this is no value"));




        // Optional接口对对象中包含list操作的一个实践
        Person person = new Person();
        person.setName("aaa");
        List<Hobby> hobbies = Arrays.asList(new Hobby("h1"), new Hobby("h2"));

//        person.setHobbies(hobbies);

        // Optional实践  因为Optional.map返回的也是一个Optional，所以对象中的集合 可以通过map方法进行Optional的封装
        // 达到防止空指针的效果
        System.out.println(
                Optional.of(person)
                .map(Person::getHobbies).orElseGet(ArrayList::new)
        );

    }


    static class Person {
        String name;
        List<Hobby> hobbies;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Hobby> getHobbies() {
            return hobbies;
        }

        public void setHobbies(List<Hobby> hobbies) {
            this.hobbies = hobbies;
        }
    }

    static  class  Hobby {
        String name;

        public Hobby(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Hobby{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

}
