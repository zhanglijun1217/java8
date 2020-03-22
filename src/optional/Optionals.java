package optional;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by zlj on 2020/3/22.
 * 基于Optional接口实现的一个 是否存在的 判断方法
 */
public final class Optionals {

    public static <T> Optional<T> ofPredicable(T value, Predicate<T> predicate) {
        return Optional.ofNullable(value).filter(predicate);
    }


    static class User {
        private String name;
        private Integer age;
        private Integer height;

        public User(String name, Integer age, Integer height) {
            this.name = name;
            this.age = age;
            this.height = height;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", height=" + height +
                    '}';
        }
    }

    public static void main(String[] args) {
        // test
        Optionals.ofPredicable(new User("zhangsan", null, null),
                user -> user.getName() !=null
        ).ifPresent(System.out::println);

        Optionals.ofPredicable(new User("lisi", 11, null),
                user -> user.getHeight() != null).orElseThrow(IllegalArgumentException::new); // 会抛异常 当然这里的predicate也可以用commons-lang之类的工具类 ObjejctUtils.allNotNull
    }
}
