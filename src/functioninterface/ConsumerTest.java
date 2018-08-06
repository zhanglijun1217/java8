package functioninterface;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author 夸克
 * @create 2018/8/6 19:37
 *
 * consumer函数式接口 表示接受一个泛型参数，不需要返回值 抽象方法是accept方法
 * 这里去做了一个Predicate和Consumer两个函数式接口的小demo
 *
 *
 */
public class ConsumerTest {

    public static void main(String[] args) {

        Student student1 = new Student("张三", 8.0);

        Student student2 = new Student("李四", 8.5);

        ConsumerTest consumerTest = new ConsumerTest();

        // 传入一个predicate函数式接口 和一个consumer函数式接口
        consumerTest.calAndPrintFee(student1, student -> student.grade > 8.0, student -> student.feeDiscount = 30.0);
        student1.printFee();

        consumerTest.calAndPrintFee(student2, student -> student.grade > 8.0, student -> student.feeDiscount = 30.0);
        student2.printFee();

    }

    private void calAndPrintFee(Student s, Predicate<Student> predicate, Consumer<Student> consumer) {
        if (s == null) {
            return;
        }
        if (predicate.test(s)) {
            consumer.accept(s);
        }
    }
}
