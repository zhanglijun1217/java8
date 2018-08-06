package functioninterface;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**
 * @author 夸克
 * @create 2018/8/6 17:23
 *
 * Operator其实就是Function，函数有时候也叫算子，算子在Java8中接口描述更像是算子的补充
 * 算子包括 UnaryOperator（单元算子）和 BinaryOperator（二元算子）
 * 二元算子中默认实现了两个快捷实现 帮助实现二元函数min(x, y) 和 max(x, y)
 */
public class OperatorTest {

    public static void main(String[] args) {
        UnaryOperator<Integer> unaryOperator = x -> x+1;
        System.out.println(unaryOperator.apply(1));

        BinaryOperator<Integer> binaryOperator = (a, b)-> a+b;
        System.out.println(binaryOperator.apply(1, 2));

        // 二元算子的两个快捷实现
        BinaryOperator<Integer> bmin = BinaryOperator.minBy(Integer::compareTo);// 这里可以用Java8的 Comparetor去代替 (o1, o2) -> o1 - o2
        System.out.println(bmin.apply(2,3));

        BinaryOperator<Integer> bmax = BinaryOperator.maxBy(Integer::compareTo);
        System.out.println(bmax.apply(2, 3));


    }
}
