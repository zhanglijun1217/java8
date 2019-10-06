package functioninterface;

import java.util.Comparator;
import java.util.function.BinaryOperator;

/**
 * BinaryOperation是特殊的BiFunction 即两个参数和返回值相等的BiFunction
 * @see java.util.function.BinaryOperator
 * Created by zlj on 2019/10/4.
 */
public class BinaryOperationTest {

    public static void main(String[] args) {

        System.out.println(compute(1,2, (a, b) -> a+b));
        System.out.println(compute(1,2, (a, b) -> a-b));
        System.out.println(compute(1,2, (a, b) -> a*b));


        System.out.println(minBy("hello", "aello1", String::compareTo));
        System.out.println(minBy("hello32434234234", "world22322", Comparator.comparingInt(String::length)));
    }

    /**
     * 利用binaryOperator去实现四则运算
     * @param a
     * @param b
     * @param binaryOperator
     * @return
     */
    public static int compute(int a, int b, BinaryOperator<Integer> binaryOperator) {
        return binaryOperator.apply(a, b);
    }

    /**
     * BinaryOperator接口的静态方法 minBy
     * @param a
     * @param b
     * @param comparator
     * @return
     */
    static String minBy(String a, String b, Comparator<String> comparator) {
        return BinaryOperator.minBy(comparator).apply(a,b);
    }
}
