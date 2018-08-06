package functioninterface;

import java.util.function.Supplier;

/**
 * @author 夸克
 * @create 2018/8/6 20:20
 * supplier函数式接口 只声明了返回值，不需要参数。表达的是一种生成能力，还包括了new这个动作
 * 抽象函数是get()方法
 */
public class SupplierTest {

    public static void main(String[] args) {
        Supplier<Integer> supplier = () -> 1;
        System.out.println(supplier.get());

        Supplier<Student> studentSupplier;
        for (int i=0;i<10;i++) {
            studentSupplier = Student::new;
            System.out.println(studentSupplier.get());
        }
    }
}
