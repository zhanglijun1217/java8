package functioninterface;

import java.util.function.Function;

/**
 * Function<T, R> 函数式接口 接受一个输入参数，返回一个结果
 * @author 夸克
 * @create 2018/7/10 21:17
 */
public class FunctionTest {

    public static void main(String[] args) {
        int number = 10;

        /**
         * 匿名内部类实现接口
         */
        Function<Integer, Integer> function = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer + 20;
            }
        };

        int i = modifyValue(number, function);

        /**
         * lambda表达式实现接口 代码简洁很多
         */
        Function<Integer, Integer> function1 = (x) -> x + 20;
        int i1 = modifyValue(number, function1);

        System.out.println(i + "\t" +i1);

        int i2 = modufyValueAndThen(number, x -> x + 20, x -> x + 30);
        System.out.println(i2);

    }

    /**
     * 定义修改方法
     * @param value
     * @param function
     * @return
     */
    static int modifyValue(int value, Function<Integer, Integer> function) {

        // 应用function接口的apply方法
        return function.apply(value);
    }

    static int modufyValueAndThen(int value, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        // 应用function接口的andThen方法 执行完f1的结果作为执行f2的参数
        return function1.andThen(function2).apply(value);
    }


}
