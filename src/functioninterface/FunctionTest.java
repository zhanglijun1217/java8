package functioninterface;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Function<T, R> 函数式接口 接受一个输入参数，返回一个结果
 * 其中抽象表达函数为： R apply(T t);
 * 其默认实现了3个default方法 分别是compose、andThen和identity
 *    -- compose对应嵌套关系 V = Function(ParamFunction(T))
 *    -- andThen转换了嵌套的顺序 V = ParamFunction(Function(T))
 *    -- identity对应了一个传递自身的函数调用 Function(T) = T
 *
 *  BiFunction提供了二元函数的一个接口声明  可以组合拓展出更多的函数
 *
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
        Function<Integer, Integer> function1 = x -> x + 20;
        int i1 = modifyValue(number, function1);

        System.out.println(i + "\t" +i1);

        int i2 = modufyValueAndThen(number, x -> x + 20, x -> x + 30);
        System.out.println(i2);



        // 区分compose andThen方法
        Function<Integer, Integer> incr1 = x -> x + 1;
        Function<Integer, Integer> multiply = x -> x * 2;
        int x = 2;
        System.out.println("f(x)=x+1,when x=" + x + ", f(x)=" + incr1.apply(x));
        System.out.println("f(x)=x+1,g(x)=2x, when x=" + x + ", f(g(x))=" + incr1.compose(multiply).apply(x));
        System.out.println("f(x)=x+1,g(x)=2x, when x=" + x + ", g(f(x))=" + incr1.andThen(multiply).apply(x));
        // f compose (g) 相当于 g andThen f
        System.out.println("compose vs andThen:f(g(x))=" + incr1.compose(multiply).apply(x) + "," + multiply.andThen(incr1).apply(x));

        Function<Integer, Integer> identity = Function.identity();
        System.out.println(identity.apply(9));
        System.out.println(identity.andThen(incr1).apply(3));
        System.out.println(identity.compose(multiply).apply(4));

        BiFunction<Integer, Integer, Integer> biFunction = (m, n) -> m * n;
        System.out.println(biFunction.apply(3, 4));


        System.out.println(calByBiFunction(5, 4, (j, k) -> j * k));
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

    /**
     * 应用BiFunction去实现对两个参数的计算
     * @param value1
     * @param value2
     * @param <T>
     * @param <T>
     * @param <T>
     * @return
     */
    static <T> T calByBiFunction(T value1, T value2, BiFunction<T, T, T> f) {
        return f.apply(value1, value2);
    }


}
