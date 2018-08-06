package functioninterface;

import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * @author 夸克
 * @create 2018/8/6 17:49
 *
 * 帮助开发一些返回bool值得Function 抽象方法是test 接受一个泛型参数T 返回一个Boolean值。
 * 等价于一个Function的boolean型返回值的子集
 * 其默认方法也封装了and、or和negate逻辑。
 * Predicate很重要的应用是在Stream的应用，Stream的filter方法就是接受Predicate作为入参的。
 *
 * 其他Predicate接口：
 * - BiPredicate：boolean test(T t, U u);接受两个参数的二元谓词
 * - DoublePredicate：boolean test(double value);入参为double的谓词函数
 * - IntPredicate：boolean test(int value);入参为int的谓词函数
 * - LongPredicate：boolean test(long value);入参为long的谓词函数
 */
public class PredicateTest {

    public static void main(String[] args) {
        PredicateTest predicateTest = new PredicateTest();
        System.out.println(predicateTest.print1("zhang", v -> v.length() > 5));
        System.out.println(predicateTest.print2("zhang1", v -> v.length() < 5));


    }
    private String print1(String value, Predicate<String> predicate) {
        if (predicate.test(value)) {
            return value;
        }
        else {
            return null;
        }
    }

    private String print2(String value, Predicate<String> predicate) {
        if (predicate.or(v -> v.contains("1")).test(value)) {
            return value;
        } else {
            return null;
        }
    }
}


